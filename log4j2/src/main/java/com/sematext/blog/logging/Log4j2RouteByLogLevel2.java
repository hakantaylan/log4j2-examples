package com.sematext.blog.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.InputStream;

public class Log4j2RouteByLogLevel2 {
    static {
        try {
            InputStream inputStream = Log4j2SizeExample.class.getResourceAsStream("/log4j2-routing-through-loglevels2.xml");
            ConfigurationSource source = new ConfigurationSource(inputStream);
            Configurator.initialize(null, source);
//            LoggerContext loggerCtx = Configurator.initialize(null, source);
//            loggerCtx.getLogger(Log4j2DifferentLogLevelsForDifferentAppendersExample.class);
        } catch (Exception ex) {
            // Handle here
            ex.printStackTrace();
        }
    }

    private static final Logger LOGGER = LogManager.getLogger(Log4j2RouteByLogLevel2.class);

    public static void main(String[] args) {
        LOGGER.info("This is an example of using Appender log levels to separate log messages to their respective files");
        LOGGER.trace("This is trace");
        LOGGER.info("This is info");
        LOGGER.debug("This is debug");
        LOGGER.warn("This is warning");
        LOGGER.error("This is error");
        LOGGER.fatal("This is fatal");
    }
}
