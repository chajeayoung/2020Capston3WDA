import React, {Component}from 'react'
import ReactDOM from 'react-dom';
import ItemCard3 from '../items/itemCard3_big.jsx';
import './css/communityIndex.css';

const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');

class ProgramItem extends Component {
    constructor(props){
        super(props);
        this.state = { data: [] };
       
    }

     render() {
        const { data } = this.props;
          return data.map((program,index)=>{
            return (
                <div key={'div'+index} className="index_item">
                    <a href={"/community/"+program.id}>
                       <ItemCard3 img={program.img} title={program.name}/>
                    </a>
                 </div>
            )
        })
      }
}
export default ProgramItem;

