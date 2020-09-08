import React, {Component}from 'react'
import ReactDOM from 'react-dom';
import ProgramItem from './programItem.jsx';


import Pagination from '@material-ui/lab/Pagination';

import './css/index.css'
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
  
class Index extends Component {
    
    constructor(props){
        
        super(props);
      
        this.state = { data: [], pageNum: 1 };
    }

    setUrl(){
        this.url = '/community/axios?page='+(this.state.pageNum-1)+'&size='+3+'&sort="date"';
      }

    async componentDidMount(){
        let {data} = await axios.get('/community/axios');
        this.setState({data});
    }

    pagenation(e,page){
        this.state.pageNum = page
        this.setUrl()   
        this.componentDidMount()
    }

    render() {
        
        return(
        <div>

                <div className="voteItem">
                    <ProgramItem data={this.state.data}/>

                    <Pagination count={this.state.count} page={this.state.pageNum} onChange={this.pagenation.bind(this)}> </Pagination>
        
                </div>
         </div> )
    }
}
     
  


ReactDOM.render(<Index/>,document.getElementById('communityIndex'));
