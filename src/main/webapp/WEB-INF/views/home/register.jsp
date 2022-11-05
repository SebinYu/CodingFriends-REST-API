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
<body>
<div class="container">
    <div class="box">
        <h1>회원 가입</h1>
        <form:form method="post" modelAttribute="userRegistration">
            <div>
                <div class="label">아이디:</div>
                <form:input path="userid" />
                <form:errors path="userid" class="error" />
            </div>
            <div>
                <div class="label">비밀번호:</div>
                <form:password path="passwd1" />
                <form:errors path="passwd1" class="error" />
            </div>
            <div>
                <div class="label">비밀번호 학인:</div>
                <form:password path="passwd2" />
                <form:errors path="passwd2" class="error" />
            </div>
            <div>
                <div class="label">이름:</div>
                <form:input path="name" />
                <form:errors path="name" class="error" />
            </div>
            <div>
                <div class="label">이메일:</div>
                <form:input path="email" />
                <form:errors path="email" class="error" />
            </div>
<%--            <div>--%>
<%--                <div class="label">주소:</div>--%>
<%--                <form:input path="email" id="address_kakao" />--%>
<%--                <form:errors path="email" class="error" />--%>
<%--            </div>--%>
<%--            <div>--%>
<%--                <div class="label">상세주소:</div>--%>
<%--                <form:input path="email" />--%>
<%--                <form:errors path="email" class="error" />--%>
<%--            </div>--%>
            <button type="submit" class="btn">회원가입</button>
            <a href="${R}studygroup/list" class="btn">취소</a>
        </form:form>
    </div>
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
