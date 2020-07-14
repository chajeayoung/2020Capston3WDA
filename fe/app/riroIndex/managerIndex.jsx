import React from 'react'
import ReactDOM from 'react-dom';
import ManageVote from './../userInfo/manageVote.jsx';

class ManagerIndex extends React.Component {
    render() {
        return (
            <div>관리자
                    <ManageVote/>   
            </div>
        );
      }
}


ReactDOM.render(<ManagerIndex/>,document.getElementById('managerIndex2'));
