import React, {Component} from 'react'
import ReactDOM from 'react-dom';
const regeneratorRuntime = require("regenerator-runtime");
import jQuery from "jquery";
import '../smart.css';
window.$ = window.jQuery = jQuery;
import './profileIndex.css';

import TextField from '@material-ui/core/TextField';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import FormHelperText from '@material-ui/core/FormHelperText';
import OutlinedInput from '@material-ui/core/OutlinedInput';
import InputAdornment from '@material-ui/core/InputAdornment';


const axios = require('axios');

class Index extends Component {
    constructor(props) {
        super(props);
        this.state = { profile: [] };
        this.gender = 1;
    }
    async componentDidMount() {
        let { data: profile } = await axios.get('/userInfo/axios')
        this.setState({ profile })
        $("input:radio[class ='test']:input[value=" + profile[0].gender + "]").attr("checked", true)
    }
    none(e) {
        alert("카카오톡 유저입니다. 카톡에서 프로필사진을 바꿔주세요!!!");
        e.preventDefault();

    }


    render() {
        const profile = this.state.profile;
        console.log(profile)

        return this.state.profile.map((p, index) => {
            if (p.kakao == "1") { // 카카오톡 유저이면?
                return (
                    <div key={'div' + index}>

<TextField key={index} id="standard-secondary" name="name" label="아이디" placeholder="아이디" name="name" value={p.userid} color="primary" readOnly /> 
{/* <tr><td key={index} >아이디<input type="text" id="id" placeholder="이메일" value={p.userid} readOnly /></td></tr>           */}

<tr><td>프로필<img src={p.profile ? p.profile : '#'} id="profile" name="profile2" alt="profile" /></td></tr>
                                <tr><td><input type="file" name="profile2" id="file" onClick={this.none.bind(this)} /></td></tr>

                                <tr><td>닉네임<input type="text" name="nickname" placeholder="입력해주세요" defaultValue={p.nickname} /></td></tr>
                                <tr> <td colSpan="2"><hr></hr></td></tr>
                                <tr><td>이름<input type="text" id="name" placeholder="이름" value={p.username} readOnly /></td></tr>
                                <tr> <td>생년월일<input type="date" name="birth" defaultValue={p.birth} /></td></tr>
                                <tr>
                                    <td className="register_left_td"><input className="register_gender" className="test" type="radio" name="gender" value="0" required />남자</td>
                                    <td className="register_right_td"><input type="radio" className="test" name="gender" value="1" required />여자</td>
                                </tr>
                                <tr><td colSpan="2"><hr></hr></td></tr>
                                <tr><td>연락처<input type="tel" name="phone" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" placeholder="입력해주세요" defaultValue={p.phone} /></td></tr>
                                <tr><td>주소<input type="text" name="addr" placeholder="입력해주세요" defaultValue={p.addr} /></td></tr>
                                <tr><td>상세주소<input type="text" name="addr2" placeholder="입력해주세요" defaultValue={p.addr2} /></td></tr>

                        <input type="hidden" name="profile" value={p.profile}></input>
                        <input type="hidden" name="userid" value={p.userid}></input>
                        <input type="hidden" name="no" value={p.r_id}></input>
                        <input type="hidden" name="name" value={p.username}></input>

                        <button className="submit_button" type="submit">수정하기</button>
                    </div>

                )
            } else {
                return (
                    <div key={'div' + index}>
                        <table>
                            <tbody>
                                <tr><td key={index} >아이디<input type="text" id="id" placeholder="이메일" value={p.userid} readOnly /></td></tr>
                                <tr><td>비밀번호<input type="password" name="password" placeholder="비밀번호" defaultValue={p.password ? p.password : ''} required /></td></tr>
                                <tr><td key={index} >프로필<img id="profile" src={p.profile ? p.profile : '/img/defaultProfile.png'} /></td></tr>
                                <tr><td><input type="file" name="profile2" /></td></tr>
                                <tr><td>닉네임<input type="text" name="nickname" placeholder="입력해주세요" defaultValue={p.nickname} /></td></tr>
                                <tr><td colSpan="2"><hr></hr></td></tr>
                                <tr><td>이름<input type="text" name="name" placeholder="이름" defaultValue={p.username} /></td></tr>
                                <tr><td>생년월일<input type="date" name="birth" defaultValue={p.birth} /></td></tr>
                                <tr>
                                    <td className="register_left_td"><input className="register_gender" className="test" type="radio" name="gender" value="0" required />남자</td>
                                    <td className="register_right_td"><input type="radio" className="test" name="gender" value="1" required />여자</td>
                                </tr>
                                <tr><td colSpan="2"><hr></hr></td></tr>
                                <tr><td>연락처<input type="tel" name="phone" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" placeholder="입력해주세요" defaultValue={p.phone} /></td></tr>
                                <tr><td>주소<input type="text" name="addr" placeholder="입력해주세요" defaultValue={p.addr} /></td></tr>
                                <tr><td>상세주소<input type="text" name="addr2" placeholder="입력해주세요" defaultValue={p.addr2} /></td></tr>
                            </tbody>
                        </table>
                        <input type="hidden" name="profile" value={p.profile ? p.profile : '/img/defaultProfile.png'}></input>
                        <input type="hidden" name="userid" value={p.userid}></input>
                        <input type="hidden" name="no" value={p.r_id}></input>
                        {/* <input type="hidden" name="name" value={p.username}></input> */}
                        <button className="submit_button" type="submit">수정하기</button>
                    </div>
                )
            }
        })

    }
}


ReactDOM.render(<Index/>, document.getElementById('userIndex'));
