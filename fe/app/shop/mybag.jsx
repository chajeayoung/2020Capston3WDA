import React from 'react';
import ReactDOM from 'react-dom';
import BagItem from './bagItem.jsx';
import Pagination from '@material-ui/lab/Pagination';
import './css/mybag.css'



const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
import jQuery from "jquery";
window.$ = window.jQuery = jQuery;

class Mybag extends React.Component {
    constructor(props){
        super(props)
        this.state = { mybags: [], pageNum: 1 , count: 0, sumPrice: 0};
        this.url = '/shop/mybag/axios?page='+(this.state.pageNum-1)+'&size='+3+'&sort="id"';
        this.buyItems = []
        this.buyUrl = '/shop/order?'
    }
    setUrl(){
        this.url = '/shop/mybag/axios?page='+(this.state.pageNum-1)+'&size='+3+'&sort="id"';
    }
    async componentDidMount(){
        this.getMybags();
    }

    async getMybags(){ 
        let {data} = await axios.get(this.url)
        console.log("----------",data);
        this.state.count = Math.ceil((data.count*1.0)/3)
        this.setState({mybags : data.prds})
    }

    // pagenation(e,page){
    //     console.log(page)
    //     this.state.pageNum = page
    //     this.setUrl()   
    //     this.getMybags()
    // }
    async deleteItem(id){ // 장바구니에서, 삭제
        if(!confirm("장바구니에서 삭제하시겠습니까?"))
            return
        let {data} = await axios.delete("/shop/mybag/"+id)

        if(data.success)
            alert(data.success);
        else
            alert(data.error);
        
        location.reload();
    }
    check(that,index,price,e){
        console.log(price);

        if($(e.target).is(":checked")){
            that.buyItems.push(index);
            var sum = that.state.sumPrice+price
            that.setState({sumPrice: sum})
        }else{
            that.buyItems.splice(that.buyItems.indexOf(index),1)
            var sum = that.state.sumPrice-price
            that.setState({sumPrice: sum})
        }    
        
    }
    gotoBuy(){
        if(this.buyItems.length == 0)
            return alert("구매하실 상품을 골라주세요");
        
        this.buyItems.sort(function(a, b) { // 오름차순 정렬 
            return a - b;
            // 1, 2, 3, 4, 10, 11
            // return b-a : 내림차순
        });
        for(var i=0;i<this.buyItems.length; i++){
            var data =this.state.mybags[this.buyItems[i]]
            if(data.pStock < data.quantity){
                return alert("해당 상품의 재고가 부족합니다.\n상품명: "+data.name+"\재고: "+data.pStock)
            }
        }

        for(var i =0;i<this.buyItems.length; i++){
            var data =this.state.mybags[this.buyItems[i]]
            this.buyUrl += "productId="+data.productId+"&optionId="+data.optionId+"&quantity="+data.quantity
            this.buyUrl +="&bagId="+data.id
            this.buyUrl +="&"
        }
        window.location.href = this.buyUrl;

    }
    render() {
        
        return (
            <div>
                <div className="link_div">
                    <a href="/shop/list"><h5>상품 목록</h5></a>
                    &nbsp;&nbsp;
                    <a href="/shop/orderList"><h5>주문 목록</h5></a>
                </div>
                <div className="menuTitle">
                    <h4>장바구니 목록</h4>
                </div>
                <div className="grid">
                    <div className="items">
                    <table>
                        <thead>
                            <tr>
                                <th>주문</th>
                                <th>이미지</th>
                                <th>상품 정보</th>
                            </tr>
                        </thead>
                        <BagItem data={this.state.mybags} event={this.deleteItem} check={this.check} this={this}/>
                        {/* <Pagination count={this.state.count} page={this.state.pageNum} onChange={this.pagenation.bind(this)}> </Pagination> */}
                    </table>
                    </div>
                    <div className="centerOrderGo">
                        <div className="sumPriceTitle">총 상품 가격</div>
                        <div><h2 className="sumPrice">{this.state.sumPrice} 원</h2></div>
                        <div><input type="button" onClick={this.gotoBuy.bind(this)} value="주문하기"/></div>
                    </div>
                </div>
                
            </div>
            
        )
    }
}

ReactDOM.render(<Mybag/>,document.getElementById('mybag'));