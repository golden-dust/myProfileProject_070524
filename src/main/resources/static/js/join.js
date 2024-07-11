function joinCheck() {
	
	if(document.joinForm.mid.value.length == 0) {
		alert("아이디를 입력해주세요");
		documnet.joinForm.mid.focus();
	}
	if(document.joinForm.mpw.value.length == 0) {
		alert("비밀번호를를 입력해주세요");
		documnet.joinForm.mpw.focus();
	}
	if(document.joinForm.mname.value.length == 0) {
		alert("이름을 입력해주세요");
		documnet.joinForm.mname.focus();
	}
	if(document.joinForm.memail.value.length == 0) {
		alert("이메일을 입력해주세요");
		documnet.joinForm.memail.focus();
	}
	if(!document.joinForm.mpw.value.equals(document.joinForm.mpwCheck.value)) {
		alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
		documnet.joinForm.mpw.focus();
	}
	
	document.joinForm.submit(); // 이 코드가 맞는 지 확인할 것!
}