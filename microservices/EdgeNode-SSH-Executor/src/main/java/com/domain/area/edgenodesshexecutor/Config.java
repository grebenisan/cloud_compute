package com.domain.area.edgenodesshexecutor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("ssh-config")
@RefreshScope
class Config {
    private char[] key;
    private String environmentGatewayUrl;
    private String validCmds;

    public String getEnvironmentGatewayUrl() {
        return environmentGatewayUrl;
    }

    public void setEnvironmentGatewayUrl(String environmentGatewayUrl) {
        this.environmentGatewayUrl = environmentGatewayUrl;
    }

    public char[] getKey() {
        return key;
    }
    public void setKey(char[] key) {
        this.key = key;
    }

    public void setValidCmds(String validCmds) {
        this.validCmds = validCmds;
    }

    public String getValidCmds() {
        return validCmds;


    }
}