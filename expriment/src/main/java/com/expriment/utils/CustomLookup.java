/*
package com.expriment.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

public class CustomLookup */
/*extends AbstractLookup*//*
 {


    @Autowired
    AppProps appProps;

//    @Override
    @EventListener(ApplicationReadyEvent.class)
//    public String lookup(LogEvent event, String log) {
    public void lookup() {
        if ("dev".equals(appProps.getActiveProfile())) {
            // Perform your custom logic to determine the dynamic value
//            String dynamicValue = determineDynamicValue();
            appProps.setLogFile("C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/LogsFile/Event");
//            return dynamicValue;
        }
//        return null; // Return null for other keys to continue with default behavior
    }

    private String determineDynamicValue() {
        // Implement your logic to determine the dynamic value
        // This could be based on configuration, environment, or any other factor
        return "C:/Users/Indradev.Kumar/IdeaProjects/MyExperiment/expriment/LogsFile/new";
    }
}
*/
