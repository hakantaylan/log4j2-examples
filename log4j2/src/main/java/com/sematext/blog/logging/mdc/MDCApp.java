package com.sematext.blog.logging.mdc;

import com.sematext.blog.logging.Log4j2SizeExample;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.MDC;

import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.*;

public class MDCApp {

    private static Logger logger;
    private final ExecutorService dbExecutor = Executors.newFixedThreadPool(5, namedThreadFactory("dbThread"));
    private final ExecutorService webExecutor = Executors.newFixedThreadPool(5, namedThreadFactory("webthread"));

    static {
        try {
            System.setProperty("isThreadContextMapInheritable", "true");
            InputStream inputStream = Log4j2SizeExample.class.getResourceAsStream("/log4j2-inheritable-thread-context.xml");
            ConfigurationSource source = new ConfigurationSource(inputStream);
            Configurator.initialize(null, source);
            logger = LogManager.getLogger(MDCApp.class);
        } catch (Exception ex) {
            // Handle here
            ex.printStackTrace();
        }
    }

    public CompletableFuture<String> registerUser(final String phoneNo) {
        MDC.put("phoneNo", phoneNo);
        logger.debug("Request received to register user");

        return CompletableFuture.supplyAsync(() -> {
            //create user in DB
            int userId = 15;
            MDC.put("userId", Integer.toString(userId));
            logger.debug("Created user in database");

            return userId;
        }, dbExecutor)
                .thenApplyAsync(userId -> {
                    //generate an OTP for the user
//            String otp = generateOTP(userId);
                    logger.debug("Generated OTP for user");

                    //send the OTP to user
                    String messageId = UUID.randomUUID().toString();
                    MDC.put("messageId", messageId);
                    logger.debug("OTP sent to user");

                    return messageId;
                }, webExecutor);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MDCApp app = new MDCApp();
        CompletableFuture<String> future = app.registerUser("123");
        String join = future.join();
        logger.debug("join");
        app.shutdown();

    }

    private void shutdown() throws InterruptedException {
        dbExecutor.shutdown();
        dbExecutor.awaitTermination(1, TimeUnit.SECONDS );

        webExecutor.shutdown();
        webExecutor.awaitTermination(1, TimeUnit.SECONDS );
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
}
