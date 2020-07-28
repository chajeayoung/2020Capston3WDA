import React from 'react';
import ReactDOM from 'react-dom';
import "./auth/register.css";
const axios = require('axios');
const regeneratorRuntime = require("regenerator-runtime");

class Register extends React.Component {
    
    constructor(props){
        super(props);
        this.state = { check : 0};

        document.getElementById("register_form").addEventListener("submit",this.result_submit.bind(this));
        

        
        
    }

    result_submit(e){
        
        const pwd = document.getElementById("pwd").value;
        const pwd_check = document.getElementById("pwd_check").value;
        
        if(this.state.check ==0){
            e.preventDefault();    
            alert("아이디 중복체크를 해주세요");
        }else if(pwd != pwd_check){
            e.preventDefault();    
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
        
        
    }

    // input_Change(e){
    //     console.log("아이디 값 변경됨");
    //     // this.state.check = 0;
    //     console.log("check 값 0으로 초기화: "+this.state.check);
    // }


    checkId(e){
        e.preventDefault();
        console.log("아이디 중복확인");
        console.log(this.state.check);
        var id = document.getElementById("e-mail").value;
        console.log(id);
        if(!id)  return alert("이메일을 입력해 주세요");

        axios.get("/auth/register/checkId/"+id)
        .then((response)=>{
            let {data} = response;

            alert(data.message);

            this.setState({check:data.check});
            console.log(this.state.check);
                
        });
    }
        
    

    sample4_execDaumPostcode() {
      
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }


     Postcode(){
        const handleComplete = (data) => {
          let fullAddress = data.address;
          let extraAddress = ''; 
          
          if (data.addressType === 'R') {
            if (data.bname !== '') {
              extraAddress += data.bname;
            }
            if (data.buildingName !== '') {
              extraAddress += (extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName);
            }
            fullAddress += (extraAddress !== '' ? ` (${extraAddress})` : '');
          }
      
          console.log(fullAddress);  // e.g. '서울 성동구 왕십리로2길 20 (성수동1가)'
        }
    }

    render() {
        
        return (
            <div>
               
                <table className="register_table">
                    <tbody>
                        <tr>
                            <td><input className="register_input" type="e-mail" id="e-mail" name="userid"  placeholder="이메일"  required/></td>
                            <td><input className="check_button"  type="button" value="중복확인" onClick={this.checkId.bind(this)} /></td>
                        </tr>
                        <tr>
                            <td><input className="register_input" type="password" name="password" id="pwd" placeholder="비밀번호" required/></td>
                        </tr>
                        <tr>
                            <td><input className="register_input" type="password" name="password_check" id="pwd_check" placeholder="비밀번호 확인" required/></td>
                        </tr>
                        <tr>
                            <td colSpan="2"><hr></hr></td>
                        </tr>
                        <tr>
                            <td><input className="register_input" type="text" name="name" placeholder="이름"  required/></td>
                        </tr>
                        <tr>
                            <td><input className="register_birth" type="date" name="birth"  required/></td>
                        </tr>
                        <tr>
                            <td className="register_left_td"><input className="register_gender" type="radio" id="male" name="gender" value="0" required/>남자</td>
                            <td className="register_right_td"><input type="radio" id="female" name="gender" value="1" required/>여자</td>
                        </tr>
                        <tr>
                            <td colSpan="2"><hr></hr></td>
                        </tr>
                        <tr>
                            <td><input className="register_input" type="tel" name="phone"  placeholder="010-0000-0000"  pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" required/></td>
                        </tr>
                        <tr>
                            <td ><input className="register_input" type="text" name="addr" placeholder="도로명 주소" required/></td>
                            {/* <td colSpan="0.3"><input className="addr_input" type="text" name="addr" placeholder="시 도"/></td>
                            <td colSpan="0.3"><input className="addr_input" type="text" name="addr" placeholder="시 군 구"/></td>
                            <td colSpan="0.3"><input className="addr_input" type="text" name="addr" placeholder="동 명 읍"/></td> */}
                        </tr>
                        <tr>
                            <td><input className="register_input" type="text" name="addr2" placeholder="기타주소" required/></td>
                        </tr>
                        <tr>
                            <td><input className="register_input" type="text" name="nickname" placeholder="닉네임"  required/></td>
                        </tr>
                        
                    </tbody>
                </table>
                <button className="submit_button" type="submit">회원가입</button>
            </div>

        );
      }
}


ReactDOM.render(<Register/>,document.getElementById('register'));
