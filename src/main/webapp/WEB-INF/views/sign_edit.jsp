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
				<input type="text" id="USER_ID" name="USER_ID" class="input" value="${USER_ID}" placeholder="아이디를 입력하세요" data-text="아이디" readonly="readonly">
			</label>
			
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
				<input type="text" id="USER_NAME" name="USER_NAME" class="input" value="${USER_NAME}" placeholder="이름을 입력하세요" data-text="이름">
			</label>
			<label>
				이메일 <br>
				<input type="text" id="USER_MAIL" name="USER_MAIL" class="input" value="${USER_MAIL}" placeholder="이메일을 입력하세요" data-text="이메일">
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
			<input type="submit" onclick="return edit()" value="정보수정">	
			<input type="button" onclick="sign_in()" value="로그인">
		</form>
	</div>
</body>
<script type="text/javascript">
	$(function(){		
		$('input[name=USER_GENDER]').each(function() {
			if ($(this).val() == "${USER_GENDER}") {
				$(this).attr('checked', true);
			}
		});
	})
	function edit() {
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