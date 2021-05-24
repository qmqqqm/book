/**
 * 
 */
$(function(){
	$('#frm').submit(function(){
	if($('#agree').is(':checked')==false){
		alert('이용약관에 동의해 주세요');
		return false;
	}
    //사용자 아이디는 5-15자리까지
    if ($('#id').val().length<5 || $('#id').val().length>15) {
        alert('id는 5-15자리입니다.');
        frm.id.value='';
        frm.id.focus();
        return false;
    }
    //비밀번호는 5-15자리까지
    if ($('#pwd').val().length<5 || $('#pwd').val().length>15){
        alert('비밀번호는 5-15자리입니다.');
        frm.pwd.value='';
        frm.pwd.focus();
        return false
    }
    //비밀번호와 비밀번호 확인 체크
    if ($('#pwd').val()!=$('#confirmPwd').val()){
        alert('입력한 비밀번호가 일치하지 않습니다');
        $('#pwd').val('');
        $('#confirmPwd').val('');
        $('#pwd').focus();
        return false
    }
    //사용자 이름이 3글자-40글자 까지만 입력
    if ($('#name').val().length<3 || $('#name').val().length>7){
        alert('사용자이름은 3-7글자 입니다')
        $('#name').val('');
        frm.name.focus();
        return false
    }
    //주민등록번호 6글자, 7글자 길이 체크하세요
    //alert (isNaN(frm.id1.value))  //Not a Number
    if ($('#birth').val().length!=8) {
        alert('생년월일을 정확히 입력 하세요(ex:19990101)');
        frm.id1.focus();
        return false;
    }
  
    //성별
    if ($('#gender option:selected').val()=='선택'){
        alert('성별을 선택하세요')
        return false;
    }
    if ($('#hp').val().length<12) {
        alert('휴대폰번호를 정확히 입력해 주세요(ex:010-0000-0000)');
        $('#hp').val('')
        frm.hp.focus();
        return false;
    }
    return true;

	})//서브및끝
})
