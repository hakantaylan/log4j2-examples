package com.sematext.blog.logging.fqcnexample.deneme;

import com.sematext.blog.logging.fqcnexample.logger.LoggerFactory;
import org.apache.logging.log4j.Logger;

public class Deneme {

    private static final Logger logger = LoggerFactory.getLogger(Deneme.class);

    public void deneme() {
        logger.debug("deneme");
        logger.error("deneme");
        logger.info("deneme");
        logger.warn("deneme");
        logger.fatal("deneme");
    }
}
