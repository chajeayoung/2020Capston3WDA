import React from 'react';

export default class Item2 extends React.Component {
    constructor(props){
        super(props)

    }
    render() {
        console.log(this.props)
        if(this.props.data.length != 0){
            return this.props.data.map((prd, index) => {
                return (
                    <tbody className="orderItem" key={index}>
                        <tr>
                            <td rowSpan="4" className="item">
                                <div className="imgName">
                                    <a href={"/shop/product/"+prd.id}>
                                        <img src={"/uploads/"+prd.img}/>
                                    </a>
                                </div>
                            </td>
                            <td>
                                <div>상품명: {prd.name}</div>
                            </td>
                        </tr>
                        <tr>
                            <td><div>옵션: {prd.optionName}</div></td>
                        </tr>
                        <tr>
                            <td><div>개수: {prd.count}</div></td>
                        </tr>
                        <tr>
                            <td> <div>가격: {(prd.price + prd.oPrice)*prd.count}</div></td>
                        </tr>
                        <input type="hidden" name="productId" value={prd.id}/>
                        <input type="hidden" name="optionId" value={prd.optionId}/>
                        <input type="hidden" name="count" value={prd.count}/>
                        <input type="hidden" name="bagId" value={prd.bagId}/>
                    </tbody>
                 )
            })
        }else{
            return <div>상품이 없습니다.</div>
        }
        
        
    }
}