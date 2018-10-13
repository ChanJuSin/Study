function checkForm() {
	if ($("#email").val() === "") {
		alert("이메일을 입력하세요.");
		$("#email").focus();
		return false;
	} else if ($("#pw").val() === "") {
		alert("비밀번호를 입력하세요.");
		$("#pw").focus();
		return false;
	}
}