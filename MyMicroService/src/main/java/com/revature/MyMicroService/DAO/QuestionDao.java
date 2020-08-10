package com.revature.MyMicroService.DAO;

import com.revature.MyMicroService.Model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionDao extends JpaRepository<QuestionModel, Integer> {

}