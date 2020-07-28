import React, {Component}from 'react'
import ReactDOM from 'react-dom';

import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';

import Pagination from '@material-ui/lab/Pagination';
import ItemCard4 from '../items/itemCard4.jsx';

import './Modal.css';

const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
  
var url = document.location.href;
const num = url.split('/');
var param = num[num.length-1];
var param2 = num[num.length-2];
var param3 = num[num.length-3];

class PopularBoardView extends Component {
    
    constructor(props){
        
        super(props);
        this.state = { popularBoard: [] , pageNum: 1 , count: 0, allCount:0 ,board :"" , update:false,data:[]};
      }

    async componentDidMount(){
        console.log("dd: "+$("#popLogoImg").val())
        $("header").css("background-image","url('/uploads/"+$("#popLogoImg").val()+"')")
        $("header").css("background-position","top")

        let {data: data} = await axios.get(`/community/${param3}/${param2}/${param}/axios`)  

        this.state.popularBoard = (data.pop())
        this.setState({data})
        console.log(this.state.popularBoard);
        console.log(this.state.data);

        this.state.board = `/community/${param3}/${param2}`
        //this.setState({popularBoard})

        //this.state.board = `/community/${param3}/${param2}`
        console.log(param)

        

    }
    goBack(){
        location.href=this.state.board;

    }
    update(){
        this.setState({update:true});  
    }
    unUpdate(){
        this.setState({update:false});  
    }

    deleteFile(c){
    if(!confirm(c.name+"을 삭제하시겠습니까")) return;
    axios.delete('/community/file/'+c.id)
    location.reload()

    }


    deletePost(){
    if(!confirm("게시글을 삭제하시겠습니까")) return;

    axios.delete(`/community/${param3}/${param2}/${param}/delete`)

    alert("삭제완료")

    this.goBack()

    }

    render() {
        
        return(
        <div>
              <Paper >
                            <Table size="small">
                            <TableHead>
                                <TableRow>
                                {this.state.update && <TableCell colSpan="5"><input type="text" name="title" defaultValue={this.state.popularBoard.title} required/></TableCell>}
                                {!this.state.update &&<TableCell colSpan="5">{this.state.popularBoard.title}</TableCell>}
                                </TableRow>  
                                </TableHead> 

                               

                                <TableBody>

                                <TableRow>
                                <TableCell>{this.state.popularBoard.nickname}</TableCell>
                                <TableCell>등록일 : {this.state.popularBoard.date}</TableCell>
                                <TableCell>수정일 : {this.state.popularBoard.mdate}</TableCell>
                                {/* {this.state.popularBoard.viewCount && this.state.popularBoard.viewCount }{} */}
                                <TableCell>{this.state.popularBoard.viewCount}</TableCell>
                                <TableCell>{this.state.popularBoard.replyCount}</TableCell>
                                </TableRow> 
                                
                                {/* <ItemCard4 key={c.img} img={c.img} name={c.name} /> */}
                                
                                <TableRow>                             
                                {this.state.data.map((c,index)=>{
                                                                    
                                    return !this.state.update?(
                                        
                                        <TableCell key={'tablecell'+index}><ItemCard4 key={c.img} img={c.name} /></TableCell> ):
                                        (<TableCell key={'tablecell'+index}><ItemCard4 key={c.img} img={c.name} />
                                        <button type="button" onClick={this.deleteFile.bind(this,c)}>삭제</button>
                                         </TableCell>)
                                       
                                            
                                    })}

                                </TableRow>   
                                 {/* {this.state.data!=null && <TableCell colSpan="5">{this.state.data}</TableCell>}                      */}
                            
                               
                                <TableRow>
                                {this.state.update && <TableCell colSpan="5" rowSpan="200"><input type="text" name="content" defaultValue={this.state.popularBoard.content} required/></TableCell>}
                                {!this.state.update &&<TableCell colSpan="5" rowSpan="200">{this.state.popularBoard.content}</TableCell>}                             
                                </TableRow>  
                         

                                </TableBody>
                                    </Table>
                                    </Paper> 
                                 <input type="hidden" name="date" value={this.state.popularBoard.date}/>
                                 <input type="hidden" name="rid" value={this.state.popularBoard.r_id}/> 
                                 <input type="hidden" name="id" value={this.state.popularBoard.id}/> 
                                 <input type="hidden" name="popularid" value={this.state.popularBoard.popular_id}/> 
                               
                                
                                {/* 게시글작성자 or 관리자 or 매니저일경우 수정삭제가능 */}
                                {(this.state.popularBoard.r_id==this.state.popularBoard.sessionUser||
                                this.state.popularBoard.sessionRole==1||
                                this.state.popularBoard.sessionUser==this.state.popularBoard.managerId)&&
                                <div>
                               
                                <button type="button" onClick={this.update.bind(this)}>수정</button>
                                {this.state.update && <button onClick={this.unUpdate.bind(this)} >취소</button>}
                                {this.state.update && <button formAction={'/community/'+param3+'/'+param2+'/update'} >확인</button>}
                                <button type="button" onClick={this.deletePost.bind(this)}>삭제</button>
                                </div>
                                 }{
                                 <div><button type="button" onClick={this.goBack.bind(this)}>글 목록</button></div>}
                               
                                  
        </div> 
         )
    }
}
  

ReactDOM.render(<PopularBoardView/>,document.getElementById('popularBoardView'));