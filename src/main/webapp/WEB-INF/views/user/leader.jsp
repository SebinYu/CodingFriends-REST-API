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
  </style>
</head>
<body style="background: rgba(243,243,243,0.22);">
<jsp:include page="/template/header.jsp"></jsp:include>
<jsp:include page="/template/footer.jsp"></jsp:include>

<div class="dashboard" style="background: #0dc9ef; height: 100vh; width: 20%;float:left;">sss</div>
<div class="container" style="margin-top: 100px;width: 70%; float:left; padding: 50px 50px 50px 50px;">
  <div><h1>Overview</h1>
        <h3>Do it! 점프 투 파이썬</h3>

        <div>
            <form method="post">
                <c:forEach var="Applier" items="${ ApplierList }">
                <a href="#" class="list-group-item list-group-item-action">${ Applier.name }</a>
                <p>지원자 번호 <textarea name="studentId" value=" ${participationrate.studentId }" readonly>${Applier.userId }</textarea></p>
                    <hr>
                    <p>스터디 번호 <textarea name="studygroupId" value=" ${participationrate.studygroupId }" readonly>54</textarea></p>
                    <p>스터디 조직장 이름 <textarea name="studyGroup_Leader" value=" ${participationrate.studyGroup_Leader}" readonly> <sec:authentication property="name"/> </textarea></p>
                    <button type="submit" id="request" class="btn btn-primary">신청수락</button>
                </c:forEach>
            </form>

        </div>
  </div>
</div>
</body>
</html>
