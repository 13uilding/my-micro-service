package com.revature.MyMicroService.Model;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @ToString
public class AnswerControllerModel {

    private String answer;
    private int question_id;
    private int id = 0;
    private LocalDate post_date = LocalDate.now();
    private QuestionModel question;

    public AnswerModel makeIntoAnswerModel() {
        return new AnswerModel(this.id, this.answer, this.question, this.post_date);
    }

    public AnswerControllerModel(int id, String answer, int question_id, LocalDate post_date) {
        this.id = 0;
        this.answer = answer;
        this.question_id = question_id;
        this.post_date = (post_date == null) ? LocalDate.now() : post_date;
    }

}
