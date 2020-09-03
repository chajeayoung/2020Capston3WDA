import React, { Component } from 'react'
import ReactDOM from 'react-dom';

import PopularItem from './popularItem.jsx';
import HotclibItem from './hotclibItem.jsx'
import AudienceItem from './audienceItem.jsx'
import Ulist from './../audience/ulist2.jsx'

import Pagination from '@material-ui/lab/Pagination';

const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');

var url = document.location.href;
const num = url.split('/');
var param = num[num.length - 1];

class Index extends Component {

    constructor(props) {
        super(props);
        this.state = {
            program: [], hotclib: [], audience: [], count: 0, popular: [], pageNum: 1, allCount: 0,
            count2: 0, pageNum2: 1, count3: 0, pageNum3: 1
        };
        // count : 인기인 pagenation,c count2 : 핫클립 pagenation, count3 : 방청권 pagenation
        // 인기인 url
        this.url = '/community/' + param + '/popular/axios?page=' + (this.state.pageNum - 1) + '&size=' + 10 + '&sort="id"';
        // 핫클릿 url
        this.url2 = '/community/' + param + '/hotclip/axios?page=' + (this.state.pageNum2 - 1) + '&size=' + 3 + '&sort="id"'; // 5개씩
        this.url3 = '/community/' + param + '/audience/axios?page=' + (this.state.pageNum3 - 1) + '&size=' + 5 + '&sort="id"'; //5개씩
    }

    async componentDidMount() {

        let { data } = await axios.get('/community/' + param + '/axios')

        console.log(data);
        // 프로그램 정보
        if (data.logo != '0') {

            $("header").css("background-image", "url(/uploads/" + data.logo + ")")
            $("header").css("background-position", "center")
            $("header").css("background-repeat", "no-repeat")
            $("header").css("background-size", "contain")


        }
        $("header")
        // this.setState({program})    

        this.setState({ program: data })

        this.getPopular();
        // let {data} = await axios.get(this.url2); // 핫클립 정보 가져오기
        // console.log("핫클립 정보 :",data);
        this.getHotclib()

        this.getAudience()
        // console.log(this.state);


    }
    async getPopular() {
        let { data: popular } = await axios.get(this.url)
        // 프로그램 인기인 정보

        this.state.allCount = (popular.pop())
        console.log(this.state.allCount)
        this.state.count = Math.ceil((this.state.allCount * 1.0) / 10)
        this.setState({ popular })

    }
    async getHotclib() {
        console.log("호출", this.url2)
        let { data } = await axios.get(this.url2); // 핫클립 정보 가져오기

        var count = Math.ceil((data.count * 1.0) / 3) // 3 개씩
        this.setState({ count2: count, hotclib: data.hotclips })
    }
    async getAudience() {// 방청권 정보 들고오기
        let { data } = await axios.get(this.url3);

        var count = Math.ceil((data.count * 1.0) / 5) // 5개 씩
        this.setState({ count3: count, audience: data.audiences })
        console.log(this.state)
    }

    setUrl() {// 인기인 페이징
        this.url = '/community/' + param + '/popular/axios?page=' + (this.state.pageNum - 1) + '&size=' + 10 + '&sort="id"';

    }
    setUrl2() { // 핫클립 페이징
        this.url2 = '/community/' + param + '/hotclip/axios?page=' + (this.state.pageNum2 - 1) + '&size=' + 3 + '&sort="id"';// 5개씩
    }
    setUrl3() { // 방청권 페이징
        this.url3 = '/community/' + param + '/audience/axios?page=' + (this.state.pageNum3 - 1) + '&size=' + 5 + '&sort="id"';// 5개씩
    }
    pagenation(e, page) {
        //console.log(page)
        this.state.pageNum = page
        this.setUrl()
        this.getPopular()
    }
    pagenation2(e, page) {
        this.state.pageNum2 = page;
        this.setUrl2();
        this.getHotclib();
    }
    pagenation3(e, page) {
        this.state.pageNum3 = page;
        this.setUrl3();
        this.getAudience();
    }
    plus() {
        console.log("클릭")
        location.href = document.location.href + "/hotclib";
    }

    goUrl() {
        location.href = `/audience/ulist2?programId=${param}`;
    }
    back() {
        location.href = `/community`;
    }
    render() {
        console.log("ggg")
        console.log(this.state.hotclib)
        return (
            <div className="community_item">

                <div id="audience">
                    <div className="communityTitle">방청권</div><br />
                    {this.state.audience.length>0?
                    
                    <div>
                    <Ulist param={param}></Ulist>                 
                    <br /><br /><br />
                    <Pagination count={this.state.count3} page={this.state.pageNum3} onChange={this.pagenation3.bind(this)} style={{ placeContent: "center" }}> </Pagination>
                    <button className="plus" type="button" onClick={this.goUrl.bind(this)} >더보기</button>
                    
                    </div>:<span>진행중인 방청권이 없습니다.</span>}

                    


                </div>
                <br /><br />
                <div id="hotclib">
                <div className="communityTitle">핫클립</div><br /><hr></hr>

                    {this.state.hotclib.length>0?
                    
                    <div>
                    <HotclibItem data={this.state.hotclib} />                    
                    <br /><br /><br />
                    <Pagination count={this.state.count2} page={this.state.pageNum2} onChange={this.pagenation2.bind(this)} style={{ placeContent: "center" }}> </Pagination>
                    <button className="plus" type="button" onClick={this.plus.bind(this)}>더보기</button>
                    
                    </div>:<span>등록된 핫클립 없습니다.</span>}

               

                </div>

                <br /><br />
                <div id="popular">
                <div className="communityTitle">출연자</div><br />                    

                    {this.state.popular.length>0?
                    
                    <div>
                    <PopularItem data={this.state.popular} />          
                    <br /><br /><br />
                    <Pagination count={this.state.count} page={this.state.pageNum} onChange={this.pagenation.bind(this)} style={{ placeContent: "center" }}> </Pagination>
                    
                    </div>:
                    <div>
                    <span>등록된 핫클립 없습니다.</span>

                    </div>}

                    <br /><br /><br />
                    <button className="plus"type="button" onClick={this.back.bind(this)}>목록</button>
                </div>

            </div>)
    }
}

ReactDOM.render(<Index />, document.getElementById('communityIndex'));


