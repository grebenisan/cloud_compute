/**********************************************************
 * class:		UpdateProfColClsQue
 * Description:	The class modeling the input object of the "update data class que" end-point
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle.dataclass;

public class UpdateProfColClsQue {

	private String col_id;
	private String data_cls_nm;
	private long batch_id;
	private long task_stat_cd;	//1=running, 2=success, 3=failed
	private long fail_cnt;
	
	public UpdateProfColClsQue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateProfColClsQue(String col_id, String data_cls_nm, long batch_id, long task_stat_cd, long fail_cnt) {
		super();
		this.col_id = col_id;
		this.data_cls_nm = data_cls_nm;
		this.batch_id = batch_id;
		this.task_stat_cd = task_stat_cd;
		this.fail_cnt = fail_cnt;
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

	public long getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(long batch_id) {
		this.batch_id = batch_id;
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

	@Override
	public String toString() {
		return "UpdateProfColClsQue {col_id=" + col_id + ", data_cls_nm=" + data_cls_nm + ", batch_id=" + batch_id
				+ ", task_stat_cd=" + task_stat_cd + ", fail_cnt=" + fail_cnt + "}";
	}

	
	
}
