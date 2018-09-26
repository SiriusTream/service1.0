package com.cntest.su.boot.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;


/**
 */
@SpringBootApplication
@EnableEurekaClient
public class AdminServer {

    public static void main(String[] args) {

        SpringApplication.run(AdminServer.class, args);
    }
 
}