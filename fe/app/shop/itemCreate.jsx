import React from 'react';
import ReactDOM from 'react-dom';
import './css/ItemCreate.css';
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
import jQuery from "jquery";
window.$ = window.jQuery = jQuery;

class ItemCreate extends React.Component {
    constructor(props){
        super(props)
        this.state = {category:[], categoryD:[], color:[],size:[], pop:[]}
        this.colorTag;
        this.sizeTag;
    }
    async componentDidMount(){    
        let {data} = await axios.get('/shop/store/axios');
        console.log(data);
        
       
        this.setState({category:data[0], categoryD:data[1], color:data[2], size:data[3], pop:data[4]})
        console.log(this.state);
        this.categorys();
        this.setPop();
        // this.setOptionSelect();
    }
    setPop(){
        var select = $("#pop_select")
        
        this.state.pop.map((popInfo,index)=>{
            var option = $(document.createElement("option"));
            option.val(popInfo.id)
            option.text(popInfo.name)
            select.append(option);
        })
        var option = $(document.createElement("option"));
        option.val(0)
        option.text("기타상품")
        select.append(option);
    }
    categorys(){
        var categorySelect = $('#category_select');
        var initCategoryValue;

        this.state.category.map((category,index)=>{
            var option = $(document.createElement("option"));
            option.val(category.category_id)
            option.text(category.category)
            
            if(index == 0){
                option.defaultSelected = true;
                initCategoryValue = category.category_id
            }

            categorySelect.append(option) 
        });

        // 첫뻔째 카테고리에 대하여, 세부 카테고리 나타냄. 
        this.setCategoryD(initCategoryValue);

    }
    // setOptionSelect(){
    //    var selectColor = $(document.createElement("select"));
    //    var selectSize = $(document.createElement("select"));

    //    this.state.color.map((color,index)=>{
    //         var option = $(document.createElement("option"));
    //         option.val(color.colorId);
    //         option.text(color.pColor);

    //         selectColor.append(option)
    //    });

    //    this.state.size.map((size,index)=>{
    //         var option = $(document.createElement("option"));
    //         option.val(size.sizeId);
    //         option.text(size.pSize);

    //         selectSize.append(option)
    //    })

    //    this.colorTag = selectColor;
    //    this.sizeTag = selectSize;
    // //    console.log(selectColor);
    // //    console.log(selectSize);
    // //    $('.prdOption').append(selectColor);
       
    // //    $('.prdOption').append(selectSize);
    // }

    setCategoryD(nowCategoryId){
        var categoryDSelect = $('#categoryD_select')
        categoryDSelect.empty();
        this.state.categoryD.map((categoryD,index)=>{
            if(categoryD.category == nowCategoryId){
                var option = $(document.createElement("option"));
                option.val(categoryD.category_d_id)
                option.text(categoryD.category_d)
                option.attr("required", true)
                    // option.defaultSelected = true;
                categoryDSelect.append(option)
            }
        });
    }
    categoryDSet(){
        this.setCategoryD($('#category_select').val())
    }
    addPrdOption(){
        var div = $('.prdOption');
        div.empty();
        
        
        
        
        
        for( var i = 0; i< $('.optionNum').val(); i++){
            
            var topDiv = $(document.createElement("div"))              
            
            // select  옵션 테그 생성,  메소드로 따로 만들어 보았으나, append() 함수는 같은 블록 내에서 생성된 것이 아니면, 한번만 추가함.
            var selectColor = $(document.createElement("select"));
            var selectSize = $(document.createElement("select"));

            selectColor.attr("name","optionColor")
            selectSize.attr("name","optionSize")
    
            this.state.color.map((color,index)=>{
                var option = $(document.createElement("option"));
                option.val(color.colorId);
                option.text(color.pColor);
    
                selectColor.append(option)
            });


            this.state.size.map((size,index)=>{
                var option = $(document.createElement("option"));
                option.val(size.sizeId);
                option.text(size.pSize);
    
                selectSize.append(option)
            })


            
            topDiv.append(selectColor)
            topDiv.append(selectSize)

            // 옵션 정보 및, 추가 금액 입력

            var body = $('<div>옵션명</div><input type="text" name="optionTitle" placeholder="색상,사이즈 기타 시 입력"/>\
            <div>추가금액</div><input type="number" name="optionPrice" min="0"max="9999"/>원\
            <div>재고</div><input type="number" name="optionStock" min="0" max="9999"/>\
            </div>');
            topDiv.append(body);

            div.append(topDiv)    
        }

    }

    render() {
        
        return (
            <form method="post" encType="multipart/form-data" action="/shop/store">
                <div className="createItemBody">
                    {/* <div className="div_center">상품 등록</div> */}
                    <input type="hidden" name="_csrf" value={document.cookie.split("=")[1]}/>
                    <div className="item">
                        <div className="leftBox">
                            <strong><div>분류</div></strong>
                            <select id="category_select"  name="category" onChange={this.categoryDSet.bind(this)} required>                
                            </select>
                            <select id="categoryD_select"  name="categoryD" required>                
                            </select>
                            <strong><div>관련 출연자</div></strong>
                            <select id="pop_select"  name="pop" onChange={this.categoryDSet.bind(this)} required>                
                            </select>
                            <strong><div>상품판매 종료날짜</div></strong>
                            <input type="date" name="endTime" required/>
                            <strong><div>상품명</div></strong>
                            <input type="text" name="title" placeholder="상품명" required/>
                            <strong><div>간단설명</div></strong>
                            <textarea name="info1" placeholder="간단한 설명을 입력하세요" required/>
                            <strong><div>상세설명</div></strong>
                            <textarea className="text1" name="info2"  placeholder="상세설명을 입력하세요" required/>
                        </div>
                        <div className="rightBox">
                            <label>대표 이미지</label>   
                            <input type="file" name="file1" required/>
                            <label>부가 이미지</label>    
                            <input type="file" name="file2" multiple required/>
                            <div>재고</div>
                            <input className="raribi" type="number" name="stock" placeholder="개수" min="1" required/>개
                            <div>개당 가격</div>
                            <input className="raribi" type="number" name="price" placeholder="가격" min="1" required/>원
                            <div> 선택 항목 </div>
                            <label>상세 이미지</label>    
                            <input type="file" name="file3"multiple/>
                        </div>
                        <div>
                            <div>옵션</div>
                            
                            <input type="number" className="optionNum" min="0"/>개<button type="button" onClick={this.addPrdOption.bind(this)}>확인</button>
                            <div className="prdOption"></div>
                        </div>
                        <div>
                            <button className="submitButton">상품 등록</button>
                            <a href="/userInfo/manage/product" className="cancle">취소</a>
                        </div>
                    </div>
                </div>
            </form>
            
        )
    }
}

ReactDOM.render(<ItemCreate/>,document.getElementById('shopItemCreate'));