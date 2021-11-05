/* 각 문제는 function 내부만 작업하시오. */

$(document).ready(function(){
    fnCheckId();
    fnCheckPw();
    fnCheckName();
    fnCheckEmail();
    fnCheckSubmit();
});

// 1. 아이디
function fnCheckId(){
    let result = false;
    let userId = $('#userId');
    let regId = /^[a-z][0-9a-z]{3,19}$/;     
    // /^[안에있는문자]로 시작해라 &/    /^[a-z] : 첫번째 글자는 소문자 {}중괄호 대상은 앞에[]
    let msgId = $('#msgId');

    $(userId).on('keyup', function(){
        if( regId.test($(this).val())){ //정규식.text(텍스트)
            $('#msgId').text('올바른 형식입니다.');
            $('#msgId').addClass('ok');
            $('#msgId').removeClass('not_ok');
            result = true;
        } else {
            $('#msgId').text('아이디는 소문자/숫자 4자 이상 사용 가능합니다.');
            $('#msgId').addClass('not_ok');
            $('#msgId').removeClass('ok');
            
        }
        return result;
    });
}

// 2. 비밀번호
function fnCheckPw(){
    let result = false;
    let regPw = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9!@#$%^&*]{8,20}$/;
    $('#userPw').on('blur keyup', function(){     // 비밀번호 다 입력하고 빠져나갈때 한 번만 점검
        if(regPw.test($(this).val())){
            $('#msgPw').text('사용 가능한 비밀번호입니다.');
            $('#msgPw').addClass('ok');
            $('#msgPw').removeClass('not_ok');
            result=true;
        }else {
            $('#msgPw').text('잘못 만들었습니다.');
            $('#msgPw').addClass('not_ok');
            $('#msgPw').removeClass('ok');
        }
    });
    return result;
}
 
// 3. 이름
function fnCheckName(){
    
}

// 4. 이메일
function fnCheckEmail(){
    
}

// 5. 서브밋      
function fnCheckSubmit(){
    $('#join_form').on('submit', function(event){
        if(confirm('가입할까요?') == false) {
            event.preventDefault();
            return false;
        }
        if(fnCheckId() && fnCheckPw()) {
       // if(fnCheckId() == false || fnCheckPw() == false) {
            event.preventDefault();
            return false;
        }
        return true;
    });
/*
if (fnCheckId()){
    서브밋 해도 좋다.
}
*/
}