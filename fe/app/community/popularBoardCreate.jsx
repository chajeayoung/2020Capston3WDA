import React, {Component}from 'react'
import ReactDOM from 'react-dom';

import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';


const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
  
var url = document.location.href;
const num = url.split('/');
var param = num[num.length-1];
var param2 = num[num.length-2];

class PopularBoardCreate extends Component {
    
    constructor(props){
        
        super(props);
        this.state = { popularBoard: [] , pageNum: 1 , count: 0, allCount:0 ,board :"" };
      }

    async componentDidMount(){
  
  
    }
  

    render() {
           
        return(
        <div>
              <Paper >
                            <Table size="small" id="myTable">
                            <TableHead>
                            <TableCell>제목<input type="text" name="content"></input></TableCell>
                            <TableRow>
                             
                                            <TableCell>작성자</TableCell>
                                            <TableCell>댓글수</TableCell>
                                            <TableCell>작성일</TableCell>
                                            <TableCell>첨부파일</TableCell>
                            </TableRow>
                                </TableHead>
                                <TableBody>
                                </TableBody>
                            </Table>
             </Paper> 
                   
                               
                                
                                   
                                  
        </div> 
         )
    }
}
  

ReactDOM.render(<PopularBoardCreate/>,document.getElementById('popularBoardCreate'));