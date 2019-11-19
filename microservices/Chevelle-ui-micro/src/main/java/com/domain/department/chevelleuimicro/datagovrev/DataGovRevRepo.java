package com.domain.department.chevelleuimicro.datagovrev;

import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Repository
public class DataGovRevRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<DCDataGovRev> getDataGovRevByDbNm(String dbNm, int pageSize, int pageOffset){

        Query query = entityManager.createNativeQuery("SELECT * FROM (SELECT DG_STATUS, TABLE_NM, COLUMN_NM, " +
                "SCHEMA_NM, DB_NM, DATA_CLS_NM, PROF_START_TS, PROF_END_TS, TOT_ROW_CNT, SAMPLE_ROW_CNT, " +
                "COL_VAL_UNIQ_CNT, COL_VAL_DATA_CLS_CNT, COL_VAL_DATA_CLS_PERCENT, COL_MAX_VAL, COL_MIN_VAL, " +
                "COL_AVG_LEN, APPL_REGEX_STR, COL_ID, TABLE_ID, BATCH_ID, CRT_BY, CRT_TS, DG_UPD_BY, DG_UPD_TS, " +
                "ROW_NUMBER() OVER (ORDER BY TABLE_NM DESC, COLUMN_NM DESC) as ROW_CNT FROM (SELECT CASE DATA_GOV_SENS_CD " +
                "WHEN 21 THEN 'Clear' WHEN 20 THEN 'Protect' ELSE 'Pending' END AS DG_STATUS, TGT_TABLE_NM AS TABLE_NM, " +
                "TGT_COL_NM AS COLUMN_NM, SCHEMA_NM, DB_NM, PCC.DATA_CLS_NM, PROF_START_TS, PROF_END_TS, TOT_ROW_CNT, " +
                "SAMPLE_ROW_CNT, COL_VAL_UNIQ_CNT, COL_VAL_DATA_CLS_CNT, CASE WHEN SAMPLE_ROW_CNT <> 0 THEN " +
                "ROUND((COL_VAL_DATA_CLS_CNT*100)/SAMPLE_ROW_CNT,2) ELSE 0 END AS COL_VAL_DATA_CLS_PERCENT, COL_MAX_VAL," +
                " COL_MIN_VAL, COL_AVG_LEN, APPL_REGEX_STR, PCC.COL_ID, PCC.TABLE_ID, PCC.BATCH_ID, PCC.CRT_BY, " +
                "PCC.CRT_TS, DATA_GOV_UPD_BY AS DG_UPD_BY, DATA_GOV_UPD_TS AS DG_UPD_TS, ROW_NUMBER() OVER (PARTITION BY" +
                " PCC.COL_ID,PCC.DATA_CLS_NM ORDER BY PCC.CRT_TS DESC ) as RN FROM CHEVELLE.PROF_COL_CLS PCC JOIN " +
                "CHEVELLE.PROF_COL_CLS_QUE PCCQ ON PCC.COL_ID = PCCQ.COL_ID AND PCC.BATCH_ID = PCCQ.BATCH_ID WHERE " +
                "PCCQ.BATCH_ID IS NOT NULL AND DB_NM = ? ) A WHERE RN =1 ) B WHERE B.ROW_CNT BETWEEN ? and (? + ? - 1)",
                DCDataGovRev.class);

        query.setParameter(1,dbNm);
        query.setParameter(2,pageOffset);
        query.setParameter(3,pageOffset);
        query.setParameter(4,pageSize);

        List<DCDataGovRev> dcDataGovRevList = (List<DCDataGovRev>) query.getResultList();

        String encodeStr;
        for (DCDataGovRev revrec : dcDataGovRevList){
        	if (revrec.getAppl_regex_str() != null)
        	{
        		encodeStr=this.encodeRegexp(revrec.getAppl_regex_str());
        		revrec.setAppl_regex_str(encodeStr);
        	}
        }

        return dcDataGovRevList;
    }

    public int getDgTotalCnt(String dbNm){
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM (SELECT PCC.COL_ID,PCC.DATA_CLS_NM," +
                "PCC.CRT_TS, PCCQ.BATCH_ID, ROW_NUMBER() OVER (PARTITION BY PCC.COL_ID,PCC.DATA_CLS_NM " +
                "ORDER BY PCC.CRT_TS DESC ) AS RN FROM CHEVELLE.PROF_COL_CLS PCC JOIN CHEVELLE.PROF_COL_CLS_QUE PCCQ " +
                "ON PCC.COL_ID = PCCQ.COL_ID AND PCC.BATCH_ID = PCCQ.BATCH_ID WHERE PCCQ.BATCH_ID IS NOT NULL AND " +
                "DB_NM = ?) WHERE RN=1");

        query.setParameter(1,dbNm);

        int dgTotalCnt = ( (BigDecimal) query.getSingleResult()).intValue();
        //System.out.println("DGTotalCnt:" + dgTotalCnt);
        return dgTotalCnt;

    }

    public int getDgPendingCnt(String dbNm){
        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM (SELECT PCC.COL_ID,PCC.DATA_CLS_NM," +
                "PCC.CRT_TS, PCCQ.BATCH_ID, ROW_NUMBER() OVER (PARTITION BY PCC.COL_ID,PCC.DATA_CLS_NM ORDER BY " +
                "PCC.CRT_TS DESC ) AS RN FROM CHEVELLE.PROF_COL_CLS PCC JOIN CHEVELLE.PROF_COL_CLS_QUE PCCQ ON " +
                "PCC.COL_ID = PCCQ.COL_ID AND PCC.BATCH_ID = PCCQ.BATCH_ID WHERE PCCQ.BATCH_ID IS NOT NULL AND " +
                "(DATA_GOV_SENS_CD IS NULL OR DATA_GOV_SENS_CD=0) AND DB_NM = ?) WHERE RN=1");

        query.setParameter(1,dbNm);

        int dgPendingCnt = ( (BigDecimal) query.getSingleResult()).intValue();
        //System.out.println("DGPendingCnt:" + dgPendingCnt);
        return dgPendingCnt;
    }

    public void updateCol(DataGovRevMsg dataGovRevMsg) {

/* for Reema: this code doesn't work. It does not throw any error, but it does not update anything either!
 * Please review and correct !
 * 
        Query query = entityManager.createNativeQuery("UPDATE CHEVELLE.PROF_COL_CLS SET DATA_GOV_SENS_CD=?," +
                "DATA_GOV_UPD_BY=?,DATA_GOV_UPD_TS=current_timestamp(6) WHERE COL_ID=? AND DATA_CLS_NM=? AND " +
                "CRT_TS=TO_TIMESTAMP(?,'yyyy-mm-dd hh24:mi:ss')");

        query.setParameter(1,dataGovRevMsg.getDg_status());
        query.setParameter(2,dataGovRevMsg.getDg_upd_by());
        query.setParameter(3,dataGovRevMsg.getCol_id());
        query.setParameter(4,dataGovRevMsg.getData_cls_nm());
        query.setParameter(5,dataGovRevMsg.getCrt_ts().toString().substring(0,19));
*/
     
        Query query = entityManager.createNativeQuery("UPDATE CHEVELLE.PROF_COL_CLS SET DATA_GOV_SENS_CD=?, " +
                "DATA_GOV_UPD_BY=?, DATA_GOV_UPD_TS=current_timestamp(6) WHERE COL_ID=? AND DATA_CLS_NM=? AND " +
                "BATCH_ID=?");

        query.setParameter(1,dataGovRevMsg.getDg_status());
        query.setParameter(2,dataGovRevMsg.getDg_upd_by());
        query.setParameter(3,dataGovRevMsg.getCol_id());
        query.setParameter(4,dataGovRevMsg.getData_cls_nm());
        query.setParameter(5,dataGovRevMsg.getBatch_id().toString());        

        int rowsUpd = query.executeUpdate();
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
