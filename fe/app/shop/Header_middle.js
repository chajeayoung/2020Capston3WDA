import React from 'react';

class Header_middle extends React.Component {
    render() {
        return (
            <div className="header_middle">
                <div className="container">
                    <div className="row">
                        <div className="col-sm-4">
                            <div className="logo pull-left">
                                {/* <a href="/shop/index"><img src="/shop/images/home/logo.png" alt="" /></a> */}
                            </div>

                        </div>
                        <div className="col-sm-8">
                            <div className="shop-menu pull-right">
                                <ul className="nav navbar-nav">
                                    <li><a href="/shop/orderList"><i className="fa fa-crosshairs"></i> 주문목록</a></li>
                                    <li><a href="/shop/mybag"><i className="fa fa-shopping-cart"></i> 장바구니</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default Header_middle;