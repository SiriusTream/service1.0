package com.cntest.su.boot.admin.server;

import de.codecentric.boot.admin.config.EnableAdminServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Spring
 *
 * @author wanglei
 * @version 1.0 Create By 2018年3月21日
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableAdminServer
public class AdminServer {

    public static void main(String[] args) {
        SpringApplication.run(AdminServer.class, args);
    }
};