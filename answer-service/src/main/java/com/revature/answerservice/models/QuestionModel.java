package com.revature.answerservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions", schema = "msa_example")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class QuestionModel {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question;

    // DIRTY
    @JsonIgnoreProperties({"answer", "question", "date"})
    @OneToMany(mappedBy = "question", orphanRemoval = true)
    private List<AnswerModel> answer;
}
