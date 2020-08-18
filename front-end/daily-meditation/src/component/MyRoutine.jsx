import React, { useState } from 'react';
import { Grid, Typography, makeStyles, Divider, Collapse, Button, ButtonBase } from '@material-ui/core';
import MyQuestion from './MyQuestion';
import clsx from 'clsx';
import MyButtonGroup from './MyButtonGroup';

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
  },
  buttonStyle: {
    marginBottom: 20
  },
  fullSizeQuestion: {
    height: '100%'
  }
})
export default function MyRoutine(props) {
  const classes = useStyles();

  const [state, setState] = useState({
    showRoutine: false,

  })

  const handleRoutineButtonClick = (routineId, selectedIndex) => {
    console.log("The routine clicked was " + routineId + " and the selected index " + selectedIndex);
    // Put this in a utility?
    switch(selectedIndex) {
      case 0:
        // send to the inprogress page
        console.log("do");
        break;
      case 1:
        // Should only be able to modify your own routines
        console.log("modify");
        break;
      case 2:
        // removes from your list of routines
        // if it is your routine maybe you can delete? Idk
        console.log("remove")
        break;
    }
  }

  const toggleRoutine = (e) => {
    console.log(e.target);
    setState({...state, showRoutine: !state.showRoutine});
  }

  const doRoutine = (e) => {
    console.log(e.currentTarget)
    console.log(e.target);
    console.log(e.currentTarget.value)
    console.log(e.currentTarget.name);

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
          {`Routine ${props.routineName}`}
        </Typography>
        <Divider className={clsx(classes.lineColor)}></Divider>
      </Grid>
      {/* Right here I only want to display if they click on it */}
      <Grid item container alignContent='center' justify='flex-start' wrap='wrap' xs={12} >
        <Collapse in={state.showRoutine}>
          <Grid item container alignContent='center' justify='flex-start' wrap='wrap' xs={12}>
            {props.questions.map(question => {
              return (
                <Grid item xs={6} key={question.id} height="100%">
                  <MyQuestion question={question.question} questionId={question.id} ></MyQuestion>
                </Grid>
              )
            })}
          </Grid>
          <Grid container item alignContent='center' justify='center' xs={12} 
              className={classes.buttonStyle}>
            {/* <Button
              color='primary'
              variant='outlined'
              size='large'
              className={classes.buttonStyle}
              id={props.routineId}
              value={props.routineId}
              onClick={doRoutine}
            >
              Do Routine
            </Button> */}
            <MyButtonGroup
              handleClick={handleRoutineButtonClick}
              routineid={props.routineid}
            >
            </MyButtonGroup>
          </Grid>
        </Collapse>
      </Grid>
    </>
  )
}

