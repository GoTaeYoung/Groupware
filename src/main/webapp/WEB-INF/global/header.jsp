<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<link rel="stylesheet" type="text/css"
	href="resources/style.css?version=<%=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.KOREA).format(new Date())%>">
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="resources/global.js?version=<%=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.KOREA).format(new Date())%>"></script>
<header class="same">
	<c:if test="${!empty USER_ID}">
		<li>${title}</li>
		<div>
			<a href="sign_in">로그아웃</a>
		</div>
		<div>
			<a href="mail_send">기본 메일함</a>
		</div>
		<div>
			<a href="sign_edit">${USER_NAME}님 환영합니다</a>
		</div>
	</c:if>
	<c:if test="${empty USER_ID}">
		<%
			response.sendRedirect("sign_in");
		%>
	</c:if>
</header>