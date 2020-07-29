import React, {Component}from 'react'
import ReactDOM from 'react-dom';
import ItemCard4 from '../items/itemCard4.jsx';

import './detailIndex.css';

const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
  
class Hotclib extends Component {
    
    constructor(props){
        super(props);
        this.state = { data: [] };
     
    }

    render() {
        const { data } = this.props;     
        return data.map((hotclib,index)=>{
            return (
                <div key={'div'+index} className="community_index_item">
                    <a href={"/community/"+ hotclib.programid + "/hotclib/read/"+hotclib.hotclibid}>
                     <ItemCard4 key={hotclib.hotclibid} img={hotclib.filename2} name={hotclib.htitle} />
                    </a>
                 </div>
            )
        })
      }
}

export default Hotclib;