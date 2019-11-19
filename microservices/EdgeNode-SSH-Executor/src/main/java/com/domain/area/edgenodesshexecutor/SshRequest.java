package com.domain.area.edgenodesshexecutor;


import java.util.List;

class SshRequest {
    private String cmd;
    private String account;
    private List<String> env;


    public SshRequest() {}

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<String> getEnv() {
        return env;
    }

    public void setEnv(List<String> env) {
        this.env = env;
    }
}
