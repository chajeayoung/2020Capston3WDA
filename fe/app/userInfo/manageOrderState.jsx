import React from 'react'
import ReactDOM from 'react-dom';
import jQuery from "jquery";
import Graph from '../items/stateGraph.jsx'
import OrderStateItem from './orderStateItem.jsx'
import BarChart from '../items/barChart.jsx';
import OrderStateItemPop from './OrderStateItemPop.jsx'
window.$ = window.jQuery = jQuery;

import './css/orderState.css'
import '../shop/css/mybag.css'

// npm i @bit/nexxtway.react-rainbow.chart
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');


var stateData=
{
    labels: ['5 ~ 4달 전', '4 ~ 3달 전', '3 ~ 2달 전', '2 ~ 1달 전', '1달전 ~ 현재'],
    datasets: [

    ],
};
var barChart = {
    labels: [],// 후보 이름.
    datasets: [
        {
            title: '총 굿즈 판매수',
            backgroundColor: '#FA5858',
            values: [],
        },
    ],
};
var barChartAge = {
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
            title: '50',
            backgroundColor: '#D358F7',
            values: [],
        },
    ],
};
var barChartGender = {
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

class ManageOrderState extends React.Component{
    constructor(props){
        super(props);
        this.state = {item:[],showState:0, pop:[],total:0, age:1,gender:1}
    }
    async componentDidMount(){
        let {data} = await axios.get('/userInfo/manage/manageOrderState/axios');
        console.log(data);
        this.setData(data);
        this.setState({item:data.state, pop :data.pop  })
    }
    
    async setData(data){

        data.state.map((state)=>{
            var color = '#'+Math.round(Math.random() * 0xffffff).toString(16);
            var set = {
                title: state.name,
                borderColor: color,
                values: [state.five,state.four,state.three,state.two,state.one]
            }
            
            stateData.datasets.push(set);
        })
        data.pop.map((pop)=>{
            barChart.labels.push(pop.pName);
            barChartAge.labels.push(pop.pName);
            barChartGender.labels.push(pop.pName);
            barChart.datasets[0].values.push(pop.count);
        })
        barChart.datasets[0].values.push(0);
        
        // 데이터 세팅
        for(var i =0; i<data.pop.length;i++){
            barChartGender.datasets[0].values.push(0);
            barChartGender.datasets[1].values.push(0);
            barChartAge.datasets[0].values.push(0);
            barChartAge.datasets[1].values.push(0);
            barChartAge.datasets[2].values.push(0);
            barChartAge.datasets[3].values.push(0);
            barChartAge.datasets[4].values.push(0);
        }
        barChartGender.datasets[1].values.push(0);
        barChartAge.datasets[4].values.push(0);
        // 나이 / 후보별
                for(var i=0; i<data.pop.length;i++){
            for(var j=0; j<data.age.length;j++){
                if(barChartAge.labels[i] == data.age[j].pName){
                    if(data.age[j].age==1){
                        barChartAge.datasets[0].values[i] = data.age[j].count;
                    }else if(data.age[j].age==2){
                        barChartAge.datasets[1].values[i] = data.age[j].count;
                    }else if(data.age[j].age==3){
                        barChartAge.datasets[2].values[i] = data.age[j].count;
                    }else if(data.age[j].age==4){
                        barChartAge.datasets[3].values[i] = data.age[j].count;
                    }else{
                        barChartAge.datasets[4].values[i] = data.age[j].count;
                    }
                }
            }
        }
        // 성별 / 후보별
        for(var i=0; i<data.pop.length;i++){
            for(var j=0; j<data.gender.length;j++){
                if(barChartGender.labels[i] == data.gender[j].pName){
                    if(data.gender[j].gender==0){
                        barChartGender.datasets[0].values[i] = data.gender[j].count;
                    }else{
                        barChartGender.datasets[1].values[i] = data.gender[j].count;
                    }
                }
                
            }
        }
        this.forceUpdate();
        
    }
    setStatePrd(){
        this.setState({showState:0})
        
        $("#stateButtonPrd").css("background-color","rgb(245, 169, 169)")
        $("#stateButtonPop").css("background-color","#e0ecf8")
        
    }
    setStatePop(){
        this.setState({showState:1,total:0, age:1,gender:1})
        
        $("#stateButtonPrd").css("background-color","#e0ecf8")
        $("#stateButtonPop").css("background-color","rgb(245, 169, 169)")
    }
    onTotal(){
        this.setState({total:0, age:1,gender:1})

        $("#stateButtonTotal").css("background-color","rgb(245, 169, 169)")        
        $("#stateButtonAge").css("background-color","#e0ecf8")
        $("#stateButtonGender").css("background-color","#e0ecf8")
    }
    onAge(){
        this.setState({total:1, age:0,gender:1})
        
        $("#stateButtonTotal").css("background-color","#e0ecf8")
        $("#stateButtonAge").css("background-color","rgb(245, 169, 169)")
        $("#stateButtonGender").css("background-color","#e0ecf8")
    }
    onGender(){
        this.setState({total:1, age:1,gender:0})

        $("#stateButtonTotal").css("background-color","#e0ecf8")
        $("#stateButtonAge").css("background-color","#e0ecf8")
        $("#stateButtonGender").css("background-color","rgb(245, 169, 169)")
    }
    render() {
        // console.log(this.state.vote)
        console.log(barChartAge.labels)
        return(
            <div>
                <div  className="divAlignCenter">
                    <button onClick={this.setStatePrd.bind(this)} id="stateButtonPrd" className="stateDefaultButton">상품별</button>
                    <button onClick={this.setStatePop.bind(this)} id="stateButtonPop" className="stateButton">후보별</button>
                </div>
                {
                    this.state.showState == 0 ? (

                        <div>
                            <div className="stateTitle"><h4>판매추이 그래프</h4></div>
                            <div className="graphDiv">
                                <Graph data={stateData}/>
                            </div>
                            <div className="itemTitle"><h4>상품정보</h4></div>
                            <div className="itemDiv">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>이미지</th>
                                            <th>상품 정보</th>
                                        </tr>
                                    </thead>
                                    <OrderStateItem data={this.state.item}/>
                                </table>
                            </div>
                        </div>

                    ):(
                        <div>
                            <div  className="divAlignCenter"><h4>후보별 굿즈 판매 분석</h4></div>
                            <div className="divAlignCenter">
                                <button onClick={this.onTotal.bind(this)} id="stateButtonTotal" className="stateDefaultButton">총 판매량</button>
                                <button onClick={this.onAge.bind(this)} id="stateButtonAge" className="stateButton">나이별</button>
                                <button onClick={this.onGender.bind(this)} id="stateButtonGender" className="stateButton">성비별</button>
                            </div>
                            {
                                this.state.total == 0?
                                    <div className="barChartTopDiv"><BarChart data={barChart}/></div>
                                : this.state.age ==0?
                                    <div className="barChartTopDiv"><BarChart data={barChartAge}/></div>
                                    : this.state.gender ==0?    
                                        <div className="barChartTopDiv"><BarChart data={barChartGender}/></div>
                                        :<div></div>

                                        
                            }
                            <div className="itemDiv">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>이미지</th>
                                            <th>정보</th>
                                        </tr>
                                    </thead>
                                    <OrderStateItemPop data={this.state.pop}/>
                                </table>
                            </div>
                        </div>
                    )
                }
                
               
                
            </div>
        )
    }
}




ReactDOM.render(<ManageOrderState/>, document.getElementById('manageOrderState'));