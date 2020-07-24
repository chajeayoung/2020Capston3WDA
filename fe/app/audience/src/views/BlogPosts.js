/* eslint jsx-a11y/anchor-is-valid: 0 */
import React from "react";
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
import jQuery from "jquery";
import PageTitle from "../components/common/PageTitle";

const axios = require('axios');
const regeneratorRuntime = require("regenerator-runtime");

class BlogPosts extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      // First list of posts.
      postsListOne: [
        // {
        //   backgroundImage: require("../images/content-management/1.jpeg"),
        //   category: "Business",
        //   categoryTheme: "dark",
        //   author: "Anna Kunis",
        //   authorAvatar: require("../images/avatars/1.jpg"),
        //   title: "Conduct at an replied removal an amongst",
        //   body:
        //     "However venture pursuit he am mr cordial. Forming musical am hearing studied be luckily. But in for determine what would see...",
        //   date: "28 February 2019"
        // },
      ],



    };
  }

  async componentDidMount() {
    let { data: postsListOne } = await axios.get('/audience/axios')
    console.log(postsListOne);
    this.setState({ postsListOne })

  }

  render() {
    const
      postsListOne
        = this.state.postsListOne;
    return (
      <Container fluid className="main-content-container px-4">
        {/* Page Header */}
        <Row noGutters className="page-header py-4">
          <PageTitle sm="4" title="방청권 응모" subtitle="커뮤니티" className="text-sm-left" />
        </Row>

        {/* First Row of Posts */}
        <Row>
          {postsListOne.map((post, idx) => (
            <Col lg="3" md="6" sm="12" className="mb-4" key={idx}>
              <Card small className="card-post card-post--1">
                <div
                  className="card-post__image"
                // style={{ backgroundImage: `url(${post.backgroundImage})` }}
                >
                  <Badge
                    pill
                  // className={`card-post__category bg-${post.categoryTheme}`}
                  >
                    {post.applyId}
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
                    <a href="#" className="text-fiord-blue">
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
    );
  }
}

export default BlogPosts;
