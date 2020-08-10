package com.revature.MyMicroService.Controller;

import com.revature.MyMicroService.DAO.QuestionDao;
import com.revature.MyMicroService.Model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
