<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <style>

    textarea{
      width:80px;
      border: none;
      outline: none;
      height: 50px;
    }

    textarea:focus {outline: none;}

    .dashboard {
      background: #0dc9ef;
      height: 100vh;
      width: 20%;
      float:left;
      text-align: left;
      padding: 50px 100px 50px 100px;
    }

    .link {
      color: rgb(255,255,255);
      text-decoration: none;
      font-weight: bold;
      margin-top: 30px;
      font-size: 25px;
    }

  </style>
</head>
<body style="background: rgba(243,243,243,0.22);">
<div class="dashboard" style="">
  <div style="margin: 100px 0px 80px 10px">
    <img src="../../../../../resources/static/imgs/settings.png" width="250px" height="250px">
  </div>
  <div style="height: 35%; margin-top: 50px">
    <hr>
    <a class="link" href="/user/leader/attendance/index">주차별 참여율</a>
    <br><br>
    <a href="/user/leader/participantManage/index" class="link">스터디원 관리</a>
    <br><br>
    <a href="/user/leader/applicationManage/index" class="link" >스터디 지원자 리스트</a>

  </div>

</div>

</body>
</html>
