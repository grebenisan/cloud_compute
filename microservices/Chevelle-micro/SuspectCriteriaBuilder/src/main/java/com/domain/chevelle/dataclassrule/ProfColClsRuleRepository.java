package com.domain.chevelle.dataclassrule;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import com.domain.chevelle.dataclassrule.ProfColClsRuleId;

@Repository
@Transactional

public class ProfColClsRuleRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<ProfColClsRuleId> GenerateQueryTypeAll() {
        return entityManager.createNativeQuery("SELECT DATA_CLS_NM, MATCH_TYPE_OPR, "+
                "case when lower(MATCH_TYPE_OPR)='regexp_like' then ("+
                "        case when  OR_AND_RNK=MIN_OR_AND_RNK and OR_AND_RNK<MAX_OR_AND_RNK then ("+
                "                    case when MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK < MAX_MATCH_TYPE_RNK then 'when ((regexp_like(lower(d.column_name), '|| '''^.*(' || trim(MATCH_NM) || ').*$'',''c'')' "+
                "                         when MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK = MAX_MATCH_TYPE_RNK then 'when ((regexp_like(lower(d.column_name), '|| '''^.*(' || trim(MATCH_NM) || ').*$'',''c'')' || ')'"+
                "                         when MATCH_TYPE_OPR_RNK>MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK < MAX_MATCH_TYPE_RNK then ' or regexp_like(lower(d.column_name), '|| '''^.*(' || trim(MATCH_NM) || ').*$'',''c'')' "+
                "                         when MATCH_TYPE_OPR_RNK>MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK = MAX_MATCH_TYPE_RNK then ' or regexp_like(lower(d.column_name), '|| '''^.*(' || trim(MATCH_NM) || ').*$'',''c'')' || ')' "+
                "                         end) "+
                "              when  OR_AND_RNK=MIN_OR_AND_RNK and OR_AND_RNK=MAX_OR_AND_RNK then ("+
                "                    case when MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK<MAX_MATCH_TYPE_RNK then 'when ((regexp_like(lower(d.column_name), '|| '''^.*(' || trim(MATCH_NM) || ').*$'',''c'')'"+
                "                         when MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK=MAX_MATCH_TYPE_RNK then 'when ((regexp_like(lower(d.column_name), '|| '''^.*(' || trim(MATCH_NM) || ').*$'',''c''))' || ') then ''' || DATA_CLS_NM || ''''"+
                "                         when MATCH_TYPE_OPR_RNK>MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK<MAX_MATCH_TYPE_RNK then 'or regexp_like(lower(d.column_name), '|| '''^.*(' || trim(MATCH_NM) || ').*$'',''c'')' "+
                "                         when MATCH_TYPE_OPR_RNK>MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK = MAX_MATCH_TYPE_RNK then 'or regexp_like(lower(d.column_name), '|| '''^.*(' || trim(MATCH_NM) || ').*$'',''c''))' || ') then ''' || DATA_CLS_NM || ''''"+
                "                         end) "+
                "                         end)    "+
                        "when lower(MATCH_TYPE_OPR) in ('=') then ("+
                        "        case when  OR_AND_RNK=MIN_OR_AND_RNK and OR_AND_RNK<MAX_OR_AND_RNK then ("+
                        "                    case when MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK < MAX_MATCH_TYPE_RNK then 'when ((lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ''' || trim(MATCH_NM) || ''''"+
                        "                         when MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK = MAX_MATCH_TYPE_RNK then 'when ((lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ''' || trim(MATCH_NM) || ''')' "+
                        "                         when MATCH_TYPE_OPR_RNK>MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK < MAX_MATCH_TYPE_RNK then ' or lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ''' || trim(MATCH_NM) || ''''"+
                        "                         when MATCH_TYPE_OPR_RNK>MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK = MAX_MATCH_TYPE_RNK then '( or lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ''' || trim(MATCH_NM) || ''')' "+
                        "                         end) "+
                        "              when  OR_AND_RNK=MIN_OR_AND_RNK and OR_AND_RNK=MAX_OR_AND_RNK then ("+
                        "                    case when MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK<MAX_MATCH_TYPE_RNK then 'when ((lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ''' || trim(MATCH_NM) || ''')'  || ') then ''' || DATA_CLS_NM || ''''"+
                        "                         when MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK=MAX_MATCH_TYPE_RNK then 'when ((lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ''' || trim(MATCH_NM) || '''' || ') then ''' || DATA_CLS_NM || ''''"+
                        "                         when MATCH_TYPE_OPR_RNK>MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK<MAX_MATCH_TYPE_RNK then ' or lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ''' || trim(MATCH_NM) || '''' || ') then ''' || DATA_CLS_NM || ''''"+
                        "                         when MATCH_TYPE_OPR_RNK>MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK = MAX_MATCH_TYPE_RNK then '( or lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ''' || trim(MATCH_NM) || '''' || ')'|| ') then ''' || DATA_CLS_NM || ''''"+
                        "                         end) "+
                        "                         end)    "+
                        "when lower(MATCH_TYPE_OPR) in ('in') then("+
                        "case when  OR_AND_RNK=MIN_OR_AND_RNK and MIN_OR_AND_RNK=MAX_OR_AND_RNK then ("+
                        "            case when  MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK<MAX_MATCH_TYPE_RNK then 'when ( lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) "+
                        "                 when  MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK=MAX_MATCH_TYPE_RNK then 'when ( lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) ||  ') then ''' || DATA_CLS_NM || ''''"+
                        "             when  MATCH_TYPE_OPR_RNK> MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK< MAX_MATCH_TYPE_RNK then ' or lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) "+
                        "             when  MATCH_TYPE_OPR_RNK> MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK = MAX_MATCH_TYPE_RNK then ' or lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) ||  ') then ''' || DATA_CLS_NM || ''''"+
                        "            end)"+
                        "    when  OR_AND_RNK=MIN_OR_AND_RNK and OR_AND_RNK<MAX_OR_AND_RNK then ("+
                        "        case when MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK<MAX_MATCH_TYPE_RNK then 'when ( lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) "+
                        "             when MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK=MAX_MATCH_TYPE_RNK then 'when ( lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) || ')'"+
                        "             when MATCH_TYPE_OPR_RNK>MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK<MAX_MATCH_TYPE_RNK then ' or lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) "+
                        "             when MATCH_TYPE_OPR_RNK>MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK<MAX_MATCH_TYPE_RNK then ' or lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) || ')'"+
                        "            end)"+
                        "            end)"+
                        "when lower(MATCH_TYPE_OPR) in ('not in') then("+
                        "case when  OR_AND_RNK=MIN_OR_AND_RNK and MIN_OR_AND_RNK=MAX_OR_AND_RNK then ("+
                        "            case when  MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK<MAX_MATCH_TYPE_RNK then 'when lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) "+
                        "                 when  MATCH_TYPE_OPR_RNK=MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK=MAX_MATCH_TYPE_RNK then 'when ( lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) ||  ') then ''' || DATA_CLS_NM || ''''"+
                        "             when  MATCH_TYPE_OPR_RNK> MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK< MAX_MATCH_TYPE_RNK then ' and lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) "+
                        "             when  MATCH_TYPE_OPR_RNK> MIN_MATCH_TYPE_RNK and MATCH_TYPE_OPR_RNK = MAX_MATCH_TYPE_RNK then ' and lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) ||  ') then ''' || DATA_CLS_NM || ''''"+
                        "            end)"+
                        "    when  OR_AND_RNK=MAX_OR_AND_RNK and OR_AND_RNK>MIN_OR_AND_RNK then ("+
                        "        case when MATCH_TYPE_OPR_RNK=MAX_MATCH_TYPE_RNK then ' and lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) || ') then ''' || DATA_CLS_NM || ''''"+
                        "            when  MATCH_TYPE_OPR_RNK<MAX_MATCH_TYPE_RNK then ' and lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ' || trim(MATCH_NM) "+
                        "            end)"+
                        "            end)"+
                        "when lower(MATCH_TYPE_OPR)='not regexp_like' then ("+
                        "case when  OR_AND_RNK=MAX_OR_AND_RNK and MIN_OR_AND_RNK=MAX_OR_AND_RNK then ("+
                        "        case when MATCH_TYPE_OPR_RNK<MAX_MATCH_TYPE_RNK then MATCH_TYPE_OPR || '(lower(d.column_name)' || ',''^.*(' ||  trim(MATCH_NM) || ').*$'',''c'')'"+
                        "            when  MATCH_TYPE_OPR_RNK=MAX_MATCH_TYPE_RNK then MATCH_TYPE_OPR || '(lower(d.column_name)' || ',''^.*(' ||  trim(MATCH_NM) || ').*$'',''c'')' || ') then ''' || DATA_CLS_NM || ''''"+
                        "             end)"+
                        "     when  OR_AND_RNK=MAX_OR_AND_RNK and MIN_OR_AND_RNK<MAX_OR_AND_RNK then (            "+
                        "             case when  MATCH_TYPE_OPR_RNK< MAX_MATCH_TYPE_RNK  then  ' and ' || MATCH_TYPE_OPR || '(lower(d.column_name)' || ',''^.*(' ||  trim(MATCH_NM) || ').*$'',''c'')'"+
                        "                  when  MATCH_TYPE_OPR_RNK=MAX_MATCH_TYPE_RNK then ' and ' || MATCH_TYPE_OPR || '(lower(d.column_name)' || ',''^.*(' ||  trim(MATCH_NM) || ').*$'',''c'')' || ') then ''' || DATA_CLS_NM || ''''"+
                        "            end)end)"+
                        "ELSE ("+
                        "case when  OR_AND_RNK=MAX_OR_AND_RNK and MIN_OR_AND_RNK=MAX_OR_AND_RNK then ("+
                        "        case when MATCH_TYPE_OPR_RNK<MAX_MATCH_TYPE_RNK then 'lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ''' ||  trim(MATCH_NM) || ''''  "+
                        "             when  MATCH_TYPE_OPR_RNK=MAX_MATCH_TYPE_RNK then 'lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ''' ||  trim(MATCH_NM) || '''' || ') then ''' || DATA_CLS_NM || ''''"+
                        "             end)"+
                        "     when  OR_AND_RNK=MAX_OR_AND_RNK and MIN_OR_AND_RNK<MAX_OR_AND_RNK then (            "+
                        "             case when  MATCH_TYPE_OPR_RNK< MAX_MATCH_TYPE_RNK  then ' and lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ''' ||  trim(MATCH_NM) || ''''  "+
                        "                  when  MATCH_TYPE_OPR_RNK=MAX_MATCH_TYPE_RNK then ' and lower(d.column_name)' || ' ' || MATCH_TYPE_OPR  || ' ''' ||  trim(MATCH_NM) || ''''  || ') then ''' || DATA_CLS_NM || ''''"+
                        "            end)end) end AS MATCH_NM "+
                        "from "+
                        "(SELECT OR_AND_RNK, MATCH_TYPE_OPR_RNK,"+
                        "MIN(OR_AND_RNK) over (partition by DATA_CLS_NM) AS MIN_OR_AND_RNK, MAX(OR_AND_RNK) over (partition by DATA_CLS_NM) AS MAX_OR_AND_RNK,"+
                        "MIN(MATCH_TYPE_OPR_RNK) over (partition by DATA_CLS_NM, OR_AND_RNK) AS MIN_MATCH_TYPE_RNK, MAX(MATCH_TYPE_OPR_RNK) over (partition by DATA_CLS_NM, OR_AND_RNK) AS MAX_MATCH_TYPE_RNK,"+
                        "DATA_CLS_NM, MATCH_TYPE_OPR , MATCH_NM "+
                        "FROM (SELECT "+
                        "OR_AND_RNK, row_number() over (partition by DATA_CLS_NM, OR_AND_RNK order by MATCH_TYPE_OPR) AS MATCH_TYPE_OPR_RNK, DATA_CLS_NM , MATCH_TYPE_OPR , MATCH_NM from "+
                        "(select T2.OR_AND_RNK, T1.* from "+
                        "(select DATA_CLS_NM , MATCH_TYPE_OPR , MATCH_NM from "+
                        "("+
                        "SELECT DATA_CLS_NM, MATCH_TYPE_OPR, '(' || MATCH_NM || ')' AS MATCH_NM FROM"+
                        "   (SELECT DATA_CLS_NM, MATCH_TYPE_OPR, ROW_NUM_RNK,  listagg(chr(39) || MATCH_NM || chr(39), ',') within group (order by MATCH_NM) as MATCH_NM FROM "+
                        "   (select DATA_CLS_NM, MATCH_TYPE_OPR, MATCH_NM, TRUNC(MATCH_TYPE_RNK/10) ROW_NUM_RNK from "+
                        "    (select DATA_CLS_NM, MATCH_TYPE_OPR, MATCH_NM, row_number() over(partition by DATA_CLS_NM, MATCH_TYPE_OPR order by MATCH_NM) MATCH_TYPE_RNK FROM CHEVELLE.PROF_COL_CLS_RULE "+
                        "    where lower(MATCH_TYPE_OPR) in ( 'not in', 'in') )T1)T2"+
                        "    group by DATA_CLS_NM, MATCH_TYPE_OPR, ROW_NUM_RNK)T3"+
                        " union all "+
                "select DATA_CLS_NM, MATCH_TYPE_OPR, rtrim(MATCH_NM, '|') as MATCH_NM from"+
                "  (SELECT DATA_CLS_NM, MATCH_TYPE_OPR, ROW_NUM_RNK,  listagg(MATCH_NM || '|' )  within group (order by MATCH_NM) as MATCH_NM FROM"+
                "   (select DATA_CLS_NM, MATCH_TYPE_OPR, MATCH_NM, TRUNC(MATCH_TYPE_RNK/10) ROW_NUM_RNK from "+
                "    (select DATA_CLS_NM, MATCH_TYPE_OPR, MATCH_NM, row_number() over(partition by DATA_CLS_NM, MATCH_TYPE_OPR order by MATCH_NM) MATCH_TYPE_RNK FROM CHEVELLE.PROF_COL_CLS_RULE "+
                "    where lower(MATCH_TYPE_OPR) like '%regexp_like' )T1)T2"+
                "    group by DATA_CLS_NM, MATCH_TYPE_OPR, ROW_NUM_RNK)T3"+
                        " union all "+
                        "select DATA_CLS_NM, MATCH_TYPE_OPR, ' ' || MATCH_NM || ' ' AS MATCH_NM from CHEVELLE.PROF_COL_CLS_RULE "+
                        "where lower(MATCH_TYPE_OPR) NOT IN ('not in', 'in', 'regexp_like') and  lower(MATCH_TYPE_OPR) not like '%regexp_like'"+
                        ") union_tb "+
                        ") T1"+
                        " INNER JOIN  "+
                        "(select  MATCH_TYPE_OPR, case when lower(MATCH_TYPE_OPR)='regexp_like' or lower(MATCH_TYPE_OPR)='=' or lower(MATCH_TYPE_OPR)='in' then 1 "+
                        "           else 2 end as OR_AND_RNK "+
                        "           from (select MATCH_TYPE_OPR from CHEVELLE.PROF_COL_CLS_RULE group by MATCH_TYPE_OPR)) T2 "+
                        "           ON T1.MATCH_TYPE_OPR=T2.MATCH_TYPE_OPR "+
                        ")T3 ) T4 ) T5  "+
                //  "where DATA_CLS_NM in ('LOGIN_ID', 'VIN','SOCIAL_SECURITY_NUMBER') "+
                " order by DATA_CLS_NM, OR_AND_RNK, MATCH_TYPE_OPR_RNK  ", "ProfColClsRuleId.QueryTypeInMapping").getResultList();

    }

    public List<ProfDataClsRegexId> GenerateRegex(){
        return entityManager.createNativeQuery(
            "SELECT CONCAT(CONCAT('''',REGEX_STR),'''') || ' as ' ||  DATA_CLS_NM || case when T1.regex_rownum =T2.max_regex_rownum then ' ' else ', '  end AS REGEX_STR \n" +
                    "from (select DATA_CLS_NM, REGEX_STR, rownum as regex_rownum from  CHEVELLE.PROF_DATA_CLS_REGEX) T1 \n" +
                    "LEFT OUTER JOIN (select max(rownum) as max_regex_rownum from CHEVELLE.PROF_DATA_CLS_REGEX) T2 ON T1.regex_rownum = T2.max_regex_rownum", "ProfDataClsRegexId.QueryRegexMapping").getResultList();
    }

    public List<ProfDataClsRegexCase> GenerateRegexcase(){
        return entityManager.createNativeQuery(
                "select 'when Suspected_Data_Classification =''' || DATA_CLS_NM || ''' then '  ||  DATA_CLS_NM   AS REGEX_STR_CASE from CHEVELLE.PROF_DATA_CLS_REGEX", "ProfDataClsRegexCase.QueryRegexCaseMapping").getResultList();

    }

    public String GetsqlString() {

        String sqlString = "Select c1.asms" +
                ", c1.\"Database Schema\"" +
                ", c1.\"Database Table\"" +
                ", c1.Suspected_Data_Classification" +
                ", c1.column_name " +
                ", c1.regex " +
                "from ( " +
                "Select " +
                "B1.column_name, " +
                "\'42124_EDW_BigInsights\' as Host ," +
                " B1.ASMS," +
                " B1.\"Database Schema\"," +
                " B1.\"Database Table\"," +
                " B1.Suspected_Data_Classification,case ";
        return sqlString;
    }

    public String GetsqlString2() {
        String sqlString2 = "else  'Filter'      \n" +
                "end as REGEX " +
                "from ( " +
                "select distinct " +
                "d.column_name, " +
                "'42124_EDW_BigInsights' as Host," +
                "case  " +
                "when length(substr(regexp_substr(b.name,'(_\\d{2,10})',1,1),2) ) > 6 then null " +
                "else " +
                "substr(regexp_substr(b.name,'(_\\d{2,6})',1,1),2)  " +
                "end as ASMS, " +
                "b.name as \"Database Schema\", " +
                "a.tbl_name as \"Database Table\"," +
                "case ";
        return sqlString2;
    }

    public String GetsuffixString(){
        String suffixString=" Else \'No Classification Detected\' "+
                "END as Suspected_Data_Classification, "+
                "sysdate as \"Suspected Data Class Date Ran\", ";
        return suffixString;
    }

    }

