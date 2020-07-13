import React from 'react';
import ReactDOM from 'react-dom';
import './css/ItemCreate.css';
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
import jQuery from "jquery";

import '../smart.css';
import './css/ItemCreate.css'
window.$ = window.jQuery = jQuery;


class ItemEdit extends React.Component {
    constructor(props){
        super(props)
        this.state = {category:[], categoryD:[], color:[],size:[]}
        this.data;
        this.colorTag;
        this.sizeTag;
    }
    async componentDidMount(){    
        
        let prdData = await axios.get("/shop/edit/"+$("#prdId").val()+"/axios")
        this.data = prdData.data;
        console.log(this.data);
        let {data} = await axios.get('/shop/store/axios');

        console.log(data);
        
        this.setState({category:data[0], categoryD:data[1], color:data[2], size:data[3]})
        console.log(this.state);
        this.categorys();

        this.initSubImg();// 서브 이미지, 상세 이미지
        this.initOption(); // 옵션
        
    }
    categorys(){
        var categorySelect = $('#category_select');
        var initCategoryValue;

        this.state.category.map((category,index)=>{// index 0 ~ 5
            var option = $(document.createElement("option"));
            option.val(category.category_id)
            option.text(category.category)
            
            if(index == this.data.prd.categoryId-1){
                initCategoryValue = category.category_id
            }

            categorySelect.append(option) 
        });

        categorySelect.val(this.data.prd.categoryId)
        // 첫뻔째 카테고리에 대하여, 세부 카테고리 나타냄. 
        this.setCategoryD(initCategoryValue);
        $('#categoryD_select').val(this.data.prd.categoryD);
    }

    setCategoryD(nowCategoryId){
        var categoryDSelect = $('#categoryD_select')
        categoryDSelect.empty();
        this.state.categoryD.map((categoryD,index)=>{
            if(categoryD.category == nowCategoryId){
                var option = $(document.createElement("option"));
                option.val(categoryD.category_d_id)
                option.text(categoryD.category_d)

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
    initSubImg(){
        var div = $("#subImg")
        var detail = $("#detailImg")

        
        this.data.img.map((img)=>{
            var addImg = $(document.createElement("img")).attr("src","/uploads/"+img.productImage).attr("class","subImg")
            if(img.imageState == 1){// 서브 이미지
                div.append(addImg)
            }else{//상세이미지
                detail.append(addImg)
            }
                
        })
    }
    initOption(){
        
        var div = $('.prdOption');
        div.empty();
        
        this.data.option.map((item)=>{

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

            selectColor.val(item.colorId)
            selectSize.val(item.sizeId)
            
            topDiv.append(selectColor)
            topDiv.append(selectSize)

            // 옵션 정보 및, 추가 금액 입력
            var body = '<input type="hidden"name="optionId" value="'+item.optionId+'"></input>\
            <div>옵션명</div><input type="text" name="optionTitle" placeholder="색상,사이즈 기타 시 입력"\
            value="'+item.oTitle+'"/>\
            <div>추가금액</div><input type="number" name="optionPrice" min="0"max="9999"\
            value="'+item.oPrice+'"/>원\
            <div>재고</div><input type="number" name="optionStock" min="0" max="9999"\
            value="'+item.pStock+'"/>\
            </div>'

            var bodyTag = $(body);
            console.log(bodyTag)
            topDiv.append(bodyTag);

            div.append(topDiv)    

        });
    
    }
    render() {
        
        return (
            <form method="post" encType="multipart/form-data" action={"/shop/update/"+$("#prdId").val()}>
                <div className="createItemBody">
                    <input type="hidden" name="_csrf" value={document.cookie.split("=")[1]}/>
                    <div className="item">
                        <div className="leftBox">
                            <div>분류</div>
                            <select id="category_select"  name="category" onChange={this.categoryDSet.bind(this)} required>                
                            </select>
                            <select id="categoryD_select"  name="categoryD" required>                
                            </select>
                            <div>상품판매 종료날짜</div>
                            <input type="date" name="endTime" defaultValue={this.data?this.data.prd.endDate.split(' ')[0]:''}  required/>
                            <div>상품명</div>
                            <input type="text" name="title" placeholder="상품명"defaultValue={this.data?this.data.prd.name:''}required/>
                            <div>간단설명</div>
                            <textarea name="info1" placeholder="간단한 설명을 입력하세요" defaultValue={this.data?this.data.prd.content:''} required/>
                            <div>상세설명</div>
                            <textarea name="info2" placeholder="자세한 설명을 입력하세요" defaultValue={this.data?this.data.prd.detail:''} required/>
                        </div>
                        <div className="rightBox">
                            <div>대표이미지</div>
                            <img className="smartImg" src={this.data? "/uploads/"+this.data.prd.img:"#"}/>
                            <input type="file" name="file1"/>
                            <div>부가 이미지</div>
                            <div id="subImg"></div>
                            <input type="file" name="file2"  multiple />
                            <div>재고</div>
                            <input type="number" name="stock" placeholder="개수" defaultValue={this.data?this.data.prd.stock:''} min="1"/>개
                            <div>개당 가격</div>
                            <input type="number" name="price" placeholder="가격" defaultValue={this.data?this.data.prd.price:''} min="1"/>원
                            <div> 선택 항목 </div>
                            <div>상세 이미지</div>
                            <input type="checkbox" name="detailImgDelete"/>상세 이미지 전체 삭제
                            <div id="detailImg"></div>
                            <input type="file" name="file3"multiple/>
                        </div>
                        <div>
                            <div>옵션</div>
                            
                            <input type="number" className="optionNum" min="0"/>개<button type="button" onClick={this.addPrdOption.bind(this)}>확인</button>
                            <div className="prdOption"></div>
                        </div>
                        <div>
                            <button className="submitButton">수정완료</button>
                            <a href="/userInfo/manage/product" className="cancle">취소</a>
                        </div>
                    </div>
                </div>
            </form>
            
        )
    }
}

ReactDOM.render(<ItemEdit/>,document.getElementById('shopItemEdit'));