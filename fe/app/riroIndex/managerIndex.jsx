import React from 'react'
import ReactDOM from 'react-dom';
import VoteTable from './test.jsx';

class ManagerIndex extends React.Component {
    render() {
        return (
            <div>관리자
                    <VoteTable></VoteTable> 
            </div>
        );
      }
}


ReactDOM.render(<ManagerIndex/>,document.getElementById('managerIndex2'));
