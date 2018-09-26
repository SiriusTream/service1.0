package com.cntest.su.boot.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Spring Cloud Config 云配置服务
 *
 * @author LYC
 * @version 1.0 Create By 2018年3月21日
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServer {

    public static void main(String[] args) {

        SpringApplication.run(ConfigServer.class, args);
    }
}