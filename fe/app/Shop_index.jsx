import React, { Fragment } from 'react';
import ReactDOM from 'react-dom';
import Header_top from './shop/Header_top.js';
import Header_middle from './shop/Header_middle.js';
import Header_bottom from './shop/Header_bottom.js';
import SliderFrame from './shop/SliderFrame'
import Footer from './shop/Footer.js';
import Footer2 from './shop/Footer2.js';
import Chat from './shop/Chat.jsx';
import Section from './shop/Section.js'
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
import jQuery from "jquery";
window.$ = window.jQuery = jQuery;
// import Section from './shop/section'
// import 'bootstrap/dist/css/bootstrap.min.css';
// import './css/animate.css';
// import './css/main.css';
// import './css/responsive.css';
// import './js/jquery';
class Shop_index extends React.Component {
    constructor(props) {
        super(props);
        this.state = { data: [], recommend: [] }
        this.chat = $("#principal").val();
        
    }

    async componentDidMount() {
        let { data } = await axios.get("/shop/index/axios");
        console.log(data);
        this.setState({ data });
    }

    render() {
            return (
                <Fragment>
                    {/* <Header_top></Header_top> */}
                    <Header_middle></Header_middle>
                    <Header_bottom></Header_bottom>
                    {/* <SliderFrame></SliderFrame> */}
                    <Section data={this.state.data}></Section>
                    {/* <Footer></Footer>
                    <Footer2></Footer2> */}
                    {
                        this.chat != 0 ? <Chat></Chat> : <div></div>
                    }
                    
                </Fragment>
            )
        
    }
}



ReactDOM.render(<Shop_index />, document.getElementById('root'));