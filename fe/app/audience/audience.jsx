import React, { Component, Fragment } from "react";
import ReactDOM from "react-dom";
import Pagination from "./pagination.jsx";
import { paginate } from "./paginate.jsx";
import "../css/boardstyle.css";
// import '../css/contents.css';
import "../css/default.css";
import "../css/board.common.css";
import Button from "@material-ui/core/Button";

const regeneratorRuntime = require("regenerator-runtime");
const axios = require("axios");
class Audience extends React.Component {
  constructor(props) {
    super(props);
    this.state = { audiences: [], pageSize: 3, itemsCount: "", currentPage: 1 };
  }

  //클래스 생성 시 최초에 한번만.. 이후 state 수정되면 바꾼 state 값으로 render만 호출
  async componentDidMount() {
    console.log("마운트");
    let { data } = await axios.get("/audience/axios");
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
                  href={"/audience/read/" + audience.applyId}
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
