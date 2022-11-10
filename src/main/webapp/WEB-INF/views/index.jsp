<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>첫 페이지</h1>

<sec:authorize access="not authenticated">
    <a href="/oauth2/authorization/github">Github 로그인</a>
    <a href="/oauth2/authorization/google">Google 로그인</a>
</sec:authorize>

<sec:authorize access="authenticated">
    <a href="/logout">로그아웃</a>
</sec:authorize>

<p>
    <a href="/user">사용자 정보</a>
</p>
</body>
</html>