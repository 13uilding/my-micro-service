package com.revature.controller;

import com.revature.dao.QuestionDao;
import com.revature.dao.RoutineDao;
import com.revature.model.QuestionModel;
import com.revature.model.RoutineModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/routine")
@RestController
public class RoutineController {

    @Autowired
    RoutineDao routineDao;

    @Autowired
    QuestionDao questionDao;

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

    @PostMapping
    public ResponseEntity<RoutineModel> insert(@RequestBody RoutineModel routine) {
        int id = routine.getId();

        if (id != 0) return ResponseEntity.badRequest().build();

        routineDao.save(routine);
        return ResponseEntity.status(201).body(routine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoutineModel> delete(@PathVariable("id") int id) {
        Optional<RoutineModel> optional = routineDao.findById(id);
        if (optional.isPresent()) {
            routineDao.delete(optional.get());
            return ResponseEntity.accepted().body(optional.get());
        }

        return ResponseEntity.notFound().build();
    }
//
//    // I need to add a mapping to get questions from a routine
//    @GetMapping("/{id}/questions")
//    public ResponseEntity<List<QuestionModel>>

    @PutMapping("/{id}")
    public ResponseEntity<RoutineModel> addQuestionToRoutine(@PathVariable("id") int id, @RequestBody int questionId) {
        // Check to see if routine in database
        Optional<RoutineModel> optionalR = routineDao.findById(id);
        if (!optionalR.isPresent()) return ResponseEntity.notFound().build();
        // Check to see if question in database
        Optional<QuestionModel> optionalQ = questionDao.findById(questionId);
        if (optionalQ.isPresent()) {
            // Check to see if question is already in routine
            if (optionalR.get().getQuestions().contains(optionalQ.get())) {
                // Might have to override the isEquals() method
                return ResponseEntity.badRequest().build();
            } else {
                // Add the question

                System.out.println("Error after here?");
                routineDao.addQuestionToRoutine(questionId, id);
        System.out.println("Error before here?");
                return ResponseEntity.ok(optionalR.get());

            }

        }
        return ResponseEntity.badRequest().build();


    }
}
