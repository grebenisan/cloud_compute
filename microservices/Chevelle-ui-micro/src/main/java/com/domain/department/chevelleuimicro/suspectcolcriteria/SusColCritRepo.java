package com.domain.department.chevelleuimicro.suspectcolcriteria;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SusColCritRepo extends CrudRepository<ProfColClsRule,Long> {

    @Query("SELECT rule FROM ProfColClsRule rule ORDER BY data_cls_nm ASC , match_type_opr DESC ")
    List<ProfColClsRule> findAllCriteria();

    @Query("SELECT rule FROM ProfColClsRule rule WHERE data_cls_nm=:dataClsNm ORDER BY match_type_opr DESC ")
    List<ProfColClsRule> findAllByData_cls_nm(@Param("dataClsNm") String dataClsNm);

    @Query(value = "SELECT DISTINCT DATA_CLS_NM FROM CHEVELLE.PROF_DATA_CLS_REGEX ORDER BY DATA_CLS_NM ASC ", nativeQuery = true)
    List<String> findDistinctByData_cls_nm();
    
//    @Query(value = "SELECT DISTINCT DATA_CLS_NM, MATCH_THOLD_PCT FROM CHEVELLE.PROF_DATA_CLS_REGEX ORDER BY DATA_CLS_NM ASC ", nativeQuery = true)
//    List<DcNameThold> findAllDcNamesAndTholds();
}
