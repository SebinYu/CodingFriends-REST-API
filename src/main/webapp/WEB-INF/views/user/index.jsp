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
    </style>
</head>
<body style="background: rgba(243,243,243,0.22);">
<jsp:include page="/template/header.jsp"></jsp:include>
<jsp:include page="/template/footer.jsp"></jsp:include>

<div class="nav-container" style="margin-top: 130px;">
    <div style="background:black; width: 50%; height: 50px; margin-left:370px">sss</div>
</div>

<div class="container" style="margin-top: 7px">
<div style="clear:both"></div>
<div class="profile" style="width:25%;">
    <div class="container-p">
        <div class="box">
            <h2 style="text-align: center; margin-bottom: 20px">내 프로필</h2>
            <table>
                <tr>
                    <td>아이디</td>
                    <td><sec:authentication property="name"/></td>
                </tr>
                <tr>
                    <td>이름</td>
                    <td><sec:authentication property="principal.name" /></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><sec:authentication property="principal.email" /></td>
                </tr>

            </table>
        </div>
    </div>
</div>




<div class="profile">
    <div class="list-group" style="margin-bottom: 30px">
        <a href="#" class="list-group-item list-group-item-action active" aria-current="true" style="background: #03dac6; border: #03dac6">
            신청 스터디
        </a>
        <c:forEach var="userApply" items="${ userApplyList }">
            <a href="#" class="list-group-item list-group-item-action">${ userApply.title }</a>
        </c:forEach>
    </div>

    <div class="list-group" >
        <a href="#" class="list-group-item list-group-item-action active" aria-current="true">
            참여중인 스터디
        </a>
        <a href="#" class="list-group-item list-group-item-action">미정</a>
    </div>
</div>
<div class="profile">
    <div class="list-group" >
        <a href="#" class="list-group-item list-group-item-action active" aria-current="true" style="background: #59be7e; border: #59be7e">
            내 스터디 평점
        </a>
        <a class="list-group-item list-group-item-action disabled">
            밑바닥부터 시작하는 딥러닝
            ⭐⭐⭐⭐⭐</a>
    </div>
</div>

</div>
</body>
</html>
