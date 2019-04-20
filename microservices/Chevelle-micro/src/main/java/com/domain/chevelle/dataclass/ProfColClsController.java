/**********************************************************
 * class:		ProfColClsController
 * Description:	The controller serving all of the data class end-points
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle.dataclass;


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
@RequestMapping("/dataclass")
public class ProfColClsController {

	@Autowired
	private ProfColClsService profColClsService;
	
	@Autowired
	private ChevelleMicroConfig chevelleMicroConfig;
	
    @GetMapping(path="/test")
    public String dcTest() {
        return "DataClass controller test successfull!";
    }
    
    @GetMapping(path="/config", produces="application/json")
    public String dcConfig() {
    	
		return 	"{ " + 
				"\"dc_batch_size\": " + chevelleMicroConfig.getDc_batch_size() +
				", \"dc_retry_count\": " + chevelleMicroConfig.getDc_retry_count() +
				" }";
    }    
	
	@PostMapping(path="/out/save")
	public void saveOne(@RequestBody ProfColCls profColCls) {
		profColClsService.saveOne(profColCls);
	}
    
	@PostMapping(path="/out/savebatch")
	public void saveBatch(@RequestBody List<ProfColCls> list_profColCls) {
		profColClsService.saveBatch(list_profColCls);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/que/update")
	public void updateDCQue(@RequestBody UpdateProfColClsQue updateProfColClsQue)
	{
		//System.out.println(updateProfColClsQue.toString());
		profColClsService.updateDCQue(updateProfColClsQue);
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, path="/que/getworkunit/{srvr_nm}/{upd_by}/{batch_size}/json", produces="application/json")
	public List<DCWorkUnitReturn> getDCWorkUnitBsize_json(@PathVariable String srvr_nm, @PathVariable String upd_by, @PathVariable Integer batch_size)
	{
		return profColClsService.getDCWorkUnit_json(srvr_nm, upd_by, batch_size, chevelleMicroConfig.getDc_retry_count());
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path="/que/getworkunit/{srvr_nm}/{upd_by}/json", produces="application/json")
	public List<DCWorkUnitReturn> getDCWorkUnit_json(@PathVariable String srvr_nm, @PathVariable String upd_by)
	{
		return profColClsService.getDCWorkUnit_json(srvr_nm, upd_by, chevelleMicroConfig.getDc_batch_size(), chevelleMicroConfig.getDc_retry_count());
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/que/getworkunit/{srvr_nm}/{upd_by}/{batch_size}/text", produces="text/plain")
	public String getDCWorkUnitBsize_text(@PathVariable String srvr_nm, @PathVariable String upd_by, @PathVariable Integer batch_size)
	{
		return profColClsService.getDCWorkUnit_text(srvr_nm, upd_by, batch_size, chevelleMicroConfig.getDc_retry_count());
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/que/getworkunit/{srvr_nm}/{upd_by}/text", produces="text/plain")
	public String getDCWorkUnit_text(@PathVariable String srvr_nm, @PathVariable String upd_by)
	{
		return profColClsService.getDCWorkUnit_text(srvr_nm, upd_by, chevelleMicroConfig.getDc_batch_size(), chevelleMicroConfig.getDc_retry_count());
		
	}	
	
}
