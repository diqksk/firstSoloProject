$(function() {
	$('form').submit(function() {
		event.preventDefault();
		if (this.writer.value == "") {
			alert("작성자를 입력해주세요");
			this.name.focus();
			return;
		}
		if (this.password.value == "") {
			alert("비밀번호를 입력해주세요");
			this.password.focus();
			return;
		}
		if (this.title.value == "") {
			alert("제목을 입력해주세요");
			this.title.focus();
			return;
		}
		if (this.content.value == "") {
			alert("내용를 입력해주세요");
			this.content.focus();
			return;
		}
		this.action = "reg";
		this.method = "POST";
		this.submit();
	});
});