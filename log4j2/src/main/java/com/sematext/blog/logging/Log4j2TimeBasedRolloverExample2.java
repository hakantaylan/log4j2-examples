package com.sematext.blog.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.InputStream;

public class Log4j2TimeBasedRolloverExample2 {
    static {
        try {
            InputStream inputStream = Log4j2SizeExample.class.getResourceAsStream("/log4j2-time-based-rollover2.xml");
            ConfigurationSource source = new ConfigurationSource(inputStream);
            Configurator.initialize(null, source);
//            LoggerContext loggerCtx = Configurator.initialize(null, source);
//            loggerCtx.getLogger(Log4j2DifferentLogLevelsForDifferentAppendersExample.class);
        } catch (Exception ex) {
            // Handle here
            ex.printStackTrace();
        }
    }

    private static final Logger LOGGER = LogManager.getLogger(Log4j2TimeBasedRolloverExample2.class);

    public static void main(String[] args) {
//        try {
//            Thread.sleep(5_000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while(true) {
            LOGGER.info("1");
            LOGGER.error("2");
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
