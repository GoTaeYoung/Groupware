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
				<input type="text" id="USER_ID" name="USER_ID" class="input" placeholder="아이디를 입력하세요" data-korea="아이디">
			</label>
			<label>
				비밀번호 <br>
				<input type="password" id="USER_PASSWORD" name="USER_PASSWORD" class="input" placeholder="비밀번호를 입력하세요" data-korea="비밀번호">
			</label>
			<input type="submit" onclick="return login()" value="로그인">
			<input type="button" onclick="sign_up()" value="회원가입">
		</form>
	</div>
</body>
<script type="text/javascript">
function emptyCheck(element){
	var val = element.val();
	var flag = true;
	if(val == ""){
		flag = false;
	}
	return flag;
}
function login(){
	var array = makeArray();
	var flag = false;
	var msg = "";
	var korea="";

	$.each(array, function (index, item){
		flag = emptyCheck(item);
		if(flag == false){
			korea = item.data("korea");
			msg = "[" + korea + "] 값을 확인 해 주세요";
			alert(msg);
			return false;
		}
	});
	return flag;
}
function makeArray(){
	var array = new Array();
	array.push($("#USER_ID"));
	array.push($("#USER_PASSWORD"));

	return array;
}

function sign_up() {
	window.location.href = "sign_up";
}
</script>
</html>