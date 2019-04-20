package com.domain.chevelle.dataclassrule;

import org.springframework.data.jpa.repository.Query;
import javax.persistence.EntityManager;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//       @NamedNativeQuery(name="ProfColClsRuleId.getruleresult", resultSetMapping = "RuleResult",
 //       query="select DATA_CLS_NM as DataClsNm, MATCH_TYPE_OPR as MatchTypeOpr, MATCH_NM as MatchNm from CHEVELLE.PROF_COL_CLS_RULE")

@Entity

@SqlResultSetMapping(name="ProfColClsRuleId.QueryTypeInMapping",
        classes = @ConstructorResult(
                targetClass = ProfColClsRuleId.class,
                columns = {
                        @ColumnResult(name = "DATA_CLS_NM"),
                        @ColumnResult(name = "MATCH_TYPE_OPR"),
                        @ColumnResult(name = "MATCH_NM")}))



public class ProfColClsRuleId implements Serializable {

    @Id
    private String dataclsNm;
    @Id
    private String matchtypeOpr;
    @Id
    private String matchNm;

    public ProfColClsRuleId() {

    }

    public String getDataclsNm() {
        return dataclsNm;
    }

    public void setDataclsNm(String dataclsNm) {
        this.dataclsNm = dataclsNm;
    }

    public String getMatchtypeOpr() {
        return matchtypeOpr;
    }

    public void setMatchtypeOpr(String matchtypeOpr) {
        this.matchtypeOpr = matchtypeOpr;
    }

    public String getMatchNm() {
        return matchNm;
    }

    public void setMatchNm(String matchNm) {
        this.matchNm = matchNm;
    }

    public ProfColClsRuleId(String dataclsNm, String matchtypeOpr, String matchNm) {
        this.dataclsNm = dataclsNm;
        this.matchtypeOpr = matchtypeOpr;
        this.matchNm = matchNm;
    }
}

