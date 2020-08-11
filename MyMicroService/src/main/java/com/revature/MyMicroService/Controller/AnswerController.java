package com.revature.MyMicroService.Controller;

import com.revature.MyMicroService.DAO.AnswerDao;
import com.revature.MyMicroService.DAO.QuestionDao;
import com.revature.MyMicroService.Model.AnswerControllerModel;
import com.revature.MyMicroService.Model.AnswerModel;
import com.revature.MyMicroService.Service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequestMapping("/answer")
@RestController
public class AnswerController {

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private AnswerService answerService;

    // Microservice SLICE
    @Autowired
    private QuestionDao questionDao;

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

    // This might be changed. We'll see how parsing works here
    @GetMapping("/after/{post_date}")
    public ResponseEntity<List<AnswerModel>> getAnswersAfterDate(@PathVariable("post_date") String strDate) {
        LocalDate date = LocalDate.parse(strDate);
        Optional<List<AnswerModel>> optional = answerDao.findAllAfterDate(date);
        /*
        Replaced this
        if (optional.isPresent()) return ResponseEntity.ok(optional.get());
        return ResponseEntity.noContent().build();
        with that
         */
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
    @GetMapping("/before/{post_date}")
    public ResponseEntity<List<AnswerModel>> getAnswersBeforeDate(@PathVariable("post_date") String strDate) {
        LocalDate date = LocalDate.parse(strDate);
        Optional<List<AnswerModel>> optional = answerDao.findAllBeforeDate(date);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/range/{startDate}/{endDate}")
    public ResponseEntity<List<AnswerModel>> getAnswersInRange(@PathVariable("startDate") String strStart, @PathVariable("endDate") String strEnd) {
        LocalDate startDate = LocalDate.parse(strStart);
        LocalDate endDate = LocalDate.parse(strEnd);
        Optional<List<AnswerModel>> optional = answerDao.findAllBetweenDates(startDate, endDate);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<AnswerModel> insert(@RequestBody AnswerControllerModel answer) {
        int id = answer.getId();
        if (id != 0) return ResponseEntity.badRequest().build();
        // Gonna need to split here
        int questionId = answer.getQuestion_id();
        // This potentially creates a new question without
        answer.setQuestion(answerService.getQuestion(questionId));
        if (answer.getQuestion().getId() != 0) {
            AnswerModel answerSaved = answer.makeIntoAnswerModel();
            answerDao.save(answerSaved);
            return ResponseEntity.status(201).body(answerSaved);
        }
        return ResponseEntity.badRequest().build();
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

}
