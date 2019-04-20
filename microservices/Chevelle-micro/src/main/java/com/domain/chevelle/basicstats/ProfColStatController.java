/**********************************************************
 * class:		ProfColStatController
 * Description:	The controller serving all basic stats end-points
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle.basicstats;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domain.chevelle.ChevelleMicroConfig;



@RestController
@RequestMapping("/basicstats")
public class ProfColStatController {

	@Autowired
	private ProfColStatService profColStatService;
	
	@Autowired
	private ChevelleMicroConfig chevelleMicroConfig;
	
    @GetMapping(path="/test")
    public String bsTest() {
        return "BasicStats controller test successfull!";
    }
    
    @GetMapping(path="/config", produces="application/json")
    public String bsConfig() {
    	
		return 	"{ " + 
				" \"bs_batch_size\": " + chevelleMicroConfig.getBs_batch_size() +
				", \"bs_retry_count\": " + chevelleMicroConfig.getBs_retry_count() + 
				" }";
    }
    
    
	@PostMapping(path="/out/save")
	public void saveOne(@RequestBody ProfColStat one_profColStat) {
		profColStatService.saveOne(one_profColStat);
	}
    
	@PostMapping(path="/out/savebatch")
	public void saveBatch(@RequestBody List<ProfColStat> list_profColStat) {
		profColStatService.saveBatch(list_profColStat);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/que/update")
	public void updateBStatQue(@RequestBody UpdateProfTableStatQue updateProfTableStatQue)
	{
		//System.out.println(updateProfTableStatQue.toString());
		profColStatService.updateBStatQue(updateProfTableStatQue);
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/que/getworkunit/{srvr_nm}/{batch_size}/text", produces="text/plain")
	public String getBSWorkUnitBsize_text(@PathVariable String srvr_nm, @PathVariable Integer batch_size)
	{
		return profColStatService.getBSWorkUnit_text(srvr_nm, batch_size);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/que/getworkunit/{srvr_nm}/text", produces="text/plain")
	public String getBSWorkUnit_text(@PathVariable String srvr_nm)
	{
		return profColStatService.getBSWorkUnit_text(srvr_nm, chevelleMicroConfig.getBs_batch_size());
		
	}	
	
	@RequestMapping(method = RequestMethod.GET, path="/que/getworkunit/{srvr_nm}/{batch_size}/json", produces="application/json")
	public List<BSWorkUnitReturn> getBSWorkUnitBsize_json(@PathVariable String srvr_nm, @PathVariable Integer batch_size)
	{
		return profColStatService.getBSWorkUnit_json(srvr_nm, batch_size);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/que/getworkunit/{srvr_nm}/json", produces="application/json")
	public List<BSWorkUnitReturn> getBSWorkUnit_json(@PathVariable String srvr_nm)
	{
		return profColStatService.getBSWorkUnit_json(srvr_nm, chevelleMicroConfig.getBs_batch_size());
		
	}
	
	
}









