package com.domain.department.chevelleuimicro.regexp;


public class DcName {

	private String data_cls_nm;

	public DcName() {
	}
	
	public DcName(ProfDataClsRegex profDataClsRegex)
	{
		this.data_cls_nm = profDataClsRegex.getData_cls_nm();
	}

	public DcName(String data_cls_nm) {
		super();
		this.data_cls_nm = data_cls_nm;
	}

	public String getData_cls_nm() {
		return data_cls_nm;
	}

	public void setData_cls_nm(String data_cls_nm) {
		this.data_cls_nm = data_cls_nm;
	}
	
	
}
