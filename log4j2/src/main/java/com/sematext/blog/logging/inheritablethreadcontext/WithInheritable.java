package com.sematext.blog.logging.inheritablethreadcontext;

import com.sematext.blog.logging.Log4j2SizeExample;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.InputStream;

public class WithInheritable {

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

    public static void main(String[] args) {
//        System.setProperty("isThreadContextMapInheritable", "true");
//        Hemen arkasına yapınca çalışmıyor. Muhtemelen thread oluşturulmadan önce set edilmeli
        ThreadContext.put("useId", "testUser");
        Logger logger = LogManager.getLogger(WithInheritable.class);
        logger.info("log something from parent thread.");
        new Thread(() -> logger.info("log something from child thread")).start();
    }
}