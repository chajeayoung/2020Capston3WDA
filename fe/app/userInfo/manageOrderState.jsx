import React from 'react'
import ReactDOM from 'react-dom';
import jQuery from "jquery";
import Graph from '../items/stateGraph.jsx'
import OrderStateItem from './orderStateItem.jsx'
window.$ = window.jQuery = jQuery;

import './orderState.css'
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


class ManageOrderState extends React.Component{
    constructor(props){
        super(props);
        this.state = {item:[],dataSetState:0}
    }
    async componentDidMount(){
        
        this.setData();

    }
    async setData(){
        let {data} = await axios.get('/userInfo/manage/manageOrderState/axios');
        console.log(data);

        data.map((state)=>{
            var color = '#'+Math.round(Math.random() * 0xffffff).toString(16);
            var set = {
                title: state.name,
                borderColor: color,
                values: [state.five,state.four,state.three,state.two,state.one]
            }
            
            stateData.datasets.push(set);
        })
        this.setState({item:data,dataSetState:0})
    }
    
    render() {
        // console.log(this.state.vote)
        return(
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
        )
    }
}




ReactDOM.render(<ManageOrderState/>, document.getElementById('manageOrderState'));