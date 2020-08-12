package com.revature.MyMicroService.Controller;

import com.revature.MyMicroService.DAO.QuestionDao;
import com.revature.MyMicroService.Model.AnswerModel;
import com.revature.MyMicroService.Model.QuestionModel;
import com.revature.MyMicroService.Service.AnswerService;
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

     @Autowired
     private AnswerService answerService;

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


    @GetMapping("/{id}/answers")
    public ResponseEntity<List<AnswerModel>> getAnswersByQuestionId(@PathVariable("id") int id) {
        Optional<QuestionModel> optional = questionDao.findById(id);
        if (optional.isPresent()) {
            Optional<List<AnswerModel>> optionalList = answerService.getAnswersByQuestionId(optional.get().getId());
            return optionalList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
        }
        return ResponseEntity.notFound().build();
    }

}
