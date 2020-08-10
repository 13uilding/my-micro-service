package com.revature.MyMicroService.Controller;

import com.revature.MyMicroService.DAO.QuestionDao;
import com.revature.MyMicroService.Model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionController {

     @Autowired
     private QuestionDao questionDao;

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

/*
    @GetMapping("/question/{id}/answers")
    public ResponseEntity<List<Answer>> getAnswersByQuestionId(@PathVariable("id") int id) {
        // First find the question
        // Then find the answers corresponding to that question
    }

    Tying date into this later might be a bit difficult.
     */
}
