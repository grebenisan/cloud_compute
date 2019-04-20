package com.domain.chevelle.dataclassrule;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProfColClsRuleRepo extends JpaRepository<ProfColClsRule, ProfColClsRuleId> {


    ///@Query("select DataClsNm, MatchTypeOpr, MatchNm from ProfColClsRule")
    @Query(value ="SELECT DATA_CLS_NM || ' ' || MATCH_TYPE_OPR || '(' || listagg(chr(39) || MATCH_NM || chr(39), ',') within group (order by MATCH_NM) || ')' as COL2 FROM " +
            " (select * from CHEVELLE.PROF_COL_CLS_RULE where lower(MATCH_TYPE_OPR) in ('not in', 'in')) a " +
            "group by DATA_CLS_NM, MATCH_TYPE_OPR", nativeQuery = true)
   public List<String> QueryRuleTable();


    @Query(value ="select DATA_CLS_NM, MATCH_TYPE_OPR, MATCH_NM from(" +
            "SELECT DATA_CLS_NM, MATCH_TYPE_OPR, listagg(MATCH_NM, ',''' ) within group (order by MATCH_NM) as MATCH_NM FROM " +
            "(select * from CHEVELLE.PROF_COL_CLS_RULE where lower(MATCH_TYPE_OPR) in ('not in', 'in'))" +
            "group by DATA_CLS_NM, MATCH_TYPE_OPR)",nativeQuery = true)
    public List<String> QueryRuleTable2();

    }


