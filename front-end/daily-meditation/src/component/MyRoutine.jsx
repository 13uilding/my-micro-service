import React, { useState } from 'react';
import { Grid, Typography, makeStyles, Divider, Collapse } from '@material-ui/core';
import MyQuestion from './MyQuestion';
import clsx from 'clsx';

const useStyles = makeStyles({
  lineColor: {
    backgroundColor: '#9adcfb',
    marginBlockStart: 0,
    marginBlockEnd: 0
  },
  routine: {
    "&:hover": {
      cursor: 'pointer',
      textDecoration: 'underline',
      background: '#9778ce'
    }
  }
})
export default function MyRoutine(props) {
  const classes = useStyles();

  const [state, setState] = useState({
    showRoutine: false
  })

  const toggleRoutine = (e) => {
    console.log(e.target);
    setState({...state, showRoutine: !state.showRoutine});
  }

  return (
    <>
      <Grid item xs={12}>
        <Divider className={clsx(classes.lineColor)}></Divider>
        <Typography 
          variant='h2' 
          display='block' 
          align='center' 
          color='primary'
          onClick={toggleRoutine}
          className={classes.routine}
        >
          {`Routine ${props.routineId}`}
        </Typography>
        <Divider className={clsx(classes.lineColor)}></Divider>
      </Grid>
      {/* Right here I only want to display if they click on it */}
      <Grid item container alignContent='center' justify='flex-start' wrap='wrap' xs={12} >
        <Collapse in={state.showRoutine} container>
          <Grid item container alignContent='center' justify='flex-start' wrap='wrap' xs={12}>
            {props.questions.map(question => {
              return (
                <Grid item xs={6} key={question.id}>
                  <MyQuestion question={question.question} questionId={question.id}></MyQuestion>
                </Grid>
              )
            })}
          </Grid>
        </Collapse>
      </Grid>
    </>
  )
}

