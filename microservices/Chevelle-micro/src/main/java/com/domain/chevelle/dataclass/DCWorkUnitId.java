/**********************************************************
 * class:		DCWorkUnitId
 * Description:	The Id class of the DCWorkUnit entity
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle.dataclass;

import java.io.Serializable;

public class DCWorkUnitId implements Serializable {

	private String col_id;
	private Long batch_id;
	
	public DCWorkUnitId() {
	}

	public DCWorkUnitId(String col_id, Long batch_id) {
		super();
		this.col_id = col_id;
		this.batch_id = batch_id;
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

	@Override
	public String toString() {
		return "DCWorkUnitId {col_id=" + col_id + ", batch_id=" + batch_id + "}";
	}
	
	
	
	
	
}
