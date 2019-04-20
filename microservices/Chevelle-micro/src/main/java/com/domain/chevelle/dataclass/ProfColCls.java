/**********************************************************
 * class:		ProfColCls
 * Description:	Entity class modeling the data class output table PROF_COL_CLS
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/


package com.domain.chevelle.dataclass;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="PROF_COL_CLS", schema="CHEVELLE")
@IdClass(ProfColClsId.class)
public class ProfColCls {

	@Column(name = "COL_ID", nullable = false)
	@Id private String col_id;
	
	@Column(name = "DATA_CLS_NM", nullable = false)
	@Id private String data_cls_nm;
	
	@Column(name = "CRT_TS", nullable = false)
	@Id 
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")    // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp crt_ts;
	
	@Column(name = "PROF_START_TS", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")    // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp prof_start_ts;
	
	@Column(name = "PROF_END_TS", nullable = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")    // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp prof_end_ts;
	
	@Column(name = "TABLE_ID", nullable = true)
	private String table_id;
	
	@Column(name = "BATCH_ID", nullable = true)
	private long batch_id;
	
	@Column(name = "TOT_ROW_CNT", nullable = true)
	private long tot_row_cnt;
	
	@Column(name = "SAMPLE_ROW_CNT", nullable = true)
	private long sample_row_cnt;
	
	@Column(name = "COL_VAL_UNIQ_CNT", nullable = true)
	private long col_val_uniq_cnt;
	
	@Column(name = "COL_VAL_DATA_CLS_CNT", nullable = true)
	private long col_val_data_cls_cnt;
	
	@Column(name = "COL_MAX_VAL", nullable = true)
	private String col_max_val;
	
	@Column(name = "COL_MIN_VAL", nullable = true)
	private String col_min_val;
	
	@Column(name = "COL_AVG_LEN", nullable = true)
	private long col_avg_len;
	
	@Column(name = "APPL_REGEX_STR", nullable = true)
	private String appl_regex_str;
	
	@Column(name = "CRT_BY", nullable = true)
	private String crt_by;

	
	public ProfColCls() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ProfColCls(String col_id, String data_cls_nm, Timestamp crt_ts, Timestamp prof_start_ts,
			Timestamp prof_end_ts, String table_id, long batch_id, long tot_row_cnt, long sample_row_cnt,
			long col_val_uniq_cnt, long col_val_data_cls_cnt, String col_max_val, String col_min_val, long col_avg_len,
			String appl_regex_str, String crt_by) {
		super();
		this.col_id = col_id;
		this.data_cls_nm = data_cls_nm;
		this.crt_ts = crt_ts;
		this.prof_start_ts = prof_start_ts;
		this.prof_end_ts = prof_end_ts;
		this.table_id = table_id;
		this.batch_id = batch_id;
		this.tot_row_cnt = tot_row_cnt;
		this.sample_row_cnt = sample_row_cnt;
		this.col_val_uniq_cnt = col_val_uniq_cnt;
		this.col_val_data_cls_cnt = col_val_data_cls_cnt;
		this.col_max_val = col_max_val;
		this.col_min_val = col_min_val;
		this.col_avg_len = col_avg_len;
		this.appl_regex_str = appl_regex_str;
		this.crt_by = crt_by;
	}


	public String getCol_id() {
		return col_id;
	}


	public void setCol_id(String col_id) {
		this.col_id = col_id;
	}


	public String getData_cls_nm() {
		return data_cls_nm;
	}


	public void setData_cls_nm(String data_cls_nm) {
		this.data_cls_nm = data_cls_nm;
	}


	public Timestamp getCrt_ts() {
		return crt_ts;
	}


	public void setCrt_ts(Timestamp crt_ts) {
		this.crt_ts = crt_ts;
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


	public String getTable_id() {
		return table_id;
	}


	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}


	public long getBatch_id() {
		return batch_id;
	}


	public void setBatch_id(long batch_id) {
		this.batch_id = batch_id;
	}


	public long getTot_row_cnt() {
		return tot_row_cnt;
	}


	public void setTot_row_cnt(long tot_row_cnt) {
		this.tot_row_cnt = tot_row_cnt;
	}


	public long getSample_row_cnt() {
		return sample_row_cnt;
	}


	public void setSample_row_cnt(long sample_row_cnt) {
		this.sample_row_cnt = sample_row_cnt;
	}


	public long getCol_val_uniq_cnt() {
		return col_val_uniq_cnt;
	}


	public void setCol_val_uniq_cnt(long col_val_uniq_cnt) {
		this.col_val_uniq_cnt = col_val_uniq_cnt;
	}


	public long getCol_val_data_cls_cnt() {
		return col_val_data_cls_cnt;
	}


	public void setCol_val_data_cls_cnt(long col_val_data_cls_cnt) {
		this.col_val_data_cls_cnt = col_val_data_cls_cnt;
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


	public long getCol_avg_len() {
		return col_avg_len;
	}


	public void setCol_avg_len(long col_avg_len) {
		this.col_avg_len = col_avg_len;
	}


	public String getAppl_regex_str() {
		return appl_regex_str;
	}


	public void setAppl_regex_str(String appl_regex_str) {
		this.appl_regex_str = appl_regex_str;
	}


	public String getCrt_by() {
		return crt_by;
	}


	public void setCrt_by(String crt_by) {
		this.crt_by = crt_by;
	}

	
	
	
}
