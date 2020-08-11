package com.revature.MyMicroService.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions", schema = "msa_example")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString
public class QuestionModel {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question;

    @JsonIgnoreProperties({"answer", "question", "date"})
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<AnswerModel> answer;

}

