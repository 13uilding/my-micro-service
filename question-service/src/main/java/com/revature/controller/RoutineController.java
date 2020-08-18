package com.revature.controller;

import com.revature.dao.RoutineDao;
import com.revature.model.RoutineModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/routine")
@RestController
public class RoutineController {

    @Autowired
    RoutineDao routineDao;

    @GetMapping
    public ResponseEntity<List<RoutineModel>> getAllRoutines() {
        List<RoutineModel> routines = routineDao.findAll();

        if (routines.isEmpty()) ResponseEntity.noContent().build();

        return ResponseEntity.ok(routines);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RoutineModel> getRoutineById(@PathVariable("id") int id) {
        Optional<RoutineModel> optional = routineDao.findById(id);

        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
