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
           return (
  
                    <div key={'div' + index} className="userInfoBox">

            
                                <TextField fullWidth key={index} id="standard-secondary" label="아이디" placeholder="아이디" value={p.userid} color="primary" readOnly />
                                <br/> 
                                {p.kakao==1?"":<TextField id="standard-secondary" label="비밀번호" placeholder="비밀번호" defaultValue={p.password} color="primary" /> }      
                                <br/>
                              
                                <img src={p.profile ? p.profile : '/img/defaultProfile.png'} id="profile" name="profile2" alt="profile" />     
                                {p.kakao==1?<input type="file" name="profile2" id="file" onClick={this.none.bind(this)} />:<input type="file" name="profile2" />}      
                                <br/>                               
                                
                                <TextField fullWidth id="standard-secondary" label="이름" placeholder="이름" value={p.username} color="primary" readOnly/> 
                                <br/> 

                                <TextField fullWidth id="standard-secondary" name="nickname" label="닉네임" placeholder="닉네임" defaultValue={p.nickname} color="primary" /> 
                                <br/> 
                                <TextField
                                fullWidth
                                    id="standard-secondary"
                                    name="birth"
                                    label="생년월일"
                                    InputLabelProps={{ shrink: true, required: true }}
                                    type="date"  
                                    defaultValue={p.birth}
                                    required    
                                />
                                <br/> 

                                <input className="register_gender" className="test" type="radio" id="men" name="gender" value="0" required /><label for="men">남자</label>
                                <input type="radio" className="test" name="gender" value="1" id="girl" required /><label for="girl">여자</label>
                                <br/> 

                                <TextField fullWidth id="standard-secondary" type="tel" name="phone"  
                                 inputProps={{ className: TextField, pattern: "[0-9]{3}-[0-9]{4}-[0-9]{4}" }}
                                label="연락처" placeholder="연락처" defaultValue={p.phone} color="primary" /> 
                                <br/>


                                <TextField fullWidth id="standard-secondary" name="addr" label="주소" placeholder="주소" defaultValue={p.addr} color="primary" /> 
                                <br/> 
                                <TextField fullWidth id="standard-secondary" name="addr2" label="상세주소" placeholder="상세주소" defaultValue={p.addr2} color="primary" /> 

                        <input type="hidden" name="profile" value={p.profile}></input>
                        <input type="hidden" name="userid" value={p.userid}></input>
                        <input type="hidden" name="no" value={p.r_id}></input>
                        <input type="hidden" name="name" value={p.username}></input>

                        <br/> 
                        <button className="submit_button" type="submit">수정하기</button>
                    </div>
            

                ) 
            })

    }
}


ReactDOM.render(<Index/>, document.getElementById('userIndex'));
