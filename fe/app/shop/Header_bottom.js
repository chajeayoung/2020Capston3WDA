import React from 'react';

class Header_bottom extends React.Component {
    render() {
        return (
            <div className="header-bottom">
                <div className="container">
                    <div className="row">
                        <div className="col-sm-9">
                            <div className="navbar-header">
                                <button type="button" className="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                    <span className="sr-only">Toggle navigation</span>
                                    <span className="icon-bar"></span>
                                    <span className="icon-bar"></span>
                                    <span className="icon-bar"></span>
                                </button>
                            </div>
                            <div className="mainmenu pull-left">
                                <ul className="nav navbar-nav collapse navbar-collapse">
                                <li><a href="/" className="active">RIRO 홈</a></li>
                                    <li><a href="/shop/index" className="active">홈</a></li>
                                    <li><a href="/shop/list" className="active">전체상품</a></li>
                                    {/* <li className="dropdown"><a href="#">굿즈샵<i className="fa fa-angle-down"></i></a>
                                        <ul role="menu" className="sub-menu">
                                            <li><a href="/shop/list">전체상품</a></li>

                                        </ul>
                                    </li> */}
                                    {/* <li className="dropdown"><a href="#">QnA<i className="fa fa-angle-down"></i></a>
                                        <ul role="menu" className="sub-menu">
                                            <li><a href="">자주 묻는 질문</a></li>
                                            <li><a href="">1:1 문의하기</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="">이벤트</a></li>
                                    <li><a href="">공지사항</a></li> */}
                                </ul>
                            </div>
                        </div>
                        <div className="col-sm-3">

                            <div className="search_box pull-right">
                                {/* <input type="text" placeholder="Search" /> */}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default Header_bottom;