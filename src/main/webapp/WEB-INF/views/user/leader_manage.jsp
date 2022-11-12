<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <style>
    .profile {
      width:30%; background: #ffffff; padding: 50px 50px 50px 50px; border: 1px solid rgba(128,128,128,0.35); border-radius: 20px; float:left;
      margin:10px
    }
    td{
      width: 70px;
    }
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


    table.list { display: inline-block; }
    td:nth-child(1) { text-align: center; }
    td:nth-child(2) { text-align: center; min-width: 30px; }
    input[name=name] { width: 250px; }
    input[name=shortName] { width: 80px; }
    input[name=phonename] { width: 120px; }
  </style>
</head>
<body style="background: rgba(243,243,243,0.22);">
<jsp:include page="/template/header.jsp"></jsp:include>
<jsp:include page="/template/footer.jsp"></jsp:include>

<div class="dashboard" style="">
  <div style="height: 35%; margin-top: 350px">
    <hr>
    <a class="link" href="/user/leader">주차별 참여율</a>
    <br><br>
    <a href="detail?studyGroup_id=${ studygroup.studyGroup_id }" class="link">스터원 관리</a>
    <br><br>
    <a href="/user/leader" class="link" >스터디 지원자 리스트</a>

  </div>

</div>
<div class="container" style="margin-top: 100px;width: 70%; float:left; padding: 50px 50px 50px 50px;">
  <div><h1>스터디 지원자</h1>
    <h3>Do it! 점프 투 파이썬</h3>

    <div style="margin-top: 30px">
      <form method="post">
        <c:forEach var="Applier" items="${ ApplierList }">
          <h2>${ Applier.name }</h2>
          <p>지원자 번호 <textarea class="form-control" name="studentId" value=" ${participationrate.studentId }" readonly>${Applier.userId }</textarea></p>
        </c:forEach>
        <p>스터디 번호 <textarea class="form-control" name="studygroupId" value=" ${participationrate.studygroupId }" readonly>54</textarea></p>
        <p>스터디 조직장 이름 <textarea class="form-control" name="studyGroup_Leader" value=" ${participationrate.studyGroup_Leader}" readonly> <sec:authentication property="name"/> </textarea></p>
        <button type="submit" id="request" class="btn btn-primary">신청수락</button>
        <hr>
      </form>

    </div>


</body>
</html>
