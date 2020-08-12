package com.revature.MyMicroService.DAO;

import com.revature.MyMicroService.Model.AnswerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnswerDao extends JpaRepository<AnswerModel, Integer> {

    @Query(nativeQuery = true, value = "select * from msa_example.answers a where a.post_date >= :date")
    Optional<List<AnswerModel>> findAllAfterDate(@Param("date") LocalDate date);

    @Query(nativeQuery = true, value = "select * from msa_example.answers a where a.post_date <= :date")
    Optional<List<AnswerModel>> findAllBeforeDate(@Param("date") LocalDate date);

    @Query(nativeQuery = true, value = "select * from msa_example.answers a where a.post_date >= :startDate and a.post_date <= :endDate")
    Optional<List<AnswerModel>> findAllBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(nativeQuery = true, value = "select * from msa_example.answers a where a.question_id = :id")
    Optional<List<AnswerModel>> findAllByQuestionId(@Param("id") int id);
}
