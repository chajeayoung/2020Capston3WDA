import React from 'react';
export default class OrderStateItemPop extends React.Component {
    constructor(props){
        super(props)

    }
    render() {
        console.log(this.props)
        if(this.props.data.length != 0){
            return this.props.data.map((item, index) => {
                return (
                    <tbody className="bagItem" key={index}>
                        <tr>
                            <td rowSpan="2" className="item">
                                <span className="imgName">                        
                                    <img src={"/uploads/"+item.img}/>
                                </span>
                            </td>
                            <td>이름: {item.pName}</td>
                        </tr>
                        <tr>
                            <td>총 판매수: {item.count}</td>
                        </tr>
                    </tbody>

                 )
            })
        }else{
            return <span>상품이 없습니다.</span>
        }
        
        
    }
}