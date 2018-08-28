package com.load.simple.dimensions;

import com.load.simple.metadata.source.Airport;
import com.load.simple.metadata.source.Carrier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.load.simple.util.Helper.initHeader;
import static com.load.simple.util.Helper.rmEncQuotes;


public class Dimensions {

    private static final Map<String, Integer> AIRPORTS_HEADER = new ConcurrentHashMap<>();
    public static final Map<String, Integer> CARRIERS_HEADER = new ConcurrentHashMap<>();

    public static final Map<String, Airport>  AIRPORTS = new ConcurrentHashMap<>();
    public static final Map<String, Carrier> CARRIERS = new ConcurrentHashMap<>();




    public static void loadAirports(String fileName) {

        initHeader(fileName, AIRPORTS_HEADER);


        
        try (Stream<String> airports = Files.newBufferedReader(Paths.get(fileName)).lines()){
            AIRPORTS.putAll(airports
                                .skip(1L)
                                .map(Mapplets.AIRPORT_MAPPLET)
                                .collect(Collectors.toMap(Airport::getIata, airport -> airport))
            );
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void loadCarriers(String fileName) {
        initHeader(fileName, CARRIERS_HEADER);


        try (Stream<String> carriers = Files.newBufferedReader(Paths.get(fileName)).lines()){
            CARRIERS.putAll(carriers
                    .skip(1L)
                    .map(Mapplets.CARRIER_MAPPLET)
                    .collect(Collectors.toMap(Carrier::getCode, carrier -> carrier))
            );
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static class Mapplets {

        static final Function<String, Airport> AIRPORT_MAPPLET = raw -> {
            String data[] = raw.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            Airport airport = new Airport();
            airport.setAirport(rmEncQuotes(data[AIRPORTS_HEADER.get("airport")]));
            airport.setCity(rmEncQuotes(data[AIRPORTS_HEADER.get("city")]));
            airport.setCountry(rmEncQuotes(data[AIRPORTS_HEADER.get("country")]));
            airport.setIata(rmEncQuotes(data[AIRPORTS_HEADER.get("iata")]));
            airport.setLat(Double.parseDouble(data[AIRPORTS_HEADER.get("lat")]));
            airport.setLongFild(Double.parseDouble(data[AIRPORTS_HEADER.get("long")]));
            airport.setState(rmEncQuotes(data[AIRPORTS_HEADER.get("state")]));

            return airport;

        };

        static final Function<String, Carrier> CARRIER_MAPPLET = raw -> {
            String data[] = raw.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            Carrier carrier = new Carrier();
            carrier.setCode(rmEncQuotes(data[CARRIERS_HEADER.get("code")]));
            carrier.setDescription(rmEncQuotes(data[CARRIERS_HEADER.get("description")]));

            return carrier;
        };
    }

}
