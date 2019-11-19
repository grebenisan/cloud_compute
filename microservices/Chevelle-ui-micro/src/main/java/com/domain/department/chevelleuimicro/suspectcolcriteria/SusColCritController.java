package com.domain.department.chevelleuimicro.suspectcolcriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/suspectcriteria")
@CrossOrigin(origins = { "http://localhost", "http://localhost:4200", "https://chevelle-ui-dev.apps.host.domain.com", "https://chevelle-ui-dev.host.domain.com", "https://chevelle-ui-test.apps.host.domain.com", "https://chevelle-ui-test.host.domain.com", "https://chevelle-ui-qa.apps.host.domain.com", "https://chevelle-ui-qa.host.domain.com", "https://chevelle-ui.apps.pcfmi.gm.com", "https://chevelle-ui.cpi.gm.com" }, exposedHeaders = { "ETag", "ETAG", "dg_pending_cnt", "dg_total_cnt" }, methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class SusColCritController {

    @Autowired
    private SusColCritService critService;

    @RequestMapping("/test")
    public String sayHi(){
        return "ChevelleUI Micro - Suspected Criteria Test successful !";
    }

    @RequestMapping("/getall")
    public List<ProfColClsRule> getAllCriteria(){
        return critService.getAllCriteria();
    }

    @RequestMapping("/get/{dataClsNm}")
    public List<ProfColClsRule> getCritByDCN(@PathVariable String dataClsNm){
        return critService.getCritByDataClsNm(dataClsNm);
    }

    @RequestMapping(method= RequestMethod.POST, value="/save")
    public void saveCriteria(@RequestBody ProfColClsRule criteria){
        critService.saveCriteria(criteria);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/delete")
    public void delCriteria(@RequestBody ProfColClsRule criteria){
        critService.delCriteria(criteria);
    }

    @RequestMapping("/getdcnames")
    public List<Map<String,String>> getDistDataClsNm(){
       return critService.getDistDataCLsNm();
    }
    

}
