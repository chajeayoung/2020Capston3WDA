import React from 'react'
import ReactDOM from 'react-dom';
import ItemCard5 from '../items/ItemCard5.jsx';
// import ItemCard2 from '../items/itemCard2.jsx';
import VoteResult from './voteResult.jsx'

import './votePreShow.css'
import './css/voteDoShow.css'
const axios = require('axios');

var url = document.location.href;
const num = url.split('/');
var param = num[num.length-1];
// url.substr(url.length-1,1);

const regeneratorRuntime = require("regenerator-runtime");


class VoteShow extends React.Component {
    constructor(props){
        super(props);
    }
    
    render() {
        
        return this.props.votes.map((vote,index)=>{
            if (vote.name != 0){
                return (
                    <div key={vote.name+index} className="card_div" > 
                        <ItemCard5 key={vote.img} img={vote.img} name={vote.name} result={this.props.data.data[index]} count={this.props.data.count}
                        win={this.props.data.win.includes(this.props.data.data[index])} show={this.props.data.show}
                        result={1}/>
                    </div>
                )
            }
        })
    }
}

class Show extends React.Component{

    constructor(props){
        super(props);
        this.state = { votes: [], title: "",program:{img:"검정고무신.png",name:"검정고무신"}, date:{startTime:"000",endTime:"0000",resultShowTime:"0000"},
        showCandidate:0};
        this.stTime;
        this.edTime;
        this.rsTime;
        this.voteData = {data:[],count:0, win:[],winNum:0, show:1}
    }

    async componentDidMount(){

        let {data} = await axios.get('/vote/axios/'+param);
        this.setState({votes : data[0], title : data[1], program:data[2], date: data[3],selectNum:data[4], canNum:data[5]});
        $("header").css("background-image","url(/uploads/"+data[2].logo+")")
        $("header").css("background-position","top")
        $("header").css("background-repeat","no-repeat")
        $("header").css("background-size","contain")
        $("#showCandidate").css("background-color", "rgb(245, 169, 169)")
    }
    setDate(){
        var start = String(this.state.date.startTime);
        var end = String(this.state.date.endTime);
        var resultShow = String(this.state.date.resultShowTime);
        
        
        this.stTime = start.substr(0,4)+"-"+start.substr(4,2)+"-"+start.substr(6,2)+" "+start.substr(8,2)+":"+start.substr(10,2);
        this.edTime = end.substr(0,4)+"-"+end.substr(4,2)+"-"+end.substr(6,2)+" "+end. substr(8,2)+":"+end.substr(10,2);
        this.rsTime = resultShow.substr(0,4)+"-"+resultShow.substr(4,2)+"-"+resultShow.substr(6,2)+" "+resultShow. substr(8,2)+":"+resultShow.substr(10,2);
    }
    
    onSubmitVoteResult(data){
        console.log("데이터 받음")
        var data_list = [];
        var winnerData = [];
        
        //object 형태여서, List 로 변환
        for(var i=0; i<data.data.length; i++){
            data_list.push(data.data[i]);
        }
        console.log("받은 데이터들을 출력합니다."+data_list)
        for(var i=0; i<data.win; i++){
            winnerData.push(Math.max.apply(Math, data_list));
            var index = data_list.indexOf(Math.max.apply(Math, data_list));
            if (index !== -1) data_list.splice(index, 1);
            
            console.log(index);
        }
        // https://stackoverflow.com/questions/32647149/why-is-math-max-returning-nan-on-an-array-of-integers
        
        this.voteData = {data : data.data, count : data.count, win : winnerData, winNum : data.win, show:0}
        console.log("aaaaa", this.voteData)

        this.forceUpdate()
    }
    showCandidate(){
        this.setState({showCandidate:0})
        $("#showCandidate").css("background-color","rgb(245, 169, 169)")
        $("#showResult").css("background-color","#e0ecf8")
    }
    showResult(){
        this.setState({showCandidate:1})
        $("#showCandidate").css("background-color","#e0ecf8")
        $("#showResult").css("background-color","rgb(245, 169, 169)")
    }
    render(){
        console.log("render")
        const {title} = this.state.title
        this.setDate();
        return(
            <div id="itemTopDiv">
                <div className="topDiv">
                    {/* <h2>투표</h2> */}
                    <div className="circle">투표 마감</div>
                    {/* https://basketdeveloper.tistory.com/4 */}
                </div>
                
                <div className="list_a_tag"><a href="/vote">목록</a></div>
                <div className="div_center"><h3>{title}</h3></div>
                <div>
                    <button onClick={this.showCandidate.bind(this)} id="showCandidate" className="showButton">후보</button>
                    <button onClick={this.showResult.bind(this)} id="showResult" className="showButton">결과</button>
                </div>
                <div id="voteDate">
                    <div className="text_center br_div">투표기간</div>
                    <div className="text_center">시작: {this.stTime}</div>
                    <div className="text_center">마감: {this.edTime}</div>
                    <div className="text_center">집계공개: {this.rsTime}</div>
                    <div className="text_center">선발인원: {this.state.selectNum}&nbsp;명</div>
                    <div className="text_center vote_during">투표가능 횟수: {this.state.canNum}&nbsp;번</div>
                </div>
                
                {/* <div>
                    <button onClick={this.showCandidate.bind(this)} id="showCandidate" className="showButton">후보</button>
                    <button onClick={this.showResult.bind(this)} id="showResult" className="showButton">결과</button>
                </div>
                <div className="text_center show_result">★☆공동 우승자가 있을 경우 우승인원이 선발인원보다 많아 질 수 있습니다.☆★</div> */}
                
                <div className="left_right_box">
                    {
                        this.state.showCandidate ==0? (
                            <div id="item">
                            <div className="candidate">&lt;&lt; 후보 정보 &gt;&gt;</div>
                            <div className="candidate_op">★☆마감된 투표입니다.☆★</div>
                            <div className="cards">
                                <VoteShow votes={this.state.votes} data={this.voteData}/>   
                            </div>
                        </div> 
                        ):<div></div>
                    }     
                    <div className="right_div_box">
                    {/* <div className="show_result">★☆마감 결과☆★</div> */}
                        <div className="vote_result">
                            <VoteResult event={this.onSubmitVoteResult.bind(this) } showCandidate={this.state.showCandidate}/>
                        </div>
                    </div>
                </div>
                     
            </div>
        )
    }
        
}

ReactDOM.render(<Show/>,document.getElementById('voteShow'));
