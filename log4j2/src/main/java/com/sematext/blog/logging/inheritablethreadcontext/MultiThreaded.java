package com.sematext.blog.logging.inheritablethreadcontext;

import com.sematext.blog.logging.Log4j2SizeExample;
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
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreaded {

    static {
        try {
            System.setProperty("isThreadContextMapInheritable", "true");
            InputStream inputStream = Log4j2SizeExample.class.getResourceAsStream("/log4j2-inheritable-thread-context.xml");
            ConfigurationSource source = new ConfigurationSource(inputStream);
            Configurator.initialize(null, source);
        } catch (Exception ex) {
            // Handle here
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService poolOne = Executors.newFixedThreadPool(3, threadFactory("pool-1-"));
        ExecutorService poolTwo = Executors.newFixedThreadPool(1, threadFactory("pool-2-"));

        AtomicInteger atomicInteger = new AtomicInteger(1);
        Logger logger = LogManager.getLogger(MultiThreaded.class);

        Runnable runnableTwo = () -> logger.info("now we are in another");

        Runnable runnableOne = () -> {
            String value = String.valueOf(atomicInteger.getAndIncrement());
            ThreadContext.put(value, value);
            ThreadContext.put("thread", Thread.currentThread().getName());
            logger.info("something to log in one thread");
//            poolTwo.submit(runnableTwo);
            new Thread(runnableTwo).start();
            ThreadContext.remove("thread");
            ThreadContext.remove(value);
        };

        poolOne.submit(runnableOne);
        poolOne.submit(runnableOne);
        poolOne.submit(runnableOne);
        poolOne.submit(runnableOne);
        poolOne.submit(runnableOne);
        poolOne.submit(runnableOne);

        poolOne.shutdown();
        poolOne.awaitTermination(5, TimeUnit.SECONDS);


        poolTwo.shutdown();
        poolTwo.awaitTermination(5, TimeUnit.SECONDS);

    }


    private static ThreadFactory threadFactory(final String prefix) {
        return new ThreadFactory() {
            int counter = 0;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, prefix + (++counter));
            }
        };
    }
}
