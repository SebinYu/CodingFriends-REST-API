<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div>
  <h1>사용자 정보</h1>
  <ul>
    <li>${ login }</li>
    <li>${ name }</li>
    <li>${ email }</li>
    <li><img src="${ avatar_url }" /></li>
  </ul>

  <a href="/logout">로그아웃</a>
</div>
</body>
</html>