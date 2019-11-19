package com.domain.area.edgenodesshexecutor;


import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class SSH {

    private static final Logger logger = LoggerFactory.getLogger(SSH.class.getName());

    @Autowired
    private Config config;

    public SSH() {

    }


    public SshResponse execCmd(String command, String srcIp, String account) throws IOException {

        Connection conn = new Connection(srcIp);

        /* Now connect */

        conn.connect();

        /* Authenticate */

        boolean isAuthenticated = conn.authenticateWithPublicKey(account, config.getKey(), null);

        if (!isAuthenticated)
            throw new IOException("Authentication failed.");

        /* Create a session */

        Session sess = conn.openSession();
        sess.execCommand(command);

        InputStream stdout = new StreamGobbler(sess.getStdout());
        InputStream stderr = new StreamGobbler(sess.getStderr());

        BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
        BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr));

        String output = "";

        while (true) {
            String line = stderrReader.readLine();
            if (line == null)
                break;
            if (!line.contains("WARN util.NativeCodeLoader")
                    && !line.contains("WARN shortcircuit.DomainSocketFactory")) { // suppresses
                // hadoop
                // warnings
                output += line + "\n";
            }
        }

        while (true) {
            String line = stdoutReader.readLine();
            if (line == null)
                break;
            output += line + "\n";
        }

        stdoutReader.close();
        stderrReader.close();
        sess.close();
        conn.close();
        return new SshResponse(sess.getExitStatus(), output);
    }

}