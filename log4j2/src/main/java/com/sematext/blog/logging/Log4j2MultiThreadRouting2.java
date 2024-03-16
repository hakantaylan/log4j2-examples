package com.sematext.blog.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class Log4j2MultiThreadRouting2 {
    static {
        try {
            InputStream inputStream = Log4j2SizeExample.class.getResourceAsStream("/log4j2-routing-by-user-and-thread2.xml");
            ConfigurationSource source = new ConfigurationSource(inputStream);
            Configurator.initialize(null, source);
//            LoggerContext loggerCtx = Configurator.initialize(null, source);
//            loggerCtx.getLogger(Log4j2DifferentLogLevelsForDifferentAppendersExample.class);
        } catch (Exception ex) {
            // Handle here
            ex.printStackTrace();
        }
    }

    private static final Logger LOGGER = LogManager.getLogger(Log4j2MultiThreadRouting2.class);

    public static void main(String[] args) {
        ThreadContext.put("logMethod", "userId");
        ThreadContext.put("userId", "HTAYLAN");
        LOGGER.info("info log with userId");
        LOGGER.error("error log with userId");
        ThreadContext.remove("logMethod");
        ThreadContext.remove("userId");

        ThreadContext.put("logMethod", "userId");
        ThreadContext.put("userId", "HTAYLAN2");
        LOGGER.info("info log with userId");
        LOGGER.error("error log with userId");
        ThreadContext.remove("logMethod");
        ThreadContext.remove("userId");

        ExecutorService executorService = Executors.newFixedThreadPool(5, new NameableThreadFactory("thrd"));
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                LOGGER.info("info message");
                LOGGER.error("debug message");
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class NameableThreadFactory implements ThreadFactory {
        private int threadsNum;
        private final String namePattern;

        public NameableThreadFactory(String baseName){
            namePattern = baseName + "-%d";
        }

        @Override
        public Thread newThread(Runnable runnable){
            threadsNum++;
            return new Thread(runnable, String.format(namePattern, threadsNum));
        }
    }
}
