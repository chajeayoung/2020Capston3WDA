import React from 'react'
import ReactDOM from 'react-dom';
import ItemCard2 from '../items/itemCard2.jsx';
import ItemCard3 from '../items/itemCard3_big.jsx';
import ItemCard5 from '../items/ItemCard5.jsx';
import './../items/itemcard5.css'
// import './voteShow.css'
import './votePreShow.css'
import "./css/addressModal.css";

// import './index.css';
// import App from './App.js';

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
    
    async viewItem(vote){       
        let {data} = await axios.get("/vote/axios/hash/"+vote.popularid);
        //this.props.setState({})
        console.log(data)
        this.props.that.setState({modal:0,state:{popularid:vote.popularid, option:0, data:data.hash ,vote:vote, pop:data.pop}})

        // var modalItem = $("modalItem");
        // modalItem.empty();
        // modalItem.append(<App />);
        //<App /> 
        //modalItem.append()
        
    }
    
    render() {
        // {this.sendSelect.bind(this,index)}
        return this.props.votes.map((vote,index)=>{
            if (vote.name != 0){
                return (
                    <div key={vote.name+index} className="card_div" onClick={this.viewItem.bind(this,vote)} > 
                        <ItemCard5 key={vote.img} img={vote.img} name={vote.name} info={vote.info} />
                   </div>

                 
                )
            }
        })
    }
}

class Show extends React.Component{

    constructor(props){
        super(props);
        this.state = { votes: [], title: "",program:{img:"defaultProfile.png",name:"준비중",info:"설명"}, date:{startTime:"000",endTime:"0000",resultShowTime:"0000",selectNum:0}
                        ,modal:1};
        this.stTime;
        this.edTime;
        this.rsTime; // 투표 집계공개 시간
    }

    async componentDidMount(){
        console.log("----test----");
        let {data} = await axios.get('/vote/axios/'+param);
        console.log(data);

        $("header").css("background-image","url(/uploads/"+data[2].logo+")")
        $("header").css("background-position","top")
        $("header").css("background-repeat","no-repeat")
        $("header").css("background-size","contain")

        console.log(data);
        this.setState({votes : data[0], title : data[1], program:data[2], date: data[3], selectNum:data[4], canNum:data[5], state:{popularid:0, option:1, data:[]}  } );
        console.log(data[2]);

        
    }
    setDate(){
        
        var start = String(this.state.date.startTime);
        var end = String(this.state.date.endTime);
        var resultShow = String(this.state.date.resultShowTime);
        
        // console.log(typeof(start));
        this.stTime = start.substr(0,4)+"-"+start.substr(4,2)+"-"+start.substr(6,2)+" "+start.substr(8,2)+":"+start.substr(10,2);
        this.edTime = end.substr(0,4)+"-"+end.substr(4,2)+"-"+end.substr(6,2)+" "+end. substr(8,2)+":"+end.substr(10,2);
        this.rsTime = resultShow.substr(0,4)+"-"+resultShow.substr(4,2)+"-"+resultShow.substr(6,2)+" "+resultShow. substr(8,2)+":"+resultShow.substr(10,2);

        
        
    }

    modalOn(){
        this.setState({modal : 0})
    }
    modalOff(){
        this.setState({modal:1})
    }

    viewCandidate(programid,popularid,hash){
        window.open("/community/"+this.state.program.id+"/"+this.state.state.popularid+"?page=0&size=10&sort=date&hash="+hash.hashtag, "Hi");

        console.log(programid)
        console.log(popularid)
        console.log(hash.hashtag)
    }


    render(){
        console.log(this.state.state)
        const {title} = this.state.title
        this.setDate();
        console.log("render")
        return(
            <div id="itemTopDiv">
                <div className="topDiv">
                    {/* <h2>투표</h2> */}
                    <div className="circle">투표 시작전</div>
                </div>
                
                <div className="list_a_tag"><a href="/vote">목록</a></div>
                <div id="item">
                    <div className="div_center"><h3>{title}</h3></div>
                    <div id="program_info">
                        <div className="img">
                        <ItemCard3 img={this.state.program.img}title={this.state.program.name}/>
                        </div>                    
                        <div className="info" style={{width: "100%"}}>
                            <div className="div_center textColor">★☆프로그램 소개☆★</div>
                            <div>{this.state.program.info ? this.state.program.info :'정보가 없습니다.'}</div>
                        </div>
                    </div>
                    <br/><br/>
                    <div className="text_center br_div vote_info_during">투표기간</div>
                    <div className="text_center vote_during">시작: {this.stTime}</div>
                    <div className="text_center vote_during">마감: {this.edTime}</div>
                    <div className="text_center vote_during">집계공개: {this.rsTime}</div>
                    <div className="text_center vote_during">선발인원: {this.state.selectNum}&nbsp;명</div>
                    <div className="text_center vote_during">투표가능 횟수: {this.state.canNum}&nbsp;번</div>
                    <div className="text_center show_result">★☆공동 우승자가 있을 경우 우승인원이 선발인원보다 많아 질 수 있습니다.☆★</div>
                    <div className="candidate">&lt;&lt; 후보 정보 &gt;&gt;</div>
                    <div className="candidate_op">★☆후보 클릭 시 관련 정보로 이동☆★</div>
                    <div className="cards">
                        <VoteShow votes={this.state.votes} that={this} program={this.state.program}/>   
                    </div>
                    
                            {
                                    this.state.modal == 0?(
                                        <div className="modal">
                                            <div className="modalContentBox">
                                                <div className="modalItem">
                                                     {this.state.state.option==0?(
                                                     <div className="votePreShowDiv">
                                                         <div><img className="votePreShowInfoImg" src={'/uploads/'+this.state.state.vote.img}></img>
                                             
                                                         <table className="votePreShowInfoTable">
                                                            <thead>
                                                                <tr>
                                                                <th>{this.state.state.vote.name}</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                <td>생년월일</td><td>{this.state.state.pop.birth.split("T")[0]}</td>
                                                                </tr>
                                                                <tr>
                                                                <td>혈액형</td><td>{this.state.state.pop.blood}</td>
                                                                </tr>
                                                                <tr>
                                                                <td>키</td><td>{this.state.state.pop.height}</td>
                                                                </tr>
                                                                <tr>
                                                                <td>몸무게</td><td>{this.state.state.pop.weight}</td>
                                                                </tr>
                                                                <tr>
                                                                <td>취미</td><td>{this.state.state.pop.hobby}</td>
                                                                </tr>
                                                                <tr>
                                                                <td>특기</td><td>{this.state.state.pop.ability}</td>
                                                                </tr>
                                                                <tr>
                                                                <td>한마디</td><td>{this.state.state.vote.info}</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                         {this.state.state.data.map((hash,index)=>{
                                                              return <button type="button" className="btn-hash1" onClick={this.viewCandidate.bind(this,this.state.program.id,this.state.state.popularid,hash)}>
                                                              {hash.hashtag} <sup>{hash.count}</sup>
                                                              </button>
                                                         })}
                                                        {this.state.state.data.length==0?<div>등록된 해시태그가 없습니다.</div>:<div></div>}
                                                        
                                                     </div>):(
                                                        
                                                    <div></div>)}
                                                  
                                                    
                                                    
                                                </div>
                                                <div className="closeBtn" onClick={this.modalOff.bind(this)}>닫기</div>
                                            </div>
                                        </div>
                                    ):(
                                    <div></div>
                                    )
                                }

                   </div>             
            </div>
        )
    }
        
}

ReactDOM.render(<Show/>,document.getElementById('voteShow'));
