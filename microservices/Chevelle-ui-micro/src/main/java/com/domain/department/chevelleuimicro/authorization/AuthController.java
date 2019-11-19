package com.domain.department.chevelleuimicro.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = { "http://localhost", "http://localhost:4200", "https://chevelle-ui-dev.apps.host.domain.com", "https://chevelle-ui-dev.host.domain.com", "https://chevelle-ui-test.apps.host.domain.com", "https://chevelle-ui-test.host.domain.com", "https://chevelle-ui-qa.apps.host.domain.com", "https://chevelle-ui-qa.host.domain.com", "https://chevelle-ui.apps.pcfmi.gm.com", "https://chevelle-ui.cpi.gm.com" }, exposedHeaders = { "ETag", "ETAG", "dg_pending_cnt", "dg_total_cnt" }, methods= { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/getroles")
    public List<Map<String,BigDecimal>> getRolesByUserId(@RequestParam("user_id") String userID){
        return authService.getRoleByUserId(userID);
    }
    
    @RequestMapping("/getroles/{user_id}")
    public List<Map<String,BigDecimal>> getRolesByUserId_ext(@PathVariable String user_id){
        return authService.getRoleByUserId(user_id);
    }
    
}
