/**********************************************************
 * class:		ChevelleMicroController
 * Description:	The controller of the application, used for testing the current profile and config
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RestController
public class ChevelleMicroController {
	
	@Autowired
	private ChevelleMicroConfig chevelleMicroConfig;
	
	
	@GetMapping(path = "/test", produces="application/json")
	public String getTest() {
		return "Chevelle microservice - Test successfull!";
	}

	@GetMapping(path = "/config", produces="application/json")
	public String getStatus() {
		return 	"{ " + 
				" \"spring_profiles_active\": \"" + chevelleMicroConfig.getSpring_profiles_active() + 
				"\", \"config_server_uri\": \"" + chevelleMicroConfig.getConfig_server_uri() + 				
				"\", \"bs_batch_size\": " + chevelleMicroConfig.getBs_batch_size() +
				", \"bs_retry_count\": " + chevelleMicroConfig.getBs_retry_count() + 
				", \"dc_batch_size\": " + chevelleMicroConfig.getDc_batch_size() +
				", \"dc_retry_count\": " + chevelleMicroConfig.getDc_retry_count() +
				", \"db_username\": \"" + chevelleMicroConfig.getDb_username() +
				"\", \"db_url\": \"" + chevelleMicroConfig.getDb_url() + 
				"\", \"db_driver_class_name\": \"" + chevelleMicroConfig.getDb_driver_class_name() + 
				"\" }";
	}

}
