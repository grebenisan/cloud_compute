package com.domain.area.edgenodesshexecutor;



import com.domain.area.nomadsso.config.PathPatterns;
import com.domain.area.nomadsso.config.SsoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static springfox.documentation.builders.PathSelectors.regex;


@SpringBootApplication(scanBasePackages = {"com.gm.gdaas"})
@EnableSwagger2
public class EdgenodeSshExecutorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgenodeSshExecutorApplication.class, args);
	}

	@Bean
    public PathPatterns getPathPatterns() {
        return new PathPatterns("/ssh/*");
    }

    @Bean
    public SsoFilter getSsoFilter() {
        return new SsoFilter();
    }

    @Bean
    public FilterRegistrationBean regSsoFilter(SsoFilter ssoFilter, PathPatterns pathPatterns) {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(ssoFilter);
        registrationBean.addUrlPatterns(pathPatterns.toArray());
        return registrationBean;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.domain.area.edgenodesshexecutor"))
                .paths(regex(".*"))
                .build()
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

}



