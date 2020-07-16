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
                <hr></hr>
                <h2>나의 오디션 신청현황</h2>
                <AuditionTable id="vote"></AuditionTable> 
                <a class="btn btn-warning center" href='/userInfo/myAudition'>더보기</a>

                <hr></hr>
                <h2>나의 참여 투표</h2>
                <VoteTable id="vote"></VoteTable> 
                <a class="btn btn-warning center" href='/userInfo/voter'>더보기</a>

                <hr></hr>
                <h2>커뮤니티 목록</h2>
                <CommunityItem ></CommunityItem>    
                <a class="btn btn-warning center" href='/community'>더보기</a>

                <hr></hr>

            </div>
        );
      }
}


ReactDOM.render(<UserIndex/>,document.getElementById('userIndex'));
