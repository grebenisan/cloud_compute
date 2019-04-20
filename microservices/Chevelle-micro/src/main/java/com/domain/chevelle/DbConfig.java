/**********************************************************
 * class:		DbConfig
 * Description:	Bean generating the data source
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@RefreshScope
public class DbConfig {

    @Bean
    @RefreshScope
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource getDatasource() {
        return DataSourceBuilder.create().build();
    }

}
