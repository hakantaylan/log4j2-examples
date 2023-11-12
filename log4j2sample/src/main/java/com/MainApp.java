package com;

import com.package1.Log4J2Deneme1;
import com.package2.Log4J2Deneme2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MainApp implements ApplicationRunner {

    private static Logger logger = LogManager.getLogger(MainApp.class);

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    public void run(String... args) throws Exception {
        Log4J2Deneme1 a1 = new Log4J2Deneme1();
        a1.performSomeTask();

        Log4J2Deneme2 a2 = new Log4J2Deneme2();
        a2.performSomeTask();

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Log4J2Deneme1 a1 = new Log4J2Deneme1();
        a1.performSomeTask();

        Log4J2Deneme2 a2 = new Log4J2Deneme2();
        a2.performSomeTask();

        logger.info("This is an info message from main app");

    }

}

