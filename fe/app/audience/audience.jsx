import React, { Component, Fragment } from "react";
import ReactDOM from "react-dom";
import Pagination from "./pagination.jsx";
import { paginate } from "./paginate.jsx";

// import "../css/boardstyle.css";
// import '../css/contents.css';
// import "../css/default.css";
// import "../css/board.common.css";
import Button from "@material-ui/core/Button"
const axios = require("axios");;
const regeneratorRuntime = require("regenerator-runtime");
class Audience extends React.Component {
  constructor(props) {
    super(props);
    this.state = { audiences: [], pageSize: 3, itemsCount: "", currentPage: 1 };
  }

  //클래스 생성 시 최초에 한번만.. 이후 state 수정되면 바꾼 state 값으로 render만 호출
  async componentDidMount() {
    console.log("마운트");
    let { data } = await axios.get("/audience/list/axios");
    this.setState({
      audiences: data,
      itemsCount: data.length,
    });
    console.log(data);
  }

  handlePageChange(page) {
    // this.state.currentPage = page
    this.setState({ currentPage: page });
  }

  render() {
    const { pageSize, itemsCount, currentPage } = this.state;
    const { length: count } = this.state.audiences;
    if (count === 0) return <p>There are no data in the database.</p>;
    const audiences = paginate(this.state.audiences, currentPage, pageSize);
    return (
      <React.Fragment>
        <p>showing {count} data in the database.</p>
        <div className="webzineList">
          <ul>
            {audiences.map((audience) => (
              <li key={audience.applyId} className="">
                <a href="#" className="list_img">
                  <img src={"/uploads/" + audience.img} alt=""></img>
                </a>
                <a
                  href={"/audience/read2/" + audience.applyId}
                  className="bo_tit"
                >
                  <span className="sound_only"></span>{" "}
                  <strong>{audience.aTitle}</strong>
                  <span className="content">{audience.aContent}</span>
                </a>

                <span>모집인원 {audience.aRecruits}</span>
                <span> / 조회수 {audience.aViewCount}</span>
                <span> / 등록일 {audience.aDate}</span>
              </li>
            ))}
          </ul>
        </div>


        <Button
          className="createBtn"
          variant="contained"
          color="primary"
          size="small"
        >
          글쓰기
        </Button>

        <Pagination
          pageSize={pageSize}
          itemsCount={itemsCount}
          currentPage={currentPage}
          onPageChange={this.handlePageChange.bind(this)}
        />
      </React.Fragment>
    );
  }
}

ReactDOM.render(<Audience />, document.getElementById("audienceBoard"));
// import React, { Component, Fragment } from "react";
// import ReactDOM from "react-dom";
// // import Footer from "./Footer.js";
// import MainFooter from "./MainFooter";
// import "bootstrap/dist/css/bootstrap.min.css";
// import "./shards-dashboard/styles/shards-dashboards.1.1.0.min.css";
// import {
//   Container,
//   Row,
//   Col,
//   Card,
//   CardBody,
//   CardFooter,
//   Badge,
//   Button,
// } from "shards-react";
// import MainSidebar from "./MainSidebar";
// const regeneratorRuntime = require("regenerator-runtime");
// const axios = require("axios");
// class Audience extends React.Component {
//   constructor(props) {
//     super(props);

//     this.state = {
//       // First list of posts.
//       PostsListOne: [
//         {
//           backgroundImage: require("../images/avatars/1.jpg"),
//           category: "Business",
//           categoryTheme: "dark",
//           author: "Anna Kunis",
//           authorAvatar: require("../images/avatars/1.jpg"),
//           title: "Conduct at an replied removal an amongst",
//           body:
//             "However venture pursuit he am mr cordial. Forming musical am hearing studied be luckily. But in for determine what would see...",
//           date: "28 February 2019",
//         },
//         {
//           backgroundImage: require("../images/avatars/1.jpg"),
//           category: "Travel",
//           categoryTheme: "info",
//           author: "James Jamerson",
//           authorAvatar: require("../images/avatars/2.jpg"),
//           title: "Off tears are day blind smile alone had ready",
//           body:
//             "Is at purse tried jokes china ready decay an. Small its shy way had woody downs power. To denoting admitted speaking learning my...",
//           date: "29 February 2019",
//         },
//         {
//           backgroundImage: require("../images/avatars/1.jpg"),
//           category: "Technology",
//           categoryTheme: "royal-blue",
//           author: "Jimmy Jackson",
//           authorAvatar: require("../images/avatars/2.jpg"),
//           title: "Difficult in delivered extensive at direction",
//           body:
//             "Is at purse tried jokes china ready decay an. Small its shy way had woody downs power. To denoting admitted speaking learning my...",
//           date: "29 February 2019",
//         },
//         {
//           backgroundImage: require("../images/avatars/1.jpg"),
//           category: "Business",
//           categoryTheme: "warning",
//           author: "John James",
//           authorAvatar: require("../images/avatars/3.jpg"),
//           title: "It so numerous if he may outlived disposal",
//           body:
//             "How but sons mrs lady when. Her especially are unpleasant out alteration continuing unreserved ready road market resolution...",
//           date: "29 February 2019",
//         },
//       ],

//       // Second list of posts.
//       PostsListTwo: [
//         {
//           backgroundImage: require("../images/avatars/1.jpg"),
//           category: "Travel",
//           categoryTheme: "info",
//           author: "Anna Ken",
//           authorAvatar: require("../images/avatars/0.jpg"),
//           title:
//             "Attention he extremity unwilling on otherwise cars backwards yet",
//           body:
//             "Conviction up partiality as delightful is discovered. Yet jennings resolved disposed exertion you off. Left did fond drew fat head poor jet pan flying over...",
//           date: "29 February 2019",
//         },
//         {
//           backgroundImage: require("../images/avatars/1.jpg"),
//           category: "Business",
//           categoryTheme: "dark",
//           author: "John James",
//           authorAvatar: require("../images/avatars/1.jpg"),
//           title:
//             "Totally words widow one downs few age every seven if miss part by fact",
//           body:
//             "Discovered had get considered projection who favourable. Necessary up knowledge it tolerably. Unwilling departure education to admitted speaking...",
//           date: "29 February 2019",
//         },
//       ],

//       // Third list of posts.
//       PostsListThree: [
//         {
//           author: "John James",
//           authorAvatar: require("../images/avatars/1.jpg"),
//           title: "Had denoting properly jointure which well books beyond",
//           body:
//             "In said to of poor full be post face snug. Introduced imprudence see say unpleasing devonshire acceptance son. Exeter longer wisdom work...",
//           date: "29 February 2019",
//         },
//         {
//           author: "John James",
//           authorAvatar: require("../images/avatars/2.jpg"),
//           title: "Husbands ask repeated resolved but laughter debating",
//           body:
//             "It abode words began enjoy years no do ﻿no. Tried spoil as heart visit blush or. Boy possible blessing sensible set but margaret interest. Off tears...",
//           date: "29 February 2019",
//         },
//         {
//           author: "John James",
//           authorAvatar: require("../images/avatars/3.jpg"),
//           title:
//             "Instantly gentleman contained belonging exquisite now direction",
//           body:
//             "West room at sent if year. Numerous indulged distance old law you. Total state as merit court green decay he. Steepest merit checking railway...",
//           date: "29 February 2019",
//         },
//       ],

//       // Fourth list of posts.
//       PostsListFour: [
//         {
//           backgroundImage: require("../images/avatars/1.jpg"),
//           author: "Alene Trenton",
//           authorUrl: "#",
//           category: "News",
//           categoryUrl: "#",
//           title: "Extremity so attending objection as engrossed",
//           body:
//             "Pursuit chamber as elderly amongst on. Distant however warrant farther to of. My justice wishing prudent waiting in be...",
//           date: "29 February 2019",
//         },
//         {
//           backgroundImage: require("../images/avatars/1.jpg"),
//           author: "Chris Jamie",
//           authorUrl: "#",
//           category: "News",
//           categoryUrl: "#",
//           title: "Bed sincerity yet therefore forfeited his",
//           body:
//             "Speaking throwing breeding betrayed children my to. Me marianne no he horrible produced ye. Sufficient unpleasing and...",
//           date: "29 February 2019",
//         },
//         {
//           backgroundImage: require("../images/avatars/1.jpg"),
//           author: "Monica Jordan",
//           authorUrl: "#",
//           category: "News",
//           categoryUrl: "#",
//           title: "Object remark lively all did feebly excuse our",
//           body:
//             "Morning prudent removal an letters by. On could my in order never it. Or excited certain sixteen it to parties colonel not seeing...",
//           date: "29 February 2019",
//         },
//         {
//           backgroundImage: require("../images/avatars/1.jpg"),
//           author: "Monica Jordan",
//           authorUrl: "#",
//           category: "News",
//           categoryUrl: "#",
//           title: "His followed carriage proposal entrance",
//           body:
//             "For county now sister engage had season better had waited. Occasional mrs interested far expression directly as regard...",
//           date: "29 February 2019",
//         },
//       ],
//     };
//   }

//   //클래스 생성 시 최초에 한번만.. 이후 state 수정되면 바꾼 state 값으로 render만 호출
//   async componentDidMount() {
//     console.log("마운트");
//     let { data } = await axios.get("/audience/axios");
//     this.setState({
//       audiences: data,
//       itemsCount: data.length,
//     });
//     console.log(data);
//   }

//   render() {
//     const {
//       PostsListOne,
//       PostsListTwo,
//       PostsListThree,
//       PostsListFour,
//     } = this.state;
//     return (
//       <Fragment>
//         <Container fluid>
//           <Col>
//             {/* <Slider
//               scrollingSpeed={10}
//               touchSensitivity={5}
//               scrollingSpeed={500}
//               className="fullpage-slider"
//               scrollPendingThreshold={200}
//               style={style}
//               total={numSlides}
//               notifyCurrent={(idx) => notifyCurrent(idx)}
//             >
//               ... content ...
//             </Slider> */}
//           </Col>
//           {/* <MainSidebar></MainSidebar> */}
//           <Col
//           // className="main-content p-0"
//           // lg={{ size: 10, offset: 2 }}
//           // md={{ size: 9, offset: 3 }}
//           // sm="12"
//           // tag="main"
//           >
//             <Container fluid className="main-content-container px-4">
//               {/* Page Header */}
//               <Row noGutters className="page-header py-4"></Row>

//               {/* First Row of Posts */}
//               <Row>
//                 {PostsListOne.map((post, idx) => (
//                   <Col lg="3" md="6" sm="12" className="mb-4" key={idx}>
//                     <Card small className="card-post card-post--1">
//                       <div
//                         className="card-post__image"
//                         style={{
//                           backgroundImage: `url(${post.backgroundImage})`,
//                         }}
//                       >
//                         <Badge
//                           pill
//                           className={`card-post__category bg-${post.categoryTheme}`}
//                         >
//                           {post.category}
//                         </Badge>
//                         <div className="card-post__author d-flex">
//                           <a
//                             href="#"
//                             className="card-post__author-avatar card-post__author-avatar--small"
//                             style={{
//                               backgroundImage: `url('${post.authorAvatar}')`,
//                             }}
//                           >
//                             Written by {post.author}
//                           </a>
//                         </div>
//                       </div>
//                       <CardBody>
//                         <h5 className="card-title">
//                           <a href="#" className="text-fiord-blue">
//                             {post.title}
//                           </a>
//                         </h5>
//                         <p className="card-text d-inline-block mb-3">
//                           {post.body}
//                         </p>
//                         <span className="text-muted">{post.date}</span>
//                       </CardBody>
//                     </Card>
//                   </Col>
//                 ))}
//               </Row>

//               {/* Second Row of Posts */}
//               <Row>
//                 {PostsListTwo.map((post, idx) => (
//                   <Col lg="6" sm="12" className="mb-4" key={idx}>
//                     <Card
//                       small
//                       className="card-post card-post--aside card-post--1"
//                     >
//                       <div
//                         className="card-post__image"
//                         style={{
//                           backgroundImage: `url('${post.backgroundImage}')`,
//                         }}
//                       >
//                         <Badge
//                           pill
//                           className={`card-post__category bg-${post.categoryTheme}`}
//                         >
//                           {post.category}
//                         </Badge>
//                         <div className="card-post__author d-flex">
//                           <a
//                             href="#"
//                             className="card-post__author-avatar card-post__author-avatar--small"
//                             style={{
//                               backgroundImage: `url('${post.authorAvatar}')`,
//                             }}
//                           >
//                             Written by Anna Ken
//                           </a>
//                         </div>
//                       </div>
//                       <CardBody>
//                         <h5 className="card-title">
//                           <a className="text-fiord-blue" href="#">
//                             {post.title}
//                           </a>
//                         </h5>
//                         <p className="card-text d-inline-block mb-3">
//                           {post.body}
//                         </p>
//                         <span className="text-muted">{post.date}</span>
//                       </CardBody>
//                     </Card>
//                   </Col>
//                 ))}
//               </Row>

//               {/* Third Row of Posts */}
//               <Row>
//                 {PostsListThree.map((post, idx) => (
//                   <Col lg="4" key={idx}>
//                     <Card small className="card-post mb-4">
//                       <CardBody>
//                         <h5 className="card-title">{post.title}</h5>
//                         <p className="card-text text-muted">{post.body}</p>
//                       </CardBody>
//                       <CardFooter className="border-top d-flex">
//                         <div className="card-post__author d-flex">
//                           <a
//                             href="#"
//                             className="card-post__author-avatar card-post__author-avatar--small"
//                             style={{
//                               backgroundImage: `url('${post.authorAvatar}')`,
//                             }}
//                           >
//                             Written by James Khan
//                           </a>
//                           <div className="d-flex flex-column justify-content-center ml-3">
//                             <span className="card-post__author-name">
//                               {post.author}
//                             </span>
//                             <small className="text-muted">{post.date}</small>
//                           </div>
//                         </div>
//                         <div className="my-auto ml-auto">
//                           <Button size="sm" theme="white">
//                             <i className="far fa-bookmark mr-1" /> Bookmark
//                           </Button>
//                         </div>
//                       </CardFooter>
//                     </Card>
//                   </Col>
//                 ))}
//               </Row>

//               {/* Fourth Row of posts */}
//               <Row>
//                 {PostsListFour.map((post, idx) => (
//                   <Col lg="3" md="6" sm="12" className="mb-4" key={idx}>
//                     <Card small className="card-post h-100">
//                       <div
//                         className="card-post__image"
//                         style={{
//                           backgroundImage: `url('${post.backgroundImage}')`,
//                         }}
//                       />
//                       <CardBody>
//                         <h5 className="card-title">
//                           <a className="text-fiord-blue" href="#">
//                             {post.title}
//                           </a>
//                         </h5>
//                         <p className="card-text">{post.body}</p>
//                       </CardBody>
//                       <CardFooter className="text-muted border-top py-3">
//                         <span className="d-inline-block">
//                           By
//                           <a className="text-fiord-blue" href={post.authorUrl}>
//                             {post.author}
//                           </a>{" "}
//                           in
//                           <a
//                             className="text-fiord-blue"
//                             href={post.categoryUrl}
//                           >
//                             {post.category}
//                           </a>
//                         </span>
//                       </CardFooter>
//                     </Card>
//                   </Col>
//                 ))}
//               </Row>
//             </Container>
//             <MainFooter></MainFooter>
//           </Col>
//         </Container>
//       </Fragment>
//     );
//   }
// }
// export default Audience;
// // ReactDOM.render(<Audience />, document.getElementById("audienceBoard"));
