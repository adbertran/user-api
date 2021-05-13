package com.api.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PingControllerTest {
    @Test
    public void testPing(){
        assertEquals("pong",PingController.ping(null,null));
    }

}