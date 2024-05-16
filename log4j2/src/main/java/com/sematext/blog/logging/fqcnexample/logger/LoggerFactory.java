package com.sematext.blog.logging.fqcnexample.logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.InputStream;

public class LoggerFactory {
    static {
        try {
            InputStream inputStream = LoggerFactory.class.getResourceAsStream("/log4j2-console-file-linenumber.xml");
            ConfigurationSource source = new ConfigurationSource(inputStream);
            Configurator.initialize(null, source);
//            LoggerContext loggerCtx = Configurator.initialize(null, source);
//            loggerCtx.getLogger(Log4j2DifferentLogLevelsForDifferentAppendersExample.class);
        } catch (Exception ex) {
            // Handle here
            ex.printStackTrace();
        }
    }


    public static Logger getLogger() {
        return ExtLogger.create();
    }

    public static Logger getLogger(Class<?> clazz) {
        return ExtLogger.create(clazz);
    }

    public static Logger getLogger(String loggerName) {
        return ExtLogger.create(loggerName);
    }
}
