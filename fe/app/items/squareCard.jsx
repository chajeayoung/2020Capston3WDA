import React from 'react';
import SquareCard from '@bit/isaachardy.airship.square-card';


class SquareCard extends Component {
	constructor(props){
		super(props);

		this.item = {		
		name: "Hello",
		theme: "Theme",
		thumbnailUrl: "https://picsum.photos/300/300"
	}
		

		
	}

	render() {
		return (
			<div>
				<SquareCard featuredItem={this.item} libraryId={5} />
			</div>
		)
	}
}
export default SquareCard

