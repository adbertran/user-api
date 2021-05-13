package com.api.router;

import com.api.controllers.PingController;
import spark.Spark;
import spark.servlet.SparkApplication;

import static spark.Spark.get;

public class Router implements SparkApplication {
    @Override
    public void init() {
        get("/ping", PingController::ping);

        Spark.notFound((req, res) -> {res.type("application/json");
            return "{\"message\":\"Invalid URL.\"}";});

        Spark.exception(Exception.class, new ApiExceptionHandler<>());
    }
}
