import React from "react";
import {
    Dropdown,
    DropdownToggle,
    DropdownMenu,
    DropdownItem
} from "shards-react";

export default class SortButton extends React.Component {
    constructor(props) {
        super(props);
        this.toggle = this.toggle.bind(this);
        this.state = { open: false };
    }

    toggle() {
        this.setState(prevState => {
            return { open: !prevState.open };
        });
    }

    render() {
        return (
            <Dropdown open={this.state.open} toggle={this.toggle}>
                <DropdownToggle>Dropdown</DropdownToggle>
                <DropdownMenu>
                    <DropdownItem>Action</DropdownItem>
                    <DropdownItem>Another action</DropdownItem>
                    <DropdownItem>Something else here</DropdownItem>
                </DropdownMenu>
            </Dropdown>
        );
    }
}