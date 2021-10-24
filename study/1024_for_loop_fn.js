let names = ["Steven Paul jobs", 
"Bill Gates", 
"Mark Elliot Zuckerberg", 
"Elon Musk",
"Jeff Bezos",
"Warren Edward Buffett",
"Larry Page",
"Larry Ellison",
"Tim Cook",
"Lloyd Blankfein"
]

let ceoList = [
    {name:"Larry Page", age:23, ceo:true},
    {name:"Tim Cook", age:40, ceo:true},
    {name:"Elon Musk", age:55, ceo:false},
]

for (let i=0; i<names.length; i++) {
    console.log(names[i])
}

// 선언적 함수
// function printName(item){
//     console.log(item)
// }
// names.forEach(printName)    // forEach함수의 매개변수는 함수

// 익명함수 사용
// names.forEach(function(item){
//     console.log(item);
// });

names.forEach((item)=>{console.log(item)})  // 매개변수로 index도 같이 넘겨준다 (생략가능)

let data = ceoList.map((item)=>{          // 매개변수 인덱스는 필요하면 넣고 필요없으면 생략
    return item.name
})  
console.log(data);

// forEach / map 차이점 : forEach는 반환값 없음 => 변수를 만들어 저장할 필요가 없음
//                        map => 배열이 있음 => 반드시 배열을 return -> ruturn item + 원하는 값 하면 원하는 값 까지 출력
//                        ex) return item.age  -> 객체의 age property만 

let data2 = ceoList.filter((item)=>{
    return item.age == 23     // L로 시작하는 
})
console.log(data2)                  // filtering을 해준다 기준은 return의 조건을 기준(true/false)으로
                                    // true의 값만 반환


let data3 = ceoList.some((item)=>{
    return item.name == 'Larry Page' 
})
console.log(data3)                  // 조건에 충족하는 값이 있으면 true를 반환 (있냐/없냐)


let data4 = ceoList.every((item)=>{
    return item.ceo == 'true'   
})
console.log(data4)                  // 조건에 충족하는 값이 모두 그 값("L")이냐? (true/false)


let data5 = names.find((item)=>{
    return item=="Larry Page"   
})
console.log("find", data5)                  // 조건에 적합하는 첫 번째 index의 값만 반환


let data6 = names.findIndex((item)=>{
    return item=="Larry Page"   
})
console.log("findIndex", data6)            // 조건에 적합하는 첫 번째 index num을 반환
