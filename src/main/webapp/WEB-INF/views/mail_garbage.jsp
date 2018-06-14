<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="../global/header.jsp"%>
<body>
	<%@ include file="../global/nav.jsp"%>
	<%@ include file="../global/footer.jsp"%>

	<div id="container">
		<div id="mailCountSpace">${NO_READ_MAIL_COUNT} / ${ALL_MAIL_COUNT}</div>
		<div id="searchDate">
			<input type="text" name="searchStr" id="searchStr" placeholder="검색 내용 입력"> 
			<input type="button" name="searchBtn" id="searchBtn" value="검색">
		</div>
		<div class="subMenu">
			<input type="checkbox" name="allCheck" class="allCheck"> 
			선택한 항목을 
			<input type="button" name="delBtn" class="delBtn" value="영구삭제"> 
			<input type="button" name="rollbackBtn" class="rollbackBtn" value="복원하기">
		</div>
		<div id="mailList">
			<c:forEach items="${MAIL_LIST}" var="mail">
				<div class="mailItem" <c:if test="${mail.MAIL_READ eq 1}">style="background:#e7e7e7;"</c:if>>
					<input type="hidden" name="mailPk" class="mailPk" value="${mail.MAIL_PK}">
					<input type="checkbox" name="checkMail" class="checkMail">
					<a href="/mail_read?MAIL_PK=${mail.MAIL_PK}">${mail.MAIL_TITLE}</a>
					<b>${mail.MAIL_DATE}</b>
					<b>보낸 사람 ${mail.MAIL_SENDER}</b>
					<b>받는 사람 ${mail.MAIL_RECEIVER}</b>
					<c:if test="${mail.MAIL_ATTACH eq 1}">
						<b>첨부파일 있음</b>
					</c:if>
					<c:if test="${mail.MAIL_ATTACH eq 0}">
						<b></b>
					</c:if>
				</div>
			</c:forEach>
		</div>
		<div class="subMenu">
			<input type="checkbox" name="allCheck" class="allCheck"> 
			선택한 항목을 
			<input type="button" name="delBtn" class="delBtn" value="영구삭제"> 
			<input type="button" name="rollbackBtn" class="rollbackBtn" value="복원하기">
		</div>
	</div>
</body>
</html>