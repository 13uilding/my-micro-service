import React, { useState, useEffect } from 'react';
import { getAllQuestions } from '../api/question';
import MyQuestion from '../component/MyQuestion';
import { makeStyles, Typography } from '@material-ui/core';
import { Grid } from '@material-ui/core';
import MyRoutine from '../component/MyRoutine';

const useStyles = makeStyles({
  root: {
    backgroundColor: '#424242',
    opacity: 0.95,
    margin: '20px auto 10px',
    maxWidth: '85vw',
  },
});


export default function RoutinePage() {
  const classes = useStyles();
  const [questions, setQuestions] = useState([]);
  useEffect( () => {
    try {
      console.log("Attempting to get questions")
      getQuestions();
    } catch(e) {
      console.error(e);
    }
  }, []);  

  const getQuestions = async () => {
    let questions = await getAllQuestions();
    setQuestions(questions);
  }

  return (
    // Adjust the scroll bar later
    <div className="myPage" id="routinePage">
      <Grid container alignContent='center' justify='flex-end' className={classes.root} wrap='wrap'>
        <MyRoutine routineId='1' questions={questions.filter((question) => question.id <= 5)}></MyRoutine>
        <MyRoutine routineId='2' questions={questions.filter((question) => question.id > 5 && question.id < 11)}></MyRoutine>
        <MyRoutine routineId='3' questions={questions.filter((question) => question.id > 13)}></MyRoutine>
      </Grid>
    </div>
  )
}