import React, { Component, Fragment } from "react";
import ReactDOM from "react-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./shards-dashboard/styles/shards-dashboards.1.1.0.min.css";
import Pagination from "./pagination.jsx";
import { paginate } from "./paginate.jsx";
import SortButton from './SortButton.jsx';
import "./css/audience.css";
import {
  Container,
  Row,
  Col,
  Card,
  CardBody,
  CardFooter,
  Badge,
  Button
} from "shards-react";
import PageTitle from "./src/components/common/PageTitle";
import MainFooter from "./src/components/layout/MainFooter";
const axios = require("axios");
const regeneratorRuntime = require("regenerator-runtime");



class Audience extends React.Component {


  constructor(props) {
    super(props);
    this.state = { audiences: [], pageSize: 4, itemsCount: "", currentPage: 1, postsListOne: [], role: "" };
  }

  //클래스 생성 시 최초에 한번만.. 이후 state 수정되면 바꾼 state 값으로 render만 호출
  async componentDidMount() {

    let { data } = await axios.get("/audience/list/axios");
    this.setState({
      audiences: data,
      itemsCount: data.length,
      role: $("#userRole").text()
    });
    console.log(this.state.role);
  }

  handlePageChange(page) {
    // this.state.currentPage = page;
    this.setState({ currentPage: page });
  }

  render() {

    const { pageSize, itemsCount, currentPage } = this.state;
    const { length: count } = this.state.audiences;
    const audiences = paginate(this.state.audiences, currentPage, pageSize);

    return (
      <React.Fragment>
        <Container fluid className="main-content-container px-4">
          {/* Page Header */}
          <Row noGutters className="page-header py-4">
            <Col>
              <PageTitle sm="2" title="방청권 응모" subtitle="커뮤니티" className="text-sm-left" />
            </Col>
          </Row>
          {/* Row of Posts */}
          <Row>
            {audiences.map((post, idx) => (
              <Col lg="3" md="6" sm="12" className="mb-4" key={idx}>
                <Card small className="card-post card-post--1">
                  <div
                    className="card-post__image"
                    style={{ backgroundImage: `url(/uploads/${post.img})` }}
                  >


                    <Badge
                      pill
                      className={`card-post__category bg-${post.badgetheme}`}
                    >
                      {post.badge}
                    </Badge>


                    <div className="card-post__author d-flex">
                      <a
                        href="#"
                        className="card-post__author-avatar card-post__author-avatar--small"
                      // style={{ backgroundImage: `url('${post.authorAvatar}')` }}
                      >
                        {/* Written by {post.author} */}
                      </a>
                    </div>
                  </div>
                  <CardBody>
                    <h5 className="card-title">
                      <a href={`/audience/read/${post.applyId}`} className="text-fiord-blue">
                        {post.aTitle}
                      </a>
                    </h5>
                    <p className="card-text d-inline-block mb-3">{post.aContent}</p>
                    <span className="text-muted">{post.aDate}</span>
                  </CardBody>
                </Card>
              </Col>
            ))}
          </Row>
        </Container>

        {/* <Button
          className="createBtn"
          variant="contained"
          color="primary"
          size="small"
        >
          글쓰기
        </Button> */}

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