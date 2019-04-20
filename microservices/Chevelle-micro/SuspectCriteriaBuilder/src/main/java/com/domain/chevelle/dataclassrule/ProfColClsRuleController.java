package com.domain.chevelle.dataclassrule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// import com.domain.chevelle.dataclassrule.ProfColClsRuleService.*;

@RestController

@RequestMapping("/")

public class ProfColClsRuleController {

    @GetMapping
    public String sayHello() {
        return "hello";
    }

    @Autowired
    private ProfColClsRuleService profColClsRuleService;

    @Autowired
    private ProfColClsRuleRepo repo;

    @RequestMapping(method = RequestMethod.GET, value = "/getrule")
    public List<String> QueryRuleTable(){
        List<String> response = repo.QueryRuleTable();
        String firstItem = response.get(0);
        System.out.println(firstItem);
        return repo.QueryRuleTable();

    }

    @Autowired
    private ProfColClsRuleRepository profColClsRuleRepository;


    @GetMapping("/getruleByAsms/{asmsid}")

    public String getDCRuleothers(@PathVariable String asmsid){

        String sqlString=profColClsRuleRepository.GetsqlString();
        String sqlString2=profColClsRuleRepository.GetsqlString2();
        String suffixString=profColClsRuleRepository.GetsuffixString();


        String suffixString2="from  hiveprdm.tbls a, hiveprdm.dbs b, hiveprdm.sds c, hiveprdm.columns_v2 d "+
                "where  a.db_id = b.db_id "+
                "and  a.sd_id = c.sd_id "+
                "and  c.cd_id = d.cd_id "+
                "and  d.column_name not like (\'dw_%\') "+
                "and  d.column_name not like (\'gg_%\') "+
                "and substr(regexp_substr(b.name,\'(_\\d{2,10})\',1,1),2) = " + "\'" + asmsid + "\'" +
                "and regexp_substr(b.name,\'edge\') = \'edge\'"+
                "and b.name not like (\'%_raw_%\')  "+
                "order by 2,3,4,5,6,7) B1 ) c1  " +
                "where C1.REGEX <> \'Filter\'";
            //    "where c1.Suspected_Data_Classification <> 'No Classification Detected'";

        List<ProfDataClsRegexCase> profDataClsRegexCaseList=profColClsRuleRepository.GenerateRegexcase();

        for(ProfDataClsRegexCase a:profDataClsRegexCaseList){
            String str=a.getRegexStr();
            sqlString=sqlString+str;
        }

        sqlString=sqlString+sqlString2;

        List<ProfColClsRuleId> profColClsRuleIdList = profColClsRuleRepository.GenerateQueryTypeAll();

        for (ProfColClsRuleId a : profColClsRuleIdList){
            String str=a.getMatchNm();
            sqlString = sqlString + "\r\n"+  str;
           // GenerateOutput.add(str);
        }


        sqlString=sqlString+suffixString;

        List<ProfDataClsRegexId> profDataClsRegexIdList=profColClsRuleRepository.GenerateRegex();

        for(ProfDataClsRegexId a: profDataClsRegexIdList){
            String str=a.getRegexStr();
            sqlString=sqlString+str;
        }

        sqlString=sqlString+suffixString2;


        String finalresult = sqlString.replaceAll("where ", "<br>where ").replaceAll("and ", "<br>and ").replaceAll("or ", "<br>or ").replaceAll("when ","<br>when ").replaceAll("else ", "<br>else ").replaceAll("case ", "<br>case").replaceAll(", ", ", <br>");

        return finalresult;

    }


    @GetMapping("/getruleByDBName/{dbname}")

    public String getDCRuleoDb(@PathVariable String dbname){

        String sqlString=profColClsRuleRepository.GetsqlString();
        String sqlString2=profColClsRuleRepository.GetsqlString2();
        String suffixString=profColClsRuleRepository.GetsuffixString();

        String suffixString2="from  hiveprdm.tbls a, hiveprdm.dbs b, hiveprdm.sds c, hiveprdm.columns_v2 d "+
                "where  a.db_id = b.db_id "+
                "and  a.sd_id = c.sd_id "+
                "and  c.cd_id = d.cd_id "+
                "and  d.column_name not like (\'dw_%\') "+
                "and  d.column_name not like (\'gg_%\') "+
                "and  b.name = " + "\'" + dbname + "\'" +
             //   "and regexp_substr(b.name,\'edge\') = \'edge\'"+
                "and b.name not like (\'%_raw_%\')  "+
                "order by 2,3,4,5,6,7) B1 ) c1  " +
                "where c1.REGEX <> \'Filter\'";

        List<ProfDataClsRegexCase> profDataClsRegexCaseList=profColClsRuleRepository.GenerateRegexcase();

        for(ProfDataClsRegexCase a:profDataClsRegexCaseList){
            String str=a.getRegexStr();
            sqlString=sqlString+str;
        }

        sqlString=sqlString+sqlString2;

        List<ProfColClsRuleId> profColClsRuleIdList = profColClsRuleRepository.GenerateQueryTypeAll();

        for (ProfColClsRuleId a : profColClsRuleIdList){
            String str=a.getMatchNm();
            sqlString = sqlString +  str;
            // GenerateOutput.add(str);
        }

        sqlString=sqlString+suffixString;

        List<ProfDataClsRegexId> profDataClsRegexIdList=profColClsRuleRepository.GenerateRegex();

        for(ProfDataClsRegexId a: profDataClsRegexIdList){
            String str=a.getRegexStr();
            sqlString=sqlString+str;
        }

        sqlString=sqlString+suffixString2;


        String finalresult = sqlString.replaceAll("where ", "<br>where ").replaceAll("and ", "<br>and ").replaceAll("or ", "<br>or ").replaceAll("when ","<br>when ").replaceAll("else ", "<br>else ").replaceAll("case ", "<br>case").replaceAll(", ", ", <br>");

        return finalresult;

    }


    }

