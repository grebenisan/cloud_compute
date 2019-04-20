/**********************************************************
 * class:		DCWorkUnitReturn
 * Description:	The class modeling the output (return) of the getDCWorkUnit end-point
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle.dataclass;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "hive_schema", "table_name", "hdfs_location", "col_name", "data_cls_nm", "regex_str", "table_id", "col_id", "batch_id", "srvr_nm", "fail_cnt" })
public class DCWorkUnitReturn {
	
 	private String hive_schema;
 	private String table_name;
 	private String hdfs_location;
 	private String col_name;
 	private String data_cls_nm;
 	private String regex_str;
 	private String table_id;
 	private String col_id;
 	private Long batch_id;
 	private String srvr_nm;
 	private Long fail_cnt;
 	
 	
	public DCWorkUnitReturn() {
	}


	public DCWorkUnitReturn(String hive_schema, String table_name, String hdfs_location, String col_name,
			String data_cls_nm, String regex_str, String table_id, String col_id, Long batch_id, String srvr_nm,
			Long fail_cnt) 
	{
		this.hive_schema = hive_schema;
		this.table_name = table_name;
		this.hdfs_location = hdfs_location;
		this.col_name = col_name;
		this.data_cls_nm = data_cls_nm;
		this.regex_str = regex_str;
		this.table_id = table_id;
		this.col_id = col_id;
		this.batch_id = batch_id;
		this.srvr_nm = srvr_nm;
		this.fail_cnt = fail_cnt;
	}
 	
	
	public DCWorkUnitReturn(DCWorkUnit dcWorkUnit) 
	{
		this.hive_schema = dcWorkUnit.getHive_schema();
		this.table_name = dcWorkUnit.getHive_table_name();
		this.hdfs_location = dcWorkUnit.getHdfs_path();
		this.col_name = dcWorkUnit.getColumn_name();
		this.data_cls_nm = dcWorkUnit.getData_cls_nm();
		this.regex_str = dcWorkUnit.getRegex_str();
		this.table_id = dcWorkUnit.getTable_id();
		this.col_id = dcWorkUnit.getCol_id();
		this.batch_id = dcWorkUnit.getBatch_id();
		this.srvr_nm = dcWorkUnit.getSrvr_nm();
		this.fail_cnt = dcWorkUnit.getFail_cnt();		
	
	}


	public String getHive_schema() {
		return hive_schema;
	}


	public void setHive_schema(String hive_schema) {
		this.hive_schema = hive_schema;
	}


	public String getTable_name() {
		return table_name;
	}


	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}


	public String getHdfs_location() {
		return hdfs_location;
	}


	public void setHdfs_location(String hdfs_location) {
		this.hdfs_location = hdfs_location;
	}


	public String getCol_name() {
		return col_name;
	}


	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}


	public String getData_cls_nm() {
		return data_cls_nm;
	}


	public void setData_cls_nm(String data_cls_nm) {
		this.data_cls_nm = data_cls_nm;
	}


	public String getRegex_str() {
		return regex_str;
	}


	public void setRegex_str(String regex_str) {
		this.regex_str = regex_str;
	}


	public String getTable_id() {
		return table_id;
	}


	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}


	public String getCol_id() {
		return col_id;
	}


	public void setCol_id(String col_id) {
		this.col_id = col_id;
	}


	public Long getBatch_id() {
		return batch_id;
	}


	public void setBatch_id(Long batch_id) {
		this.batch_id = batch_id;
	}


	public String getSrvr_nm() {
		return srvr_nm;
	}


	public void setSrvr_nm(String srvr_nm) {
		this.srvr_nm = srvr_nm;
	}


	public Long getFail_cnt() {
		return fail_cnt;
	}


	public void setFail_cnt(Long fail_cnt) {
		this.fail_cnt = fail_cnt;
	} 	
 	
 	
 	
 	
 	
}
