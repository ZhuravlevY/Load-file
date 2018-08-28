package com.load.simple.workflow;

import com.load.simple.metadata.source.Airport;
import com.load.simple.metadata.source.Carrier;
import com.load.simple.metadata.target.TfctFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.load.simple.dimensions.Dimensions.*;
import static com.load.simple.util.Helper.initHeader;
import static com.load.simple.util.Helper.rmEncQuotes;
import static com.load.simple.workflow.WfTfctAirlineDataset.Maplets.RAW_TO_MAP;
import static java.lang.Integer.parseInt;

public class WfTfctAirlineDataset implements IWorkflow {

    @Autowired
    private Environment env;
    
    private static final Map<String, Integer> TFCT_FLIGHT_HEADER = new ConcurrentHashMap<>();

    private NamedParameterJdbcTemplate jdbcTemplate;

    //private String insertQuery;

    @Autowired
    public WfTfctAirlineDataset(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        /*try {
            this.insertQuery = new Scanner(new File(env.getProperty("fn.insert"))).useDelimiter(";").next();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }*/


    }
    
    public void start () {

        initHeader(env.getProperty("fn.flight"), TFCT_FLIGHT_HEADER);

        loadAirports(env.getProperty("fn.airports"));
        loadCarriers(env.getProperty("fn.carriers"));


        System.out.println("Data loading started..."); long start = System.currentTimeMillis();
        final long[] counter = new long[]{0};
        final int batchSize = Integer.parseInt(env.getProperty("bs.size"));
        final List<Map<String, ?>> batch = new ArrayList<>();
        final String[] insertQuery = new String[]{""};

        try (Stream<String> flights = Files.lines(Paths.get(env.getProperty("fn.flight")))) {
            flights
                .skip(1L)
                .map(RAW_TO_MAP)
                .forEach((List<Object> flight) -> {

                    counter[0]++;
                    Map<String, Object> flightRaw = (Map<String, Object>)flight.get(1);
                    insertQuery[0] = (String) flight.get(0);

                    flightRaw.put("id", counter[0]);

                    batch.add(flightRaw);
                    if (counter[0] % batchSize == 0) {
                        jdbcTemplate.batchUpdate(insertQuery[0],  batch.toArray(new HashMap[0]));
                        batch.clear();
                    }
                });

            jdbcTemplate.batchUpdate(insertQuery[0],  batch.toArray(new HashMap[0]));
            batch.clear();

        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Data loading finished in " + ((System.currentTimeMillis() - start) / 1000 / 60) + " minutes");
    }

    static class Maplets {

        static final Function<String, List<Object>> RAW_TO_MAP = (String raw) -> {
            String data[] = raw.split(",");
            Map<String, Object> result = new HashMap<>();
            TfctFlight flight = new TfctFlight();
            String columns = "insert into TfctFlight (id,";
            String values = "VALUES (:id,";
            List <String> list = new ArrayList<>();
            TFCT_FLIGHT_HEADER.forEach((column, index) -> {
                Object val = data[index];
                list.add(column);

                try {
                    Field field = flight.getClass().getDeclaredField(column);
                    Type type = field.getType();

                    if (type.equals(int.class)||type.equals(Integer.class)) {
                        try {
                            result.put(column, parseInt((String) val));
                        } catch (NumberFormatException ex) {
                            result.put(column, 0);
                        }
                    } else {
                        result.put(column, val != null ? rmEncQuotes(val.toString()) : "NA");
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }

            });
            for (String l : list){
                columns = columns +  l + ", ";
                values = values + ":" + l + ", ";
            }

            Airport origin =  AIRPORTS.get(data[TFCT_FLIGHT_HEADER.get("origin")]);
            Airport dest = AIRPORTS.get(data[TFCT_FLIGHT_HEADER.get("dest")]);
            Carrier carrier = CARRIERS.get(data[TFCT_FLIGHT_HEADER.get("uniquecarrier")]);

            if (origin != null) {
                result.put("originairport", origin.getAirport());
                result.put("origincity", origin.getCity());
                result.put("originstate", origin.getState());
                result.put("origincountry", origin.getCountry());
                result.put("originlat", origin.getLat());
                result.put("originfild", origin.getLongFild());
                columns = columns + "originairport, origincity, originstate, origincountry, originlat, originfild" ;
                values = values + ":originairport, :origincity, :originstate, :origincountry, :originlat, :originfild";
            }

            if (dest != null) {
                if (origin != null) {
                    columns = columns + "," ;
                    values = values + ",";
                }
                result.put("destairport", dest.getAirport());
                result.put("destcity", dest.getCity());
                result.put("deststate", dest.getState());
                result.put("destcountry", dest.getCountry());
                result.put("destlat", dest.getLat());
                result.put("destfild", dest.getLongFild());
                columns = columns + " destairport, destcity, deststate, destcountry, destlat, destfild," ;
                values = values + " :destairport, :destcity, :deststate, :destcountry, :destlat, :destfild,";
            }

            if (carrier != null) {
                if (origin == null || dest == null) {
                    columns = columns + "," ;
                    values = values + ",";
                }
                result.put("description", carrier.getDescription());
                columns = columns + " description" ;
                values = values + " :description";
            }
            if (origin == null && dest == null && carrier == null) {
                columns = columns.replaceFirst(".$","");
                values = values.replaceFirst(".$","");
            }
            List<Object> objects = new ArrayList<>();
            objects.add(columns+") "+values+")");
            objects.add(result);
            return objects;
        };
    }
}
