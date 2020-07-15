import React from 'react'
import ReactDOM from 'react-dom';
import VoteTable from './managerVote.jsx';
import './riroIndex.css';


class ManagerIndex extends React.Component {
    render() {
        return (
            <div>


                <h2>오디션 모집현황</h2>
                <VoteTable id="vote"></VoteTable> 

                <h2>투표현황</h2>
                <VoteTable id="vote"></VoteTable> 
            </div>
        );
      }
}


ReactDOM.render(<ManagerIndex/>,document.getElementById('managerIndex'));
