package com.domain.chevelle.dataclassrule;

import javax.persistence.*;


@Entity

@SqlResultSetMapping(name="ProfDataClsRegexId.QueryRegexMapping",
        classes = @ConstructorResult(
                targetClass = ProfDataClsRegexId.class,
                columns = {
                        @ColumnResult(name = "REGEX_STR")}))

public class ProfDataClsRegexId {

    @Id
    private String RegexStr;


    public String getRegexStr() {
        return RegexStr;
    }

    public void setRegexStr(String regexStr) {
        RegexStr = regexStr;
    }

    public ProfDataClsRegexId(String regexStr) {
        RegexStr = regexStr;
    }
}
