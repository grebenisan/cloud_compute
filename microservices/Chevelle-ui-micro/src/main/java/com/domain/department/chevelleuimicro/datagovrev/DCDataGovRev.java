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
public class DCDataGovRev {

    @Column(name="DG_STATUS")
    private String dg_status;

    @Column(name="TABLE_NM")
    private String table_nm;

    @Column(name="COLUMN_NM")
    private String column_nm;

    @Column(name="SCHEMA_NM")
    private String schema_nm;

    @Column(name="DB_NM")
    private String db_nm;

    @Column(name="DATA_CLS_NM")
    private String data_cls_nm;

    @Column(name="PROF_START_TS", nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")
    private Timestamp prof_start_ts;

    @Column(name="PROF_END_TS", nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")
    private Timestamp prof_end_ts;

    @Column(name="TOT_ROW_CNT", nullable = true)
    private Long tot_row_cnt;

    @Column(name="SAMPLE_ROW_CNT", nullable = true)
    private Long sample_row_cnt;

    @Column(name="COL_VAL_UNIQ_CNT", nullable = true)
    private Long col_val_uniq_cnt;

    @Column(name="COL_VAL_DATA_CLS_CNT", nullable = true)
    private Long col_val_data_cls_cnt;

    @Column(name="COL_VAL_DATA_CLS_PERCENT", nullable = true)
    private Long col_val_data_cls_percent;

    @Column(name="COL_MAX_VAL", nullable = true)
    private String col_max_val;

    @Column(name="COL_MIN_VAL", nullable = true)
    private String col_min_val;

    @Column(name = "COL_AVG_LEN", nullable = true)
    private Long col_avg_len;

    @Column(name = "APPL_REGEX_STR", nullable = true)
    private String appl_regex_str;

    @Column(name = "COL_ID")
    @Id
    private String col_id;

    @Column(name="TABLE_ID")
    private String table_id;

    @Column(name="BATCH_ID")
    @Id
    private Long batch_id;

    @Column(name = "CRT_BY", nullable = true)
    private String crt_by;

    @Column(name="CRT_TS")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")
    private Timestamp crt_ts;

    @Column(name ="DG_UPD_BY", nullable = true)
    private String dg_upd_by;

    @Column(name = "DG_UPD_TS", nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")
    private Timestamp dg_upd_ts;

    @Column(name="ROW_CNT")
    private Long row_cnt;

    public DCDataGovRev() {

    }

    public DCDataGovRev(String dg_status, String table_nm, String column_nm, String schema_nm, String db_nm, String data_cls_nm, Timestamp prof_start_ts, Timestamp prof_end_ts, Long tot_row_cnt, Long sample_row_cnt, Long col_val_uniq_cnt, Long col_val_data_cls_cnt, Long col_val_data_cls_percent, String col_max_val, String col_min_val, Long col_avg_len, String appl_regex_str, String col_id, String table_id, Long batch_id, String crt_by, Timestamp crt_ts, String dg_upd_by, Timestamp dg_upd_ts, Long row_cnt) {
        this.dg_status = dg_status;
        this.table_nm = table_nm;
        this.column_nm = column_nm;
        this.schema_nm = schema_nm;
        this.db_nm = db_nm;
        this.data_cls_nm = data_cls_nm;
        this.prof_start_ts = prof_start_ts;
        this.prof_end_ts = prof_end_ts;
        this.tot_row_cnt = tot_row_cnt;
        this.sample_row_cnt = sample_row_cnt;
        this.col_val_uniq_cnt = col_val_uniq_cnt;
        this.col_val_data_cls_cnt = col_val_data_cls_cnt;
        this.col_val_data_cls_percent = col_val_data_cls_percent;
        this.col_max_val = col_max_val;
        this.col_min_val = col_min_val;
        this.col_avg_len = col_avg_len;
        this.appl_regex_str = appl_regex_str;
        this.col_id = col_id;
        this.table_id = table_id;
        this.batch_id = batch_id;
        this.crt_by = crt_by;
        this.crt_ts = crt_ts;
        this.dg_upd_by = dg_upd_by;
        this.dg_upd_ts = dg_upd_ts;
        this.row_cnt = row_cnt;
    }

    public String getDg_status() {
        return dg_status;
    }

    public void setDg_status(String dg_status) {
        this.dg_status = dg_status;
    }

    public String getTable_nm() {
        return table_nm;
    }

    public void setTable_nm(String table_nm) {
        this.table_nm = table_nm;
    }

    public String getColumn_nm() {
        return column_nm;
    }

    public void setColumn_nm(String column_nm) {
        this.column_nm = column_nm;
    }

    public String getSchema_nm() {
        return schema_nm;
    }

    public void setSchema_nm(String schema_nm) {
        this.schema_nm = schema_nm;
    }

    public String getDb_nm() {
        return db_nm;
    }

    public void setDb_nm(String db_nm) {
        this.db_nm = db_nm;
    }

    public String getData_cls_nm() {
        return data_cls_nm;
    }

    public void setData_cls_nm(String data_cls_nm) {
        this.data_cls_nm = data_cls_nm;
    }

    public Timestamp getProf_start_ts() {
        return prof_start_ts;
    }

    public void setProf_start_ts(Timestamp prof_start_ts) {
        this.prof_start_ts = prof_start_ts;
    }

    public Timestamp getProf_end_ts() {
        return prof_end_ts;
    }

    public void setProf_end_ts(Timestamp prof_end_ts) {
        this.prof_end_ts = prof_end_ts;
    }

    public Long getTot_row_cnt() {
        return tot_row_cnt;
    }

    public void setTot_row_cnt(Long tot_row_cnt) {
        this.tot_row_cnt = tot_row_cnt;
    }

    public Long getSample_row_cnt() {
        return sample_row_cnt;
    }

    public void setSample_row_cnt(Long sample_row_cnt) {
        this.sample_row_cnt = sample_row_cnt;
    }

    public Long getCol_val_uniq_cnt() {
        return col_val_uniq_cnt;
    }

    public void setCol_val_uniq_cnt(Long col_val_uniq_cnt) {
        this.col_val_uniq_cnt = col_val_uniq_cnt;
    }

    public Long getCol_val_data_cls_cnt() {
        return col_val_data_cls_cnt;
    }

    public void setCol_val_data_cls_cnt(Long col_val_data_cls_cnt) {
        this.col_val_data_cls_cnt = col_val_data_cls_cnt;
    }

    public Long getCol_val_data_cls_percent() {
        return col_val_data_cls_percent;
    }

    public void setCol_val_data_cls_percent(Long col_val_data_cls_percent) {
        this.col_val_data_cls_percent = col_val_data_cls_percent;
    }

    public String getCol_max_val() {
        return col_max_val;
    }

    public void setCol_max_val(String col_max_val) {
        this.col_max_val = col_max_val;
    }

    public String getCol_min_val() {
        return col_min_val;
    }

    public void setCol_min_val(String col_min_val) {
        this.col_min_val = col_min_val;
    }

    public Long getCol_avg_len() {
        return col_avg_len;
    }

    public void setCol_avg_len(Long col_avg_len) {
        this.col_avg_len = col_avg_len;
    }

    public String getAppl_regex_str() {
        return appl_regex_str;
    }

    public void setAppl_regex_str(String appl_regex_str) {
        this.appl_regex_str = appl_regex_str;
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

    public Long getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(Long batch_id) {
        this.batch_id = batch_id;
    }

    public String getCrt_by() {
        return crt_by;
    }

    public void setCrt_by(String crt_by) {
        this.crt_by = crt_by;
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

    public Timestamp getDg_upd_ts() {
        return dg_upd_ts;
    }

    public void setDg_upd_ts(Timestamp dg_upd_ts) {
        this.dg_upd_ts = dg_upd_ts;
    }

    public Long getRow_cnt() {
        return row_cnt;
    }

    public void setRow_cnt(Long row_cnt) {
        this.row_cnt = row_cnt;
    }
}
