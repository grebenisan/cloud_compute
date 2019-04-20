/**********************************************************
 * class:		BSWorkUnit
 * Description:	Entity used to map the return record list of the basic stats "get work unit" stored procedure
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

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@Entity
@Immutable
@IdClass(BSWorkUnitId.class)
public class BSWorkUnit {

	@Column(name = "HDFS_PATH")
	private String hdfs_path;
	
	@Column(name = "HIVE_SCHEMA")
	@Id 
	private String hive_schema;
	
	@Column(name = "HIVE_TABLE_NAME")
	private String hive_table_name;
	
	@Column(name = "TABLE_ID")
	@Id 
	private String table_id;
	
	@Column(name = "CRT_TS")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")
	private Timestamp crt_ts;	
	
	@Column(name = "BATCH_ID")
	@Id 
	private Long batch_id;
	
	@Column(name = "PRTY_NBR")
	private Long prty_nbr;
	
	@Column(name = "SRVR_NM")
	private String srvr_nm;
	
	@Column(name = "CRT_BY")
	private String crt_by;
	
	@Column(name = "UPD_BY")
	private String upd_by;
	
	@Column(name = "UPD_TS")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")
	private Timestamp upd_ts;	
	
	@Column(name = "TASK_STAT_CD")
	private Long task_stat_cd;
	
	@Column(name = "FAIL_CNT")
	private Long fail_cnt;
	
	
	public BSWorkUnit() {
	}


	public BSWorkUnit(String hdfs_path, String hive_schema, String hive_table_name, String table_id, Timestamp crt_ts,
			Long batch_id, Long prty_nbr, String srvr_nm, String crt_by, String upd_by, Timestamp upd_ts,
			Long task_stat_cd, Long fail_cnt) {
		this.hdfs_path = hdfs_path;
		this.hive_schema = hive_schema;
		this.hive_table_name = hive_table_name;
		this.table_id = table_id;
		this.crt_ts = crt_ts;
		this.batch_id = batch_id;
		this.prty_nbr = prty_nbr;
		this.srvr_nm = srvr_nm;
		this.crt_by = crt_by;
		this.upd_by = upd_by;
		this.upd_ts = upd_ts;
		this.task_stat_cd = task_stat_cd;
		this.fail_cnt = fail_cnt;
	}


	public String getHdfs_path() {
		return hdfs_path;
	}


	public void setHdfs_path(String hdfs_path) {
		this.hdfs_path = hdfs_path;
	}


	public String getHive_schema() {
		return hive_schema;
	}


	public void setHive_schema(String hive_schema) {
		this.hive_schema = hive_schema;
	}


	public String getHive_table_name() {
		return hive_table_name;
	}


	public void setHive_table_name(String hive_table_name) {
		this.hive_table_name = hive_table_name;
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


	public Long getBatch_id() {
		return batch_id;
	}


	public void setBatch_id(Long batch_id) {
		this.batch_id = batch_id;
	}


	public Long getPrty_nbr() {
		return prty_nbr;
	}


	public void setPrty_nbr(Long prty_nbr) {
		this.prty_nbr = prty_nbr;
	}


	public String getSrvr_nm() {
		return srvr_nm;
	}


	public void setSrvr_nm(String srvr_nm) {
		this.srvr_nm = srvr_nm;
	}


	public String getCrt_by() {
		return crt_by;
	}


	public void setCrt_by(String crt_by) {
		this.crt_by = crt_by;
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


	public Long getTask_stat_cd() {
		return task_stat_cd;
	}


	public void setTask_stat_cd(Long task_stat_cd) {
		this.task_stat_cd = task_stat_cd;
	}


	public Long getFail_cnt() {
		return fail_cnt;
	}


	public void setFail_cnt(Long fail_cnt) {
		this.fail_cnt = fail_cnt;
	}


	
	
	
}
