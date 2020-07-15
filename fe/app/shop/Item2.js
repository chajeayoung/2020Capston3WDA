import React from 'react';
import './css/item2.css'


export default class Item2 extends React.Component {
    constructor(props){
        super(props)

    }
    render() {
        console.log(this.props)
        if(this.props.data){
            if(this.props.data.length != 0){
                return this.props.data.map((prd, index) => {
                    return (
                        <div className="col-sm-3" key={index}>
                            <div className="product-image-wrapper">
                                <div className="single-products">
                                    <div className="productinfo text-center">
                                        <a href={"/shop/product/"+prd.productId}>
                                            <div className="product_Top_div">
                                                <div className="imgDiv">
                                                    <img src={"/uploads/"+prd.img} alt="" />
                                                </div>
                                                <h2></h2>
                                                <p className="productName">{prd.name}</p>
                                            </div>
                                        </a>
                                        {/* <a href="#"className="btn btn-default add-to-cart"><i className="fa fa-shopping-cart"></i>장바구니에 추가</a> */}
                                    </div>
                                </div>
                            </div>
                        </div>
                     )
                })
            }else{
                return <div></div>
            }
        }else{
            return <div></div>
        }
    }
}