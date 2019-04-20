/**********************************************************
 * class:		ChevelleMicroConfig
 * Description:	Configuration component, storing config properties from local and from the cloud config server
 * History:		D. Grebenisan, Created
 *  
 **********************************************************/

package com.domain.chevelle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@ConfigurationProperties
public class ChevelleMicroConfig {

	@Value("${spring_profiles_active}")
	private String spring_profiles_active;
    
    @Value("${config_server_uri}")
    private String config_server_uri;    

	@Value("${spring.datasource.username}")
	private String db_username;
	
    @Value("${spring.datasource.password}")
	private String db_password;
	
    @Value("${spring.datasource.url}")
	private String db_url;
    
    @Value("${spring.datasource.driver-class-name}")
	private String db_driver_class_name;	
	
    @Value("${basicstats.batch_size}")
	private Integer bs_batch_size;	   
    
    @Value("${basicstats.retry_count}")
	private Integer bs_retry_count;	    
    
    @Value("${dataclass.batch_size}")
	private Integer dc_batch_size;	   
    
    @Value("${dataclass.retry_count}")
	private Integer dc_retry_count;	        
    
	@Value("${jwt.macKey}")
    private String macKey;
    
    
    public ChevelleMicroConfig() {

	}


   
    public String getSpring_profiles_active() {
		return spring_profiles_active;
	}


	public void setSpring_profiles_active(String spring_profiles_active) {
		this.spring_profiles_active = spring_profiles_active;
	}


	public String getConfig_server_uri() {
		return config_server_uri;
	}


	public void setConfig_server_uri(String config_server_uri) {
		this.config_server_uri = config_server_uri;
	}

    
    
	public String getDb_username() {
		return db_username;
	}


	public void setDb_username(String db_username) {
		this.db_username = db_username;
	}


	public String getDb_password() {
		return db_password;
	}


	public void setDb_password(String db_password) {
		this.db_password = db_password;
	}


	public String getDb_url() {
		return db_url;
	}


	public void setDb_url(String db_url) {
		this.db_url = db_url;
	}


	public String getDb_driver_class_name() {
		return db_driver_class_name;
	}


	public void setDb_driver_class_name(String db_driver_class_name) {
		this.db_driver_class_name = db_driver_class_name;
	}


	public Integer getBs_batch_size() {
		return bs_batch_size;
	}


	public void setBs_batch_size(Integer bs_batch_size) {
		this.bs_batch_size = bs_batch_size;
	}


	public Integer getBs_retry_count() {
		return bs_retry_count;
	}


	public void setBs_retry_count(Integer bs_retry_count) {
		this.bs_retry_count = bs_retry_count;
	}


	public Integer getDc_batch_size() {
		return dc_batch_size;
	}


	public void setDc_batch_size(Integer dc_batch_size) {
		this.dc_batch_size = dc_batch_size;
	}


	public Integer getDc_retry_count() {
		return dc_retry_count;
	}


	public void setDc_retry_count(Integer dc_retry_count) {
		this.dc_retry_count = dc_retry_count;
	}



	public String getMacKey() {
		return macKey;
	}



	public void setMacKey(String macKey) {
		this.macKey = macKey;
	}
    
    
    
}
