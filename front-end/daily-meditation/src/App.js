import React from 'react';
import './App.css';
import { getAllQuestions } from './api/question';
import { axiosClient } from './api/axios';
import MyDrawer from './component/MyDrawer';
import { createMuiTheme, MuiThemeProvider } from '@material-ui/core/styles';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import RoutinePage from './page/RoutinePage';

const theme = createMuiTheme({
  palette: {
    primary: {
      main: '#9adcfb',
    },
    secondary: {
      main: '#9778ce',
    },
    contrastThreshold: 3,
    text: {
      primary: '#9adcfb',
      secondary: '#9778ce'
    },
    background: '#303030',
  },
  overrides: {
    MuiDrawer: {
      paper: {
        height: 'auto',
      }
    },
    // MuiCollapse: {
    //   wrapper: {
    //     width: '100%'
    //   }
    // }
  }

})


function App() {
  return (
    <MuiThemeProvider theme={theme}>
      <div className="container">
        <Router>
          <MyDrawer></MyDrawer>
          <Switch>
            <Route path="/routines">
              <RoutinePage></RoutinePage>
            </Route>
            <Route path="/answers">
              Answer
            </Route>
            <Route path="/in-progress">
              create routine
            </Route>
          </Switch>
        </Router>
      </div>
    </MuiThemeProvider>
  );
}

export default App;
