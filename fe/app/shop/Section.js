import React from 'react';
import LeftSidebar from './LeftSidebar';
import NewItem from './NewItem';
import CateTab from './CateTab';
import Item2 from './Item2'; 
import './css/section.css'
export default class Section extends React.Component {
    
    constructor(props){
        super(props)
    }
    render() {
        console.log("ㅁㅁㅁ"+this.props.data);
        console.log("111"+this.props.data[1]);
        return (
            <section>
                <div className="container">
                    <div className="row">
                        {/* <div className="col-sm-3">  */}
                        {/* 왼쪽 카테고리, 프로그램 별... */} 
                            {/* <LeftSidebar></LeftSidebar> */}
                        {/* </div> */}
                        
                        <div className="items"> {/* 오른쪽 신상품... 상품목록 */}
                            {/*  신상품 */}
                            {/* <NewItem data={this.props.data}></NewItem> */}

                            {/* 카테고리별 아이템, 5개씩 */}
                            <CateTab data={this.props.data[0]}/>

                            <div className="recommended_items">
                                <h2 className="title text-center">recommended items</h2>

                                <div id="recommended-item-carousel" className="carousel slide" data-ride="carousel">
                                    <div className="carousel-inner">
                                        {/*  추천상품 */}
                                        <div className="item active"> 
                                            <Item2 data={this.props.data[1]}/>
                                        </div>
                                        <div className="item"> 
                                            <Item2 data={this.props.data[2]}/>
                                        </div>
                                    </div>
                                    <a className="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
                                        <i className="fa fa-angle-left"></i>
                                    </a>
                                    <a className="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
                                        <i className="fa fa-angle-right"></i>
                                    </a>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </section>
        )
    }
}