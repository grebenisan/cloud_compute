package com.domain.department.chevelleuimicro.datagovrev;

import java.io.Serializable;

public class DCDataGovRevId implements Serializable {

    private String col_id;
    private Long batch_id;

    public DCDataGovRevId() {
    }

    public String getCol_id() {
        return col_id;
    }

    public void setCol_id(String col_id) {
        this.col_id = col_id;
    }

    public Long getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(Long batch_id) {
        this.batch_id = batch_id;
    }
}
