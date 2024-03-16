package com.sematext.blog.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.appender.routing.RoutingAppender;
import org.apache.logging.log4j.core.config.AppenderControl;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class Log4j2MultiThreadRouting3 {
    static {
        try {
            InputStream inputStream = Log4j2SizeExample.class.getResourceAsStream("/log4j2-routing-by-user-and-thread3.xml");
            ConfigurationSource source = new ConfigurationSource(inputStream);
            Configurator.initialize(null, source);
//            LoggerContext loggerCtx = Configurator.initialize(null, source);
//            loggerCtx.getLogger(Log4j2DifferentLogLevelsForDifferentAppendersExample.class);
        } catch (Exception ex) {
            // Handle here
            ex.printStackTrace();
        }
    }

    private static final Logger LOGGER = LogManager.getLogger(Log4j2MultiThreadRouting3.class);

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

        ExecutorService executorService = Executors.newFixedThreadPool(5, namedThreadFactory("thrd-"));
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
        org.apache.logging.log4j.core.LoggerContext  context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        int size = context.getConfiguration().getAppenders().size();
        RoutingAppender threadRoutingAppender1 = (RoutingAppender) context.getConfiguration().getAppenders().get("ThreadRoutingAppender");
        Map<String, AppenderControl> threadRoutingAppender = threadRoutingAppender1.getAppenders();
        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int purgedAppenderSize = context.getConfiguration().getAppenders().size();
        Map<String, AppenderControl> threadRoutingAppender2 = threadRoutingAppender1.getAppenders();
        ThreadContext.put("logMethod", "userId");
        ThreadContext.put("userId", "HTAYLAN");
        LOGGER.info("info log with userId");
        LOGGER.error("error log with userId");
        ThreadContext.remove("logMethod");
        ThreadContext.remove("userId");
        executorService = Executors.newFixedThreadPool(5, namedThreadFactory("aaa-"));
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

        System.out.println("aaaaa");
    }

    private static ThreadFactory namedThreadFactory(final String prefix) {
        return new ThreadFactory() {
            int counter = 0;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, prefix + (++counter));
            }
        };
    }

//    static class NameableThreadFactory implements ThreadFactory {
//        private int threadsNum;
//        private final String namePattern;
//
//        public NameableThreadFactory(String baseName){
//            namePattern = baseName + "-%d";
//        }
//
//        @Override
//        public Thread newThread(Runnable runnable){
//            threadsNum++;
//            return new Thread(runnable, String.format(namePattern, threadsNum));
//        }
//    }
}
