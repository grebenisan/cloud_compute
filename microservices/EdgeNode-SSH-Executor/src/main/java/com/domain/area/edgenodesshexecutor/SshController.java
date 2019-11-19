package com.domain.area.edgenodesshexecutor;


import com.domain.area.nomadsso.config.TokenAuthorized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.naming.directory.InvalidAttributesException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ssh")
@RefreshScope
class SshController {


    private static final Logger log = LoggerFactory.getLogger(SshController.class.getName());

    private final SSH ssh;
    private final Config config;
    private final Environment environment;

    private String account;

    @Autowired
    public SshController(Config config, SSH ssh, Environment environment) {
        this.config = config;
        this.ssh = ssh;
        this.environment = environment;
    }


    //TODO split on pipe |
    private boolean validCmd(String cmd) {

        List<String> validCmds = Arrays.asList(config.getValidCmds().split(","));
        String[] cmdTokens = cmd.split("\\|");
        validCmds.forEach(System.out::println);
        boolean validStringOfTokens = true;

        for (String token: cmdTokens) {
            System.out.println(token);
            boolean validToken = false;
            for (String valid: validCmds) {
                if (token.trim().startsWith(valid)) {
                    validToken = true;
                }
            }
            validStringOfTokens = validStringOfTokens && validToken;
        }
        log.info("valid command: {}", validStringOfTokens);
        return validStringOfTokens;
    }

    @PostMapping
    @TokenAuthorized(roles={"superadmin"})
    public Map<String, SshResponse> execute(@RequestBody SshRequest sshReq) throws Exception {
        log.info("Command: {}", sshReq.getCmd());
//        log.info("Env: {}", sshReq.getEnv());

        if (!this.validCmd(sshReq.getCmd())) {
            throw new Exception("Unathorized command");
        }

        Map<String, SshResponse> response = new HashMap<>();
        SshResponse res;
        sshReq.setEnv(sshReq.getEnv()
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList()));


        if (environment.acceptsProfiles("prod")) {
            //TODO don't allow prod executions unless the app is in prod
        }

        if (sshReq.getEnv().contains("prod")) {
            sshReq.getEnv().remove("prod");
            sshReq.getEnv().add("prod1");
            sshReq.getEnv().add("prod2");
        } else if (sshReq.getEnv().contains("test")) {
            sshReq.getEnv().remove("test");
            sshReq.getEnv().add("test1");
            sshReq.getEnv().add("test2");
        }

        try {

            for (String env: sshReq.getEnv()) {
                String ip = this.getIpFromEnvAndSetAccount(env);

                if (sshReq.getAccount() != null) {
                    this.account = sshReq.getAccount();
                }

                if (ip == null) {
                    throw new InvalidAttributesException();
                }

                res =  ssh.execCmd(sshReq.getCmd(), ip, this.account);
                response.put(env, res);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getIpFromEnvAndSetAccount(String envName) {
        RestTemplate rest = new RestTemplate();
        EnvironmentBean e = rest.getForObject(config.getEnvironmentGatewayUrl() + envName, EnvironmentBean.class);
        this.account = e.getAccountPrefix() + "edwdlload";
        return e.getIp();
    }


}
