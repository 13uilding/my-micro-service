import React, { useState, useEffect } from 'react';
import { getAllQuestions } from '../api/question';
import MyQuestion from '../component/MyQuestion';
import { Grid } from '@material-ui/core';


export default function RoutinePage() {
  const [questions, setQuestions] = useState([]);
  useEffect(async () => {
    let questions = await getAllQuestions;
    console.log(questions.data);
    setQuestions(questions.data);
  }, []);  

  return (
    // Adjust the scroll bar later
    <div className="myPage" id="routinePage">
      <Grid container spacing={3} alignContent='center' justify='flex-end' className="myWidth">
        {questions.map(question => {
          return (
            <Grid item xs={6}>
              <MyQuestion key={question.id} question={question.question} questionId={question.id}></MyQuestion>
            </Grid>
          )
        })}
      </Grid>
    </div>
  )
}