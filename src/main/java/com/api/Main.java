package com.api;

import com.api.config.Config;
import com.api.router.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main (String[] args){
        Spark.port(Config.getSparkPort());
        new Router().init();

        log.info("Listening on http://localhost:{}/{}" , Config.getSparkPort(),"api");
    }
}
