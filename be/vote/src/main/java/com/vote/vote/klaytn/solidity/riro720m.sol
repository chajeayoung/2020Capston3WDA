// pragma solidity 0.5.6;
// pragma experimental ABIEncoderV2;

// contract Riro720m{
//     address public owner;
    
//     uint private count = 1;
    
//     uint private startTime = 0;
//     uint private endTime = 3;
    
    
    
//     uint[] private list;
//     // uint[5][] private age; // 0 ~ 4  10 ,20 ... over 50
//     // uint[2][] private gender; // 0,1  /  man, girl
    
//     uint[] private age1 ;
//     uint[] private age2 ;
//     uint[] private age3 ;
//     uint[] private age4 ;
//     uint[] private age5 ;
    
//     uint[] private gender0;
//     uint[] private gender1;
    
    
    
    
    
   
//   function () payable external{}
//     constructor() public {
//         owner = msg.sender;
//         count = 0;
//     }

//     function getBalance() public view returns (uint){
//         return address(this).balance;
//     }
    
//     function deposit() public payable{
//         require(msg.sender == owner);
//     }

//     function getCount() public view returns(uint){
//         return count;
//     }
//     function transfer(uint _value) public returns (bool){

//         require(getBalance()>= _value);
//         msg.sender.transfer(_value);

//         return true;
//     }
    
    
//     function transferWithData(uint _value, uint _time,uint _uAge,uint _uGender,string  memory _val) public returns (bool){// 1: klay , 2: nowTime,      ...3:age, 4: gender, last: Seleted

//         if(startTime <= _time && _time <= endTime){ // during vote
            
//             require(getBalance()>= _value);
//             msg.sender.transfer(_value);
        
//             listVote( _val, _uAge, _uGender);
//             return true;
//         }else{
//             return false;
//         }
//     }
//     function setOptions(uint _startTime, uint _endTime, string memory  _limit) public returns(bool){
//         // 1: startTime, 2: endTime, 3: candidate num 
//         setTimes(_startTime,_endTime);
        
        
//         bytes memory b = bytes(_limit);
//         uint i;
//         uint result = 0;
//         for (i = 0; i < b.length; i++) {
//             uint c = uint(uint8(b[i]));
//             if (c >= 48 && c <= 57) {
//                 result = result * 10 + (c - 48);
//             }
//         }
        
//         setSize(result);
//         return true;
//     }
//     function setTimes(uint _startTime, uint _endTime) public returns(bool){
//         startTime = _startTime;
//         endTime = _endTime;
//         return true;
//     }

//     function getTimes() public view returns(uint _startTime, uint _endTime){
//         return(
//             startTime,endTime
//             );
//     }
//     function setSize(uint _limit) public returns(bool){
        

//         while(list.length != _limit){
//             list.push(0);
//         }
//         while(age1.length != _limit){
//             age1.push(0);
//         }
//         while(age2.length != _limit){
//             age2.push(0);
//         }
//         while(age3.length != _limit){
//             age3.push(0);
//         }
//         while(age4.length != _limit){
//             age4.push(0);
//         }
//         while(age5.length != _limit){
//             age5.push(0);
//         }
//         while(gender0.length != _limit){
//             gender0.push(0);
//         }
//         while(gender1.length != _limit){
//             gender1.push(0);
//         }
        
//         return true;
//     }
    
    
//     function getSize() public view returns(uint _limit){
//         return list.length;
//     }
//     function listVote(string memory num, uint _uAge, uint _uGender) public returns(bool){
//         bytes memory b = bytes(num);
//         uint i;
//         uint result = 0;
//         for (i = 0; i < b.length; i++) {
//             uint c = uint(uint8(b[i]));
//             if (c >= 48 && c <= 57) {
//                 result = result * 10 + (c - 48);
//             }
//         }
        
//         list[result-1]++;
        
        
//         if(_uAge == 1)
//             age1[result-1]++;
//         else if(_uAge == 2)
//             age2[result-1]++;
//         else if(_uAge == 3)
//             age3[result-1]++;
//         else if(_uAge == 4)
//             age4[result-1]++;
//         else if(_uAge == 5)
//             age5[result-1]++;
            
//         if(_uGender == 0)
//             gender0[result-1]++;
//         else if(_uGender == 1)
//             gender1[result-1]++;
            
//         count++;
        
        
        
//         return true;
//     }
//     function getListVote() public view returns(uint[] memory _list){
//         return list;
//     }
//     function getListGender() public view returns(uint[] memory _gender0,uint[] memory _gender1){
//         return (gender0,gender1);
//     }
//     function getListAge() public view returns(uint[] memory _age1,uint[] memory _age2,uint[] memory _age3,uint[] memory _age4,uint[] memory _age5){
        
//         return (age1,age2,age3,age4,age5);
//     }
    
    
    
//     function getListTotal() public view returns(
//         uint[] memory _list
//         ,uint[] memory _age1,uint[] memory _age2,uint[] memory _age3,uint[] memory _age4,uint[] memory _age5
//         ,uint[] memory _gender0,uint[] memory _gender1
//         , uint _count
        
//         ){// nowTime
        
//         return (list,age1,age2,age3,age4,age5,gender0,gender1,count);
        
        
//     }
    

// }