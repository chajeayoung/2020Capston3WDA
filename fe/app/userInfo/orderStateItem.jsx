import React from 'react';
export default class OrderStateItem extends React.Component {
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
                                <div className="imgName">
                                    <a href={"/shop/"}>
                                        <img src={"/uploads/"+item.image}/>
                                    </a>
                                </div>
                            </td>
                            <td><a href="">상품명: {item.name}</a></td>
                        </tr>
                        <tr>
                            <td>전체 판매개수: {item.sum}</td>
                        </tr>
                    </tbody>

                 )
            })
        }else{
            return <div>상품이 없습니다.</div>
        }
        
        
    }
}