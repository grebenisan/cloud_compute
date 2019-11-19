package com.domain.department.chevelleuimicro.datagovrev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataGovRevService {

    @Autowired
    private DataGovRevRepo dataGovRevRepo;

    public List<DCDataGovRev> getDataGovRevByDbNm(String dbNm, int pageSize, int pageOffset){
        return dataGovRevRepo.getDataGovRevByDbNm(dbNm, pageSize, pageOffset);
    }

    public int getDgTotalCnt(String dbNm){
        return dataGovRevRepo.getDgTotalCnt(dbNm);
    }

    public int getDgPendingCnt(String dbNm){
        return dataGovRevRepo.getDgPendingCnt(dbNm);
    }

    public void updateCol(DataGovRevMsg dataGovRevMsg){
        dataGovRevRepo.updateCol(dataGovRevMsg);
    }

    public void updateBatch(List<DataGovRevMsg> dataGovRevMsgList){
        for (DataGovRevMsg dataGovRevMsg : dataGovRevMsgList){
            dataGovRevRepo.updateCol(dataGovRevMsg);
        }
    }

}
