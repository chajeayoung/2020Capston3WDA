import React, { Component, Fragment } from "react";
import ReactDOM from "react-dom";
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css";
import "./shards-dashboard/styles/shards-dashboards.1.1.0.min.css";
import "./css/audience.css";
import Ulist from "./ulist.jsx";
import Uread from "./uread.jsx";
import Uread2 from "./uread2.jsx";
const axios = require("axios");
const regeneratorRuntime = require("regenerator-runtime");
class Audience extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <React.Fragment>
        <Switch>
          <Route exact path="/audience/ulist2"><Ulist></Ulist></Route>
          <Route exact path="/audience/uread"><Uread2></Uread2></Route>
        </Switch>
      </React.Fragment>
    );
  }
}

ReactDOM.render(<Router><Audience /></Router>, document.getElementById("audienceBoard"));