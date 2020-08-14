import { axiosClient } from "../axios";

export const getAllQuestions = axiosClient.get("/question");
  // .then(res => {
  //   let questions = res.data;
  //   questions.map(question => console.log(question.question));
  //   return questions;
  // }, 
  // err => console.log(err.message))