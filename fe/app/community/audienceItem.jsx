import React, {Component}from 'react'
import ReactDOM from 'react-dom';
import ItemCard4 from '../items/ItemCard5.jsx';

import './css/detailIndex.css';

const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
  
class Popular extends Component {
    
    constructor(props){
        super(props);
        this.state = { data: [] };
     
    }

    render() {
        const { data } = this.props;     
        return data.map((c,index)=>{
            return (
                <div key={'div'+index} className="card_div">
                    <a href={"/audience/read/"+c.applyId}>
                     <ItemCard4 key={c.applyId} img={c.img} name={c.acontent} />
                    </a>
                 </div>
            )
        })
      }
}

export default Popular;