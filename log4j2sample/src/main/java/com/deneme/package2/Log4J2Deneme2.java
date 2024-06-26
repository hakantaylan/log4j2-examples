package com.deneme.package2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4J2Deneme2 {

    private static Logger logger = LogManager.getLogger(Log4J2Deneme2.class);

    public void performSomeTask(){
        logger.debug("This is a debug message from package2");
        logger.info("This is an info message from package2");
        logger.warn("This is a warn message from package2");
        logger.error("This is an error message from package2");
        logger.fatal("This is a fatal message from package2");
    }
}