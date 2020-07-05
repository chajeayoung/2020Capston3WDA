import React from 'react';
import ReactDOM from 'react-dom';
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
import jQuery from "jquery";
window.$ = window.jQuery = jQuery;
import './css/mybag.css'
import './css/orderShow.css'


class OrderShow extends React.Component {
    constructor(props){
        super(props)
        this.state = {prd:{}}
        this.url;
        
    }


    async componentDidMount(){    
        var listId = $("#listId").val()
        this.url = '/shop/orderShow/'+listId+'/axios'
        this.getItem();
        
    }
    async getItem(){
        let {data} = await axios.get(this.url);
        console.log(data);
        this.setState({prd : data.orderList[0]})
    }
    render() {
        var prd = this.state.prd;
        if(prd.img){
            return (
                <div className="bagItem">
                    <div className="link_div">
                        <a href="/shop/list"><h5>상품 목록</h5></a>
                        &nbsp;&nbsp;
                        <a href="/shop/mybag"><h5>장바구니</h5></a>
                    </div>
                    <div><h4 className="orderInfoDiv">주문 상품 정보</h4></div>
                    <div className="imgDiv">
                        <a href={"/shop/product/"+prd.productId}>
                            <img src={"/uploads/"+prd.img}/>
                        </a>
                    </div>
                    <table>
                        <tbody>
                            <tr>
                                <td><a href={"/shop/product/"+prd.productId}>상품명: </a></td>
                                <td>{prd.name}</td>
                            </tr>
                            <tr>
                                <td>옵션명: </td>
                                <td>{prd.oTitle}</td>
                            </tr>
                            <tr>
                                <td>개수: </td>
                                <td>{prd.count}</td>
                            </tr>
                            <tr>
                                <td>가격: </td>
                                <td>{prd.itemPrice}</td>
                            </tr>
                            <tr>
                                <td>수취자: </td>
                                <td>{prd.receiver}</td>
                            </tr>
                            <tr>
                                <td>수취자 연락처: </td>
                                <td>{prd.phone}</td>
                            </tr>
                            <tr>
                                <td>도로명 주소: </td>
                                <td>{prd.addr}</td>
                            </tr>
                            <tr>
                                <td>상세 주소: </td>
                                <td>{prd.addr2}</td>
                            </tr>
                            <tr>
                                <td>주문상태: </td>
                                <td>    
                                    {
                                        prd.invoice?prd.invoice==0?
                                        '확인전':prd.invoice==1?
                                        '확인': prd.invoice==2?
                                        '배송중:':prd.invoice==3?
                                        '배송완료':prd.invoice==4?
                                        '확인바람':'확인바람':'확인전'
                                    }
                                </td>
                            </tr>
                            <tr>
                                <td>운송장 번호: </td>
                                <td>{prd.state?prd.state:'등록되지 않음.'}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                
            )
        
        }else{
            return <div>주문 상품 정보가 없습니다.</div>
        }
    }
}

ReactDOM.render(<OrderShow/>,document.getElementById('orderShow'));

