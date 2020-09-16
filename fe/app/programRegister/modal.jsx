import React, {Component}from 'react'
import ReactDOM from 'react-dom';
import './Modal.css';
import './proRegIndex.css';

const axios = require('axios');
const regeneratorRuntime = require("regenerator-runtime");

class Modal extends Component {

    constructor(props){
        super(props);
        this.state = { check : 0, modal : false };
        document.getElementById("register_form").addEventListener("submit",this.result_submit.bind(this));

    }
    result_submit(e){
      const category = document.getElementById("category").value;
      const check = this.state.check
      const start = document.getElementById("c_startdate").value;
      const end = document.getElementById("c_enddate").value;

      if(check ==0){
        e.preventDefault();    
        alert("사업자번호 중복체크를 해주세요");

        }else if(start>end){
          e.preventDefault();    
        alert("프로그램 시작날짜가 종료날짜를 초과합니다.");
       }else if(category == ""){
          e.preventDefault();    
          alert("프로그램 카테고리를 선택해주세요");

         
      }
      
  }  
      handleOpenModal(){
        this.setState({modal:true});
      };
      handleCloseModal(){
        
        this.setState({modal:false});
      };

      checkId(e){
        e.preventDefault();
        console.log("사업자 중복확인");
        console.log(this.state.check);
        var id = document.getElementById("id").value;

        if(!id)  return alert("사업자 번호를 확인해주세요");
        if(id.length != 10)  return alert("사업자 번호 10자리를 확인해주세요");

        axios.get("/proRegIndex/register/checkId/"+id)
        .then((response)=>{
            let {data} = response;

            alert(data.message);

            this.setState({check:data.check});
            console.log(this.state.check);
                
        });
    }

      render() {
        // console.log(this.state.modal)
           return (
            <div className="proRegBut">
                <button  onClick={this.handleOpenModal.bind(this)}>신청하기</button>

                {this.state.modal && (
                   <div className="MyModal"> 
                      <div className="content">
                        <h3>신청 양식</h3>
                        
                <table className="register_table">
                    <tbody>
                        <tr>
                         
                        <td><input className="register_input" type="text" id="id" name="id" pattern="[0-9]{10}" placeholder="사업자번호 10자리"  required/></td>
                        <td><input className="check_button"  type="button" value="중복확인" onClick={this.checkId.bind(this)} /></td>
                       
                            {/* <td><input className="check_button"  type="button" value="중복확인" onClick={this.checkId.bind(this)} /></td> */}
                        </tr>
                        <tr>
                            <td colSpan="2"><hr></hr></td>
                        </tr>

                        <tr>
                            <td><input className="register_input" type="text" name="cname" placeholder="회사명"  required/></td>
                        </tr>

                        <tr>
                            <td><input className="register_input" type="text" name="creader" placeholder="대표자성함"  required/></td>
                        </tr>
                    
                        <tr>
                            <td><input className="register_input" type="tel" name="cphone"  placeholder="회사연락처 ex) 010-0000-0000" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" required/></td>
                        </tr>

                        <tr>
                            <td><input className="register_input" type="text" name="cprogram" placeholder="프로그램명"  required/></td>
                        </tr>

                        <tr>
                            <td colSpan="2"><textarea cols="50" rows="10" className="register_input" name="ccontent" placeholder="프로그램 소개"></textarea></td>
                        </tr>

                        <tr>
                            <td>
                                <select name="ccategory" id="category">
                                    <option value="">프로그램&nbsp;종류</option>
                                    <option>아이돌</option>
                                    <option>랩</option>
                                    <option>댄스</option>
                                    <option>보컬</option>
                                    <option>성악</option>
                                    <option>트로트</option>
                                </select> 
                            </td>
                        
                        </tr>

                        <tr>
                            <td colSpan="2"><hr></hr></td>
                        </tr>

                        프로그램 예상 기한
                        <tr>
                            <td><input className="register_birth" type="date" name="cstartdate" id="c_startdate"  required/>&nbsp;&nbsp;&nbsp;&nbsp;~</td>
                            <td><input className="register_birth" type="date" name="cenddate" id="c_enddate"  required/></td>
                        </tr>

                        <tr>
                            <td><input className="register_input" type="text" name="cbudget" placeholder="지출예산"  required/></td>
                        </tr>
                   
                      
                                                
                    </tbody>
                </table>
                  <button className="submit_button" type="submit">신청하기</button>




                        <button onClick={this.handleCloseModal.bind(this)}>닫기</button>
                      </div>
                  </div> )}{""}
            </div>            
            
        )
   
      }

}

ReactDOM.render(<Modal/>,document.getElementById('modal'));
