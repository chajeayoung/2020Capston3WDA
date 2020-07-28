import React, { Component } from 'react'
import ReactDOM from 'react-dom';

import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import ItemCard4 from '../items/itemCard4.jsx';
import Pagination from '@material-ui/lab/Pagination';
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');


var url = document.location.href;
const num = url.split('/');
var param = num[num.length-4];
var param1 = num[num.length-1];

class Hotclibreply extends Component{

    constructor(props){
        super(props);
        this.state = {rfile:'', reply: [],program: [], hotclib: [] , pageNum: 1 , count: 0, allCount:0, file : '', previewURL:'',sessionUser:''}
        // this.url = '/community2/'+param+'/hotclib';
        console.log(param);
        console.log(param1);
        // this.url = '/community/'+param1+'/axios?page='+(this.state.pageNum-1)+'&size='+10+'&sort="date"';
        this.url = '/community/'+param+'/hotclib/read/'+param1+'/axios';

        console.log(this.url);
    }

    async componentDidMount(){

        let {data} = await axios.get(this.url)
        console.log(data)
        // let {data : reply} = await axios.get(this.url)
        this.setState({reply:data.reply});
        this.setState({rfile:data.rfile});
        this.setState({hotclib:data.hotclib});
                        
    }
    

    render(){
        console.log("안녕")
        console.log(this.state.reply)
        console.log(this.url);
        
        
        return(
                    
           <div>
                    {this.state.reply.map((reply,index)=>{
                       return (  
                        
                         <div key={'div'+index}>
                            <div>내용 : {reply.r_content}</div>
                            <div>작성일 : {reply.r_date}</div>
                            <div>작성자 : {reply.rusername}</div>  
                            <button>수정</button>
                            <button>삭제</button>                
                            </div>
                            
                              )
                              
                            }
                            
                         ) 
                     }                            
           </div>            
        )
    }
}
export default Hotclibreply;