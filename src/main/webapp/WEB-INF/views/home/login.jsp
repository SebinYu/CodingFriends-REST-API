<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="${R}res/common.css">
  <style>
    h1 { text-align: center; }
    form { padding: 10px 30px 30px 30px; width: 300px; }
    button { margin-top: 20px; margin-left: 50px; }
    .error { color: red; }
  </style>
</head>
<body>
<div class="container">
  <form method="post" action="${R}login_processing" class="box">
    <h1>로그인</h1>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <table>
      <tr>
        <td>아이디:</td>
        <td><input type="text" name="userid" /></td>
      </tr>
      <tr>
        <td>비밀번호:</td>
        <td><input type="password" name="passwd" /></td>
      </tr>
    </table>
    <button type="submit" class="btn">로그인</button>

    <c:if test="${ param.error != null }">
      <div class="error">로그인 실패</div>
    </c:if>
  </form>
</div>
</body>
</html>