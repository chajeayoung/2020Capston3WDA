import React from "react";
import { Nav } from "shards-react";
import {
  Dropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  Button
} from "shards-react";

import SidebarNavItem from "./SidebarNavItem";
import { Store } from "../../../flux";

class SidebarNavItems extends React.Component {
  constructor(props) {
    super(props)
    this.toggle = this.toggle.bind(this);
    this.state = {
      navItems: Store.getSidebarItems(),
      open: false
    };

    this.onChange = this.onChange.bind(this);
  }

  componentWillMount() {
    Store.addChangeListener(this.onChange);
  }

  componentWillUnmount() {
    Store.removeChangeListener(this.onChange);
  }

  toggle() {
    this.setState(prevState => {
      return { open: !prevState.open };
    });
  }

  onChange() {
    this.setState({
      // ...this.state,
      navItems: Store.getSidebarItems()
    });
  }

  render() {

    const { navItems: items } = this.state;
    const myPage0 = [{
      title: "내 정보",
      htmlBefore: '',
      to: "/main",
    },
    {
      title: "나의 오디션",
      htmlBefore: '',
      to: "/main",
    },
    {
      title: "나의 투표",
      htmlBefore: '',
      to: "/main",
    },
    {
      title: "나의 주문",
      htmlBefore: '',
      to: "/main",
    },
    {
      title: "장바구니",
      htmlBefore: '',
      to: "/main",
    },
    ];
    const introduce = [];
    const audition = [];
    const vote = [];
    const community = [];
    return (
      <div className="nav-wrapper">
        <Nav className="nav--no-borders flex-column">
          <SidebarNavItem item={{
            title: "홈",
            htmlBefore: '',
            to: "/main",
          }}></SidebarNavItem>
          <Dropdown nav={true} open={this.state.open} toggle={this.toggle}>
            <DropdownToggle nav={true} theme="success"><i class="material-icons">person</i>마이페이지</DropdownToggle>
            <DropdownMenu>
              {myPage0.map((item, idx) => (
                <SidebarNavItem key={idx} item={item} />
              ))}
            </DropdownMenu>
          </Dropdown>

          <Dropdown nav={true} open={this.state.open} toggle={this.toggle}>
            <DropdownToggle nav={true} theme="success"><i class="material-icons">person</i>마이페이지</DropdownToggle>
            <DropdownMenu>
              {myPage0.map((item, idx) => (
                <SidebarNavItem key={idx} item={item} />
              ))}
            </DropdownMenu>
          </Dropdown>

          <Dropdown nav={true} open={this.state.open} toggle={this.toggle}>
            <DropdownToggle nav={true} theme="success"><i class="material-icons">person</i>마이페이지</DropdownToggle>
            <DropdownMenu>
              {myPage0.map((item, idx) => (
                <SidebarNavItem key={idx} item={item} />
              ))}
            </DropdownMenu>
          </Dropdown>

          <Dropdown nav={true} open={this.state.open} toggle={this.toggle}>
            <DropdownToggle nav={true} theme="success"><i class="material-icons">person</i>마이페이지</DropdownToggle>
            <DropdownMenu>
              {myPage0.map((item, idx) => (
                <SidebarNavItem key={idx} item={item} />
              ))}
            </DropdownMenu>
          </Dropdown>ㄴ
          <SidebarNavItem item={{
            title: "굿즈샵",
            htmlBefore: '',
            to: "/main",
          }}></SidebarNavItem>
        </Nav>

        {/* <Nav className="nav--no-borders flex-column">
          {items.map((item, idx) => (
            <SidebarNavItem key={idx} item={item} />
          ))}
        </Nav> */}
      </div>
    )
  }
}

export default SidebarNavItems;
