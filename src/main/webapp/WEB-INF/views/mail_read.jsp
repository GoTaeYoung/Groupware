<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../global/header.jsp"%>
<body>
	<%@ include file="../global/nav.jsp"%>
	<%@ include file="../global/footer.jsp"%>
	<form id="container" enctype="multipart/form-data" method="post">
		<div>
			<label for="sender">보내는 사람</label>
			<input type="text" name="sender" id="sender" value="${mail.MAIL_SENDER}" readonly="readonly">
		</div>
		<div>
			<label for="receiver">받는 사람</label>
			<input type="text" name="receiver" id="receiver" value="${mail.MAIL_RECEIVER}" readonly="readonly">
		</div>
		<div id="fileSpace">
			<div id="fileTop">
				<label>첨부파일</label>
			</div>
			<div id="fileMid">
				<div class="fileCenter"><label>파일명</label></div>
				<div class="fileRight"><label>용량</label></div>
			</div>
			<div id="fileBot">
				<c:forEach items="${attach}" var="attach">
					<div class='fileState'>
						<b class='fileCenter'>${attach.ATTACH_NAME}</b>
						<b class='fileRight'>${attach.ATTACH_SIZE}</b>
					</div>
				</c:forEach>
			</div>
		</div>
		<div id="titleSpace">
			<label for="mailTitle">제목</label>
			<input type="text" name="mailTitle" id="mailTitle" value="${mail.MAIL_TITLE}" readonly="readonly">
		</div>
		<div id="editorSpace" style="overflow: auto; width: 100%; height: 50%;">${mail.MAIL_CONTENT}</div>
	</form>
</body>
</html>