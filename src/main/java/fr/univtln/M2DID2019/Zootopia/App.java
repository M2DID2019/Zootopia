package fr.univtln.M2DID2019.Zootopia;

import org.apache.log4j.PatternLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

public class App {
    @SuppressWarnings("unused")
    private static final Class[] shadeHack = {org.apache.log4j.RollingFileAppender.class,
            org.apache.log4j.ConsoleAppender.class,
            PatternLayout.class};

    //Set the logger with the real class name.
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        logger.info("App started.");
        logger.debug("About to talk :");
        System.out.println("Hello world !");
        Properties myProperty= new Properties();
        try {
            myProperty.load(App.class.getClassLoader().getResourceAsStream("ValidationMessages.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
