package com.sematext.blog.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.InputStream;

public class Log4j2SizeAndTimeRolloverExample {
    static {
        try {
            InputStream inputStream = Log4j2SizeAndTimeRolloverExample.class.getResourceAsStream("/log4j2-size-and-time.xml");
            ConfigurationSource source = new ConfigurationSource(inputStream);
            Configurator.initialize(null, source);
//            LoggerContext loggerCtx = Configurator.initialize(null, source);
//            loggerCtx.getLogger(Log4j2DifferentLogLevelsForDifferentAppendersExample.class);
        } catch (Exception ex) {
            // Handle here
            ex.printStackTrace();
        }
    }

    private static final Logger LOGGER = LogManager.getLogger(Log4j2TimeBasedRolloverExample.class);

    public static void main(String[] args) {

        LOGGER.info("1");
        LOGGER.error("2");
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.info("1xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        LOGGER.info("2xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        LOGGER.info("3xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        LOGGER.info("4xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        LOGGER.error("5xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        LOGGER.error("6xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        LOGGER.error("7xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        LOGGER.error("8xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        LOGGER.error("9xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        LOGGER.error("10xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        LOGGER.error("11xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        LOGGER.error("12xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        LOGGER.info("Bu son");
        LOGGER.error("Bu son");
    }
}
