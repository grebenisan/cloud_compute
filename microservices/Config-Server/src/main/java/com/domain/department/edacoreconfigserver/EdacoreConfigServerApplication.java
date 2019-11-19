package com.domain.department.edacoreconfigserver;


import com.domain.department.nomadsso.config.SsoFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;

@EnableConfigServer
@SpringBootApplication
public class EdacoreConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdacoreConfigServerApplication.class, args);
	}

	@Value("${jwt.macKey}")
	private String macKey;

	@Bean
	public FilterRegistrationBean regSsoFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new SsoFilter(this.macKey));
		registrationBean.addUrlPatterns("*");
		return registrationBean;
	}

}
