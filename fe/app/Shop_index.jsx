import React, { Fragment } from 'react';
import ReactDOM from 'react-dom';
import Header_middle from './shop/Header_middle.js';
import Header_bottom from './shop/Header_bottom.js';
import Chat from './shop/Chat.jsx';
import Section from './shop/Section.js'
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
import jQuery from "jquery";
window.$ = window.jQuery = jQuery;


import './shop_index.css';

import Shop_list from './shop/Shop_list.jsx';
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

        $('header').toggleClass('changed');
        $("header").css("background-image","url('/uploads/goods.png')")
        $("header").css("background-position","center")
        // $("header").css("background-repeat","no-repeat")
        // $("header").css("background-size","contain")
    }

    render() {
            return (
                <Fragment className="shop_index">
                    {/* <Header_top></Header_top> */}
                    {/* <Header_middle></Header_middle> */}
                    {/* <Header_bottom></Header_bottom> */}
                    {/* <SliderFrame></SliderFrame> */}
                    <Section data={this.state.data}></Section>
                    {/* <Footer></Footer>
                    <Footer2></Footer2> */}
                    {/* {
                        this.chat != 0 ? <Chat></Chat> : <div></div>
                    } */}
                    <Shop_list></Shop_list>
                </Fragment>
            )
        
    }
}



ReactDOM.render(<Shop_index />, document.getElementById('root'));