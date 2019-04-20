/**********************************************************
 * class:		ProfColStat
 * Description:	Entity modeling the PROF_COL_STAT table
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle.basicstats;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="PROF_COL_STAT", schema="CHEVELLE")
@IdClass(ProfColStatId.class)
public class ProfColStat {

	@Column(name = "COL_ID", nullable = false)
	@Id private String col_id;
	
	@Column(name = "CRT_TS", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")    // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	@Id private Timestamp crt_ts;
	
	@Column(name = "PROF_START_TS", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")    // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp prof_start_ts;
	
	@Column(name = "TABLE_ID", nullable = true)
	private String table_id;
	
	@Column(name = "PROF_END_TS", nullable = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")    // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp prof_end_ts;
	
	@Column(name = "BATCH_ID", nullable = true)
	private long batch_id;
	
	@Column(name = "TOT_ROW_CNT", nullable = true)
	private long tot_row_cnt;
	
	@Column(name = "COL_VAL_UNIQ_CNT", nullable = true)
	private long col_val_uniq_cnt;
	
	@Column(name = "COL_VAL_NULL_CNT", nullable = true)
	private long col_val_null_cnt;
	
	@Column(name = "COL_MAX_VAL", nullable = true)
	private String col_max_val;
	
	@Column(name = "COL_MIN_VAL", nullable = true)
	private String col_min_val;
	
	@Column(name = "COL_MEAN_VAL", nullable = true)
	private String col_mean_val;
	
	@Column(name = "COL_STDEV_VAL", nullable = true)
	private Double col_stdev_val;
	
	@Column(name = "COL_MAX_LEN", nullable = true)
	private long col_max_len;
	
	@Column(name = "COL_MIN_LEN", nullable = true)
	private long col_min_len;
	
	@Column(name = "PROF_CMT", nullable = true)
	private String prof_cmt;
	
	@Column(name = "ORIG_FILE_NM", nullable = true)
	private String orig_file_nm;
	
	@Column(name = "SPLIT_FILE_NM", nullable = true)
	private String split_file_nm;
	
	@Column(name = "CRT_BY", nullable = true)
	private String crt_by;

	
	
	public ProfColStat() {
		super();
	}



	public ProfColStat(String col_id, Timestamp crt_ts, Timestamp prof_start_ts, String table_id, Timestamp prof_end_ts,
			long batch_id, long tot_row_cnt, long col_val_uniq_cnt, long col_val_null_cnt, String col_max_val,
			String col_min_val, String col_mean_val, Double col_stdev_val, long col_max_len, long col_min_len,
			String prof_cmt, String orig_file_nm, String split_file_nm, String crt_by) {
		super();
		this.col_id = col_id;
		this.crt_ts = crt_ts;
		this.prof_start_ts = prof_start_ts;
		this.table_id = table_id;
		this.prof_end_ts = prof_end_ts;
		this.batch_id = batch_id;
		this.tot_row_cnt = tot_row_cnt;
		this.col_val_uniq_cnt = col_val_uniq_cnt;
		this.col_val_null_cnt = col_val_null_cnt;
		this.col_max_val = col_max_val;
		this.col_min_val = col_min_val;
		this.col_mean_val = col_mean_val;
		this.col_stdev_val = col_stdev_val;
		this.col_max_len = col_max_len;
		this.col_min_len = col_min_len;
		this.prof_cmt = prof_cmt;
		this.orig_file_nm = orig_file_nm;
		this.split_file_nm = split_file_nm;
		this.crt_by = crt_by;
	}



	public String getCol_id() {
		return col_id;
	}

	public void setCol_id(String col_id) {
		this.col_id = col_id;
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

	public String getTable_id() {
		return table_id;
	}

	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}

	public Timestamp getProf_end_ts() {
		return prof_end_ts;
	}

	public void setProf_end_ts(Timestamp prof_end_ts) {
		this.prof_end_ts = prof_end_ts;
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

	public long getCol_val_uniq_cnt() {
		return col_val_uniq_cnt;
	}

	public void setCol_val_uniq_cnt(long col_val_uniq_cnt) {
		this.col_val_uniq_cnt = col_val_uniq_cnt;
	}

	public long getCol_val_null_cnt() {
		return col_val_null_cnt;
	}

	public void setCol_val_null_cnt(long col_val_null_cnt) {
		this.col_val_null_cnt = col_val_null_cnt;
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


	
	
	public String getCol_mean_val() {
		return col_mean_val;
	}



	public void setCol_mean_val(String col_mean_val) {
		this.col_mean_val = col_mean_val;
	}



	public Double getCol_stdev_val() {
		return col_stdev_val;
	}

	public void setCol_stdev_val(Double col_stdev_val) {
		this.col_stdev_val = col_stdev_val;
	}

	public long getCol_max_len() {
		return col_max_len;
	}

	public void setCol_max_len(long col_max_len) {
		this.col_max_len = col_max_len;
	}

	public long getCol_min_len() {
		return col_min_len;
	}

	public void setCol_min_len(long col_min_len) {
		this.col_min_len = col_min_len;
	}

	public String getProf_cmt() {
		return prof_cmt;
	}

	public void setProf_cmt(String prof_cmt) {
		this.prof_cmt = prof_cmt;
	}

	public String getOrig_file_nm() {
		return orig_file_nm;
	}

	public void setOrig_file_nm(String orig_file_nm) {
		this.orig_file_nm = orig_file_nm;
	}

	public String getSplit_file_nm() {
		return split_file_nm;
	}

	public void setSplit_file_nm(String split_file_nm) {
		this.split_file_nm = split_file_nm;
	}

	public String getCrt_by() {
		return crt_by;
	}

	public void setCrt_by(String crt_by) {
		this.crt_by = crt_by;
	}
	
	
	
}
