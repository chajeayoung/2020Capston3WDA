import React from 'react'
import ReactDOM from 'react-dom';

import VoteTable from './voterVoteList.jsx';
import AuditionTable from './myAudition.jsx';
import CommunityItem from './communityItem.jsx';
import './riroIndex.css';


class UserIndex extends React.Component {
    render() {
        return (
            <div id="riroindex">

                <h2>나의 오디션 신청현황</h2>
                <AuditionTable id="vote"></AuditionTable> 

                <h2>나의 참여 투표</h2>
                <VoteTable id="vote"></VoteTable> 

                <h2>커뮤니티 목록</h2>
                <CommunityItem ></CommunityItem>

            </div>
        );
      }
}


ReactDOM.render(<UserIndex/>,document.getElementById('userIndex'));
