/**********************************************************
 * class:		 ChevelleMicroApplication
 * Description:	The main class of the application
 * History:		D. Grebenisan, Created
 *  
 **********************************************************/

package com.domain.chevelle;

import com.domain.nomadsso.config.SsoFilter;
import com.domain.nomadsso.config.PathPatterns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;

import java.util.Arrays;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication(scanBasePackages = {"com.domain.gdaas"})
public class ChevelleMicroApplication {
	

	
	public static void main(String[] args) {
		SpringApplication.run(ChevelleMicroApplication.class, args);
	}

    @Bean
    public PathPatterns getPathPatterns() {
            return new PathPatterns("/basicstats/*", "/dataclass/*");
    }
	
	@Bean
	public SsoFilter getSsoFilter()
	{
		return new SsoFilter();
	}
	

    @Bean
    public FilterRegistrationBean regSsoFilter(SsoFilter ssoFilter) {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(ssoFilter);
        registrationBean.addUrlPatterns(getPathPatterns().toArray());
        return registrationBean;
    }
	

}
