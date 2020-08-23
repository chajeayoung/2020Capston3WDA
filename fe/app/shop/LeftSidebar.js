import React from 'react';
import CategoryD from './CategoryD.jsx'
import ProgramList from './ProgramList.jsx'
export default class LeftSidebar extends React.Component {
    constructor(props){
        super(props);
    }
    render() {
        console.log(this.props);
        return (
            <div className="left-sidebar">
                <div className="brands_products">
                    <h2>프로그램 별</h2>
                    <div className="brands-name">
                        <ul className="nav nav-pills nav-stacked">
                            {/* 카테고리 6번은 프로그램 목록입니다. */}
                            <ProgramList data={this.props.category[6]} event={this.props.proEvent} that={this.props.that}/>
                        </ul>
                    </div>
                </div>
                
                <h2>카테고리</h2>
                <div className="panel-group category-products" id="accordian">
                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h4 className="panel-title">
                                <a data-toggle="collapse" data-parent="#accordian" href="#1">
                                    <span className="badge pull-right"><i className="fa fa-plus"></i></span>
											악세서리
										</a>
                            </h4>
                        </div>
                        <div id="1" className="panel-collapse collapse">
                            <div className="panel-body">
                                <ul>
                                    <CategoryD data={this.props.category[0]} event={this.props.event} that={this.props.that}/>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h4 className="panel-title">
                                <a data-toggle="collapse" data-parent="#accordian" href="#2">
                                    <span className="badge pull-right"><i className="fa fa-plus"></i></span>
											의류
										</a>
                            </h4>
                        </div>
                        <div id="2" className="panel-collapse collapse">
                            <div className="panel-body">
                                <ul>
                                    <CategoryD data={this.props.category[1]} event={this.props.event} that={this.props.that}/>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h4 className="panel-title">
                                <a data-toggle="collapse" data-parent="#accordian" href="#3">
                                    <span className="badge pull-right"><i className="fa fa-plus"></i></span>
											생활용품
										</a>
                            </h4>
                        </div>
                        <div id="3" className="panel-collapse collapse">
                            <div className="panel-body">
                                <ul>
                                <CategoryD data={this.props.category[2]} event={this.props.event} that={this.props.that}/>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h4 className="panel-title">
                                <a data-toggle="collapse" data-parent="#accordian" href="#4">
                                    <span className="badge pull-right"><i className="fa fa-plus"></i></span>
											패션소품
										</a>
                            </h4>
                        </div>
                        <div id="4" className="panel-collapse collapse">
                            <div className="panel-body">
                                <ul>
                                <CategoryD data={this.props.category[3]} event={this.props.event} that={this.props.that}/>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h4 className="panel-title">
                                <a data-toggle="collapse" data-parent="#accordian" href="#5">
                                    <span className="badge pull-right"><i className="fa fa-plus"></i></span>
											테피스트리
										</a>
                            </h4>
                        </div>
                        <div id="5" className="panel-collapse collapse">
                            <div className="panel-body">
                                <ul>
                                <CategoryD data={this.props.category[4]} event={this.props.event} that={this.props.that}/>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div className="panel panel-default">
                        <div className="panel-heading">
                            <h4 className="panel-title">
                                <a data-toggle="collapse" data-parent="#accordian" href="#6">
                                    <span className="badge pull-right"><i className="fa fa-plus"></i></span>
											그 외 소품
										</a>
                            </h4>
                        </div>
                        <div id="6" className="panel-collapse collapse">
                            <div className="panel-body">
                                <ul>
                                <CategoryD data={this.props.category[5]} event={this.props.event} that={this.props.that}/>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                
            </div>
        )
    }
}