import React, {Component}from 'react'
import ReactDOM from 'react-dom';
import ProgramItem from './../community/programItem.jsx';


const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
  
class CommunityItem extends Component {
    
    constructor(props){
        
        super(props);
        this.state = { data: [] };
    }
    async componentDidMount(){

        let {data} = await axios.get('/community/axios');
        this.setState({data});
    }

    render() {
        
        return(
        <div>

                <div className="voteItem" id="last">
                    <ProgramItem data={this.state.data}/>
                </div>
         </div> )
    }
}
     
  

export default CommunityItem
