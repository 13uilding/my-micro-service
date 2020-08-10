package com.revature.MyMicroService.Controller;

import com.revature.MyMicroService.DAO.AnswerDao;
import com.revature.MyMicroService.Model.AnswerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequestMapping("/answer")
@RestController
public class AnswerController {

    @Autowired
    private AnswerDao answerDao;

    @GetMapping
    public ResponseEntity<List<AnswerModel>> getAnswers() {
        List<AnswerModel> all = answerDao.findAll();
        if (all.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(all);
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
}
