package com.sematext.blog.logging.fqcnexample;

import com.sematext.blog.logging.fqcnexample.deneme.Deneme;
import com.sematext.blog.logging.fqcnexample.logger.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger();
//        Logger logger = LogManager.getLogger();
        logger.info("1xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.info("2xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.info("3xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.info("4xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.error("5xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.error("6xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.error("7xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.error("8xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.error("9xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.error("10xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.error("11xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.error("12xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.error("13xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.error("14xzxxxxxxsfgfgdfdggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggghhgh");
        logger.info("Bu son");
        logger.error("Bu son");

        Deneme deneme = new Deneme();
        deneme.deneme();
    }
}
