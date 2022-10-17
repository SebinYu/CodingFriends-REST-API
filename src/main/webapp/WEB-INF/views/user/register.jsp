<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="es" dir="ltr">
<head>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/resources/static/css/user/register.css"><link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700;800&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page="/template/header.jsp"></jsp:include>
<div class="login-box">
    <h2>회원가입</h2>
    <form method="post" id="frm">
        <div class="user-box">
            <input type="email" name="email" value="${ userVO.email }" required="">
            <label>이메일</label>
        </div>
        <div class="user-box">
            <input type="password" name="password" value="${ userVO.password }" required="">
            <label>비밀번호</label>
        </div>
        <div class="user-box">
            <input type="text" name="name" value="${ userVO.name }" required="">
            <label>성함</label>
        </div>
        <div class="user-box">
            <input type="text" name="address" id="address_kakao" value="${ userVO.address }" required="">
            <label>주소</label>
        </div>
        <div class="user-box">
            <input type="text" name="address_detail" value="${ userVO.address_detail }" required="">
            <label>상세주소</label>
        </div>
        <a onclick="return chk_form()" href="#">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Submit
        </a>
    </form>
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