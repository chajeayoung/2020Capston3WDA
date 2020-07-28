import React, { Component } from 'react'
import ReactDOM from 'react-dom';

import Hotclibdetail from '../hotclib/hotdetail.jsx';
import Hotclibreply from '../hotclib/hotclibreply.jsx';
// import Hotclibfile from '../hotclib/hotclibfile.jsx';

const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');


var url = document.location.href;
const num = url.split('/');
var param = num[num.length-4];
var param1 = num[num.length-1];

class Hotclibread extends Component{

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
           
            <Hotclibdetail></Hotclibdetail> 
            <Hotclibreply></Hotclibreply>
            
        </div>       
        )
    }
}

ReactDOM.render(<Hotclibread/>,document.getElementById('hotclibread'));