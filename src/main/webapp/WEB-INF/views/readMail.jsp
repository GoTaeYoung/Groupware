<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="../global/header.jsp"%>
<body>
	<%@ include file="../global/nav.jsp"%>
	<%@ include file="../global/footer.jsp"%>

	<div id="container">
		<div id="searchDate">
			<input type="text" name="searchStr" id="searchStr" placeholder="검색 내용 입력"> 
			<input type="button" name="searchBtn" id="searchBtn" value="검색">
		</div>
		<div class="subMenu">
			<input type="checkbox" name="allCheck" class="allCheck">
			<input type="button" name="delBtn" class="delBtn" value="삭제"> 
			
			<select name="orderMail" class="orderMail">
				<option value="">정렬기준</option>
				<option value="MAIL_DATE">날짜</option>
				<option value="MAIL_RECEIVER">받는사람</option>
				<option value="MAIL_TITLE">메일제목</option>
			</select>
			
			<select name="itemCount" class="itemCount">
				<option value="">항목갯수</option>
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="15">15</option>
				<option value="20">20</option>
			</select>
		</div>
		<div id="mailList">
			<c:forEach items="${MAIL_LIST}" var="mail">
				<div class="mailItem" style="background:#e7e7e7;">
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
			<input type="button" name="delBtn" class="delBtn" value="삭제"> 
			
			<select name="orderMail" class="orderMail">
				<option value="">정렬기준</option>
				<option value="MAIL_DATE">날짜</option>
				<option value="MAIL_RECEIVER">받는사람</option>
				<option value="MAIL_TITLE">메일제목</option>
			</select>
			
			<select name="itemCount" class="itemCount">
				<option value="">항목갯수</option>
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="15">15</option>
				<option value="20">20</option>
			</select>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(".orderMail").change(function() {
		var selected = $(this).val();
		var element = "";
		var msg = "";
		if(selected != "") {
			$.ajax({
				url : "/mail_order?ORDER_TYPE=" + selected,
				type : 'GET',
				dataType : "json",
				success : function(mailList) {
					$("#mailList").html("");
					$.each(mailList, function(key, mail) {
						element = $("#mailList");
						msg = "" +
						"<div class='mailItem' style='background:#e7e7e7;'>" +
							"<input type='hidden' name='mailPk' class='mailPk' value=" + mail.mail_PK + "'>" +
							"<input type='checkbox' name='checkMail' class='checkMail'>" +
							"<a href='/mail_read?MAIL_PK=" + mail.mail_PK + "'>" + mail.mail_TITLE + "</a>" +
							"<b>" + mail.mail_DATE + "</b>" +
							"<b>보낸 사람 " + mail.mail_SENDER + "</b>" +
							"<b>받는 사람 " + mail.mail_RECEIVER + "</b>" +
						"</div>";
						appendElement(element, msg);

		                element = $(".mailItem").last();
		                msg = mail.mail_ATTACH == "1" ? "<b>첨부파일 있음</b>" : "<b></b>";
		                appendElement(element, msg);
					});
	            }
			});
		}
	});
</script>
</html>