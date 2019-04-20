/**********************************************************
 * class:		BSWorkUnitId
 * Description:	The Id class of the BSWorkUnit entity
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle.basicstats;

import java.io.Serializable;

public class BSWorkUnitId implements Serializable 
{
	private String hive_schema;
	private String hive_table_name;
	private Long batch_id;

	
	public BSWorkUnitId() {
	}


	public BSWorkUnitId(String hive_schema, String hive_table_name, Long batch_id) {
		this.hive_schema = hive_schema;
		this.hive_table_name = hive_table_name;
		this.batch_id = batch_id;
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


	public Long getBatch_id() {
		return batch_id;
	}


	public void setBatch_id(Long batch_id) {
		this.batch_id = batch_id;
	}


	@Override
	public String toString() {
		return "BSWorkUnitId {hive_schema=" + hive_schema + ", hive_table_name=" + hive_table_name + ", batch_id="
				+ batch_id + "}";
	}


	
}
