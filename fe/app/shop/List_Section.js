import React from 'react';
import LeftSidebar from './LeftSidebar';
import List_Item from './List_Item.jsx'
import Pagination from '@material-ui/lab/Pagination';
import NewItem from './NewItem';
import CateTab from './CateTab';
import Item2 from './Item2'; 
export default class List_Section extends React.Component {
    

    constructor(props){
        super(props)
    }
    render() {
        console.log(this.props)
        return (
            <section>
                <div className="search">
                    <div className="searchBox">
                        <input type="text" placeholder="검색어" id="itemSearch"></input><button className="searchButton" type="button" onClick={this.props.search.bind(this,this.props.that)}>검색</button>
                    </div>
                </div>
                <div className="container">
                    <div className="row">
                        <div className="col-sm-3"> {/* 왼쪽 카테고리, 프로그램 별... */} 
                            <LeftSidebar event ={this.props.event} category={this.props.data.categoryD} that={this.props.that} proEvent={this.props.proEvent}> </LeftSidebar>
                        </div>
                        
                        <div className="col-sm-9 padding-right"> {/* 오른쪽 신상품... 상품목록 */}
                            <List_Item data={this.props.data.data.prds}></List_Item>
                        </div>
                       
                    </div>
                    <div className="pagediv">
                        <div className="page">
                        <Pagination className="pagenation" count={this.props.data.count} page={this.props.data.pageNum} onChange={this.props.paging.bind(this, this.props.that)}> </Pagination>
                        </div>
                            {/* display: inline-block; */}
                           
                        </div>
                </div>
            </section>
        )
    }
}