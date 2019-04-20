/**********************************************************
 * class:		ProfColClsId
 * Description:	The Id class of the ProfColCls entity
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/


package com.domain.chevelle.dataclass;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;


public class ProfColClsId implements Serializable {

	private String col_id;
	private String data_cls_nm;
	private Timestamp crt_ts;
	
	public ProfColClsId() {
		super();
	}

	public ProfColClsId(String col_id, String data_cls_nm, Timestamp crt_ts) {
		super();
		this.col_id = col_id;
		this.data_cls_nm = data_cls_nm;
		this.crt_ts = crt_ts;
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
	
	
}
