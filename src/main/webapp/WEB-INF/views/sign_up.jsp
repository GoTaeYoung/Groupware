<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<style type="text/css">
.container {
	width: 400px;
	margin: 0 auto;
}
.container .input {
	width: 100%;
	height: 40px;
	padding: 5px;
	border-radius: 3px;
	margin-bottom: 10px;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="resources/global.js"></script>
</head>
<body>
	<div>
		<form method="post" class="container" action="mail_send">
			<label>
				아이디 <br>
				<input type="text" id="USER_ID" name="USER_ID" class="input" placeholder="아이디를 입력하세요" data-text="아이디" onchange="changeFlag">
			</label>
			<input type="button" id="check" class="input" value="중복 확인">

			<label>
				비밀번호 <br>
				<input type="password" id="USER_PASSWORD" name="USER_PASSWORD" class="input" placeholder="비밀번호를 입력하세요" data-text="비밀번호">
			</label>
			<label>
				비밀번호 확인 <br>
				<input type="password" id="USER_PASSWORD_CHECK" name="USER_PASSWORD_CHECK" class="input" placeholder="비밀번호를 다시 입력하세요" data-text="비밀번호 확인">
			</label>
			<label>
				이름 <br>
				<input type="text" id="USER_NAME" name="USER_NAME" class="input" placeholder="이름을 입력하세요" data-text="이름">
			</label>
			<label>
				이메일 <br>
				<input type="text" id="USER_MAIL" name="USER_MAIL" class="input" placeholder="이메일을 입력하세요" data-text="이메일">
			</label>
			성별
			<div class="input">
				<label>
					<input type="radio" name="USER_GENDER" value="남자"> 남자
				</label>
				<label>
					<input type="radio" name="USER_GENDER" value="여자"> 여자
				</label>
			</div>
			<input type="submit" onclick="return join()" value="가입하기">
			<input type="button" onclick="sign_in()" value="로그인">
		</form>
	</div>
</body>
<script type="text/javascript">

var flag = false;

$("#check").click(function() {
	var id = $("#USER_ID").val();
	$.ajax({
		url : '/checkId?USER_ID=' + id,
		type : 'get',
		success : function(data) {
			alert(data);
			if (data == "사용 가능") {
				flag = true;
			}
		}
	})
});
function changeFlag(){
	flag = false;
}
function join() {
	var array = [ "#USER_ID", "#USER_PASSWORD", "#USER_PASSWORD_CHECK", "#USER_NAME", "#USER_MAIL" ];
	var arr = "";
	for (var i = 0; i < array.length; i++) {
		arr = array[i];
		if ($(arr).val() == "") {
			alert($(arr).data("text") + " 입력하세요");
			$(arr).focus();
			return false;
			break;
		}
	}

	if ($("input[name=USER_GENDER]:checked").length == 0) {
		alert("성별 체크");
		$("input[name=USER_GENDER]").first().focus();
		return false;
	}
	if (flag == false) {
		alert("아이디 중복 확인 해주세요");
		return false;
	}
	if ($('#USER_PASSWORD').val() != $('#USER_PASSWORD_CHECK').val()) {
		alert("비밀번호가 다릅니다");
		return false;
	}
}
function sign_in() {
	window.location.href = "sign_in";
}
</script>
</html>