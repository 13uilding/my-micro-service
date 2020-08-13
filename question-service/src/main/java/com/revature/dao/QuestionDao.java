package com.revature.dao;

import com.revature.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionDao extends JpaRepository<QuestionModel, Integer> {
}
