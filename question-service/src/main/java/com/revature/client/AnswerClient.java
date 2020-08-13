package com.revature.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "answer")
public interface AnswerClient {

    @GetMapping("/port")
    String retrievePort();

}
