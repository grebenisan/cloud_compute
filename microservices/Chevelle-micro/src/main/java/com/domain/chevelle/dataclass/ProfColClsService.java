/**********************************************************
 * class:		ProfColClsService
 * Description:	The service class of the data class controller
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/

package com.domain.chevelle.dataclass;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProfColClsService {

	@Autowired
	private ProfColClsRepo profColClsRepo;
	
	public void saveOne(ProfColCls one_profColCls)
	{
		profColClsRepo.saveOne(one_profColCls);
	}
	
	public void saveBatch(List<ProfColCls> list_profColCls)
	{
		profColClsRepo.saveBatch(list_profColCls);
	}
	
	public void updateDCQue(UpdateProfColClsQue updateProfColClsQue)
	{
		profColClsRepo.updateDCQue(updateProfColClsQue);
	}
	
	
	public List<DCWorkUnitReturn> getDCWorkUnit_json(String srvr_nm, String upd_by, Integer batch_size, Integer retries_count)
	{

		List<DCWorkUnit> list_dcWorkUnit = profColClsRepo.getDCWorkUnit(srvr_nm, upd_by, batch_size, retries_count);
		List<DCWorkUnitReturn> list_dcWorkUnitReturn = new ArrayList<DCWorkUnitReturn>();
		
		for (DCWorkUnit dc_unit : list_dcWorkUnit)
		{
			list_dcWorkUnitReturn.add(new DCWorkUnitReturn(dc_unit));
		}
		
		return list_dcWorkUnitReturn;
		
	}
	

	public String getDCWorkUnit_text(String srvr_nm, String upd_by, Integer batch_size, Integer retries_count)
	{

		List<DCWorkUnit> list_dcWorkUnit = profColClsRepo.getDCWorkUnit(srvr_nm, upd_by, batch_size, retries_count);
		
		String response_text = "";
		String field_separator = Character.toString((char)28);
		
		for (DCWorkUnit dc_unit : list_dcWorkUnit)
		{
			response_text += dc_unit.getHive_schema() + 
					field_separator + dc_unit.getHive_table_name() + 
					field_separator + dc_unit.getHdfs_path() + 
					field_separator + dc_unit.getColumn_name() +
					field_separator + dc_unit.getData_cls_nm() +
					field_separator + dc_unit.getRegex_str() + 
					field_separator + dc_unit.getTable_id() +
					field_separator + dc_unit.getCol_id() +
					field_separator + dc_unit.getBatch_id() +
					field_separator + dc_unit.getSrvr_nm() +
					field_separator + dc_unit.getFail_cnt() +
					"\n";
		}
		
/*		
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
*/		
		return response_text;
		
	}
	
}
