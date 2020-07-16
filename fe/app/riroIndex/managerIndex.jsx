import React from 'react'
import ReactDOM from 'react-dom';

import VoteTable from './managerVote.jsx';
import AuditionTable from './myAudition.jsx';
import './riroIndex.css';


class ManagerIndex extends React.Component {
    render() {
        return (
            <div id="riroindex">



                <h2>투표현황</h2>
                <VoteTable></VoteTable> 
            </div>
        );
      }
}


ReactDOM.render(<ManagerIndex/>,document.getElementById('managerIndex'));
