package com.poseidon;

import com.poseidon.controllers.LoginController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PoseidonApplication {

    private static final Logger logger = LogManager.getLogger(PoseidonApplication.class);
    public static void main(String[] args) {
        logger.info("Starting the application...");
        SpringApplication.run(PoseidonApplication.class, args);
    }

}