import React, { Fragment } from 'react';
import ReactDOM from 'react-dom';

export class Audience extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
            return (
                <content>
    <div id="container">
        <div id="bo_list" style="width:100%">
            <div id="bo_btn_top">
                <div id="bo_list_total">
            <span>{currentPage} / {totalPages}</span>
                </div>
            </div>
        
            <div class="webzineList">
                <ul>
                    <li class="">
                        <a href="#" class="list_img"><img src={img}  alt=""></img></a>
                        <a href="#"  class="bo_tit">
                            <span class="sound_only">50</span> <strong text={aTitle}></strong>
                            <i class="fa fa-download" aria-hidden="true"></i>
                            <span class="content" text={aContent}>
                            </span>
                        </a>

                        <u>
                            <span><span class="sv_member" text={writer}></span></span>
                            <span text={list.aViewCount}><i class="fa fa-eye"></i> </span>
                            <span text={list.aDate}><i class="fa fa-clock-o"></i> </span>
                        </u>
                    </li>

                </ul>
            </div>

            <fieldset id="bo_sch">
                <legend>게시물 검색</legend>
                <form name="fsearch" method="get">
                    <label for="sfl" class="sound_only">검색대상</label>
                    <select name="sfl" id="sfl">
                        <option value="wr_subject||wr_content">제목+내용</option>
                        <option value="wr_subject">제목</option>
                        <option value="wr_content">내용</option>
                        <option value="mb_id,1">회원아이디</option>
                        <option value="wr_name,1">작성자</option>
                    </select>
                    <label for="stx" class="sound_only">검색어<strong class="sound_only"> 필수</strong></label>
                    <input type="text" name="stx" value="" required="" id="stx" class="sch_input" size="25"
                        maxlength="20" placeholder="검색어를 입력해주세요"></input>
                    <button type="submit" value="검색" class="sch_btn"><i class="fa fa-search"
                            aria-hidden="true"></i><span class="sound_only">검색</span></button>
                </form>
            </fieldset>
        
        </div>
    </div>
</content>
            )
        
    }
}

