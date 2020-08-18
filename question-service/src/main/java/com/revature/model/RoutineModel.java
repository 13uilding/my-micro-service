package com.revature.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "routine", schema = "msa_example")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class RoutineModel {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;


    @JsonIgnoreProperties({"routines"})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(schema = "msa_example", name = "question_routine", joinColumns = @JoinColumn(name = "routine_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    List<QuestionModel> questions;
}
