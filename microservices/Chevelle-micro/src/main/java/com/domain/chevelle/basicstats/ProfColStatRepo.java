/**********************************************************
 * class:		ProfColStatRepo
 * Description:	The repository serving all the basic stats features
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/


package com.domain.chevelle.basicstats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;



@Transactional
@Repository
public class ProfColStatRepo implements IProfColStatRepo
{
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	public void saveOne(ProfColStat one_profColStat)
	{
		// add error handling
		entityManager.persist(one_profColStat);
	}
	
	@Override
	public void saveBatch(List<ProfColStat> list_profColStat)
	{
		for (ProfColStat one_profColStat : list_profColStat)
		{
			// add error handling
			entityManager.persist(one_profColStat);
		}		
	}
	
	
	public void updateBStatQue(UpdateProfTableStatQue updateProfTableStatQue)
	{
		
		String sp_name = "CHEVELLE.ChevellePKG.UpdateBStatQue";
		
		Integer p_ROWS_UPDATED = 0;
		Long p_BATCH_ID = updateProfTableStatQue.getBatch_id();
		String p_TABLE_ID = updateProfTableStatQue.getTable_id();
		Long p_TASK_STAT_CD = updateProfTableStatQue.getTask_stat_cd();
		Long p_RETRIES_COUNT = updateProfTableStatQue.getFail_cnt();
		//Boolean p_DEBUG = Boolean.FALSE;
		
		
		StoredProcedureQuery sp_query = entityManager.createStoredProcedureQuery(sp_name);
		
		//System.out.println("sp_query created");

		sp_query.registerStoredProcedureParameter("p_ROWS_UPDATED", Integer.class, ParameterMode.OUT);	// p_ROWS_UPDATED
		sp_query.registerStoredProcedureParameter("p_BATCH_ID", Long.class, ParameterMode.IN);	// p_BATCH_ID
		sp_query.registerStoredProcedureParameter("p_TABLE_ID", String.class, ParameterMode.IN);	// p_TABLE_ID
		sp_query.registerStoredProcedureParameter("p_TASK_STAT_CD", Long.class, ParameterMode.IN);	// p_TASK_STAT_CD
		sp_query.registerStoredProcedureParameter("p_RETRIES_COUNT", Long.class, ParameterMode.IN);	// p_RETRIES_COUNT
		//sp_query.registerStoredProcedureParameter("p_DEBUG", Boolean.class, ParameterMode.IN);	// p_DEBUG		

		//System.out.println("sp parameters registered");

		sp_query.setParameter("p_BATCH_ID", p_BATCH_ID);
		sp_query.setParameter("p_TABLE_ID", p_TABLE_ID);
		sp_query.setParameter("p_TASK_STAT_CD", p_TASK_STAT_CD);
		sp_query.setParameter("p_RETRIES_COUNT", p_RETRIES_COUNT);
		//sp_query.setParameter("p_DEBUG", p_DEBUG);
		
		//System.out.println("sp parameters set");
		
		// sp_query.executeUpdate();
		sp_query.execute();
		
		//System.out.println("sp_query executed");
		
		p_ROWS_UPDATED = (Integer) sp_query.getOutputParameterValue("p_ROWS_UPDATED");    //getOutputParameterValue("p_ROWS_UPDATED");
		
		//System.out.println("DataClass Que rows updated:" + p_ROWS_UPDATED.toString());
		
		//List<Object[]> list_result = sp_query.getResultList();
		
	}

	
	public List<BSWorkUnit> getBStatsWorkUnit(String srvr_nm, Integer batch_size)
	{
		
		String sp_name = "CHEVELLE.ChevellePKG.getBSTATWorkUnit";
		
		//p_OutPut_RefC SYS_REFCURSOR   	// OUT
		Long p_BATCH_ID = 0L;				// OUT
		Integer p_ROWS_SELECTED = 0;		// OUT
		String p_UPD_BY = "chevelle_sys";	// IN
		String p_SRVR_NM = srvr_nm;			// IN
		Integer p_RETRIES_COUNT = 3;		// IN	//get this from the cloud config server
		Integer p_BatchSize = batch_size;	// IN
		
		StoredProcedureQuery sp_query = entityManager.createStoredProcedureQuery(sp_name, BSWorkUnit.class);
		
		sp_query.registerStoredProcedureParameter("p_OutPut_RefC", void.class, ParameterMode.REF_CURSOR);	// p_OutPut_RefC
		sp_query.registerStoredProcedureParameter("p_BATCH_ID", Long.class, ParameterMode.OUT);	// p_BATCH_ID
		sp_query.registerStoredProcedureParameter("p_ROWS_SELECTED", Integer.class, ParameterMode.OUT);	// p_ROWS_SELECTED
		sp_query.registerStoredProcedureParameter("p_UPD_BY", String.class, ParameterMode.IN);	// p_UPD_BY
		sp_query.registerStoredProcedureParameter("p_SRVR_NM", String.class, ParameterMode.IN);	// p_SRVR_NM
		sp_query.registerStoredProcedureParameter("p_RETRIES_COUNT", Integer.class, ParameterMode.IN);	// p_RETRIES_COUNT
		sp_query.registerStoredProcedureParameter("p_BatchSize", Integer.class, ParameterMode.IN);	// p_BatchSize
		
		sp_query.setParameter("p_UPD_BY", p_UPD_BY);
		sp_query.setParameter("p_SRVR_NM", p_SRVR_NM);
		sp_query.setParameter("p_RETRIES_COUNT", p_RETRIES_COUNT);
		sp_query.setParameter("p_BatchSize", p_BatchSize);
		
		try
		{
		
			sp_query.execute();
		
			p_BATCH_ID = (Long) sp_query.getOutputParameterValue("p_BATCH_ID");
			p_ROWS_SELECTED = (Integer) sp_query.getOutputParameterValue("p_ROWS_SELECTED");
		
			System.out.println("p_BATCH_ID: " + p_BATCH_ID.toString() + " p_ROWS_SELECTED: " + p_ROWS_SELECTED.toString());
		
		}
		catch(Exception ex)
		{
			
		}
		
		List<BSWorkUnit> list_bsWorkUnit = (List<BSWorkUnit>) sp_query.getResultList();
//		for (BSWorkUnit bs_unit : list_bsWorkUnit) 
//		{
//			System.out.println(bs_unit.getHIVE_TABLE_NAME());
//		}
		return list_bsWorkUnit;
		
	}
	
}
