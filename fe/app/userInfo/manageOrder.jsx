import React from 'react'
import ReactDOM from 'react-dom';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';

import Pagination from '@material-ui/lab/Pagination';
import jQuery from "jquery";
import './css/voteTable.css';

import '../smart.css';
import './css/userInfo.css'
import './css/manageOrder.css'
window.$ = window.jQuery = jQuery;


const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');

class ManageOrder extends React.Component{
    constructor(props){
        super(props);
        this.state = { order: [], pageNum: 1 , count: 0, modal:1, orderIndex:0};
        this.url = '/userInfo/manage/order/axios?page='+(this.state.pageNum-1)+'&size='+5+'&sort="id"';
        
    }
    setUrl(){
        this.url = '/userInfo/manage/order/axios?page='+(this.state.pageNum-1)+'&size='+5+'&sort="id"';
    }
    async componentDidMount(){
        
        this.getOrders();
        
    }
    async getOrders(){
        let {data} = await axios.get(this.url)
        console.log("----------",data);
        this.state.count = Math.ceil((data.count*1.0)/5)
        this.setState({order:data.orderList})
    }
    pagenation(e,page){
        console.log(page)
        this.state.pageNum = page
        this.setUrl()   
        this.getOrders();
    }
    modalOn(index,that){
        console.log(index)
        that.setState({modal : 0, orderIndex : index})
        
    }
    
    modalOff(){
        this.setState({modal:1})
    }
    updateOrder(){
        if($("#state").val().length == 0)
            return alert("상태를 입력해 주세요")
        if($("#state").val() > 4)
            return alert("상태는 최소 0, 최대 4 까지 입력가능합니다.")
        
        var order = this.state.order[this.state.orderIndex]
        let {data} = axios.put("/shop/orderShow/"+order.orderId+"/axios",{
            state : $("#state").val(),
            invoice : $("#invoice").val()
        })
        // console.log(data);// put 요청 반환값 안받아 와짐.

        this.setState({modal:1})
        this.getOrders()
    }
    render() {
        var order = this.state.order[this.state.orderIndex];//모달에 나올 아이템 정보
        console.log(this.state.modal)
        return(
                <div id="tablebox">
 
                         <Paper >
                            <Table size="small" id="myTable">
                            <TableHead>
                                <TableRow>
                                    <TableCell className="smart">상품번호</TableCell>
                                    <TableCell>상품명</TableCell>
                                    <TableCell className="smart">결재금액</TableCell>
                                    <TableCell>수량</TableCell>
                                    <TableCell className="smart">주문자 ID</TableCell>
                                    <TableCell >주문자 이름</TableCell>
                                    <TableCell >정보입력</TableCell>
                                    <TableCell className="smart">상태</TableCell>
                                </TableRow>
                                </TableHead>
                                <TableBody>
                                    <OrderList   data={this.state.order} modal={this.modalOn} that ={this}/>    
                                </TableBody>
                            </Table>
                        </Paper>
                    <Pagination count={this.state.count} page={this.state.pageNum} onChange={this.pagenation.bind(this)}> </Pagination>
                    {
                        this.state.modal == 0 ?(
                            <div className="modal">
                                <div className="modalContentBox">
                                    <div className="modalItem">
                                        <div>주문 상세 정보</div>
                                        <div className="shopImg">
                                            <img className="smartImg" id="orderImg" src={"/uploads/"+order.img}/>
                                        </div>
                                        <div>상품명: {order.name}</div>
                                        <div>옵션: {order.oTitle}</div>
                                        <div>수량: {order.count}</div>
                                        <div>결제금액: {order.itemPrice}</div>
                                        <div>수취인: {order.receiver}</div>
                                        <div>수취인 연락처: {order.phone}</div>
                                        <div>도로명 주소: {order.addr}</div>
                                        <div>상세주소: {order.addr2}</div>
                                        <div>운송장번호: <input id="invoice" defaultValue={order.invoice} type="text" /></div>
                                        <div>상태: <input type="number" defaultValue={order.state} min="0" max="4" id="state" /></div>
                                        <div> 상태 설명 :</div>
                                        <div>0 : 확인전, 1: 확인 , 2: 배송중, 3: 배송완료, 4: 확인바람</div>
                                        <div><input type="button" onClick={this.updateOrder.bind(this)} value="입력 완료"/></div>
                                    </div>
                                    <div className="closeBtn" onClick={this.modalOff.bind(this)}>닫기</div>
                                </div>
                            </div>
                        ):(
                            <div></div>
                        )
                    }

                </div>
        )
    }
}

class OrderList extends React.Component {
    constructor(props){
        super(props);
    }

    render() {
        return  this.props?this.props.data.map((order,index)=>{
            console.log(order)
            return (
                <TableRow key={'div'+index}>
                             
                    <TableCell className="smart" key={index}>{order.productId}</TableCell>
                    <TableCell><a href={"/shop/product/"+order.productId}>{order.name}</a></TableCell>
                    <TableCell className="smart">{order.itemPrice}</TableCell>
                    <TableCell>{order.count}</TableCell>
                    <TableCell className="smart">{order.rId}</TableCell>
                    <TableCell>{order.clientName}</TableCell>
                    <TableCell><input type="button" onClick={this.props.modal.bind(this,index,this.props.that)}value="입력하기"/></TableCell>
                    <TableCell className="smart">
                        {
                            order.state == 0? '확인전':
                            order.state == 1? '확인':
                            order.state == 2? '배송중':
                            order.state == 3? '배송완료':
                            order.state == 4? '확인바람':
                            '확인전'
                        }

                    </TableCell>
               
                </TableRow>
               
               )
            
        })
        : <div> 주문 정보가 없습니다. </div>
    }
}


ReactDOM.render(<ManageOrder/>, document.getElementById('manageOrder'));