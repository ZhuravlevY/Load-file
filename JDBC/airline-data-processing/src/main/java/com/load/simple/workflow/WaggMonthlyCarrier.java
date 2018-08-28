package com.load.simple.workflow;

import com.load.simple.metadata.target.AggMonthlyCarrier;
import com.load.simple.metadata.target.TfctFlight;
import com.load.simple.util.KeyMonthlyCarrier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.load.simple.workflow.WaggMonthlyCarrier.Maplets.COMBINE;
import static com.load.simple.workflow.WaggMonthlyCarrier.Maplets.MONTHLYCARRIER;

public class WaggMonthlyCarrier implements IWorkflow {

    @Autowired
    private SessionFactory sessionFactory;



    public void start () {
        System.out.println("Data loading started..." + System.currentTimeMillis()); long start = System.currentTimeMillis();
        Session session = sessionFactory.openSession();

        Stream<TfctFlight> stream = session.createQuery("FROM TfctFlight", TfctFlight.class).stream();

        Optional<Map<KeyMonthlyCarrier, AggMonthlyCarrier>> agg;
        agg = stream.limit(100000)
                .map(MONTHLYCARRIER)
                .reduce(COMBINE);

        if(agg.isPresent()){
            System.out.println(agg.get().size());
            session.beginTransaction();
            agg.get().forEach((ind, ob) -> session.save(ob));
            session.getTransaction().commit();
        }
        session.clear();
        System.out.println("Data loading finished in " + ((System.currentTimeMillis() - start) / 1000 / 60) + " minutes..." + System.currentTimeMillis());
    }
    static class Maplets {
        static final Function<TfctFlight, Map<KeyMonthlyCarrier, AggMonthlyCarrier>> MONTHLYCARRIER = (TfctFlight ob) -> {

            KeyMonthlyCarrier key = new KeyMonthlyCarrier();
            key.setYear(ob.getYear());
            key.setMonth(ob.getMonth());
            key.setUniqueCarrier(ob.getUniquecarrier());

            AggMonthlyCarrier value = new AggMonthlyCarrier();
            value.setYear(ob.getYear());
            value.setMonth(ob.getMonth());
            value.setAllArrtime(ob.getArrtime());
            value.setUniqueCarrier(ob.getUniquecarrier());
            value.setAllDistance(ob.getDistance());
            value.setMinDistance(ob.getDistance());
            value.setMaxDistance(ob.getDistance());
            value.setDescription(ob.getDescription());

            Map<KeyMonthlyCarrier, AggMonthlyCarrier> mapMonthlyCarrier = new ConcurrentHashMap<>();
            mapMonthlyCarrier.put(key, value);
            return mapMonthlyCarrier;
        };

        static final BinaryOperator<Map<KeyMonthlyCarrier, AggMonthlyCarrier>> COMBINE =
                (Map<KeyMonthlyCarrier, AggMonthlyCarrier> ob1, Map<KeyMonthlyCarrier, AggMonthlyCarrier> ob2) -> {

           Map<KeyMonthlyCarrier, AggMonthlyCarrier> mapMonthlyCarrier = ob1;

           Map.Entry<KeyMonthlyCarrier, AggMonthlyCarrier> entry = ob2.entrySet().iterator().next();
           KeyMonthlyCarrier key= entry.getKey();
           AggMonthlyCarrier value=entry.getValue();

            if(mapMonthlyCarrier.containsKey(key)){
                AggMonthlyCarrier oldValue = ob1.get(key);
                AggMonthlyCarrier newValue = new AggMonthlyCarrier();

                newValue.setYear(value.getYear());
                newValue.setMonth(value.getMonth());
                newValue.setUniqueCarrier(value.getUniqueCarrier());
                newValue.setDescription(value.getDescription());
                newValue.setAllArrtime(oldValue.getAllArrtime()+ value.getAllArrtime());
                newValue.setAllDistance(oldValue.getAllDistance()+value.getAllDistance());
                newValue.setMaxDistance(oldValue.maxDistanceF(value.getMaxDistance()));
                newValue.setMinDistance(oldValue.minDistanceF(value.getMinDistance()));
                mapMonthlyCarrier.replace(key, newValue);
            } else {
                mapMonthlyCarrier.put(key, ob2.get(key));

            }

            return mapMonthlyCarrier;
        };
    }


}
