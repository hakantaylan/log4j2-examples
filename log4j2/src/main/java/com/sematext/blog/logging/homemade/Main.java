package com.sematext.blog.logging.homemade;

public class Main {

    public static void main(String[] args) throws Exception {
        ILog logger = new ILog() {};

        logger.log("parsing message", () -> System.out.println("processing"));
        var output = logger.log("parsing message", () -> "Hello World".toUpperCase());
    }
}
