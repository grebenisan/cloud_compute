package com.domain.chevelle.dataclassrule;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="PROF_COL_CLS_RULE", schema="CHEVELLE")
@IdClass(ProfColClsRule.class)

public class ProfColClsRule implements Serializable {
    @Column(name="DATA_CLS_NM")
            @Id
            private String DataClsNm;

    @Column(name="MATCH_TYPE_OPR")
            private String MatchTypeOpr;

    @Column(name="MATCH_NM")
            private String MatchNm;

    @Column(name="UPD_BY")
            private String UpdBy;

    @Column(name="UPD_TS")
            private String UpdTs;

    @Column(name="UPD_RSN_TXT")
            private String UpdRsnTxt;

    public String getDataClsNm() {
        return DataClsNm;
    }

    public void setDataClsNm(String dataClsNm) {
        DataClsNm = dataClsNm;
    }

    public String getMatchTypeOpr() {
        return MatchTypeOpr;
    }

    public void setMatchTypeOpr(String matchTypeOpr) {
        MatchTypeOpr = matchTypeOpr;
    }

    public String getMatchNm() {
        return MatchNm;
    }

    public void setMatchNm(String matchNm) {
        MatchNm = matchNm;
    }

    public String getUpdBy() {
        return UpdBy;
    }

    public void setUpdBy(String updBy) {
        UpdBy = updBy;
    }

    public String getUpdTs() {
        return UpdTs;
    }

    public void setUpdTs(String updTs) {
        UpdTs = updTs;
    }

    public String getUpdRsnTxt() {
        return UpdRsnTxt;
    }

    public void setUpdRsnTxt(String updRsnTxt) {
        UpdRsnTxt = updRsnTxt;
    }

    public ProfColClsRule(String dataClsNm, String matchTypeOpr, String matchNm, String updBy, String updTs, String updRsnTxt) {
        DataClsNm = dataClsNm;
        MatchTypeOpr = matchTypeOpr;
        MatchNm = matchNm;
        UpdBy = updBy;
        UpdTs = updTs;
        UpdRsnTxt = updRsnTxt;
    }
}
