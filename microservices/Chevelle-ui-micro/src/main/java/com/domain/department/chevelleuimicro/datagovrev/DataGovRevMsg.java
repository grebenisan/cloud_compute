package com.domain.department.chevelleuimicro.datagovrev;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Timestamp;

@Entity
@Immutable
@IdClass(DCDataGovRevId.class)
public class DataGovRevMsg {

    @Column(name="DG_STATUS")
    private String dg_status;

    @Column(name="DATA_CLS_NM")
    private String data_cls_nm;

    @Column(name = "COL_ID")
    @Id
    private String col_id;

    @Column(name="TABLE_ID")
    private String table_id;

    @Column(name="CRT_TS")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")
    private Timestamp crt_ts;

    @Column(name ="DG_UPD_BY")
    private String dg_upd_by;

    @Column(name = "BATCH_ID")
    @Id
    private Long batch_id;

    public DataGovRevMsg() {
    }

    public String getDg_status() {
        return dg_status;
    }

    public void setDg_status(String dg_status) {
        this.dg_status = dg_status;
    }

    public String getData_cls_nm() {
        return data_cls_nm;
    }

    public void setData_cls_nm(String data_cls_nm) {
        this.data_cls_nm = data_cls_nm;
    }

    public String getCol_id() {
        return col_id;
    }

    public void setCol_id(String col_id) {
        this.col_id = col_id;
    }

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public Timestamp getCrt_ts() {
        return crt_ts;
    }

    public void setCrt_ts(Timestamp crt_ts) {
        this.crt_ts = crt_ts;
    }

    public String getDg_upd_by() {
        return dg_upd_by;
    }

    public void setDg_upd_by(String dg_upd_by) {
        this.dg_upd_by = dg_upd_by;
    }

    public Long getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(Long batch_id) {
        this.batch_id = batch_id;
    }
}
