import React from 'react';
// import LeftSidebar from './LeftSidebar';
// import NewItem from './NewItem';
// import CateTab from './CateTab';
// import Item2 from './Item2'; 
import './css/list_item.css'
export default class List_Item extends React.Component {
    
    constructor(props){
        super(props)
    }
    render() {
        console.log(this.props.data)
        if(this.props.data && this.props.data.length >0){
            return this.props.data.map((prd, index)=>{
                return(
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
                                            <p>{prd.name}</p>
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
            return <div>상품이 없습니다.</div>
        }
        
        
    }
}