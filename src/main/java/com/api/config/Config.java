package com.api.config;

import com.api.constants.Environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Config {
    private static Properties appProps;

    static {
        try {
            appProps = new Properties();
            appProps.load(new BufferedReader(new InputStreamReader(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties"))));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Environment getEnvironment() {
        return Environment.valueOf(appProps.getProperty("runtime.environment"));

    }

    public static int getSparkPort() {
        return Integer.parseInt(appProps.getProperty("spark.port"));

    }

    public static String getDomainUrl() {
        return appProps.getProperty("api.domain.host") + ":" + appProps.getProperty("api.domain.port") ;

    }

}


