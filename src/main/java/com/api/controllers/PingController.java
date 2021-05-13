package com.api.controllers;

import spark.Request;
import spark.Response;

public class PingController {
    private PingController(){}

    public static Object ping(Request req, Response res) {
        return "pong";
    }
}
