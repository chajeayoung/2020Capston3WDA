import React, { Component } from 'react';
// import Card from '@bit/jakeprins.react-milkshake.card';
import Card from './card.jsx'



class ItemCard5 extends Component{
    constructor(props){
		super(props);
    }
    //투표 후보자 카드
    render(){
        return (
            <div>
                <Card 
                    image={'/uploads/'+this.props.img}
                    title={this.props.name}
                    // text={this.props.info}
                    centered={true}
                />
            </div>
        )
        
    }
}

export default ItemCard5