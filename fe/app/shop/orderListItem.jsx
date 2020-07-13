import React from 'react';

import '../smart.css';

export default class OrderListItem extends React.Component {
    render() {
        console.log(this.props.data);
        return this.props.data? this.props.data.map((prd, index) => {
            return (
                <tbody className="bagItem" key={index}>
                    <tr>
                        <td rowSpan="3" className="item">
                            <div className="imgName">
                                <a href={"/shop/orderShow/"+prd.orderListId}>
                                    <img className="smartImg" src={"/uploads/"+prd.img}></img>
                                </a>
                            </div>
                        </td>
                        <td>
                            <a href={"/shop/orderShow/"+prd.orderListId}>상품명: {prd.name}</a>
                        </td>
                    </tr>
                    <tr>
                        <td>옵션명: {prd.oTitle}</td>
                    </tr>
                    <tr>
                        <td>가격: {prd.itemPrice}</td>
                    </tr>

                </tbody>
            )
        })
        :
        (
            <div>주문 내역이 없습니다.</div>
        )

    }
}
// import React from 'react';

// import '../smart.css';

// export default class OrderListItem extends React.Component {
//     render() {
//         console.log(this.props.data);
//         return this.props.data? this.props.data.map((prd, index) => {
//             return (
//                 <div className="bagItem" key={index}>
                    
//                     <a href={"/shop/orderShow/"+prd.orderListId}>
//                         <img className="smartImg" src={"/uploads/"+prd.img}/>
//                         <div>상품명: {prd.name}</div>
//                     </a>
//                     <div>옵션명: {prd.oTitle}</div>
//                     <div>가격: {prd.itemPrice}</div>

//                 </div>
//             )
//         })
//         :
//         (
//             <div>주문 내역이 없습니다.</div>
//         )

//     }
// }