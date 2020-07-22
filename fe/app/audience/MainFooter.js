import React from "react";
import PropTypes from "prop-types";
import { Container, Row, Nav, NavItem, NavLink } from "shards-react";
// import { Link } from "react-router-dom";
import jQuery from "jquery";
window.$ = window.jQuery = jQuery;
import socketio from 'socket.io-client';
$(document).ready(function () {
  const socket = socketio.connect('http://localhost:4000');
  $(function () {
    $("#chatinput").attr("disabled", false);
    $("#chatinput").attr("placeholder", "내용을 입력하세요.");
    $("#chatform").submit(function (e) {
      e.preventDefault(); // prevents page reloading
      socket.emit(
        "chat message",
        $("#username").text() + " : " + $("#chatinput").val()
      );
      $("#chatinput").val("");
      return false;
    });
    socket.on("chat message", function (msg) {
      var username = $("#username").text();
      var url = msg;
      const num = url.split(" : ");
      var param = num[num.length - 2];

      if (username == param) $("#messages").append($("<li id=user>").text(msg));
      else $("#messages").append($("<li>").text(msg));
      $("#messages").scrollTop($("#messages")[0].scrollHeight);
    });
  });
  $(".c_h").click(function (e) {
    if ($(".chat_container").is(":visible")) {
      $(".c_h .right_c .mini").text("+");
      $(".l_c_h").css("width", "auto");
      console.log("+");
    } else {
      $(".c_h .right_c .mini").text("-");
      $(".l_c_h").css("width", "300px");
      console.log("-");
    }
    $(".chat_container").slideToggle("slow");
    return false;
  });
});
const MainFooter = ({ contained, menuItems, copyright }) => (
  <footer className="main-footer d-flex p-2 px-3 bg-white border-top">
    <Container fluid={contained}>
      <Row>
        <Nav>
          {menuItems.map((item, idx) => (
            <NavItem key={idx}>
              <NavLink to={item.to}>
                {item.title}
              </NavLink>
            </NavItem>
          ))}
        </Nav>
        <span className="copyright ml-auto my-auto mr-2">{copyright}</span>
      </Row>
      <div className="l_c_h">
        <div className="c_h">
          <div className="left_c">
            <div className="left right_c left_icons">
              <a href="#" className="mini">+</a>
            </div>
            <div className="left center_icons">RIRO CHAT !</div>
          </div>
        </div>
        <div className="chat_container">
          <ul id="messages"></ul>
          <form id="chatform" action="">
            <input id="chatinput" autoComplete="nope" ></input>
            <button id="chatbutton">Send</button>
          </form>
        </div>
      </div >
    </Container>

  </footer>
);

MainFooter.propTypes = {
  /**
   * Whether the content is contained, or not.
   */
  contained: PropTypes.bool,
  /**
   * The menu items array.
   */
  menuItems: PropTypes.array,
  /**
   * The copyright info.
   */
  copyright: PropTypes.string
};

MainFooter.defaultProps = {
  contained: false,
  copyright: "Copyright © 2018 DesignRevision",
  menuItems: [
    {
      title: "Home",
      to: "#"
    },
    {
      title: "Services",
      to: "#"
    },
    {
      title: "About",
      to: "#"
    },
    {
      title: "Products",
      to: "#"
    },
    {
      title: "Blog",
      to: "#"
    }
  ]
};

export default MainFooter;
