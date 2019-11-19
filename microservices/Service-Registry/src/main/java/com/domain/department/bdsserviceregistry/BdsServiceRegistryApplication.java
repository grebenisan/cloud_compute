package com.domain.department.bdsserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BdsServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BdsServiceRegistryApplication.class, args);
    }
}
