package com.revature.dao;

import com.revature.model.RoutineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

//@Configuration
//@EnableTransactionManagement
//@Repository
//@EnableJpaRepositories
public interface RoutineDao extends JpaRepository<RoutineModel, Integer> {

    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "INSERT INTO msa_example.question_routine(question_id, routine_id) values(:question_id, :routine_id)"
    )
    public void addQuestionToRoutine(@Param("question_id") int questionId, @Param("routine_id") int routineId);

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    public void addQuestionToRoutine(@Param("question_id") int questionId, @Param("routine_id") int routineId) {
//      entityManager.createNativeQuery("INSERT INTO msa_example.question_routine(question_id, routine_id) values(:question_id, :routine_id)")
//        .setParameter("question_id", questionId)
//        .setParameter("routine_id", routineId)
//        .executeUpdate();
//    }
}
