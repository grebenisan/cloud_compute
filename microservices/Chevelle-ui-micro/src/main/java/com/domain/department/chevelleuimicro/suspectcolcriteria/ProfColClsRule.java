package com.domain.department.chevelleuimicro.suspectcolcriteria;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name="PROF_COL_CLS_RULE", schema = "CHEVELLE")
public class ProfColClsRule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "CLS_RULE_SEQ", allocationSize = 1, name = "CUST_SEQ")
    @Column(name = "ID")
    private long id;

    @Column(name = "DATA_CLS_NM")
    @Size(max = 50)
    private String data_cls_nm;

    @Column(name = "MATCH_TYPE_OPR")
    @Size(max = 20)
    private String match_type_opr;

    @Column(name = "MATCH_NM")
    @Size(max = 100)
    private String match_nm;

    @Column(name = "UPD_BY")
    @Size(max = 100)
    private String upd_by;

    @Column(name = "UPD_TS")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")    // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp upd_ts;

    @Column(name = "UPD_RSN_TXT")
    @Size(max = 255)
    private String upd_rsn_txt;

    public ProfColClsRule() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData_cls_nm() {
        return data_cls_nm;
    }

    public void setData_cls_nm(String data_cls_nm) {
        this.data_cls_nm = data_cls_nm;
    }

    public String getMatch_type_opr() {
        return match_type_opr;
    }

    public void setMatch_type_opr(String match_type_opr) {
        this.match_type_opr = match_type_opr;
    }

    public String getMatch_nm() {
        return match_nm;
    }

    public void setMatch_nm(String match_nm) {
        this.match_nm = match_nm;
    }

    public String getUpd_by() {
        return upd_by;
    }

    public void setUpd_by(String upd_by) {
        this.upd_by = upd_by;
    }

    public Timestamp getUpd_ts() {
        return upd_ts;
    }

    public void setUpd_ts(Timestamp upd_ts) {
        this.upd_ts = upd_ts;
    }

    public String getUpd_rsn_txt() {
        return upd_rsn_txt;
    }

    public void setUpd_rsn_txt(String upd_rsn_txt) {
        this.upd_rsn_txt = upd_rsn_txt;
    }
}
