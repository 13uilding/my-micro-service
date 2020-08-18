package com.revature.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "answers", schema = "msa_example")
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString
public class AnswerModel {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String answer;

    // Question
    @JsonIgnoreProperties({"answer"})
    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionModel question;

    @Column(name = "post_date", nullable = false)
    private LocalDate date;

}
