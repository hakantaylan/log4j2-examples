package com.sematext.blog.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.InputStream;

public class Log4j2DifferentLogLevelsForDifferentAppendersExample {
    static {
        try {
            InputStream inputStream = Log4j2SizeExample.class.getResourceAsStream("/log4j2-console-info-but-file-error-levelsize.xml");
            ConfigurationSource source = new ConfigurationSource(inputStream);
//            LoggerContext loggerCtx = Configurator.initialize(null, source);
//            loggerCtx.getLogger(Log4j2DifferentLogLevelsForDifferentAppendersExample.class);
        } catch (Exception ex) {
            // Handle here
            ex.printStackTrace();
        }
    }

    private static final Logger LOGGER = LogManager.getLogger(Log4j2DifferentLogLevelsForDifferentAppendersExample.class);

    public static void main(String[] args) {
        LOGGER.info("This is info. This will print only to console not to file logger");
        LOGGER.info("This is info. This will print only to console not to file logger");
        LOGGER.info("This is info. This will print only to console not to file logger");
        LOGGER.info("This is info. This will print only to console not to file logger");
        LOGGER.info("This is info. This will print only to console not to file logger");
        LOGGER.info("This is info. This will print only to console not to file logger");
        LOGGER.error("This is debug. This will print both console and file appender");
        LOGGER.fatal("This is fatal. This will print both console and file appender");
    }
}
