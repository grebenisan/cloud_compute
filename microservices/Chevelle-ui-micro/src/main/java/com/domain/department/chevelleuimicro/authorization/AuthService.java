package com.domain.department.chevelleuimicro.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private AuthRepo authRepo;

    public List<Map<String,BigDecimal>> getRoleByUserId(String userId){

        List<Map<String,BigDecimal>> roleList = new ArrayList<Map<String,BigDecimal>>();

        for(BigDecimal roleId: authRepo.getRoleByUserId(userId)){
            Map<String, BigDecimal> role = new HashMap<String,BigDecimal>();
            role.put("role",roleId);
            roleList.add(role);
        }
        return roleList;
    }
}
