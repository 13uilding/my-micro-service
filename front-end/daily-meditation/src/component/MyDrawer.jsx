import React from 'react';
import clsx from 'clsx';
import { makeStyles } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import Button from '@material-ui/core/Button';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import CommentIcon from '@material-ui/icons/Comment';
import InboxIcon from '@material-ui/icons/MoveToInbox';
import MailIcon from '@material-ui/icons/Mail';
import ChildCareIcon from '@material-ui/icons/ChildCare';
import MenuIcon from '@material-ui/icons/Menu';
import { Paper, Box, Typography, TextareaAutosize } from '@material-ui/core';
import { Link as RouterLink } from 'react-router-dom';
import Link from '@material-ui/core/Link';

const useStyles = makeStyles({
  list: {
    width: 250,
    background: "#424242",
    opacity: 0.95
  },
  fullList: {
    width: 'auto',
  },
  root: {
    background: "#424242",
    opacity: 0.95
  },
  listItem: {
    // background: '#303030',
    '&:hover': {
      background: '#9778ce'
    }
  },
  lineColor: {
    backgroundColor: '#9adcfb',
    marginBlockStart: 0,
    marginBlockEnd: 0
  },
  drawerModifications: {
    height: 'auto'
  }
});

export default function MyDrawer() {
  const classes = useStyles();
  const [state, setState] = React.useState({
    top: false,
  });

  const toggleDrawer = (anchor, open) => (event) => {
    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
      return;
    }

    setState({ ...state, [anchor]: open });
  };

  const list = (anchor) => (
    <div
      className={clsx(classes.list, {
        [classes.fullList]: anchor === 'top',
      })}
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <List>
        {['Routines', 'Answers'].map((text, index) => (
          <Link component={RouterLink} to={text.toLowerCase()}>
            <ListItem button key={text} className={clsx(classes.listItem)} onClick={(e)=>console.log(e.target)}>
              <ListItemIcon>{index % 2 === 0 ? <ChildCareIcon /> : <CommentIcon />}</ListItemIcon>
              <ListItemText primary={text} />
            </ListItem>
          </Link>
        ))}
      </List>
      <Divider  className={clsx(classes.lineColor)} />
      <List>
        {['In Progress'].map((text, index) => (
          <Link component={RouterLink} to={text.toLowerCase().split(" ").join("-")}>
            <ListItem button key={text} className={clsx(classes.listItem)} >
                <ListItemIcon>{index % 2 === 0 ? <InboxIcon /> : <MailIcon />}</ListItemIcon>
                <ListItemText primary={text} />
            </ListItem>
          </Link>
        ))}
      </List>
    </div>
  );

  return (
    <div className={`${classes.root} myNavContainer`} >
      <div className='myFlexContainer'>
        <Box>
          <Button onClick={toggleDrawer("top", true)}>
            <MenuIcon></MenuIcon>
          </Button>
        </Box>
        <Box>
          <Typography color="primary">
            Daily Meditation
          </Typography>
        </Box>
        <Box className='minWidth50'>
        </Box>
        {/* Change this to a swipeable drawer? */}
        <Drawer 
          anchor={"top"} 
          open={state["top"]} 
          onClose={toggleDrawer("top", false)}
          elevation='16'
          className={clsx(classes.drawerModifications)}
        >
          {list("top")}
        </Drawer>
      </div>
    </div>
  );
}
