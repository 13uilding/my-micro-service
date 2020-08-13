package com.revature.client;

import com.revature.model.AnswerModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@FeignClient(name = "answer")
public interface AnswerClient {

    @GetMapping("/port")
    String retrievePort();

    @GetMapping("/question/{id}")
    Optional<List<AnswerModel>> getAnswersByQuestionId(@PathVariable("id") int id);

}
