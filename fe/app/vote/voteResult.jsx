import React, {Component, createElement}from 'react'
import ReactDOM from 'react-dom';
import CircleChart from '../items/circleChart.jsx';
import BarChart from '../items/barChart.jsx';
import jQuery from "jquery";
import "./css/addressModal.css";
import "./css/result.css";
// import "./js/jquery.ajax-cross-origin.min.js"

window.$ = window.jQuery = jQuery;
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');

// const cors = require('cors'); // 브라우저 보안 정책
// const express = require('express');
// const app = express();
// app.use(cors());
var url = document.location.href;
const num = url.split('/');
var param = num[num.length-1];


// 원형 차트
var title = [];
var data = {
    labels: [],
    datasets: [
      {
        data: [],
        backgroundColor: ['#FA5858', '#FE9A2E', '#FFFF00','#80FF00','#00FFFF','#0080FF','#BF00FF','#848484','#525132'],
        hoverBackgroundColor: ['#FA5858', '#FE9A2E', '#FFFF00','#80FF00','#00FFFF','#0080FF','#BF00FF','#848484','#525132']
      }
    ]
  };
// 그래프 차트

var barChart = {
    labels: [],// 후보 이름.
    datasets: [
        {
            title: '10대',
            backgroundColor: '#FA5858',
            values: [],
        },
        {
            title: '20대',
            backgroundColor: '#FE9A2E',
            values: [],
        },
        {
            title: '30대',
            backgroundColor: '#F7FE2E',
            values: [],
        },
        {
            title: '40대',
            backgroundColor: '#58ACFA',
            values: [],
        },
        {
            title: '50上',
            backgroundColor: '#D358F7',
            values: [],
        },
    ],
};

//
var barChart2 = {
    labels: [],// 후보 이름.
    datasets: [
        {
            title: '남성',
            backgroundColor: '#8181F7',
            values: [],
        },
        {
            title: '여성',
            backgroundColor: '#F78181',
            values: [],
        }
    ],
};


class VoteResult extends Component {
    
    constructor(props){
        super(props);
        console.log("VoteResult : constructor")
        this.state = { data:{}, circle: 0, age: 1 ,gender: 1 , modal:1,voteAdd:"", userAdd:[]}       
        this.show = 0;
        this.send = {data:{},count:0}
        
    }
    
    async componentDidMount(){
        console.log("VoteResult : componentDidMount")
        const {data: json} =  await axios.get('/vote/result/axios/'+param);
        // json[]  0: 투표결과 , 1: 후보이름, 2: 나이별, 3: 성별별, 4:count: 투표총 횟수 ,5: 후보수, 6: 선발인원 숫자 ,7: show, 8: vote address , 9: userAddress

        if(json[7] == 1){
            console.log("결과 공개 X")
            return this.show= 1;
        }
        this.show = json[7];            
        
        // 주소
        


        title = json[1];

        for(var i = 0; i<json[5]; i++){
            data.datasets[0].data.push(json[0][i]);
            data.labels.push(title[i]);
            barChart.labels.push(title[i]);
            barChart2.labels.push(title[i]);
        }
        var ageData = json[2] ;
        var genderData = json[3];

        for(var i =1; i<=json[5]; i++){
            for(var j = 0; j<5;j++){ // 나이별 투표 10 ~50 대
                barChart.datasets[j].values.push(ageData[i][j]);
            }
        }
        for(var j = 0; j<5;j++){ 
            barChart.datasets[j].values.push(0.1);
        }

        for(var i =1; i<=json[5]; i++){
            for(var j = 0; j<2;j++){ // 성별 별로 
                barChart2.datasets[j].values.push(genderData[i][j]);
            }
        }
        
        for(var i =0; i<2; i++){ // 차트 더미데이터
            barChart2.datasets[i].values.push(0.1);

        }


        // "#" + Math.round(Math.random() * 0xffffff).toString(16)  //무작위 색상

          // json[]  0: 투표결과 , 1: 후보이름, 2: 나이별, 3: 성별별, 4:count: 투표총 횟수 ,5: 후보수, 6: 선발인원 숫자 ,7: show, 8: vote address , 9: userAddress
        if(this.props.event){
            this.send = {data:json[0], count:json[4], win:json[6]}
            this.props.event(this.send);
        }
        
        this.send.count = json[4];
        console.log(data);

        this.setState({data:data, voteAdd:json[8],userAdd:json[9]})
        
        $('.circle_result_show').css("background-color","#F5A9A9");
    }

    showChart(){// 차트
        console.log("원형 차트");
        this.setState({circle: 0, age:1 ,gender:1})
        $('.circle_result_show').css("background-color","#F5A9A9");
        $('.age_result_show').css("background-color","#E0ECF8");
        $('.gender_result_show').css("background-color","#E0ECF8");
    }

    showAge(){ // 나이별
        console.log("나이별");

        this.setState({circle: 1, age:0 ,gender:1})
        $('.circle_result_show').css("background-color","#E0ECF8");
        $('.age_result_show').css("background-color","#F5A9A9");
        $('.gender_result_show').css("background-color","#E0ECF8");
    }

    showGender(){ //성별별
        console.log("성비별");

        this.setState({circle: 1, age:1 ,gender:0})
        $('.circle_result_show').css("background-color","#E0ECF8");
        $('.age_result_show').css("background-color","#E0ECF8");
        $('.gender_result_show').css("background-color","#F5A9A9");
    }

    modalOn(){
        this.setState({modal : 0})
    }
    modalOff(){
        this.setState({modal:1})
    }
    // "http://baobab.scope.klaytn.com/" : {
    //     target: 'http://baobab.scope.klaytn.com/',
    //     changeOrigin: true,
    //     pathRewrite: { '^http://baobab.scope.klaytn.com': '' },

    // { 
    //     crossdomain : true,
    //     mode: 'no-cors',
    //     withCredentials: true,
    //     credentials: 'same-origin',
    //     headers:{
    //         "Access-Control-Allow-Origin": "*",
    //         "Access-Control-Allow-Methods": "GET",
    //         "Allow-Control-Allow-Credentials": true,
    //         "Access-Control-Max-Age": 3600,
    //         // "Access-Control-Allow-Headers": "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization",
    //         "Access-Control-Allow-Headers": "X-Requested-With",
    //         'Content-Type': 'application/json',
            
    //         // "jsonp":"callback",
    //         // "dataType":"jsonp"
    //     },
    //     // proxy: { host: "http://baobab.scope.klaytn.com"}
    //     // proxy: {
    //     //     host: '104.236.174.88',
    //     //     port: 3128
    //     //     }
    // }
    verificationAdd(){
        var topDiv = $(".modalItem")
        topDiv.empty();

        var contentDiv = $(document.createElement("div"))
        contentDiv.attr("class", "usrAdd")
        var that = this
        if(this.state.userAdd.length >0){
            this.state.userAdd.map((info)=>{
                var dateDiv = $(document.createElement("div"))
                var date = info.createdAt.split(" ")[0]
                dateDiv.html("투표일: "+date);
                contentDiv.append(dateDiv);
                var div = $(document.createElement("div"))
                div.html("해시코드: "+info.hash);
                contentDiv.append(div);
                var button = $(document.createElement("button"))
                button.html("검증하기")
                button.on('click',this.verification.bind(this,info.hash))
                contentDiv.append(button);
            });
        }else{
            var div = $(document.createElement("div"))
            div.html("회원님의 투표정보가 없습니다.");
            contentDiv.append(div);
        }
        
        topDiv.append(contentDiv);
    }
    test(hash){
        console.log(hash)
    }
    async verification(hash){// 블록체인 검증 관련 
        var item =  $(".modalItem");
        item.empty();

        var boxDiv = $(document.createElement("div"))
        boxDiv.attr("class","loadBox")

        var loadingText = $(document.createElement("div"))
        loadingText.attr("class","loadingText");
        loadingText.html("검증중..")
        var loading = $(document.createElement("img"));
        loading.attr("class","loadImg");
        loading.attr("src","/img/loading_mount.gif")

        boxDiv.append(loadingText);
        boxDiv.append(loading) 
        item.append(boxDiv);
        
        const proxyurl = "https://cors-anywhere.herokuapp.com/";
        // var html = await axios.get("http://baobab.scope.klaytn.com/tx/"+hash+"?tabId=internalTx")
        // https://api-baobab.klaytn.com/v1/txs/0x2332863bbbab340567e403e3d4bfae9db2e42888286b28d9446311c229658226
        let {data} = await axios.get(proxyurl+"https://api-baobab.klaytn.com/v1/txs/"+hash,{
            crossOrigin: true,
            crossdomain : true,
            dataType : "application/json",
            headers :{
                "Access-Control-Allow-Origin" : "*",
                'Set-Cookie':"cross-site-cookie=name; SameSite=None; Secure",
                'X-Requested-With': 'XMLHttpRequest'
            }
        } )
        
        // .then( response => {
        //     // console.log(response);
        //     // console.log(response.data);
        //     // var result = $(response);

        //     console.log(response.data);
        //     // console.log($(html).children("script").eval());
        // })
        // .catch( err => {console.log(err)});
        // PulseLoader
       if(data.result.txStatus == 1 ){
        
            item.empty();
            var loading = $(document.createElement("img"));
            loading.attr("class","loadImg");
            loading.attr("src","/img/success.gif")
            item.append(loading);
            var div = $(document.createElement("div"));
            div.html("블록체인 검증 결과, \n 당신의 투표는 정상적으로 반영되어 있습니다.")
            div.attr("class","infoResult")

            var div2 = $(document.createElement("div"));
            div2.html("자세한 내용은 ")
            div2.css("display","inline")
            div2.attr("class","infoResult")
            var aTag = $(document.createElement("a"))
            aTag.attr("href","http://baobab.scope.klaytn.com/tx/"+hash)
            aTag.html("여기")
            aTag.attr("target","_blank")
            aTag.attr("class","linkAtag infoResult")
            
            var div3 = $(document.createElement("div"))
            div3.html("를 눌러 확인하세요")
            div3.css("display","inline")
            div3.attr("class","infoResult")
            item.append(div);
            item.append(div2);
            item.append(aTag);
            item.append(div3);


            
       }
    }
    render() {
        const {data} = this.state;
        console.log(this.state.userAdd)
        if(this.show == 1){
            return(
                <div>투표 결과가 공개되지 않았습니다.</div>
            )
        }
        return(
            <div>   
                { this.send.count == 0 ? (
                    <div>투표 데이터가 없습니다.</div>
                ):(
                    <div>
                        <div id="showChart">
                            {
                                this.state.circle == 0 ? (
                                    <div>
                                        <CircleChart data={this.state.data} />
                                    </div>
                                ):(
                                    this.state.age == 0 ?(
                                        <BarChart data={barChart}/>
                                    ):(
                                        <BarChart data={barChart2}/>
                                    )
                                )
                            }
                        </div>
                        <div className="selectVoteShowType">
                            <div>
                                <button className="circle_result_show" onClick={this.showChart.bind(this)}>득표수</button>
                                <button className="age_result_show" onClick={this.showAge.bind(this)}>연령별</button>
                                <button className="gender_result_show" onClick={this.showGender.bind(this)}>성별</button>
                            </div>
                        </div>
                        <div onClick={this.modalOn.bind(this)}>블록체인 주소확인</div>
                        {
                            this.state.modal == 0?(
                                <div className="modal">
                                    <div className="modalContentBox">
                                        <div className="modalItem">
                                            <img class="loadImg" src="/img/block_chain.gif"></img>
                                            <div class="infoDiv">RIRO는 여러분의 투표 정보를 블록체인으로 <br/>안전하게 관리하고있으며,<br/> 검증을 통해 손쉽게 확인하실 수 있습니다.</div>
                                            <button onClick={this.verificationAdd.bind(this)}>검증하기</button>
                                            {/* <div className="addText">투표 블록체인 주소:</div>
                                            <div>{this.state.voteAdd}</div>
                                            <div className="addText">내가 투표한 정보</div>
                                            <div className="usrAdd"> */}
                                                {
                                                    
                                                }
                                                {/* this.state.userAdd.map((add, index) => {
                                                        return <div key={index}>{add.hash}<button type="button" onClick={this.verification.bind(this,add.hash)}>확인</button></div>
                                                    }) */}
                                            {/* </div> */}
                                            
                                            
                                        </div>
                                        <div className="closeBtn" onClick={this.modalOff.bind(this)}>닫기</div>
                                    </div>
                                </div>
                            ):(
                            <div></div>
                            )
                        }
                    </div>
                )}
            </div>
        )
      }
}



export default VoteResult



