package com.revature.answerservice.client;

import com.revature.answerservice.models.QuestionModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "question")
public interface QuestionClient {

    @GetMapping("/{id}")
    QuestionModel getQuestionById(@PathVariable("id") int id);
}
