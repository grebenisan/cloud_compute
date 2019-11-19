package com.domain.department.chevelleuimicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.domain.department"})
@EnableSwagger2
@EnableDiscoveryClient
public class ChevelleUiMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChevelleUiMicroApplication.class, args);

    }

}
