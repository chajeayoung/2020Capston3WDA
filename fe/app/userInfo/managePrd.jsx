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
import './css/userInfo.css';
import '../smart.css';

window.$ = window.jQuery = jQuery;


const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');

class ManagePrd extends React.Component{
    constructor(props){
        super(props);
        this.state = { prd: [], pageNum: 1 , count: 0};
        this.url = '/userInfo/manage/product/axios?page='+(this.state.pageNum-1)+'&size='+10+'&sort="id"';
    }
    setUrl(){
        this.url = '/userInfo/manage/product/axios?page='+(this.state.pageNum-1)+'&size='+10+'&sort="id"';
    }
    async componentDidMount(){
        let {data} = await axios.get(this.url)
        console.log("----------",data);
        this.state.count = Math.ceil((data.count*1.0)/10)
        this.setState({prd : data.prds})
        
        console.log(this.state.count);
    }
    pagenation(e,page){
        console.log(page)
        this.state.pageNum = page
        this.setUrl()   
        this.componentDidMount()
    }
    
    render() {
        // console.log(this.state.vote)
        return(
                <div id="tablebox">
 
                         <Paper >
                            <Table size="small" id="myTable">
                            <TableHead>
                                <TableRow>
                                    <TableCell className="smart">번호</TableCell>
                                    <TableCell>상품명</TableCell>
                                    <TableCell className="smart">가격</TableCell>
                                    <TableCell>재고</TableCell>
                                    <TableCell className="smart">판매종료 날짜</TableCell>
                                    <TableCell>수정</TableCell>
                                    <TableCell>삭제</TableCell>
                                </TableRow>
                                </TableHead>
                                <TableBody>
                                    <PrdList   data={this.state.prd}/>    
                                </TableBody>
                            </Table>
                        </Paper>
                    <Pagination count={this.state.count} page={this.state.pageNum} onChange={this.pagenation.bind(this)}> </Pagination>


                </div>
        )
    }
}

class PrdList extends React.Component {
    constructor(props){
        super(props);
    }
    async remove(prdId){
        
        if(!confirm("정말로 해당 상품을 삭제하시겠습니까?"))
            return;
        axios.delete('/shop/product/'+prdId)
        
        location.reload()

    }

    render() {
        return  this.props.data.map((prd,index)=>{
            var endDate = prd.endDate.split(" ");
            
            return (
                <TableRow key={'div'+index}>
                             
                    <TableCell className="smart" key={index} >{prd.productId}</TableCell>
                    <TableCell><a href={"/shop/product/"+prd.productId}>{prd.name}</a></TableCell>
                    <TableCell className="smart" >{prd.price}</TableCell>
                    <TableCell>{prd.stock}</TableCell>
                    <TableCell className="smart">{endDate[0]}</TableCell>
                    <TableCell><a href={"/shop/edit/"+prd.productId}>수정</a></TableCell>
                    <TableCell><button onClick={this.remove.bind(this,prd.productId)}>삭제</button></TableCell>
               
                </TableRow>
               
               )
            
        })
        
    }
}


ReactDOM.render(<ManagePrd/>, document.getElementById('managePrd'));