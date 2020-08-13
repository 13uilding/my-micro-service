package com.revature.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "questions", schema = "msa_example")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class QuestionModel {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question;

    // DIRTY
//    @JsonIgnoreProperties({"answer", "question", "date"})
//    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
//    private List<AnswerModel> answer;
}
