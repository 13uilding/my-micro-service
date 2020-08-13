package com.revature.controller;

import com.revature.client.AnswerClient;
import com.revature.dao.QuestionDao;
import com.revature.model.AnswerModel;
import com.revature.model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestionController {

    @Autowired
    private Environment env;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerClient answerClient;


    @GetMapping("/port")
    public String getPort() {
        String port = env.getProperty("local.server.port");
        return "Hello, this came from port " + port;
    }

    @GetMapping
    public ResponseEntity<List<QuestionModel>> findAll() {
        List<QuestionModel> all = questionDao.findAll();

        if(all.isEmpty()) ResponseEntity.noContent().build();

        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionModel> findById(@PathVariable("id") int id) {
        Optional<QuestionModel> optional = questionDao.findById(id);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());

    }

    @PostMapping
    public ResponseEntity<QuestionModel> insert(@RequestBody QuestionModel question) {
        int id = question.getId();

        if (id != 0) return ResponseEntity.badRequest().build();

        questionDao.save(question);
        return ResponseEntity.status(201).body(question);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuestionModel> delete(@PathVariable("id") int id) {

        Optional<QuestionModel> optional = questionDao.findById(id);
        if (optional.isPresent()) {
            questionDao.delete(optional.get());
            return ResponseEntity.accepted().body(optional.get());
        }

        return ResponseEntity.notFound().build();
    }

    // DIRTY
    @GetMapping("/answer")
    public String retrievePort() {
        return this.answerClient.retrievePort();
    }

    @GetMapping("/{id}/answers")
    public ResponseEntity<List<AnswerModel>> getAnswersByQuestionId(@PathVariable("id") int id) {
        Optional<QuestionModel> optional = questionDao.findById(id);
        if (optional.isPresent()) {
            Optional<List<AnswerModel>> optionalList = answerClient.getAnswersByQuestionId(id);
            return optionalList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
        }
        return ResponseEntity.notFound().build();
    }



}
