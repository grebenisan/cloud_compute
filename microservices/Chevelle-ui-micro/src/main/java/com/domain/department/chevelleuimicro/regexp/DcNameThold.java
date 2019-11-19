package com.domain.department.chevelleuimicro.regexp;

public class DcNameThold {
	
	private String data_cls_nm;
	private Float match_thold_pct;
	


	public DcNameThold() {
	}

	public DcNameThold(ProfDataClsRegex profDataClsRegex)
	{
		this.data_cls_nm = profDataClsRegex.getData_cls_nm();
		this.match_thold_pct = profDataClsRegex.getMatch_thold_pct();
	}
	

	public DcNameThold(String data_cls_nm, Float match_thold_pct) {
		super();
		this.data_cls_nm = data_cls_nm;
		this.match_thold_pct = match_thold_pct;
	}



	public String getData_cls_nm() {
		return data_cls_nm;
	}



	public void setData_cls_nm(String data_cls_nm) {
		this.data_cls_nm = data_cls_nm;
	}



	public Float getMatch_thold_pct() {
		return match_thold_pct;
	}



	public void setMatch_thold_pct(Float match_thold_pct) {
		this.match_thold_pct = match_thold_pct;
	}
	


}
