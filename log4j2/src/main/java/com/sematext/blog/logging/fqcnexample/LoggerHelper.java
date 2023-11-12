package com.sematext.blog.logging.fqcnexample;

import com.sematext.blog.logging.Log4j2SizeExample;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.InputStream;

public class LoggerHelper {
    static {
        try {
            InputStream inputStream = Log4j2SizeExample.class.getResourceAsStream("/log4j2-console-file-linenumber.xml");
            ConfigurationSource source = new ConfigurationSource(inputStream);
            Configurator.initialize(null, source);
//            LoggerContext loggerCtx = Configurator.initialize(null, source);
//            loggerCtx.getLogger(Log4j2DifferentLogLevelsForDifferentAppendersExample.class);
        } catch (Exception ex) {
            // Handle here
            ex.printStackTrace();
        }
    }


    private static final Logger LOGGER = ExtLogger.create();

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void info(String message, Object obj) {
        LOGGER.info(message, obj);
    }

    public static void error(String message) {
        LOGGER.error(message);
    }

    public static void error(String message, Object obj) {
        LOGGER.error(message, obj);
    }
}
