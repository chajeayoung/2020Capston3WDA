
import React, {Component}from 'react'
import ReactDOM from 'react-dom';

import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';

import Pagination from '@material-ui/lab/Pagination';

import './css/profileIndex.css';

import '../smart.css';

const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
  
class UserIndex extends Component {
    
    constructor(props){
        super(props);
        this.state = { profile: [] , pageNum: 1 , count: 0};
        this.url = '/userInfo/axios2?page='+(this.state.pageNum-1)+'&size='+10+'&sort="id"';
    }
    setUrl(){
        this.url = '/userInfo/axios2?page='+(this.state.pageNum-1)+'&size='+10+'&sort="id"';
    }

    haha(e,page){
        console.log(page)
        this.state.pageNum = page
        this.setUrl()   
        this.componentDidMount()
    }

    async componentDidMount(){
        let {data : profile} = await axios.get(this.url)
        this.state.count = Math.ceil((profile.pop()*1.0)/10)
        this.setState({profile})
        
    }
    render() {
        
        return(
                <div>
                    <Pagination count={this.state.count} page={this.state.pageNum} onChange={this.haha.bind(this)}> </Pagination>
 
                         <Paper>
                            <Table id="myTable">
                            <TableHead>
                                <TableRow>
                                            <TableCell>번호</TableCell>
                                            <TableCell>프로필사진</TableCell>
                                            <TableCell className="smart">성명</TableCell>
                                            <TableCell>아이디</TableCell>
                                            <TableCell className="smart">닉네임</TableCell>
                                            <TableCell className="smart">성별</TableCell>
                                            <TableCell className="smart">생년월일</TableCell>
                                            <TableCell className="smart">연락처</TableCell>
                                            <TableCell className="smart">가입일</TableCell>
                                            <TableCell className="smart">주소</TableCell>
                                            <TableCell className="smart">상세주소</TableCell>
                                            <TableCell className="smart">포인트</TableCell>
                                            <TableCell>권한번호</TableCell>
                                </TableRow>
                                </TableHead>
                                <TableBody>
                                             <Index profile={this.state.profile}/>    
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
       this.props.profile
       
    }

  

    render(){
         console.log(this.props.profile)
                    return  this.props.profile.map((p,index)=>{
                       
                        return (
                            
                            <TableRow key={'div'+index}>
                                <TableCell  key={index}>{p.r_id}</TableCell>
                                <TableCell><img id="profile" src={p.profile} alt="profile"/></TableCell>
                                <TableCell className="smart">{p.username}</TableCell>
                                <TableCell>{p.userid}</TableCell>
                                <TableCell className="smart">{p.nickname}</TableCell>
                                <TableCell className="smart">{p.gender}</TableCell>
                                <TableCell className="smart">{p.birth}</TableCell>
                                <TableCell className="smart">{p.phone}</TableCell>
                                <TableCell className="smart">{p.joindate}</TableCell>
                                <TableCell className="smart">{p.addr}</TableCell>
                                <TableCell className="smart">{p.addr2}</TableCell>
                                <TableCell className="smart">{p.point}</TableCell>
                                <TableCell>{p.role}</TableCell>
                            </TableRow>
                            
                        )
                    })
                }
            
        
    
}


ReactDOM.render(<UserIndex/>,document.getElementById('userIndex'));
