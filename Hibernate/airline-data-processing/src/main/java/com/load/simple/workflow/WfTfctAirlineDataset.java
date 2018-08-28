package com.load.simple.workflow;

import com.load.simple.metadata.source.Airport;
import com.load.simple.metadata.source.Carrier;
import com.load.simple.metadata.target.TfctFlight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


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
import static com.load.simple.workflow.WfTfctAirlineDataset.Maplets.TFCTFLIGHT;
import static java.lang.Integer.parseInt;


public class WfTfctAirlineDataset implements IWorkflow {
    @Autowired
    private Environment env;

    @Autowired
    private SessionFactory sessionFactory;
    
    private static final Map<String, Integer> TFCT_FLIGHT_HEADER = new ConcurrentHashMap<>();
    
    public void start () {

        initHeader(env.getProperty("fn.flight"), TFCT_FLIGHT_HEADER);

        loadAirports(env.getProperty("fn.airports"));
        loadCarriers(env.getProperty("fn.carriers"));

        System.out.println("Data loading started..."); long start = System.currentTimeMillis();
        Session session = sessionFactory.openSession();
        final int[] counter = new int[]{0};
        session.beginTransaction();
        try (Stream<String> flights = Files.lines(Paths.get(env.getProperty("fn.flight")))) {
            flights
                .skip(1L)
                .map(TFCTFLIGHT)
                .forEach((flight)->{
                    counter[0]++;
                    session.save(flight);
                    if (counter[0] % 10_000 == 0) {
                        session.getTransaction().commit();
                        session.clear();
                        session.beginTransaction();
                    }
                });
        } catch (IOException e){
            e.printStackTrace();
        }
        session.getTransaction().commit();
        session.clear();
        System.out.println("Data loading finished in " + ((System.currentTimeMillis() - start) / 1000 / 60) + " minutes");
    }

    static class Maplets {

        static final Function<String, TfctFlight>  TFCTFLIGHT = (String raw) -> {
            String data[] = raw.split(",");

            TfctFlight result = new TfctFlight();

            TFCT_FLIGHT_HEADER.forEach((column, index) -> {
                Object fields = data[index];
                try {
                    Field field = result.getClass().getDeclaredField(column);
                    Type type = field.getType();
                    field.setAccessible(true);

                    if (type.equals(int.class)||type.equals(Integer.class)) {
                        try {
                            field.set(result, parseInt((String) fields));
                        } catch (NumberFormatException ex) {
                            field.set(result, 0);
                        }
                    } else {
                        field.set(result, fields != null ? rmEncQuotes(fields.toString()) : "NA");
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }

            });

            Airport origin =  AIRPORTS.get(data[TFCT_FLIGHT_HEADER.get("origin")]);
            Airport dest = AIRPORTS.get(data[TFCT_FLIGHT_HEADER.get("dest")]);
            Carrier carrier = CARRIERS.get(data[TFCT_FLIGHT_HEADER.get("uniquecarrier")]);

            if (origin != null) {
                result.setOriginairport(origin.getAirport());
                result.setOrigincity(origin.getCity());
                result.setOriginstate(origin.getState());
                result.setOrigincountry(origin.getCountry());
                result.setOriginlat(origin.getLat());
                result.setOriginfild(origin.getLongFild());
            }

            if (dest != null) {
                result.setDestairport(dest.getAirport());
                result.setDestcity(dest.getCity());
                result.setDeststate( dest.getState());
                result.setDestcountry(dest.getCountry());
                result.setDestlat(dest.getLat());
                result.setDestfild(dest.getLongFild());
            }

            if (carrier != null) {
                result.setDescription(carrier.getDescription());
            }


            return  result;
        };
    }
}
