<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sebin
  Date: 12/1/2022
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
            rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css"
          rel="stylesheet">
    <title>Title</title>
</head>
<body style="background: rgba(243,243,243,0.22);">
<div class="continer" style="margin-top: 40px; color: #00b0d9; font-weight: bold; text-align: center"><h1>"내 후기"</h1></div>
<c:forEach var="MyReviewList" items="${AllMyReviewList}">
    <c:forEach var="myReview" items="${MyReviewList}">
    <c:if test="${ myReview.objection == 1 }">
    <div class="continer" style="padding: 50px; background: rgba(255,255,255,0.5)">
        분쟁중<br>
        NO. ${myReview.review_id}<br><br>
        별점(5점) :
            <c:if test="${ myReview.reviewScore == 1 }">
                ${myReview.reviewScore}점 ⭐
            </c:if>
            <c:if test="${ myReview.reviewScore == 2 }">
                ${myReview.reviewScore}점 ⭐⭐
            </c:if>
            <c:if test="${ myReview.reviewScore == 3 }">
                ${myReview.reviewScore}점 ⭐⭐⭐
            </c:if>
            <c:if test="${ myReview.reviewScore == 4 }">
                ${myReview.reviewScore}점 ⭐⭐⭐⭐
            </c:if>
            <c:if test="${ myReview.reviewScore == 5 }">
                ${myReview.reviewScore}점 ⭐⭐⭐⭐⭐
            </c:if>
            <br><br>
        <textarea class="form-control" style="width: 100%; height: 100px; padding: 20px; border-radius: 20px; margin-bottom: 10px" readonly>${myReview.reviewContents}</textarea>
        <div style="text-align: left; color: red">*** ${outOfChance} ***</div>
        <button class="btn btn-light" id="objection" style="margin-top: 10px; margin-bottom: 10px; margin-left: 10px" objection>이의신청</button>
        <hr>
    </div>
    </c:if>
    <c:if test="${ myReview.objection == 0 }">
        <div class="continer" style="padding: 50px">

            NO. ${myReview.review_id}<br><br>
            별점(5점) :
            <c:if test="${ myReview.reviewScore == 1 }">
                ${myReview.reviewScore}점 ⭐
            </c:if>
            <c:if test="${ myReview.reviewScore == 2 }">
                ${myReview.reviewScore}점 ⭐⭐
            </c:if>
            <c:if test="${ myReview.reviewScore == 3 }">
                ${myReview.reviewScore}점 ⭐⭐⭐
            </c:if>
            <c:if test="${ myReview.reviewScore == 4 }">
                ${myReview.reviewScore}점 ⭐⭐⭐⭐
            </c:if>
            <c:if test="${ myReview.reviewScore == 5 }">
                ${myReview.reviewScore}점 ⭐⭐⭐⭐⭐
            </c:if>
            <br><br>
            <textarea class="form-control" style="width: 100%; height: 100px; padding: 20px; border-radius: 20px; margin-bottom: 10px" readonly>${myReview.reviewContents}</textarea>
            <div style="text-align: left; color: red">*** ${outOfChance} ***</div>
            <button class="btn btn-light" id="objection" style="margin-top: 10px; margin-bottom: 10px; margin-left: 10px" objection>이의신청</button>
            <hr>
        </div>
    </c:if>
    </c:forEach>
</c:forEach>




</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
<script>    // 'request'라는 id를 가진 버튼 클릭 시 실행.
$('[objection]').click(function () {
    var objectedReviewID = prompt("이의 신청 리뷰번호를 입력해주세요\n" + "NO.1 -> 1 입력");


    var data = {"objectedReviewID":objectedReviewID}

    $.ajax({
        url : "/user/review/check", //요청 할 URL
        type: "post",
        data:JSON.stringify(data),
        dataType: "json",
        contentType:"application/json;",
        success: function(data){
            window.alert("성공");
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });

    if(objectedReviewID > 0){
        alert("NO." + objectedReviewID+" 리뷰 이의신청 완료");
        window.location.reload();
    }
});
</script>
</html>
