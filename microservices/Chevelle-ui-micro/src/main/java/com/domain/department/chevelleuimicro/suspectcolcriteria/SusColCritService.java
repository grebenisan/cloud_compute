package com.domain.department.chevelleuimicro.suspectcolcriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SusColCritService {

    @Autowired
    private SusColCritRepo critRepo;

    public List<ProfColClsRule> getAllCriteria(){

        return critRepo.findAllCriteria();
    }

    public List<ProfColClsRule> getCritByDataClsNm(String dataClsNm){

        return critRepo.findAllByData_cls_nm(dataClsNm);

    }

    public void saveCriteria(ProfColClsRule criteria){

        critRepo.save(criteria);
    }

    public void delCriteria(ProfColClsRule criteria) {

        critRepo.delete(criteria);

    }

    public List<Map<String,String>> getDistDataCLsNm(){

        List<Map<String,String>> dataClsNmList = new ArrayList<Map<String,String>>();
        for(String dcn: critRepo.findDistinctByData_cls_nm()){
            Map<String, String> dataClsNm = new HashMap<String, String>();
            dataClsNm.put("data_cls_nm",dcn);
            dataClsNmList.add(dataClsNm);
        }
        return dataClsNmList;

    }
    
//    public List<DcNameThold> findAllDcNamesAndTholds()
//    {
//    	return critRepo.findAllDcNamesAndTholds();
//    }

}
