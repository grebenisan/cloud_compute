package com.domain.department.chevelleuimicro.regexp;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.IdClass;
import java.sql.Timestamp;
import java.lang.Float;


@Entity
@Immutable
public class ProfDataClsRegex {

	@Id
    @Column(name="DATA_CLS_NM")
    private String data_cls_nm;
	
    @Column(name="REGEX_STR", nullable = true)
    private String regex_str;
    
    @Column(name="CRT_BY", nullable = true)
    private String crt_by;    
    
    @Column(name="UPD_BY", nullable = true)
    private String upd_by;        
    
    @Column(name="UPD_TS", nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")
    private Timestamp upd_ts;          
    
    @Column(name="CRT_TS", nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Detroit")
    private Timestamp crt_ts;            
    
    @Column(name="OBJ_STAT_CD", nullable = true)
    private Float obj_stat_cd;      
    
    @Column(name="PRTCT_TYPE_CD", nullable = true)
    private String prtct_type_cd;          
    
    @Column(name="ENCRYPT_ID_CD", nullable = true)
    private String encrypt_id_cd;        
    
    @Column(name="ENCRYPT_FMT_CD", nullable = true)
    private String encrypt_fmt_cd;        
    
    @Column(name="MATCH_THOLD_PCT", nullable = true)
    private Float match_thold_pct;
    
    @Column(name="ROW_CNT")
    private int row_cnt;

    
	public ProfDataClsRegex() {
	}


	public ProfDataClsRegex(String data_cls_nm, String regex_str, String crt_by, String upd_by, Timestamp upd_ts,
			Timestamp crt_ts, Float obj_stat_cd, String prtct_type_cd, String encrypt_id_cd, String encrypt_fmt_cd,
			Float match_thold_pct, int row_cnt) {
		super();
		this.data_cls_nm = data_cls_nm;
		this.regex_str = regex_str;
		this.crt_by = crt_by;
		this.upd_by = upd_by;
		this.upd_ts = upd_ts;
		this.crt_ts = crt_ts;
		this.obj_stat_cd = obj_stat_cd;
		this.prtct_type_cd = prtct_type_cd;
		this.encrypt_id_cd = encrypt_id_cd;
		this.encrypt_fmt_cd = encrypt_fmt_cd;
		this.match_thold_pct = match_thold_pct;
		this.row_cnt = row_cnt;
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


	public Timestamp getCrt_ts() {
		return crt_ts;
	}


	public void setCrt_ts(Timestamp crt_ts) {
		this.crt_ts = crt_ts;
	}


	public Float getObj_stat_cd() {
		return obj_stat_cd;
	}


	public void setObj_stat_cd(Float obj_stat_cd) {
		this.obj_stat_cd = obj_stat_cd;
	}


	public String getPrtct_type_cd() {
		return prtct_type_cd;
	}


	public void setPrtct_type_cd(String prtct_type_cd) {
		this.prtct_type_cd = prtct_type_cd;
	}


	public String getEncrypt_id_cd() {
		return encrypt_id_cd;
	}


	public void setEncrypt_id_cd(String encrypt_id_cd) {
		this.encrypt_id_cd = encrypt_id_cd;
	}


	public String getEncrypt_fmt_cd() {
		return encrypt_fmt_cd;
	}


	public void setEncrypt_fmt_cd(String encrypt_fmt_cd) {
		this.encrypt_fmt_cd = encrypt_fmt_cd;
	}


	public Float getMatch_thold_pct() {
		return match_thold_pct;
	}


	public void setMatch_thold_pct(Float match_thold_pct) {
		this.match_thold_pct = match_thold_pct;
	}


	public int getRow_cnt() {
		return row_cnt;
	}


	public void setRow_cnt(int row_cnt) {
		this.row_cnt = row_cnt;
	}

    
}
