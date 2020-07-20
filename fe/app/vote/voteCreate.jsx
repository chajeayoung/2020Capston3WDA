import React from 'react'
import ReactDOM from 'react-dom';
import './css/createCss.css'
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
import jQuery from "jquery";
window.$ = window.jQuery = jQuery;
import '../smart.css';
class VoteCreate extends React.Component {

  
  constructor(props){
    super(props);
    this.candidateNum = 0;
    this.check = 1; //후보자 수 체크를 하지 않은 상태 : 1, 체크함 : 0
    this.candidateCheck = 1;
    this.candidates = [];

  }
    changeCandidateNum(num){
      console.log(num)

    var childTag1 =         
    '<td rowSpan="2">'+
        '<div class="imgDiv">'+
          '<img class="candidateImg" src="/uploads/'+this.candidates[0].img+'"  alt=""/>'+
        '</div>'+
    '</td>';
      

    var childTag2 =  
    '<tr>'+
      '<td>'+
        '<textarea type="text" class="candidateInfo"placeholder="내용을 입력하세요"  name="info" required></textarea>'+
      '</td>'+
    '</tr>';



      $("#candidateInfoItem").empty();
      for(var i = 0 ; i<num; i ++){
        var topDiv = $(document.createElement("div"));
        topDiv.attr("class","grid");
        var topTable = $(document.createElement("table"));
        topTable.attr("class","candidateTable");
        var topTbody = $(document.createElement("tbody"));
        var topTr = $(document.createElement("tr"));
        var selectTd = $(document.createElement("td"));
        
        
        

        topTr.append($(childTag1));
        var selectCandidate = $(document.createElement("select"));
            
        selectCandidate.attr("name","candidate")
        selectCandidate.attr("class","candidateSelect")

    
        console.log(this.candidates)
        this.candidates.map((candidate,index)=>{
          var option = $(document.createElement("option"));
          option.val(candidate.id);
          option.text(candidate.name);
          option.attr("img",candidate.img);

          selectCandidate.append(option)
          
        });
        selectTd.append($(selectCandidate))
        topTr.append(selectTd);
        topTbody.append(topTr)
        topTbody.append($(childTag2));
        topTable.append(topTbody);
        topDiv.append(topTable);
        $("#candidateInfoItem").append(topDiv);
      }
      
      $(".candidateSelect").bind("change",function(e){
        
        e.target.parentElement.previousSibling.firstChild.firstChild.src = "/uploads/"+$(e.target).find("option:selected").attr("img")
      });
      
    }



    candidateGener(e){
      e.preventDefault();
      var numStr =  /^[0-9]*$/
      var num = $('#candidateCount').val()
      if(num >100)
        return alert("후보자 수는 100 을 초과할 수 없습니다.")
      else if(num <0)
        return alert("후보자 수는 0보다 작을 수 없습니다.")
      else if(num == 0 || num == "")
        return $("#candidateInfoItem").empty();
      
      if(num == 0 ||  !numStr.test(num)){
        this.check = 1;
      }else{
        this.check = 0;
      }
      console.log("this.check: ",this.check)
      


      this.changeCandidateNum(num)
      
    }


    async componentDidMount(){    
      let {data} = await axios.get('/vote/programAndPop/axios');
      console.log(data);
      
      var pName  = document.getElementById("program_name");
      pName.innerHTML = data[0].name;

      if(data[1].length == 0){
        alert("인기인이 없는 프로그램입니다.");
        return window.location.href  = "/userInfo"
      }
      this.candidates = data[1];
      console.log(this.candidates)
            
  }
  fileImg(e){
    console.log("aaaa");
  }
  cancle(e){
    e.preventDefault()
    if(confirm("투표 개설을 취소하시겠습니까?"))
      return window.location = '/vote'
    else return
  }
  checkNo(e){
    this.check = 1; // 체크 필요함
  }
  checkForm(e){
    
    var startTime = $("#startTime").val().replace(/[^0-9]/g,'');
    var endTime = $("#endTime").val().replace(/[^0-9]/g,'');
    var resultTime = $("#resultShowTime").val().replace(/[^0-9]/g,'');
    
    // for(var i=0;i<$("#candidateCount").val();i++){
    //   if(!$('.fileImg')[i].val()){
    //     this.candidateCheck = 1  
    //   }
    //   else if(!$('.candidateName')[i].val()){
    //     this.candidateCheck = 1 
    //   }
    //   else if(!$('.candidateTag')[i].val()){
    //     this.candidateCheck = 1 
    //   }

    // }
// console.log($('.fileImg')[i].val())
console.log("선발",$('#selectedNum').val(),"후보자", $('#candidateCount').val() , "?" )
    if(!$("#program_id").val){
      e.preventDefault();
      return alert("프로그램 정보가 없습니다.")
    }
    if(Number($('#candidateCount').val())<2){
      e.preventDefault();
      return alert("후보자 수는 2명 이상이어야 합니다.")
    }else if(Number($('#selectedNum').val())<=0){
      e.preventDefault();
      return alert("선발인원은 1명 이상이어야 합니다.")
    }else if(Number($('#selectedNum').val())>=Number($('#candidateCount').val())){
      e.preventDefault();
      console.log("선발",$('#selectedNum').val(),"후보자", $('#candidateCount').val() , "ㅡㅡ" )
      return alert("선발인원은 후보자 수 보다 적어야 합니다.")
    }else if(Number($("#voteCanNum").val())<0){
      e.preventDefault();
      return alert("다중투표 횟수는 0보다 커야합니다.")
    } else if(this.check==1){
      e.preventDefault();
      alert("후보 생성을 위해서 체크 버튼을 눌러주세요");
    }
    else if(endTime < startTime){
      e.preventDefault();
      return alert("투표 마감시간은 시작시간 보다 늦을 수 없습니다.")

    }else if(resultTime<endTime){
      e.preventDefault();
      return alert("결과 공개시간은 투표 마감시간보다 늦을 수 없습니다\
      해당 기능을 이용하고 싶으시면, 실시간 공개여부에 공개를 체크해주세요");

    }
    // else if(this.candidateCheck==1){
    //   e.preventDefault();
    //   return alert("후보자 정보를 확인해주세요.");
    else if(confirm("해당 설정으로 투표를 개설하시겠습니까?")){
      return;
    }
    
    return e.preventDefault();
  }
    render() {
        return (
          <div>
              <table className="table">
                  <tbody>
                    <tr>
                      <td>프로그램</td>
                      <td>
                        <div id="program_name"></div>
                      </td>
                    </tr>
                    <tr>
                      <td>썸네일 이미지</td>
                      <td><input type="file" name="thumbnail" required/><br/>  </td>
                    </tr>
                    <tr>
                      <td>투표제목</td>
                      <td><input type="text" name="title" required/></td>
                    </tr>
                    <tr>
                      <td>후보자 수</td>
                      <td><input type="number" min="1" className="inputTextRight inputWidth" id="candidateCount"name="count" onChange={this.checkNo.bind(this)} required/>명 &nbsp;<button onClick={this.candidateGener.bind(this)}>체크</button></td>
                    </tr>
                    <tr>
                      <td>선발인원</td>
                      <td><input type="number" min="1" id="selectedNum" name="selectNum" className="inputTextRight inputWidth" required/>명</td>
                    </tr>
                    <tr>
                      <td>다중투표</td>
                      <td>1인 최대<input type="number"  min="1" id="voteCanNum" name="voteCanNum" className="inputTextRight inputWidth" required/>표</td>
                    </tr>
                    <tr>
                      <td>투표기간</td>
                      <td><input type="datetime-local" id="startTime" name="startTime" required/> ~ <input type="datetime-local" id="endTime" name="endTime" required/></td>
                    </tr>
                    <tr>
                      <td>결과공개시간</td>
                      <td><input type="datetime-local" id="resultShowTime" name="resultShowTime" required/></td>
                    </tr>
                    <tr>
                      <td>진행사항 공개여부</td>
                      <td><input type="radio" name="show" value="0" required/> 공개 <input type="radio" name="show" value="1" required/> 비공개</td>
                    </tr>
                  </tbody>
              </table>

              <div className="candidateList">
                <div className="candidateItem">
                  <div className="candidate">후보자</div>
                  <div id="candidateInfoItem">
                </div>
              </div>
            </div>

            {/* <div className="roundGrid">
              <div>회차소개</div>
              <div className="roundInfo">
                <div>< input type="file" name="zzz" required/></div><br/>
                <div><textarea className="roundTextArea"type="text" placeholder="내용을 입력하세요"  required></textarea></div>
              </div>
            </div> */}

            <div className="Notification">※ 투표 개설 시 수정이 불가능하며 투표 시작 전 까지 삭제만 가능합니다.</div>
            <div className="formButton"><button type="submit" onClick={this.checkForm.bind(this)}>개설</button>&nbsp;&nbsp;<button type="click" onClick={this.cancle.bind(this)}>취소</button></div>
          </div>
        );
      }
}



ReactDOM.render(<VoteCreate/>,document.getElementById('voteCreate'));