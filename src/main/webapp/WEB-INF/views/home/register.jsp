<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${R}res/common.css">
    <style>
        div.box { padding: 50px; width: 300px; }
        div.label { margin-top:10px; }
        .error { color: red; display: block; }
        button { margin-top: 20px; }
    </style>
</head>
<body style="background: rgba(243,243,243,0.22);">
<jsp:include page="/template/header.jsp"></jsp:include>
<jsp:include page="/template/footer.jsp"></jsp:include>
<div style="padding: 50px"></div>
<div class="inputForm"
     style="clear: both; background: #ffffff; padding: 30px 100px 30px 100px; border: 1px solid rgba(128,128,128,0.35); border-radius: 20px; margin: 0px 530px 50px 530px">
<form:form method="post" modelAttribute="userRegistration">

<h1 style="text-align: center; padding-bottom: 8px; font-weight: bold; margin-bottom: 30px; color: #696969">회원가입</h1>
        <div class="form-floating mb-3">
            <form:input type="text" class="form-control" id="floatingInput" placeholder="ID" path="userid" />
            <label for="floatingInput">아이디</label>
            <form:errors path="userid" class="error" />
        </div>
        <div class="form-floating mb-3">
            <form:password name="passwd" class="form-control" id="floatingPassword" placeholder="Password" path="passwd1" />
            <label for="floatingPassword">비밀번호</label>
            <form:errors path="passwd1" class="error" />
        </div>
    <div class="form-floating mb-3">
        <form:password name="passwd" class="form-control" id="floatingPassword" placeholder="Password" path="passwd2" />
        <label for="floatingPassword">비밀번호 확인</label>
        <form:errors path="passwd2" class="error" />
    </div>
    <div class="form-floating mb-3">
        <form:input type="text" class="form-control" id="floatingInput" placeholder="name" path="name" />
        <label for="floatingInput">이름</label>
        <form:errors path="name" class="error" />
    </div>

    <div class="form-floating mb-3">
        <form:input type="text" class="form-control" id="floatingInput" placeholder="name@example.com" path="email" />
        <label for="floatingInput">이메일 주소</label>
        <form:errors path="email" class="error" />
    </div>
    <hr>
    <button type="submit" class="btn btn-info" style="margin-top:10px; width: 48%; color:#ffffff; font-weight: bold">회원가입</button>
    <a href="${R}studygroup/list" class="btn btn-danger" style="margin-top:10px; width: 48%; color:#ffffff; font-weight: bold">취소</a>
</form:form>

</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function chk_form() {
        document.getElementById('frm').submit();
    }

    window.onload = function(){
        document.getElementById("address_kakao").addEventListener("click", function(){ //주소입력칸을 클릭하면
            //카카오 지도 발생
            new daum.Postcode({
                oncomplete: function(data) { //선택시 입력값 세팅
                    document.getElementById("address_kakao").value = data.address; // 주소 넣기
                    document.querySelector("input[name=address_detail]").focus(); //상세입력 포커싱
                }
            }).open();
        });
    }
</script>
</body>
</html>
