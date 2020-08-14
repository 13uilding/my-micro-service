package com.revature.answerservice.controller;

import com.revature.answerservice.client.QuestionClient;
import com.revature.answerservice.dao.AnswerDao;
import com.revature.answerservice.models.AnswerControllerModel;
import com.revature.answerservice.models.AnswerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
public class AnswerController {

    @Autowired
    private Environment env;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private QuestionClient questionClient;

    @GetMapping("/port")
    public String getPort() {
        String port = env.getProperty("local.server.port");
        return "Hello, this came from port " + port;
    }

    // PURE ANSWER MAPPINGS
    @GetMapping
    public ResponseEntity<List<AnswerModel>> getAnswers() {
        List<AnswerModel> all = answerDao.findAll();
        if (all.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerModel> getAnswerById(@PathVariable("id") int id) {
        Optional<AnswerModel> optional = answerDao.findById(id);
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/before/{post_date}")
    public ResponseEntity<List<AnswerModel>> getAnswersBeforeDate(@PathVariable("post_date") String strDate) {
        LocalDate date = LocalDate.parse(strDate);
        Optional<List<AnswerModel>> optional = answerDao.findAllBeforeDate(date);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/after/{post_date}")
    public ResponseEntity<List<AnswerModel>> getAnswersAfterDate(@PathVariable("post_date") String strDate) {
        LocalDate date = LocalDate.parse(strDate);
        Optional<List<AnswerModel>> optional = answerDao.findAllAfterDate(date);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/range/{startDate}/{endDate}")
    public ResponseEntity<List<AnswerModel>> getAnswersInRange(@PathVariable("startDate") String strStart, @PathVariable("endDate") String strEnd) {
        LocalDate startDate = LocalDate.parse(strStart);
        LocalDate endDate = LocalDate.parse(strEnd);
        Optional<List<AnswerModel>> optional = answerDao.findAllBetweenDates(startDate, endDate);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AnswerModel> delete(@PathVariable("id") int id) {
        Optional<AnswerModel> optional = answerDao.findById(id);
        if (optional.isPresent()) {
            answerDao.delete(optional.get());
            return ResponseEntity.accepted().body(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

//     DIRTY QUESTION MAPPING
    @PostMapping
    public ResponseEntity<AnswerModel> insert(@RequestBody AnswerControllerModel answer) {
        // Gonna need to split here
        int questionId = answer.getQuestion_id();
    // This potentially creates a new question without
    System.out.println("Here is the question id " + questionId);
        answer.setQuestion(questionClient.getQuestionById(questionId));
        if (answer.getQuestion().getId() != 0) {
      System.out.println("inside the if");
            AnswerModel answerSaved = answer.makeIntoAnswerModel();
            answerDao.save(answerSaved);
            return ResponseEntity.status(201).body(answerSaved);
        }
        return ResponseEntity.badRequest().build();
    }

    // Could improve this by making it fail if it doesn't find the question by question id
    // Then make it fail if it doesn't find any answers to that question
    @GetMapping("/question/{id}")
    public ResponseEntity<List<AnswerModel>> getAnswersByQuestionId(@PathVariable("id") int id) {
        Optional<List<AnswerModel>> optional = answerDao.findAllByQuestionId(id);
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}


