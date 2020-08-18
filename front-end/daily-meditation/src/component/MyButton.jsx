import React from 'react';
import { ButtonBase } from '@material-ui/core';

export default function MyButton(props) {
  return (
    <ButtonBase
      component='input'
      value={props.buttonId}
      name={props.buttonName}
      onClick={props.onClick}
      className={props.className}
    >
      {props.children}
    </ButtonBase>
  )
}

// <Button
// color='primary'
// variant='outlined'
// size='large'
// className={classes.buttonStyle}
// id={props.routineId}
// value={props.routineId}
// onClick={doRoutine}
// >
// Do Routine
// </Button>