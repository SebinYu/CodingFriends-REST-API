<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--  <link rel="stylesheet" href="${R}res/common.css">--%>

</head>
<body style="background: rgba(243,243,243,0.22);">
<jsp:include page="/template/header.jsp"></jsp:include>
<jsp:include page="/template/footer.jsp"></jsp:include>
<div style="padding: 55px"></div>
<div class="inputForm"
     style="clear: both; background: #ffffff; padding: 50px 100px 50px 100px; border: 1px solid rgba(128,128,128,0.35); border-radius: 20px; margin: 50px 350px 50px 350px">
  <form method="post" action="${R}login_processing" class="box">
    <h1 style="text-align: center; padding-bottom: 8px; font-weight: bold">로그인</h1>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <div class="form-floating mb-3">
      <input type="text" name="userid"  class="form-control" id="floatingInput" placeholder="name@example.com">
      <label for="floatingInput">아이디</label>
    </div>
    <div class="form-floating">
      <input type="password" name="passwd" class="form-control" id="floatingPassword" placeholder="Password">
      <label for="floatingPassword">비밀번호</label>
    </div>
    <c:if test="${ param.error != null }">
      <div class="error" style="color: #ff0028; text-align: center;margin-top: 7px">로그인 실패</div>
    </c:if>
    <button type="submit" class="btn btn-secondary" style="margin-top:10px; width: 100%">로그인</button>
    <hr>
  </form>

    </div>
</body>
</html>