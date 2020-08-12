package com.revature.MyMicroService.Service;

import com.revature.MyMicroService.DAO.AnswerDao;
import com.revature.MyMicroService.DAO.QuestionDao;
import com.revature.MyMicroService.Model.AnswerModel;
import com.revature.MyMicroService.Model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    AnswerDao answerDao;

    public QuestionModel getQuestion(int id) {
        Optional<QuestionModel> optional = questionDao.findById(id);
        return optional.orElse(new QuestionModel());
    }

    public Optional<List<AnswerModel>> getAnswersByQuestionId(int id) {
        return answerDao.findAllByQuestionId(id);
    }
}
