import React, { Component } from 'react'
import ReactDOM from 'react-dom';

const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');

var url = document.location.href;
const num = url.split('/');
var param = num[num.length-3];

class Hotclib extends Component{

    constructor(props){
        super(props);
        this.state = { program: [], hotclib: [] , pageNum: 1 , count: 0, allCount:0, file : '', previewURL:'',sessionUser:''}
        // this.url = '/community2/'+param+'/hotclib';
        console.log(param);
        // this.url = '/community/'+param1+'/axios?page='+(this.state.pageNum-1)+'&size='+10+'&sort="date"';
        this.url = '/community/'+param+'/hotclib/upload';

        console.log(this.url);
    }

  
    
    async componentDidMount(){
       
        //  let {data : hotclib} = await axios.get('/hotclib/upload/axios')      
        // this.setState({hotclib})
      
    }
    back(){
        location.href=document.location.href
    }
    render(){
        return(
            <form method="post" encType="multipart/form-data" action="/community/24/hotclib/upload">
          <div>
            <label for="htitle">제목</label>
            <input  name="htitle"/><br/>
            <label for="h_content">내용</label>
            <textarea  name="h_content"/>
            <fieldset>
            <label for="h_reply">댓글 공개</label>
            허용<input type="radio" value="0" name="h_reply"/>
            비허용<input type="radio" value="1" name="h_reply"/>
            </fieldset>
            썸네일선택 <input type="file" name="filename2"/>
            영상선택<input type="file" name="filename"/><br/>
           <input type="submit" value="등록"/>
          </div>
          </form> 
        //   <button onclick={this.back.bind(this)}>취소</button>  
    
        )
    }
}

ReactDOM.render(<Hotclib/>,document.getElementById('hotclibupload'));