/**********************************************************
 * class:		ProfColClsRepo
 * Description:	The repository class of the data class controller
 * History:		D. Grebenisan, Created
 * 
 **********************************************************/


package com.domain.chevelle.dataclass;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class ProfColClsRepo implements IProfColClsRepo {
	
	@PersistenceContext	
	private EntityManager entityManager;	
	

	@Override
	public void saveOne(ProfColCls one_profColCls)
	{
		// this call is initiated by the script calling the "/dataclass/out/save" endpoint
		// the field "appl_regex_str" is DNA encoded
		// decode the field "appl_regex_str" containing the regular expression
		String decoded_regexp = this.decodeRegexp(one_profColCls.getAppl_regex_str());
		one_profColCls.setAppl_regex_str(decoded_regexp);
		
		//save the record in DB, with the decoded regexp
		entityManager.persist(one_profColCls);
	}
	
	@Override
	public void saveBatch(List<ProfColCls> list_profColCls)
	{
		String decoded_regexp;
		
		// this call is initiated by the script calling the "/dataclass/out/savebatch" endpoint
		for (ProfColCls one_profColCls : list_profColCls)
		{
			
			// the field "appl_regex_str" is DNA encoded
			// decode the field "appl_regex_str" containing the regular expression
			decoded_regexp = this.decodeRegexp(one_profColCls.getAppl_regex_str());
			one_profColCls.setAppl_regex_str(decoded_regexp);
			
			// save the record in DB with the decoded regexp
			entityManager.persist(one_profColCls);
		}		
	}
	
	public void updateDCQue(UpdateProfColClsQue updateProfColClsQue)
	{
		String sp_name = "CHEVELLE.ChevellePKG.UpdateDCQue";
		
		Integer p_ROWS_UPDATED = 0;
		Long p_BATCH_ID = updateProfColClsQue.getBatch_id();
		String p_COL_ID = updateProfColClsQue.getCol_id();
		String p_DATA_CLS_NM = updateProfColClsQue.getData_cls_nm();
		Long p_TASK_STAT_CD = updateProfColClsQue.getTask_stat_cd();
		Long p_RETRIES_COUNT = updateProfColClsQue.getFail_cnt();
		
		StoredProcedureQuery sp_query = entityManager.createStoredProcedureQuery(sp_name);
		
		//System.out.println("sp_query created");


		sp_query.registerStoredProcedureParameter("p_ROWS_UPDATED", Integer.class, ParameterMode.OUT);	// p_ROWS_UPDATED
		sp_query.registerStoredProcedureParameter("p_BATCH_ID", Long.class, ParameterMode.IN);	// p_BATCH_ID
		sp_query.registerStoredProcedureParameter("p_COL_ID", String.class, ParameterMode.IN);	// p_COL_ID
		sp_query.registerStoredProcedureParameter("p_DATA_CLS_NM", String.class, ParameterMode.IN);	// p_DATA_CLS_NM
		sp_query.registerStoredProcedureParameter("p_TASK_STAT_CD", Long.class, ParameterMode.IN);	// p_TASK_STAT_CD
		sp_query.registerStoredProcedureParameter("p_RETRIES_COUNT", Long.class, ParameterMode.IN);	// p_RETRIES_COUNT
		//sp_query.registerStoredProcedureParameter("p_DEBUG", Boolean.class, ParameterMode.IN);	// p_DEBUG		

		//System.out.println("sp parameters registered");
		// sp_query.setParameter(1, p_ROWS_UPDATED);

		sp_query.setParameter("p_BATCH_ID", p_BATCH_ID);
		sp_query.setParameter("p_COL_ID", p_COL_ID);
		sp_query.setParameter("p_DATA_CLS_NM", p_DATA_CLS_NM);
		sp_query.setParameter("p_TASK_STAT_CD", p_TASK_STAT_CD);
		sp_query.setParameter("p_RETRIES_COUNT", p_RETRIES_COUNT);
		//sp_query.setParameter("p_DEBUG", false);
		
		//System.out.println("sp parameters set");
		
		// sp_query.executeUpdate();
		sp_query.execute();
		
		//System.out.println("sp_query executed");
		
		p_ROWS_UPDATED = (Integer) sp_query.getOutputParameterValue("p_ROWS_UPDATED");    //getOutputParameterValue("p_ROWS_UPDATED");
		
		//System.out.println("DataClass Que rows updated:" + p_ROWS_UPDATED.toString());
		


	}
	
	
	public List<DCWorkUnit> getDCWorkUnit(String srvr_nm, String upd_by, Integer batch_size, Integer retries_count)
	{
		
		String sp_name = "CHEVELLE.ChevellePKG.getDCWorkUnit";
		
		//p_Output_RefC SYS_REFCURSOR   // OUT
		Long p_BATCH_ID = 0L;			// OUT
		Integer p_ROWS_SELECTED = 0;	// OUT
		String p_UPD_BY = upd_by;		//IN
		String p_SRVR_NM = srvr_nm;		// IN
		Integer p_RETRIES_COUNT = retries_count;		// IN	//get this from the cloud config server
		Integer p_BatchSize = batch_size;// IN
		
		StoredProcedureQuery sp_query = entityManager.createStoredProcedureQuery(sp_name, DCWorkUnit.class);
		//StoredProcedureQuery sp_query = entityManager.createStoredProcedureQuery(sp_name);
		
		sp_query.registerStoredProcedureParameter("p_Output_RefC", void.class, ParameterMode.REF_CURSOR);	// p_Output_RefC
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
			// more error handling in future releases
			System.out.println("Error exception: " + ex.getMessage());
		}
		
		List<DCWorkUnit> list_dcWorkUnit = (List<DCWorkUnit>) sp_query.getResultList();

// for debug only
//		for (DCWorkUnit dc_unit : list_dcWorkUnit) 
//		{
//			System.out.println(dc_unit.getColumn_name());
//		}		
		
/*		
		List<Object[]> list_object = sp_query.getResultList();
		
		list_object.stream().forEach((record) -> {

	        String firstCol = (String) record[0];
	        System.out.println(firstCol);

		});
*/		
		//scan the list and encode the "regex_str" field with the DNA code
		String encoded_regexp;
		for (DCWorkUnit dc_unit : list_dcWorkUnit) 
		{
			encoded_regexp = this.encodeRegexp(dc_unit.getRegex_str());
			dc_unit.setRegex_str(encoded_regexp);
		}		
		
		return list_dcWorkUnit;
		
	}
	
	
	
	// Encode a regular expression to avoid the crush of JSON parsers when encounter certain characters
	// - encode the backslash char "\" - decimal 92, with the copyright char "©" - decimal 169 
	// - encode the opening brace char “{“ - decimal  123, with the left double angle quotes char "«" - decimal 171 
	// - encode the closing brace char "}" - decimal 125, with the right double angle quotes char "»" - decimal 187 
	// The py script receiving this encoded regexp, is supposed to decode it in reverse
	private String encodeRegexp(String decoded)
	{
		
		if (decoded.isEmpty() || decoded.trim().length() == 0)
			return decoded;
		
		String encoded;	// encoded string to be returned
		
	    char copyright_char = (char) 169;		// the copyright character "©" - ascii decimal 169
	    char left_angles_char = (char) 171;		// left double angle quotes character "«" - ascii decimal 171 
	    char right_angles_char = (char) 187;	// right double angle quotes char "»" - ascii decimal 187 

	    char backslash_char = (char) 92;		// the backslash char "\" - ascii decimal 92
	    char open_brace_char = (char) 123;		// opening brace char “{“ - ascii decimal  123
	    char close_brace_char = (char) 125;		// closing brace char "}" - ascii decimal  125
	    
        char[] searchChars = decoded.toCharArray();
        
        for (int i = 0; i < searchChars.length; i++)
        {
           	//replace the backslash with the copyright
            if (searchChars[i] == backslash_char)
            	searchChars[i] = copyright_char;
            
           	//replace the open brace with the left angle quotes
            if (searchChars[i] == open_brace_char)
            	searchChars[i] = left_angles_char;            
            
           	//replace the closing brace with the right angle quotes
            if (searchChars[i] == close_brace_char)
            	searchChars[i] = right_angles_char;                        
        }
            
        encoded = String.valueOf(searchChars);

		return encoded; 
		
	}

	
	// Decode a regular expression from previous encoding
	// - decode the copyright char "©" - decimal 169 , with the backslash char "\" - decimal 92
	// - decode the left double angle quotes char "«" - decimal 171 , with the opening brace char “{“ - decimal  123
	// - decode the right double angle quotes char "»" - decimal 187 , with the closing brace char "}" - decimal 125
	// The py script is supposed to send the regexp encoded, so the microserice decodes them before saving to DB
	private String decodeRegexp(String encoded)
	{
		
		if (encoded.isEmpty() || encoded.trim().length() == 0)
			return encoded;
		
		String decoded = "";	// decoded string to be returned
		
	    char copyright_char = (char) 169;		// the copyright character "©" - ascii decimal 169
	    char left_angles_char = (char) 171;		// left double angle quotes character "«" - ascii decimal 171 
	    char right_angles_char = (char) 187;	// right double angle quotes char "»" - ascii decimal 187 

	    char backslash_char = (char) 92;		// the backslash char "\" - ascii decimal 92
	    char open_brace_char = (char) 123;		// opening brace char “{“ - ascii decimal  123
	    char close_brace_char = (char) 125;		// closing brace char "}" - ascii decimal  125		
		
        char[] searchChars = encoded.toCharArray();	    
	    
        for (int i = 0; i < searchChars.length; i++)
        {
           	//replace the copyright with the backslash
            if (searchChars[i] == copyright_char)
            	searchChars[i] = backslash_char;
            
           	//replace the left angle quotes with the open brace
            if (searchChars[i] == left_angles_char)
            	searchChars[i] = open_brace_char;            
            
           	//replace the right angle quotes, with the closing brace
            if (searchChars[i] == right_angles_char)
            	searchChars[i] = close_brace_char;                        
        }	    
	    
        decoded = String.valueOf(searchChars);
        
		return decoded;
	}
}
