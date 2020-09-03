
import React, {Component}from 'react'
import ReactDOM from 'react-dom';

import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';

import Pagination from '@material-ui/lab/Pagination';
import './../programRegister/Modal.css';

import '../smart.css';

const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
  
class AllCompany extends Component {
    
    constructor(props){
        super(props);
        this.state = { company: [] , pageNum: 1 , count: 0, modal : false };
        this.url = '/userInfo/axios3?page='+(this.state.pageNum-1)+'&size='+10+'&sort="id"';
    }

    setUrl(){
        this.url = '/userInfo/axios3?page='+(this.state.pageNum-1)+'&size='+10+'&sort="id"';
    }

    pagenation(e,page){
        console.log(page)
        this.state.pageNum = page
        this.setUrl()   
        this.componentDidMount()
    }
    


    async componentDidMount(){
        let {data : company} = await axios.get(this.url)
        this.state.count = Math.ceil((company.pop()*1.0)/10)
        this.setState({company})
        
    }
    
    render() {
        
        return(
                <div id="tablebox">
                    <Pagination count={this.state.count} page={this.state.pageNum} onChange={this.pagenation.bind(this)}> </Pagination>
 
                         <Paper >
                            <Table size="small" id="myTable">
                            <TableHead>
                                <TableRow>
                                            <TableCell>번호</TableCell>
                                            <TableCell className="smart">카테고리</TableCell>
                                            <TableCell>프로그램명</TableCell>
                                            <TableCell className="smart">예상시작기간</TableCell>
                                            <TableCell className="smart">예상종료기간</TableCell>
                                            <TableCell>회원번호</TableCell>
                                            <TableCell className="smart">사업자번호</TableCell>
                                            <TableCell className="smart">회사명</TableCell>
                                            <TableCell className="smart">회사정보</TableCell>
                                            <TableCell className="smart">대표자성함</TableCell>
                                            <TableCell className="smart">회사연락처</TableCell>
                                            <TableCell className="smart">예상금액</TableCell>
                                            <TableCell>승인여부</TableCell>
                                </TableRow>
                                </TableHead>
                                <TableBody>
                                             <Index   company={this.state.company}/>    
                                </TableBody>
                                    </Table>
                                    </Paper>



                </div>
        )
     
    }
}
class Index extends Component{
    constructor(props){
        super(props);
       this.props.company
       this.state = { modal : false };
  
       document.getElementById("register_form").addEventListener("submit",this.result_submit.bind(this));
    }

    result_submit(e){
       
     alert("승인하였습니다.");
  
        
    } 

    sendSelect(c){
        const select  =  {"select" : c.c_id}
        if(!confirm(c.c_program+"을 등록하시겠습니까?")) return;
    
        axios.post('/userInfo/companyConfirm/', select)
        .then((response)=>{
            if(response.data.errorMessage){
                alert(response.data.errorMessage);
                // window.location.href="/vote";
                window.location.reload();
            }else{
                alert(response.data.message);
                window.location.reload();
                
            }
        });

    };


    handleOpenModal(c){
        console.log(c.c_id);
       this.setState({modal:true});
      
      };
      handleCloseModal(){
        this.setState({modal:false});
      };
  

    render(){
         console.log(this.props.company)
                    return  this.props.company.map((c,index)=>{
                       
                        return (
                            
                            <TableRow key={'div'+index}>
                             
                                <TableCell key={index}>{c.rownum}</TableCell>
                                <TableCell className="smart">{c.c_category}</TableCell>
                                <TableCell>{c.c_program} </TableCell>
                                <TableCell className="smart">{c.c_startdate}</TableCell>
                                <TableCell className="smart">{c.c_enddate}</TableCell>
                                <TableCell>{c.r_id}</TableCell>
                                <TableCell className="smart">{c.c_id}</TableCell>
                                <TableCell className="smart">{c.c_name}</TableCell>
                                <TableCell className="smart">{c.c_content}</TableCell>
                                <TableCell className="smart">{c.c_reader}</TableCell>
                                <TableCell className="smart">{c.c_phone}</TableCell>
                                <TableCell className="smart">{c.c_budget}</TableCell>
                                <TableCell>{c.c_confirm} <a onClick={this.sendSelect.bind(this,c)}>승인하기</a></TableCell>
                               
                             </TableRow>
                             
                            
                        )
                           
                    })
                  
                   
                     
                }
            
        
    
}


ReactDOM.render(<AllCompany/>,document.getElementById('allCompany'));
