import React, { Component, Fragment } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./shards-dashboard/styles/shards-dashboards.1.1.0.min.css";
import Pagination from "./pagination.jsx";
import { paginate } from "./paginate.jsx";
import SortButton from './SortButton.jsx';
import "./css/audience.css";
import { BrowserRouter as Router, Route } from 'react-router-dom';
import {
    Container,
    Row,
    Col,
    Card,
    CardBody,
    CardFooter,
    Badge,
    Button,
    CardTitle,
    CardSubtitle,
    CardImg
} from "shards-react";
import PageTitle from "./src/components/common/PageTitle";
import Countdown from "./countdown.jsx";
const axios = require("axios");
const regeneratorRuntime = require("regenerator-runtime");

export default class Uread extends React.Component {


    constructor(props) {
        super(props);
        this.state = { audiences: [], pageSize: 8, itemsCount: "", currentPage: 1, postsListOne: [], role: "" };
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

            <Container>
                <Row>
                    {/* 글제목 */}
                    <Col xl="9" lg="" md="" sm="" xs="">
                        <Card>
                            <CardBody>
                                <CardTitle>Card Title</CardTitle>
                            </CardBody>
                        </Card>
                    </Col>
                    {/* 남은시간, 응모상태 */}
                    <Col xl="3" lg="" md="" sm="" xs="">
                        <Card>
                            <CardBody>
                                <Countdown date="2020-08-26 00:00:00"></Countdown>
                            </CardBody>
                        </Card>

                    </Col>
                </Row >

                <Row>
                    {/* 프로그램 사진 */}
                    <Col>
                        <CardImg top src="https://place-hold.it/300x200" />
                    </Col>
                </Row>

                <Row>
                    {/* 응모기간 */}
                    <Col>
                        <CardBody>
                            asdfasdf
                            </CardBody>
                    </Col>
                    {/* 추첨인원 */}
                    <Col>
                        <CardBody>
                            asdfasdf
                            </CardBody>
                    </Col>
                </Row>

                <Row>
                    {/* 응모제한횟수 */}
                    <Col>
                        <CardBody>
                            asdfasdf
                            </CardBody>
                    </Col>
                    {/* 응모시 소모포인트 */}
                    <Col>
                        <CardBody>
                            asdfasdf
                            </CardBody>
                    </Col>
                </Row>

                <Row>
                    {/* 세부내용 */}
                    <Col>
                        <CardBody>
                            asdfasdf
                            </CardBody>
                    </Col>
                </Row>

                <Row>
                    {/* 응모, 당첨확인버튼 */}
                    <Col xl="12" lg="12" md="12" sm="12" xs="12" >
                        <Button active theme="success">
                            응모하기
      </Button>
                    </Col>
                </Row>
            </Container >

        );
    }
}