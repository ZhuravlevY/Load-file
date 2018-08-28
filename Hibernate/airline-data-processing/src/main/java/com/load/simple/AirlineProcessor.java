package com.load.simple;

import com.load.simple.config.AirlineDataConfiguration;
import com.load.simple.workflow.IWorkflow;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AirlineProcessor {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AirlineDataConfiguration.class);
        context.refresh();
        IWorkflow tfctAirlineWorkflow = context.getBean("tfctAirlineWorkflow", IWorkflow.class);
        IWorkflow aggregationWorkflow = context.getBean("aggregationWorkflow", IWorkflow.class);
        tfctAirlineWorkflow.start();
        aggregationWorkflow.start();
    }
}
