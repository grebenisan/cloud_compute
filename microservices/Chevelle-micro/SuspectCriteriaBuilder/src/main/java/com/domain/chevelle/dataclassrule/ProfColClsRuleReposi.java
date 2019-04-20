package com.domain.chevelle.dataclassrule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

@Transactional
@Repository

public interface ProfColClsRuleReposi extends JpaRepository<ProfColClsRule, ProfColClsRuleId> {



    ///@Query("select DataClsNm, MatchTypeOpr, MatchNm from ProfColClsRule")
    @Query(value ="SELECT DATA_CLS_NM, MATCH_TYPE_OPR, listagg(MATCH_NM, ',''' ) within group (order by MATCH_NM) as MATCH_NM FROM " +
            " (select * from CHEVELLE.PROF_COL_CLS_RULE where lower(MATCH_TYPE_OPR) in ('not in', 'in')) a " +
            "group by DATA_CLS_NM, MATCH_TYPE_OPR", nativeQuery = true)
    public List<ProfColClsRuleId> QueryRuleTable();










}
