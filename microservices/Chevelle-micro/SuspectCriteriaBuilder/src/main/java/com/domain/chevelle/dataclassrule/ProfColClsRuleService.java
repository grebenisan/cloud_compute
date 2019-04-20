package com.domain.chevelle.dataclassrule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfColClsRuleService{

    @Autowired
    private ProfColClsRuleRepo profColClsRuleRepo;

    public List<String> GetRule() {

        List<String> outputRules= profColClsRuleRepo.QueryRuleTable();
        return outputRules;
    }
}

