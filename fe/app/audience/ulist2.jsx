import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./shards-dashboard/styles/shards-dashboards.1.1.0.min.css";
import Pagination from "./pagination.jsx";
import { paginate } from "./paginate.jsx";
import "./css/audience.css";

import {
    Container,
    Row,
    Col,
    Card,
    CardBody,
    Badge,
} from "shards-react";
import PageTitle from "./src/components/common/PageTitle";
const axios = require("axios");
const regeneratorRuntime = require("regenerator-runtime");


class Ulist extends React.Component {



    constructor(props) {
        super(props);
        this.state = { audiences: [], pageSize: 8, itemsCount: "", currentPage: 1, postsListOne: [], role: "", programId: "" ,param:""};


    }

    //클래스 생성 시 최초에 한번만.. 이후 state 수정되면 바꾼 state 값으로 render만 호출
    async componentDidMount() {
        console.log("---")

        let { data } = await axios.get(`/audience/list/axios/${this.props.param}`);
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



    getParams(url) {
        const params = {};
        const parser = document.createElement('a');
        parser.href = url;
        const query = parser.search.substring(1);
        const vars = query.split('&');
        for (let i = 0; i < vars.length; i++) {
            const pair = vars[i].split('=');
            params[pair[0]] = decodeURIComponent(pair[1]);
        }
        return params;
    };


    render() {

        const { pageSize, itemsCount, currentPage } = this.state;
        const { length: count } = this.state.audiences;
        const audiences = paginate(this.state.audiences, currentPage, pageSize);
 

        return (
            
            <React.Fragment>

                <Container fluid className="main-content-container px-4">
                    {/* Page Header */}

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
                                    </div>
                                    <CardBody>
                                        <h5 className="card-title">
                                            <a href={`/audience/read/${post.applyId}`} className="text-fiord-blue">
                                                {post.aTitle}
                                            </a>
                                        </h5>

                                        <p className="text-muted">{post.aDate}</p>
                                    </CardBody>
                                </Card>
                            </Col>
                        ))}

                        {/* {audiences.map((post, idx) => (
                            post == 1 ? <div></div> :
                                post == 2 ? <div> </div> : <div></div>
                        ))} */}
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


export default Ulist