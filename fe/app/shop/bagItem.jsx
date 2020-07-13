import React from 'react';
import '../smart.css';

export default class BagItem extends React.Component {
    render() {
        console.log(this.props.data);
        return this.props.data? this.props.data.map((prd, index) => {
            return (
                <tbody className="bagItem" key={index}>
                    <tr>
                        <td rowSpan="5" className="checkBox">
                            <input type="checkbox"  onClick={this.props.check.bind(this,this.props.this,index,(prd.price + prd.oPrice)*prd.quantity)}/>
                        </td>
                        <td rowSpan="5" className="item">
                            <div className="imgName">
                                <a href={"/shop/product/"+prd.productId}>
                                <img  src={"/uploads/"+prd.img}></img>
                                </a>
                            </div>
                        </td>
                        <td><a href={"/shop/product/"+prd.productId}>상품명: {prd.name}</a></td>
                    </tr>
                    <tr>
                        <td><div>옵션명: {prd.oTitle}</div></td>
                    </tr>
                    <tr>
                        <td><div>{prd.quantity}개</div></td>
                    </tr>
                    <tr>
                        <td><div>{(prd.price + prd.oPrice)*prd.quantity}원</div></td>
                    </tr>
                    <tr>
                        <td><input type="button" onClick={this.props.event.bind(this,prd.id)} value="삭제하기"/></td>
                    </tr>
                </tbody>
            )
        })
        :
        (
            <div>장바구니가 비었습니다.</div>
        )

    }
}

// import React from 'react';
// import '../smart.css';

// export default class BagItem extends React.Component {
//     render() {
//         console.log(this.props.data);
//         return this.props.data? this.props.data.map((prd, index) => {
//             return (
//                 <div className="bagItem" key={index}>
//                     <div className="bagCheckBox">
//                         <input type="checkbox"  onClick={this.props.check.bind(this,this.props.this,index)}/>
//                     </div>
//                     <a href={"/shop/product/"+prd.productId}>
//                         <img className="smartImg" src={"/uploads/"+prd.img}></img>
//                     </a>
//                     <div><a href={"/shop/product/"+prd.productId}>상품명: {prd.name}</a></div>
//                     <div>옵션명: {prd.oTitle}</div>
//                     <div>{prd.quantity}개</div>
//                     <div>{(prd.price + prd.oPrice)*prd.quantity}원</div>
//                     <input type="button" onClick={this.props.event.bind(this,prd.id)} value="삭제하기"/>
//                 </div>
//             )
//         })
//         :
//         (
//             <div>장바구니가 비었습니다.</div>
//         )

//     }
// }