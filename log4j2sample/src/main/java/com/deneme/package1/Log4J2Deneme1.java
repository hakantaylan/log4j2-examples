package com.deneme.package1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4J2Deneme1 {

    private static Logger logger = LogManager.getLogger(Log4J2Deneme1.class);

    public void performSomeTask(){
        logger.debug("This is a debug message from package1");
        logger.info("This is an info message from package1");
        logger.warn("This is a warn message from package1");
        logger.error("This is an error message from package1");
        logger.fatal("This is a fatal message from package1");
    }
}