package com.revature.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions", schema = "msa_example")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class QuestionModel {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question;

    // Routines
    @JsonIgnoreProperties({"questions"})
    @ManyToMany(mappedBy = "questions", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    List<RoutineModel> routines;

    // Answers
    @JsonIgnoreProperties({"answer", "question", "date"})
    @OneToMany(mappedBy = "question", orphanRemoval = true)
    private List<AnswerModel> answer;
}
