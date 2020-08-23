
import React, {Component}from 'react'
import ReactDOM from 'react-dom';
import TextField from '@material-ui/core/TextField';
import FormHelperText from '@material-ui/core/FormHelperText';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';

import "./css/myProgram.css";
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
  
class MyProgram extends Component {
    
    constructor(props){
        super(props);
        this.state = { program: [] , file: '', file2: '', previewURL: '', previewURL2: '' ,category:''};
     
        document.getElementById("register_form").addEventListener("submit",this.result_submit.bind(this));

    }


 
    async componentDidMount(){
        let {data: program} = await axios.get('/userInfo/myProgram/axios')
        this.setState({program})
        console.log({program})

        this.setState({category:program.category})
    }
    result_submit(e){
        
       if(confirm("프로그램 정보를 변경하시겠습니까?")){
            
            }else{
                e.preventDefault();
            };
 
    }
   
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

      selectCategory(event){
        this.setState({category : event.target.value});
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
        console.log(this.state.program)
        return(
                <div className="myProgramBox">
                  
                        <div className="myProgramItem">
                            <FormHelperText>대표이미지</FormHelperText>  

                            <div> {this.state.file == '' ? 
                            <img className='profile_preview' src={'/uploads/'+this.state.program.img}></img> :   <div>{profile_preview}</div> }
                            </div>  

                            <input type="file" name="file" onChange={this.checkImage.bind(this)} />
    
                        </div>

                        <div className="myProgramItem">

                             <FormHelperText>상단로고이미지</FormHelperText>    
                                
                            <div> {this.state.file2 == '' ? 
                            <img className='profile_preview' src={'/uploads/'+this.state.program.logo}></img> :  <div>{profile_preview2}</div> }
                            </div>       
                                     
                            <input type="file" name="logoImg" onChange={this.checkImage2.bind(this)} />
                        
                        </div>

                        <div className="myProgramItem">                          
                        <FormHelperText>프로그램명</FormHelperText> 
                        {/* <TextField id="standard-secondary" fullWidth name="name" color="primary" value={this.state.program.name} /> 
                        {this.state.program.name}  */}
                        <input type="text" name="name" defaultValue={this.state.program.name}></input>
                        </div>      


                        <div className="myProgramItem">     
                        <FormHelperText>카테고리</FormHelperText> 
                        <Select name="category" 
                              
                              
                              value={this.state.category}
                              onChange={this.selectCategory.bind(this)}
                              label="카테고리"
                              name="category" 
                              required
                            >
                              <MenuItem value="랩">랩</MenuItem>
                              <MenuItem value="아이돌">아이돌</MenuItem>
                              <MenuItem value="보컬">보컬</MenuItem>
                              <MenuItem value="트로트">트로트</MenuItem>
                           
                        </Select>

                            <input type="hidden" name="id" value={this.state.program.id}></input>
                            <input type="hidden" name="img" value={this.state.program.img?this.state.program.img:'/img/defaultProfile3.png'}></input>
                       
                        </div>    
                        
                        <div className="myProgramItem">   
                        <button className="submit_button" type="submit">수정하기</button>
                        </div>    

                            
                           


                </div>
        )
     
    }
}


ReactDOM.render(<MyProgram/>,document.getElementById('myprogram'));
