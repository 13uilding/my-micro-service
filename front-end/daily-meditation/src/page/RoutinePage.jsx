import React, { useState, useEffect } from 'react';
import { getAllQuestions } from '../api/question';
import MyQuestion from '../component/MyQuestion';
import { makeStyles, Typography } from '@material-ui/core';
import { Grid } from '@material-ui/core';
import MyRoutine from '../component/MyRoutine';
import { getAllRoutines } from '../api/routine';

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
  // const [questions, setQuestions] = useState([]);
  const [routines, setRoutines] = useState([]);
  useEffect( () => {
    try {
      // console.log("Attempting to get questions")
      // getQuestions();
      console.log("Attempting to get routines")
      getRoutines();
    } catch(e) {
      console.error(e);
    }
  }, []);  

  // const getQuestions = async () => {
  //   let questions = await getAllQuestions();
  //   setQuestions(questions);
  // }

  const getRoutines = async () => {
    let routines = await getAllRoutines();
    setRoutines(routines);
  }

  return (
    // Adjust the scroll bar later
    <div className="myPage" id="routinePage">
      <Grid container alignContent='center' justify='flex-end' className={classes.root} wrap='wrap'>

        {routines.map(routine => {
          return (
            <MyRoutine 
              routineName={routine.name} 
              routineid={routine.id} 
              questions={routine.questions}
            >
            </MyRoutine>
          )
        })}
      </Grid>
    </div>
  )
}