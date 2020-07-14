import React, { Fragment } from 'react';
import ReactDOM from 'react-dom';
import Header_top from './Header_top.js';
import Header_middle from './Header_middle.js';
import Header_bottom from './Header_bottom.js';
import SliderFrame from './SliderFrame'
import Footer from './Footer.js';
import Footer2 from './Footer2.js';
import Chat from './Chat.jsx';
// import Section from './Section.js'
import './css/shopList.css'
import Pagination from '@material-ui/lab/Pagination';
import List_Section from './List_Section.js'
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
// import Section from './shop/section'
// import 'bootstrap/dist/css/bootstrap.min.css';
// import './css/animate.css';
// import './css/main.css';
// import './css/responsive.css';
// import './js/jquery';
class Shop_list extends React.Component {
    constructor(props) {
        super(props);
        this.state = { categoryD:[],data: [] ,pageNum: 1 , count: 0 }
        this.category=0;
        this.categoryD=0;
        this.url;
        this.text = " ";
        this.program=0;
    }

    async componentDidMount() {
        // let { data } = await axios.get("/shop/index/axios");
        // console.log(data);
        // this.setState({ data });
        this.initCategory();
        this.initItem();
        
    }

    async categoryEvent(id, dId, that){
        console.log("카테고리 클릭");
       console.log("id",id)
       console.log("Did",dId)
       
       
      
       that.setCategory(id, dId);
    }
    setCategory(id, dId){
        this.category = id;
        this.categoryD = dId;
        this.url = "/shop/list/axios?categoryId="+this.category+"&categoryDId="+this.categoryD 
        this.state.pageNum =1;
        this.setItem();
        
    }
    programEvent(program, that){
        console.log("프로그램 클릭");
        console.log(program);

        that.setProgram(program);
        
    }
    setProgram(program){
        this.program = program;
        this.state.pageNum=1;
        this.url = "/shop/list/axios?programId="+this.program;
        this.setItem();
    }
    async setItem(){
        
        let {data} = await axios.get(this.url);
        console.log(data);
        this.state.count = Math.ceil((data.count*1.0)/8)
        this.setState({data:data})
    }

    async initCategory(){
        let {data} = await axios.get("/shop/categorySet");
        this.setState({categoryD:data})
    }
    async initItem(){
        let {data} = await axios.get("/shop/list/axios?&page=0&size=8&sort=0");
        console.log(data);
        this.state.count = Math.ceil((data.count*1.0)/8)
        this.setState({data})
    }
    pagenation(that,e,page){
        // console.log(that)// 부모 컴포넌트
        // console.log(e)// 이벤트 객체 ..
        // console.log(page)// 페이지 
        
        that.state.pageNum = page;
        that.setUrl();
        
        // that.setUrl();
    }
    
    setUrl(){
        this.url =  "/shop/list/axios?&page="+(this.state.pageNum-1)+'&size='+8+'&sort=0';

        if(this.category!= 0){ // 카테고리 선색이 있는 상태로, 페이지 전환 시.
            this.url += "&categoryId="+this.category+"&categoryDId="+this.categoryD;
        }
        if(this.program != 0) {// 프로그램 있는 상태에서, 페이징
            this.url +=  "&programId="+this.program;
        }
        
        
        if(this.text !=" "){// 검색어가 있으면, 
            this.url += "&search="+this.text;
        }
            
        this.setItem();    
    }

    itemSearch(that){
        that.text = $("#itemSearch").val();
        that.state.pageNum = 1;
        that.setUrl();
    }
    
    render() {
            return (
                <Fragment>
                    {/* <Header_top></Header_top> */}
                    <Header_middle></Header_middle>
                    <Header_bottom></Header_bottom>
                    <SliderFrame></SliderFrame>
                    <List_Section data={this.state} event={this.categoryEvent} that={this} paging={this.pagenation} search={this.itemSearch} proEvent={this.programEvent}> </List_Section>
                    <Footer></Footer>
                    <Footer2></Footer2>
                    <Chat></Chat>
                </Fragment>
            )
        
    }
}



ReactDOM.render(<Shop_list />, document.getElementById('Shop_list'));