package com.revature.controller;

import com.revature.client.AnswerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    @Autowired
    private Environment env;

    @Autowired
    private AnswerClient answerClient;

    @GetMapping("/port")
    public String getPort() {
        String port = env.getProperty("local.server.port");
        return "Hello, this came from port " + port;
    }

    @GetMapping("/answer")
    public String retrievePort() {
        return this.answerClient.retrievePort();
    }




}
