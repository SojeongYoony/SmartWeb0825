// 건드리지 말 것.
$(document).ready(function(){
    $('#id').on('blur', function(){ fnIdCheck(); });
    $('#pwd').on('blur', function(){ fnPwdCheck(); fnRePwdCheck(); });
    $('#re_pwd').on('blur', function(){ fnPwdCheck(); fnRePwdCheck(); });
    $('#name').on('blur', function(){ fnNameCheck(); });
    fnSubmit();
});

// 입력 점검 전역 변수
// true이면 정규식 통과, false이면 정규식 통과 못함
let idPass = false;   /* 아이디 점검 */
let pwPass = false;   /* 비밀번호 점검 */
let pwRePass = false; /* 비밀번호 재입력 점검 */
let namePass = false; /* 이름 점검 */

function fnIdCheck(){
    let regID = /^[a-z0-9][a-z0-9_-]{4,19}$/;
    // 1) 아이디 입력 점검 코드 삽입
    if(regID.test($('#id').val())){
        $('#id_check').text('멋진 아이디네요!');
        $('#id_check').addClass('check_pass');
        $('#id_check').removeClass('check_fail');
        idPass = true;

    } else if ($('#id').val().length == 0){
        $('#id_check').text('필수 정보입니다!');
        $('#id_check').addClass('check_fail'); 
        $('#id_check').removeClass('check_pass');
        $('#id').focus();

    } else {
        $('#id_check').text('5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.');
        $('#id_check').addClass('check_fail'); 
        $('#id_check').removeClass('check_pass');
    }
    return idPass;
}

function fnPwdCheck(){
    let regPWD = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9!@#$%^&*]{4,16}$/;
    // 2) 비밀번호 입력 점검 코드 삽입
    if(regPWD.test($('#pwd').val())){
        $($('i')[0]).addClass('check_pass');
        $($('i')[0]).removeClass('check_fail');
        pwPass = true;

    } else if ($('#pwd').val().length == 0){
        $($('i')[0]).addClass('check_fail');
        $($('i')[0]).removeClass('check_pass');
        $('#pwd').focus();

    } else {
        $($('i')[0]).addClass('check_fail');
        $($('i')[0]).removeClass('check_pass');         
    }
    return pwPass;
}

function fnRePwdCheck(){
    // 3) 비밀번호 재입력 점검 코드 삽입
    if($('#pwd').val()==$('#re_pwd').val()){
        $('#re_pwd_check').text('');
        $($('i')[1]).addClass('check_pass');
        $($('i')[1]).removeClass('check_fail');
        pwRePass = true;

    } else if ($('#re_pwd').val().length == 0){
        $('#re_pwd_check').text('필수 정보입니다!');
        $('#re_pwd_check').addClass('check_fail'); 
        $('#re_pwd_check').removeClass('check_pass');
        $($('i')[1]).removeClass('check_pass');
        $('#re_pwd').focus();

    } else {
        $('#re_pwd_check').text('비밀번호가 일치하지 않습니다.');
        $('#re_pwd_check').addClass('check_fail'); 
        $($('i')[1]).removeClass('check_pass');
    }
    return pwRePass;
}

function fnNameCheck(){
    let regName = /^[가-힣a-zA-Z]+$/;
    // 4) 이름 입력 점검 코드 삽입
    if(regName.test($('#name').val())){
        $('#name_check').text('');
        namePass = true;

    } else if ($('#name').val().length == 0){
        $('#name_check').text('필수 정보입니다.');
        $('#name_check').addClass('check_fail'); 
        $('#name').focus();

    } else {
        $('#name_check').text('한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)');
        $('#name_check').addClass('check_fail'); 
    }
    return namePass;


}

function fnSubmit(){
    // 5) 서브밋 점검 코드 삽입
    // Hint) 입력 점검 전역 변수가 모두 true이면 서브밋을 실행

    $('#join_form').on('submit',function(event){
        if(confirm('가입할까요?') == false){
                event.preventDefault();
                return false;
            } 
            if (idPass ==false||pwPass == false||pwRePass == false||namePass == false){
                event.preventDefault();
                return false;
            } else {
                return true;
            }
    

    })  
    

}