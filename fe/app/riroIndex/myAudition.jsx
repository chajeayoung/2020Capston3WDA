import React from 'react'
import ReactDOM from 'react-dom';
const regeneratorRuntime = require("regenerator-runtime");
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';

import Pagination from '@material-ui/lab/Pagination';

import '../smart.css';
// import './../userInfo/voteTableCss.css';

import jQuery from "jquery";

window.$ = window.jQuery = jQuery;

const axios = require('axios');

class AuditionTable extends React.Component{
    constructor(props){
        super(props);
        this.state = { auditionCon: [], pageNum: 1 , count: 0};
        this.url = '/userInfo/myAudition/axios?page='+(this.state.pageNum-1)+'&size='+10+'&sort="id"';
    }
    setUrl(){
        this.url = '/userInfo/myAudition/axios?page='+(this.state.pageNum-1)+'&size='+10+'&sort="id"';
    }
    async componentDidMount(){
        let {data} = await axios.get(this.url)
        this.state.count = Math.ceil((data.count*1.0)/10)
        this.setState({auditionCon : data.auditionCon})
        
        console.log(this.state.count);
    }
    pagenation(e,page){
        console.log(page)
        this.state.pageNum = page 
        this.setUrl()   
        this.componentDidMount()
    }
    render() {
        console.log(this.state.vote)
        return(
                <div id="tablebox">
 
                         <Paper >
                            <Table size="small" id="myTable">
                            <TableHead>
                                <TableRow>
                                    <TableCell className="smart">번호</TableCell>
                                    <TableCell>오디션명</TableCell>
                                    <TableCell className="smart">오디션 ID</TableCell>
                                    <TableCell className="smart">신청일</TableCell>
                                </TableRow>
                                </TableHead>
                                <TableBody>
                                    <MyAuditionList  data={this.state.auditionCon}/>    
                                </TableBody>
                            </Table>
                        </Paper>
                    <Pagination count={this.state.count} page={this.state.pageNum} onChange={this.pagenation.bind(this)}> </Pagination>


                </div>
        )
    }
}

class MyAuditionList extends React.Component {
    constructor(props){
        super(props);
    }

    render() {
        return  this.props.data.map((auditionCon,index)=>{
            var time = auditionCon.fdate.substr(0,10)
            return (
                <TableRow key={'div'+index}>                 
                <TableCell  className="smart">{auditionCon.formid}</TableCell>
                <TableCell><a href={"/audition_con/show/"+auditionCon.formid}>{auditionCon.ftitle}</a></TableCell>
                <TableCell className="smart">{auditionCon.auditionid}</TableCell>
                <TableCell className="smart">{time}</TableCell>
             </TableRow>
               
               )
            
        })
        
    }
}


export default AuditionTable

