
import React, {Component}from 'react'
import ReactDOM from 'react-dom';

import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';

import Pagination from '@material-ui/lab/Pagination';
import ItemCard3 from '../items/itemCard3_big.jsx';

import "./myProgram.css";
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
  
class MyProgram extends Component {
    
    constructor(props){
        super(props);
        this.state = { program: [] };
     
        document.getElementById("register_form").addEventListener("submit",this.result_submit.bind(this));

    }
 

 
    async componentDidMount(){
        let {data: program} = await axios.get('/userInfo/myProgram/axios')
      
        this.setState({program})
        console.log({program})
        
    }
    result_submit(e){
        
        if(!confirm("이미지를 변경 하시겠습니까?")) return;
 
    }
   

    render() {
        return(
                <div>
                  
                            
                            <span> 대표이미지 </span> 
                            <ItemCard3 img={this.state.program.img}/> 
                            <input type="file" name="file"></input>
                            
                            <span>로고 이미지</span>
                            <ItemCard3 img={this.state.program.logo}/> 
                            <input type="file" name="logoImg"></input>
                            
                            <span> 프로그램명 </span> 
                            <input type="text" name="name" defaultValue={this.state.program.name}></input>
                            
                           

                            
                            <span> 카테고리 </span> 
                            <input type="text" name="category" defaultValue={this.state.program.category}></input>
                            <input type="hidden" name="id" value={this.state.program.id}></input>
                            <input type="hidden" name="img" value={this.state.program.img?this.state.program.img:'/img/defaultProfile.png'}></input>
                         

                            <button className="submit_button" type="submit">변경하기</button>
                            {/* <input type="submit" value="변경" onClick={this.update.bind(this,program)}></input> */}
                            
                           


                </div>
        )
     
    }
}


ReactDOM.render(<MyProgram/>,document.getElementById('myprogram'));
