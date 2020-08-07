import React, { Component, Fragment } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./shards-dashboard/styles/shards-dashboards.1.1.0.min.css";
import Pagination from "./pagination.jsx";
import { paginate } from "./paginate.jsx";
import SortButton from './SortButton.jsx';
import "./css/audience.css";
import { TextField, Paper, Grid, Button, Card } from '@material-ui/core';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import PageTitle from "./src/components/common/PageTitle";
import Countdown from "./countdown.jsx";
const axios = require("axios");
const regeneratorRuntime = require("regenerator-runtime");

export default class Uread2 extends React.Component {


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


            <Grid
                container
                // direction="row"
                // justify="space-between"
                alignItems="center"
                spacing={1}
            >
                <Card variant="outlined">
                    {/* 제목, 남은시간 */}
                    <Grid item
                        xs={9}
                        sm={9}
                        md={9}
                        lg={9}
                        xl={9}>
                        <PageTitle title="안녕하세요">
                        </PageTitle>
                    </Grid>
                    <Grid item
                        xs={3}
                        sm={3}
                        md={3}
                        lg={3}
                        xl={3}>
                        <Countdown date="2020-08-26 00:00:00"></Countdown>
                    </Grid>

                    {/* 이미지 */}
                    <Grid item
                        xs={12}
                        sm={12}
                        md={12}
                        lg={12}
                        xl={12}>
                        <Paper elevation={3} >dsfasdfasdf</Paper>
                    </Grid>


                    {/* 응모관련 데이터 4개 */}
                    <Grid item
                        xs={6}
                        sm={6}
                        md={6}
                        lg={6}
                        xl={6}>
                        <TextField
                            id="standard-read-only-input"
                            label="Read Only"
                            defaultValue="Hello World"
                            InputProps={{
                                readOnly: true,
                            }}
                        />
                    </Grid>
                    <Grid item
                        xs={6}
                        sm={6}
                        md={6}
                        lg={6}
                        xl={6}>
                        <TextField
                            id="standard-read-only-input"
                            label="Read Only"
                            defaultValue="Hello World"
                            InputProps={{
                                readOnly: true,
                            }}
                        />
                    </Grid>
                    <Grid item
                        xs={6}
                        sm={6}
                        md={6}
                        lg={6}
                        xl={6}>
                        <TextField
                            id="standard-read-only-input"
                            label="Read Only"
                            defaultValue="Hello World"
                            InputProps={{
                                readOnly: true,
                            }}
                        />
                    </Grid>
                    <Grid item
                        xs={6}
                        sm={6}
                        md={6}
                        lg={6}
                        xl={6}>
                        <TextField
                            id="standard-read-only-input"
                            label="Read Only"
                            defaultValue="Hello World"
                            InputProps={{
                                readOnly: true,
                            }}
                        />
                    </Grid>

                    {/* 세부내용 */}
                    <Grid item
                        xs={12}
                        sm={12}
                        md={12}
                        lg={12}
                        xl={12}>
                        <Paper elevation={3} >dsfasdfasdf</Paper>
                    </Grid>

                    {/* 버튼 */}
                    <Grid container
                        xs={12}
                        sm={12}
                        md={12}
                        lg={12}
                        xl={12}
                        direction="column"
                        alignItems="center"
                        justify="center">
                        <Button variant="contained" color="primary" disableElevation>
                            Disable elevation
</Button>
                    </Grid>
                </Card>

            </Grid>
        );
    }
}