/**********************************************************
 * class:		ProfColStatId
 * Description:	The Id class of the ProfColStat entity
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle.basicstats;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

public class ProfColStatId implements Serializable
{
	private String col_id;
	private Timestamp crt_ts;
	

	public ProfColStatId() {
	}
	
	public ProfColStatId(String col_id, Timestamp crt_ts) {
		super();
		this.col_id = col_id;
		this.crt_ts = crt_ts;
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


	

}
