package com.domain.area.edgenodesshexecutor;

public class SshResponse {

    private int exitCode;
    private String stdout;

    public SshResponse() {
    }

    public SshResponse(int exitCode, String stdout) {
        this.exitCode = exitCode;
        this.stdout = stdout;
    }

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }
}
