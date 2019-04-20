package com.domain.chevelle.dataclassrule;

import javax.persistence.*;


@Entity

@SqlResultSetMapping(name="ProfDataClsRegexCase.QueryRegexCaseMapping",
        classes = @ConstructorResult(
                targetClass = ProfDataClsRegexCase.class,
                columns = {
                        @ColumnResult(name = "REGEX_STR_CASE")}))

public class ProfDataClsRegexCase {

    @Id
    private String RegexStr;


    public String getRegexStr() {
        return RegexStr;
    }

    public void setRegexStr(String regexStr) {
        RegexStr = regexStr;
    }

    public ProfDataClsRegexCase(String regexStr) {
        RegexStr = regexStr;
    }
}
