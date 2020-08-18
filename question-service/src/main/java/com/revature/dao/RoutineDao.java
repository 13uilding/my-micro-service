package com.revature.dao;

import com.revature.model.RoutineModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineDao extends JpaRepository<RoutineModel, Integer> {
}
