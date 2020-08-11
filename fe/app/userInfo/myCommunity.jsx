import React, { Component } from 'react'
import ReactDOM from 'react-dom';
import ItemCard4 from '../items/itemCard4.jsx';


import InputLabel from '@material-ui/core/InputLabel';

import TextField from '@material-ui/core/TextField';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import FormHelperText from '@material-ui/core/FormHelperText';
import OutlinedInput from '@material-ui/core/OutlinedInput';
import InputAdornment from '@material-ui/core/InputAdornment';

import './Modal.css';
import '../smart.css';

const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');

class MyCommunity extends Component {

  constructor(props) {
    super(props);
    this.state = {
      community: [], pageNum: 1, count: 0, modal: false, programId: 0, file: '', file2: '', previewURL: '', previewURL2: '', UD: 0,
      item: [],
      blood:"",weight:"",height:""
    };

    // document.getElementById("register_form").addEventListener("submit",this.result_submit.bind(this));

  }

  async componentDidMount() {
    let { data: community } = await axios.get('/userInfo/myCommunity/axios')
    this.state.programId = community.pop()

    this.setState({ community })

  }
  handleOpenModal() {
    this.setState({ modal: true });
  };
  handleOpenModal2(c) {
    console.log(c);

      var birth = c.birth
      var today = new Date();
      birth = today.toISOString().substr(0, 10);
      c.birth = birth

      this.setState({blood:c.blood})


    this.setState({ UD: 1 })
    this.setState({ item: c })
    this.setState({ modal: true });
  };
  handleCloseModal() {
    this.setState({ previewURL: "" })
    this.setState({ UD: 0 })
    this.setState({ modal: false });
  };

  checkImage(event) {

    event.preventDefault();
    let reader = new FileReader();
    let file = event.target.files[0];

    reader.onloadend = () => {
      this.setState({
        file: file,
        previewURL: reader.result
      })
    }
    reader.readAsDataURL(file);
  }

  checkImage2(event) {

    event.preventDefault();
    let reader = new FileReader();
    let file = event.target.files[0];

    reader.onloadend = () => {
      this.setState({
        file2: file,
        previewURL2: reader.result
      })
    }
    reader.readAsDataURL(file);
  }



  selectBlood(event){
    this.setState({blood : event.target.value});
  };

  insertWeight(event){
    this.setState({weight : event.target.value});
  };

  insertHeight(event){
    this.setState({height : event.target.value});
  };



  render() {
    let profile_preview = null;
    let profile_preview2 = null;
    if (this.state.file !== '') {
      profile_preview = <img className='profile_preview' src={this.state.previewURL}></img>
    }

    if (this.state.file2 !== '') {
      profile_preview2 = <img className='profile_preview' src={this.state.previewURL2}></img>
    }

    return (
      <div id="tablebox">
        <h3>후보자 and 팬클럽 목록</h3>
        {/* <Pagination count={this.state.count} page={this.state.pageNum} onChange={this.pagenation.bind(this)}> </Pagination> */}

        <div className="community_item">

          {this.state.community.map((c, index) => {

            return (

              <div key={c.name + index} className="community_index_item">

                <div onClick={this.handleOpenModal2.bind(this, c)}>
                  
                  <ItemCard4 key={c.img} img={c.img} name={c.name} title=""/>
                </div>

              </div>)
          })}

        </div>
        <button className="add" onClick={this.handleOpenModal.bind(this)}>추가</button>

        {this.state.modal && (
          <div className="MyModal">


              {this.state.UD == 0 ?
              <div className="content">
              <h3>후보 추가</h3>
           

                 <FormHelperText>세로프로필</FormHelperText>
                  <div>{profile_preview}</div>             
                  <input type="file" name="img2" accept="image/*" onChange={this.checkImage.bind(this)} />
                  <br></br>
                  <hr></hr>

                  <FormHelperText>가로프로필</FormHelperText>
                  <div>{profile_preview2}</div>  
                  <input type="file" name="img3" accept="image/*" onChange={this.checkImage2.bind(this)} />

                  <br></br>
                  <hr></hr>
               
                  <TextField id="standard-secondary" fullWidth name="name" label="이름" name="name" color="primary" required /> 
                  <TextField
                    fullWidth
                    id="standard-secondary"
                    name="birth"
                    label="생년월일"
                    InputLabelProps={{ shrink: true, required: true }}
                    type="date"  
                    required    
                  />

                  {/* <TextField id="standard-secondary" fullWidth label="혈액형" name="blood" color="primary" required /> */}
                  {/* <TextField id="standard-secondary" fullWidth label="키" name="height" color="primary" required /> */}

                  <FormControl variant="outlined">
                    <FormHelperText>키</FormHelperText>
                      <OutlinedInput
                        pattern="[0-9]{3}"
                        weight="50"
                        name="height"
                        onChange={this.insertHeight.bind(this)}
                        endAdornment={<InputAdornment position="end">cm</InputAdornment>}
                        labelWidth={0}
                      />
                  </FormControl>

                  <FormControl variant="outlined">
                    <FormHelperText>몸무게</FormHelperText>
                      <OutlinedInput
                        pattern="[0-9]{3}"
                        name="weight"
                        onChange={this.insertWeight.bind(this)}
                        endAdornment={<InputAdornment position="end">Kg</InputAdornment>}
                        labelWidth={0}
                      />
                  </FormControl>

                  <FormControl variant="outlined" >
                  <FormHelperText>혈액형</FormHelperText>
                            <Select name="blood" 
                              
                              
                              value={this.state.blood}
                              onChange={this.selectBlood.bind(this)}
                              label="혈액형"
                            >
                              <MenuItem value="">
                                <em>선택</em>
                              </MenuItem>
                              <MenuItem value="O">O</MenuItem>
                              <MenuItem value="A">A</MenuItem>
                              <MenuItem value="B">B</MenuItem>
                              <MenuItem value="AB">AB</MenuItem>
                            </Select>
                    </FormControl> 
           


             {/* <TextField id="standard-secondary" fullWidth label="몸무게" name="weight" color="primary" required /> */}
             <TextField id="standard-secondary" fullWidth label="취미" name="hobby" color="primary" required />
             <TextField id="standard-secondary" fullWidth label="특기" name="ability" color="primary" required />
             <TextField id="standard-secondary" fullWidth label="한마디" name="intro" color="primary" required />

         


             {/* <input type="hidden" name="id" value={this.state.item.id}></input> */}
             <input type="hidden" name="pid" value={this.state.programId}></input>
             <input type="hidden" name="img" value={this.state.item.img}></input>

             <button formAction="/userInfo/insertPopular">등록</button>
          
             <button type="button" onClick={this.handleCloseModal.bind(this)}>닫기</button>
                  
              
            </div> 
              
              :              
              <div className="content">
              <h3>후보 수정</h3>
                 <FormHelperText>세로프로필</FormHelperText>
                   <div> {this.state.file == '' ? <img src={'/uploads/'+this.state.item.img}></img> : <div>{profile_preview}</div> }</div>  
                  
                  <input type="file" name="img2" accept="image/*" onChange={this.checkImage.bind(this)} />
                  <br></br>
                  <hr></hr>

                  <FormHelperText>가로프로필</FormHelperText>
                  <div> {this.state.file2 == '' ? <img src={'/uploads/'+this.state.item.logo}></img> : <div>{profile_preview2}</div> }</div>
                  <input type="file" name="img3" accept="image/*" onChange={this.checkImage2.bind(this)} />

                  <br></br>
                  <hr></hr>
               
                  <TextField id="standard-secondary" fullWidth name="name" label="이름" name="name" color="primary" defaultValue={this.state.item.name} /> 
                  <TextField
                    fullWidth
                    id="standard-secondary"
                    name="birth"
                    label="생년월일"
                    InputLabelProps={{ shrink: true, required: true }}
                    type="date"  
                    // defaultValue={this.state.item.birth}
                    defaultValue={this.state.item.birth}
                    required    
                  />
        
                  {/* <TextField id="standard-secondary" fullWidth label="혈액형" name="blood" color="primary" required /> */}
                  {/* <TextField id="standard-secondary" fullWidth label="키" name="height" color="primary" required /> */}

                  <FormControl variant="outlined">
                    <FormHelperText>키</FormHelperText>
                      <OutlinedInput
                        pattern="[0-9]{3}"
                        weight="50"
                        name="height"
                        onChange={this.insertHeight.bind(this)}
                        endAdornment={<InputAdornment position="end">cm</InputAdornment>}
                        labelWidth={0}
                        defaultValue={this.state.item.height}
                      />
                  </FormControl>

                  <FormControl variant="outlined">
                    <FormHelperText>몸무게</FormHelperText>
                      <OutlinedInput
                        pattern="[0-9]{3}"
                        
                        name="weight"
                        onChange={this.insertWeight.bind(this)}
                        endAdornment={<InputAdornment position="end">Kg</InputAdornment>}
                        labelWidth={0}
                        defaultValue={this.state.item.weight}
                      />
                  </FormControl>

                  <FormControl variant="outlined" >
                  <FormHelperText>혈액형</FormHelperText>
                            <Select name="blood" 
                              
                              
                              value={this.state.blood}
                              onChange={this.selectBlood.bind(this)}
                              label="혈액형"
                            >
                              <MenuItem value="">
                                <em>선택</em>
                              </MenuItem>
                              <MenuItem value="O">O</MenuItem>
                              <MenuItem value="A">A</MenuItem>
                              <MenuItem value="B">B</MenuItem>
                              <MenuItem value="AB">AB</MenuItem>
                            </Select>
                    </FormControl> 
           


             {/* <TextField id="standard-secondary" fullWidth label="몸무게" name="weight" color="primary" required /> */}
             <TextField id="standard-secondary" fullWidth label="취미" name="hobby" color="primary" defaultValue={this.state.item.hobby} required />
             <TextField id="standard-secondary" fullWidth label="특기" name="ability" color="primary" defaultValue={this.state.item.ability} required />
             <TextField id="standard-secondary" fullWidth label="한마디" name="intro" color="primary" defaultValue={this.state.item.intro} required />
            
             <input type="hidden" name="pid" value={this.state.programId}></input>
             <input type="hidden" name="img" value={this.state.item.img}></input>
             <input type="hidden" name="logo" value={this.state.item.logo}></input>

              {this.state.UD != 0 && <button formAction="/userInfo/updatePopular">수정</button>}
            {this.state.UD != 0 && <button formAction="/userInfo/deletePopular">삭제</button>}
            {this.state.UD != 0 && <input type="hidden" name="id" value={this.state.item.id}></input>}
            <button type="button" onClick={this.handleCloseModal.bind(this)}>닫기</button>
              
              
              </div>}
            </div>
         )}{""}



      </div>
    )

  }
}

ReactDOM.render(<MyCommunity />, document.getElementById('my'));
