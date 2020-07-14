import React from 'react'
import ReactDOM from 'react-dom';
import VoteTable from './../userInfo/manageVote.jsx';

class UserIndex extends React.Component {
    render() {
        return (
            <div>일반
                        <VoteTable></VoteTable> 
            </div>
        );
      }
}


ReactDOM.render(<UserIndex/>,document.getElementById('userIndex'));
