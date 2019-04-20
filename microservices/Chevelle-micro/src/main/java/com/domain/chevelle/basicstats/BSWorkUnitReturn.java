/**********************************************************
 * class:		BSWorkUnitReturn
 * Description:	The class modeling the return of the basic stats "get work unit" end-point
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle.basicstats;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "hive_schema", "table_name", "hdfs_location", "table_id", "batch_id", "srvr_nm", "fail_cnt" })
public class BSWorkUnitReturn {

		private String hive_schema;
		private String table_name;
		private String hdfs_location;
		private String table_id;
		private Long batch_id;
		private String srvr_nm;
		private Long fail_cnt;
		
		
		public BSWorkUnitReturn() {
		}


		public BSWorkUnitReturn(String hive_schema, String table_name, String hdfs_location, String table_id,
				Long batch_id, String srvr_nm, Long fail_cnt) {
			this.hive_schema = hive_schema;
			this.table_name = table_name;
			this.hdfs_location = hdfs_location;
			this.table_id = table_id;
			this.batch_id = batch_id;
			this.srvr_nm = srvr_nm;
			this.fail_cnt = fail_cnt;
		}
		

		public BSWorkUnitReturn(BSWorkUnit bsWorkUnit) {

			this.hive_schema = bsWorkUnit.getHive_schema();
			this.table_name = bsWorkUnit.getHive_table_name();
			this.hdfs_location = bsWorkUnit.getHdfs_path();
			this.table_id = bsWorkUnit.getTable_id();
			this.batch_id = bsWorkUnit.getBatch_id();
			this.srvr_nm = bsWorkUnit.getSrvr_nm();
			this.fail_cnt = bsWorkUnit.getFail_cnt();
			
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
