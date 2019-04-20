/**********************************************************
 * class:		UpdateProfTableStatQue
 * Description:	The class modeling the input of the "update basic stats que" end-point
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/


package com.domain.chevelle.basicstats;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UpdateProfTableStatQue {

	
 	private String table_id;
 	private long batch_id;
 	private String srvr_nm;
 	private long task_stat_cd;	//1=running, 2=success, 3=failed
 	private long fail_cnt;
 	private String upd_by;
 	
 	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")
 	private Timestamp upd_ts;
 	
	public UpdateProfTableStatQue() {
		super();
	}

	public UpdateProfTableStatQue(String table_id, long batch_id, String srvr_nm, long task_stat_cd, long fail_cnt,
			String upd_by, Timestamp upd_ts) {
		super();
		this.table_id = table_id;
		this.batch_id = batch_id;
		this.srvr_nm = srvr_nm;
		this.task_stat_cd = task_stat_cd;
		this.fail_cnt = fail_cnt;
		this.upd_by = upd_by;
		this.upd_ts = upd_ts;
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

	public String getSrvr_nm() {
		return srvr_nm;
	}

	public void setSrvr_nm(String srvr_nm) {
		this.srvr_nm = srvr_nm;
	}

	public long getTask_stat_cd() {
		return task_stat_cd;
	}

	public void setTask_stat_cd(long task_stat_cd) {
		this.task_stat_cd = task_stat_cd;
	}

	public long getFail_cnt() {
		return fail_cnt;
	}

	public void setFail_cnt(long fail_cnt) {
		this.fail_cnt = fail_cnt;
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

	@Override
	public String toString() {
		return "UpdateProfTableStatQue {table_id=" + table_id + ", batch_id=" + batch_id + ", srvr_nm=" + srvr_nm
				+ ", task_stat_cd=" + task_stat_cd + ", fail_cnt=" + fail_cnt + ", upd_by=" + upd_by + ", upd_ts="
				+ upd_ts + "}";
	}
 	
 	
 	

 	
}
