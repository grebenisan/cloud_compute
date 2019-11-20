package com.domain.department.chevelleuimicro.datagovrev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/datagov")
@CrossOrigin(origins = { "http://localhost", "http://localhost:4200", "https://chevelle-ui-dev.apps.host.domain.com", "https://chevelle-ui-dev.host.domain.com", "https://chevelle-ui-test.apps.host.domain.com", "https://chevelle-ui-test.host.domain.com", "https://chevelle-ui-qa.apps.host.domain.com", "https://chevelle-ui-qa.host.domain.com", "https://chevelle-ui.apps.server.domain.com", "https://chevelle-ui.host.domain.com" }, exposedHeaders = { "ETag", "ETAG", "dg_pending_cnt", "dg_total_cnt" }, methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class DataGovRevController {

    @Autowired
    private DataGovRevService dataGovRevService;

    @RequestMapping("/getreview")
    public List<DCDataGovRev> getDataGovRevByDbname(@RequestParam("dbname") String dbname, @RequestParam("page_size")
            int pageSize, @RequestParam("page_offset") int pageOffset, HttpServletResponse response){
        response.addHeader("dg_total_cnt",String.valueOf(dataGovRevService.getDgTotalCnt(dbname)));
        response.addHeader("dg_pending_cnt",String.valueOf(dataGovRevService.getDgPendingCnt(dbname)));
        return dataGovRevService.getDataGovRevByDbNm(dbname, pageSize, pageOffset);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/updatecol")
    public void updateCol(@RequestBody DataGovRevMsg dataGovRevMsg ){
        dataGovRevService.updateCol(dataGovRevMsg);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/updatebatch")
    public void updateBatch(@RequestBody List<DataGovRevMsg> dataGovRevMsgList ){
        dataGovRevService.updateBatch(dataGovRevMsgList);
    }
}
