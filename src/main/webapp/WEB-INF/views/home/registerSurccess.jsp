<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link rel="stylesheet" href="${R}res/common.css">
  <style>
    div.box { padding: 50px; width: 300px; }
  </style>
</head>
<body>
<div class="container">
  <div class="box">
    <h3>회원 가입 성공</h3>
    <a href="login" class="btn">로그인</a>
  </div>
</div>
</body>
</html>