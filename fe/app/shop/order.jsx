import React from 'react';
import ReactDOM from 'react-dom';
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
import jQuery from "jquery";
import './css/order.css'
import './css/mybag.css'
import OrderItem from './orderItem.jsx'

window.$ = window.jQuery = jQuery;



class Order extends React.Component {

    constructor(props){

        super(props)
        this.state = {data:[],profile:[{addr:0,addr2:0,username:0,phone:0}]}
    }

    async componentDidMount(){
        this.getPrdList();
        this.getProfile();
    }
    async getPrdList(){
        
        let {data} = await axios.get(window.location.href.replace('?',"/axios?"));
         this.setState({data})
        this.setSumPrice();

        
        
    }
    async getProfile(){
        let {data:profile} = await axios.get('/userInfo/axios');
        this.setState({profile})
        
    }
    setSumPrice(){
        var sumTag = $('#sumPrice')
        var price = 0;

        this.state.data.map((prd)=>{
            price += (prd.price + prd.oPrice)*prd.count;

        });
        sumTag.text(price+" 원")
    }
    buy(e){
        // e.preventDefault();
        // alert("상품을 구매하시겠습니까?");
        if(!confirm("상품을 구매하시겠습니까?"))
            e.preventDefault();
    }
    render() {
        console.log("d"+this.state.profile)
        return(
            <div>
                <form  action="/shop/order" method="post">
                    <input type="hidden" name="_csrf" value={document.cookie.split("=")[1]}/>
                        <div className="link_div">
                        <a href="/shop/list"><h5>상품 목록</h5></a>
                        &nbsp;&nbsp;
                        <a href="/shop/orderList"><h5>주문 목록</h5></a>
                        &nbsp;&nbsp;
                        <a href="/shop/mybag"><h5>장바구니 목록</h5></a>
                    </div>
                    <div className="grid">
                        <div className="orderLeft">
                            <div>
                                <div>
                                    <div className="userInfoTitle"><h4>주문상품 정보</h4></div>
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>이미지</th>
                                                <th>상품 정보</th>
                                            </tr>
                                        </thead>
                                        <OrderItem data={this.state.data}/>
                                    </table>
                                </div>
                            </div>
                            <div className="userInfo">
                                <div className="userInfoTitle"><h4>수취자 정보</h4></div>
                                
                                <table className="userInfoTable">
                                    <tr>
                                        <td>도로명 주소</td>
                                        <td><input type="text" name="addr"  defaultValue={this.state.profile[0].addr ? this.state.profile[0].addr : ''} required/></td>
                                    </tr>
                                    <tr>
                                        <td>상세 주소</td>
                                        <td><input type="text" name="addr2"  defaultValue={this.state.profile[0].addr2 ? this.state.profile[0].addr2 : ''}  required/></td>
                                    </tr>
                                    <tr>
                                        <td>받는분 성함</td>
                                        <td><input type="text" name="receiver"  defaultValue={this.state.profile[0].username ? this.state.profile[0].username : ''}  required/></td>
                                    </tr>
                                    <tr>
                                        <td>연락처</td>
                                        <td><input type="text" name="phone"  defaultValue={this.state.profile[0].phone ? this.state.profile[0].phone : ''}  required/></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div>
                            <div className="orderDiv">
                                <div><h3>총 금액</h3></div>
                                <div id="sumPrice">---원</div>
                                <div><h3>이용약관</h3></div>
                                <div>반품불가 <input type="checkbox" required/></div>
                                <div>
                                    <input type="submit" className="buy" onSubmit={this.buy.bind(this)} value="구매"/><a   className="cancle" href="/shop/index">취소</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form> 
            </div>
        )
    }
}

ReactDOM.render(<Order/>,document.getElementById('order'));

