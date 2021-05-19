package com.api.router;

import com.api.controllers.PingController;
import com.api.controllers.UserController;
import spark.Spark;
import spark.servlet.SparkApplication;

import static spark.Spark.*;

public class Router implements SparkApplication {
    @Override
    public void init() {

        get("/ping", PingController::ping);

        get("/user/:user_id", UserController::getUser);
        post("/user",UserController::createUser);
        put("/user/:user_id", UserController::updateUser);
        delete("/user/:user_id", UserController::deleteUserById);


        Spark.notFound((req, res) -> {res.type("application/json");
            return "{\"message\":\"Invalid URL.\"}";});

        Spark.exception(Exception.class, new ApiExceptionHandler<>());
    }
}
