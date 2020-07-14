import React from 'react'
import ReactDOM from 'react-dom';
import ManageVote from './../userInfo/manageVote.jsx';

class UserIndex extends React.Component {
    render() {
        return (
            <div>일반
                        <ManageVote/>   
            </div>
        );
      }
}


ReactDOM.render(<UserIndex/>,document.getElementById('userIndex'));
