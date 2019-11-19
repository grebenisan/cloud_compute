package com.domain.department.chevelleuimicro.regexp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.domain.department.chevelleuimicro.datagovrev.DCDataGovRev;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class ProfDataClsRegexRepo {
	
    @PersistenceContext
    private EntityManager entityManager;

    public List<ProfDataClsRegex> getAllProfDataClsRegex()
    {
    	
    	Query sql_query = entityManager.createNativeQuery("select DATA_CLS_NM, "
    			+ "REGEX_STR, CRT_BY, UPD_BY, UPD_TS, CRT_TS, OBJ_STAT_CD, PRTCT_TYPE_CD, "
    			+ "ENCRYPT_ID_CD, ENCRYPT_FMT_CD, MATCH_THOLD_PCT, ROWNUM as ROW_CNT "
    			+ "from CHEVELLE.PROF_DATA_CLS_REGEX order by data_cls_nm", ProfDataClsRegex.class) ;
    	
        List<ProfDataClsRegex> list_profDataClsRegex = (List<ProfDataClsRegex>) sql_query.getResultList();

        String encodeStr;
        for (ProfDataClsRegex regrec : list_profDataClsRegex) 
        {
        	if (regrec.getRegex_str() != null)
        	{
        		encodeStr=this.encodeRegexp(regrec.getRegex_str());
        		regrec.setRegex_str(encodeStr);
        	}

        }

        return list_profDataClsRegex;
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
    
    
}
