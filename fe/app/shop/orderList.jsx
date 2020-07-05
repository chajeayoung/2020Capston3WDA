import React from 'react';
import ReactDOM from 'react-dom';
import Pagination from '@material-ui/lab/Pagination';
import jQuery from "jquery";
window.$ = window.jQuery = jQuery;

import OrderListItem from './orderListItem.jsx'
import './css/mybag.css'

const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');


var url = document.location.href;
const num = url.split('/');
var param = num[num.length-1];

class OrderList extends React.Component {
    constructor(props){
        super(props)
        this.state = { item: [], pageNum: 1 , count: 0};

        this.url = '/shop/orderList/axios?page='+(this.state.pageNum-1)+'&size='+5+'&sort="id"';
    }

    setUrl(){
        this.url = '/shop/orderList/axios?page='+(this.state.pageNum-1)+'&size='+5+'&sort="id"';
    }
    async componentDidMount(){    
        this.getItems();
        
        
        
    }
    async getItems(){
        let {data} = await axios.get(this.url);
        console.log(data);
        this.state.count = Math.ceil((data.count*1.0)/5)
        this.setState({prd : data.orderList})
        // this.setState({item:d})
    }
    pagenation(e,page){
        console.log(page)
        this.state.pageNum = page
        this.setUrl()   
        this.getItems()
    }
 
    render() {
        
        return (
            <div>
                <div className="link_div">
                    <a href="/shop/list"><h5>상품 목록</h5></a>
                    &nbsp;&nbsp;
                    <a href="/shop/mybag"><h5>장바구니</h5></a>
                </div>
                <div className="menuTitle">
                    <h4>주문목록</h4>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>이미지</th>
                            <th>상품 정보</th>
                        </tr>
                    </thead>
                    <OrderListItem data={this.state.prd}/>
                </table>
                <Pagination count={this.state.count} page={this.state.pageNum} onChange={this.pagenation.bind(this)}> </Pagination>
            </div>
            
        )
    }
}

ReactDOM.render(<OrderList/>,document.getElementById('orderList'));

