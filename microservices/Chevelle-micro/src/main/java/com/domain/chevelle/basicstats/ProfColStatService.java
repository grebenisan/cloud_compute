/**********************************************************
 * class:		ProfColStatService
 * Description:	The service serving all the basic stats features
 * History:		D. Grebenisan,	Created
 * 
 **********************************************************/

package com.domain.chevelle.basicstats;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


@Service
public class ProfColStatService {
	
	@Autowired   
	private ProfColStatRepo profColStatRepo;
	
	public void saveOne(ProfColStat one_profColStat)
	{
		profColStatRepo.saveOne(one_profColStat);
	}

	public void saveBatch(List<ProfColStat> list_profColStat)
	{
		profColStatRepo.saveBatch(list_profColStat);
	}
	
	public void updateBStatQue(UpdateProfTableStatQue updateProfTableStatQue)
	{
		profColStatRepo.updateBStatQue(updateProfTableStatQue);
	}
	
	public String getBSWorkUnit_text(String srvr_nm, Integer batch_size)
	{

		List<BSWorkUnit> list_bsWorkUnit = profColStatRepo.getBStatsWorkUnit(srvr_nm, batch_size);
		
		String response_text = "";
		String field_separator = Character.toString((char)28);
		
		for (BSWorkUnit bs_unit : list_bsWorkUnit)
		{
			response_text += bs_unit.getHive_schema() + 
					field_separator + bs_unit.getHive_table_name() + 
					field_separator + bs_unit.getHdfs_path() + 
					field_separator + bs_unit.getTable_id() +
					field_separator + bs_unit.getBatch_id() +
					field_separator + bs_unit.getSrvr_nm() +
					field_separator + bs_unit.getFail_cnt() +
					"\n";
		}
		
		return response_text;
		
	}
	
	
	public List<BSWorkUnitReturn> getBSWorkUnit_json(String srvr_nm, Integer batch_size)
	{

		List<BSWorkUnit> list_bsWorkUnit = profColStatRepo.getBStatsWorkUnit(srvr_nm, batch_size);
		List<BSWorkUnitReturn> list_bsWorkUnitReturn = new ArrayList<BSWorkUnitReturn>();
		
		for (BSWorkUnit bs_unit : list_bsWorkUnit)
		{
			list_bsWorkUnitReturn.add(new BSWorkUnitReturn(bs_unit));
		}
		
		return list_bsWorkUnitReturn;
		
	}
}
