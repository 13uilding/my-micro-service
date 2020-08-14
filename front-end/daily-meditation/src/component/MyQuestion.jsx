import React from 'react';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core';
import MyAnswer from './MyAnswer';

const useStyles = makeStyles({
  root: {
    minWidth: 275,
    backgroundColor: '#303030',
    opacity: 0.95,
    margin: '20px 10px 0 10px',
  },
  title: {
    // Play with this size
    fontSize: '1.5rem',
    fontWeight: 'bold'
  },
  questionText: {
    fontSize: '1rem'
  },
  pos: {
    marginBottom: 12,
  }
})

export default function MyQuestion(props) {
  const classes = useStyles();

  return (
    <Card className={classes.root} variant="outlined">
      <CardContent>
        <Typography className={classes.title} color="textSecondary" gutterBottom>
          Question {props.questionId}
        </Typography>
        <Typography className={classes.questionText} color="textSecondary">
          {props.question}
        </Typography>
        {(props.answers && props.answers.length > 0 ? 
          <MyAnswer></MyAnswer>
          : <></>
        )}
      </CardContent>
    </Card>
  )
}