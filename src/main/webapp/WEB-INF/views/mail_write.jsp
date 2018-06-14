<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../global/header.jsp"%>
<body>
	<%@ include file="../global/nav.jsp"%>
	<%@ include file="../global/footer.jsp"%>
	<form id="container" enctype="multipart/form-data" method="post">
		<div class="subBtn">
			<input type="button" name="send" onclick="go('mail_send')" value="보내기">
			<input type="button" name="new" onclick="go('mail_write')" value="새로쓰기">
		</div>
		<div>
			<label for="sender">보내는 사람</label>
			<input type="text" name="sender" id="sender" value="${USER_NAME} (${USER_MAIL})" readonly="readonly">
		</div>
		<div>
			<label for="receiver">받는 사람</label>
			<input type="text" name="receiver" id="receiver">
			<input type="checkbox" name="forMe" id="forMe">
			<label for="forMe">내게 쓰기</label>
		</div>
		<div id="fileSpace">
			<div id="fileTop">
				<label>첨부파일</label>
				<input type="button" name="addFile" id="addFile" value="파일찾기">
				<input type="button" name="delFile" id="delFile" value="삭제">
			</div>
			<div id="fileMid">
				<div class="fileLeft">
					<input type="checkbox" id="allCheckFile">
				</div>
				<div class="fileCenter"><label>파일명</label></div>
				<div class="fileRight"><label>용량</label></div>
			</div>
			<div id="fileBot">
				
			</div>
		</div>
		<div id="titleSpace">
			<label for="mailTitle">제목</label>
			<input type="text" name="mailTitle" id="mailTitle">
		</div>
		<div id="editorSpace">
			<%@ include file="../global/SEditor.jsp"%>
		</div>
		<div class="subBtn">
			<input type="button" name="send" onclick="go('mail_send')" value="보내기">
			<input type="button" name="new" onclick="go('mail_write')" value="새로쓰기">
		</div>
		<input type="hidden" name="files" value="file">
		<div id="hiddenFileSpace">
			<input type="file" name="file" class="file">			
		</div>
	</form>
</body>
</html>