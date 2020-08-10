package com.revature.MyMicroService.Model;

import lombok.*;

import javax.persistence.*;

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
}

