<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:url var="R" value="/" />
<!DOCTYPE html>
<>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="${R}res/common.css">

  <style>
    .profile {
      width:65%; background: #ffffff; padding: 50px 50px 50px 50px; text-align:center; border: 1px solid rgba(128,128,128,0.35); border-radius: 20px; float:left;
      margin:10px
    }
    td{
      width: 70px;
    }

    #myform fieldset{
      display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
      border: 0; /* 필드셋 테두리 제거 */
    }
    #myform input[type=radio]{
      display: none; /* 라디오박스 감춤 */
    }
    #myform label{
      font-size: 3em; /* 이모지 크기 */
      color: transparent; /* 기존 이모지 컬러 제거 */
      text-shadow: 0 0 0 #f0f0f0; /* 새 이모지 색상 부여 */
    }

    #myform label:hover{
      text-shadow: 0 0 0 #fdc007; /* 마우스 호버 */
    }
    #myform label:hover ~ label{
      text-shadow: 0 0 0 #fdc007; /* 마우스 호버 */
    }

    #myform fieldset{
      display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
      direction: rtl; /* 이모지 순서 반전 */
      border: 0; /* 필드셋 테두리 제거 */
    }

    #myform fieldset legend{
      text-align: left;
    }

    #myform input[type=radio]:checked ~ label{
      text-shadow: 0 0 0 #fdc007; /* 마우스 호버 */
    }


  </style>
</head>
<body style="background: rgba(243,243,243,0.22);">
<jsp:include page="/template/header.jsp"></jsp:include>
<jsp:include page="/template/footer.jsp"></jsp:include>

<div class="nav-container" style="margin-top: 130px;">
  <%--    <div style="background:black; width: 50%; height: 50px; margin-left:370px">sss</div>--%>
</div>
<div class="container" style="margin-top: 7px">
  <div style="clear:both"></div>
  <div class="profile" style="width:28%; clear:both;">
    <div class="list-group" style="margin-bottom: 30px;">
      <a href="#" class="list-group-item list-group-item-action active" aria-current="true" style="background: #03dac6; border: #03dac6">
        조원 후기 남기기
      </a>
      <c:forEach var="exCompanyName" items="${exCompanyNames}">
        <a href="detail?StudygroupTitle=${StudygroupTitle}&&chosenName=${exCompanyName}" class="list-group-item list-group-item-action">${exCompanyName}</a>
      </c:forEach>
    </div>
  </div>
  <div class="profile" style="width:28%; clear:both;">
    <div class="list-group" style="margin-bottom: 30px;">
      <a href="#" class="list-group-item list-group-item-action active" aria-current="true" style="background: #91c5e0; border: #03dac6">
        후기 작성완료
      </a>
      <a href="#" class="list-group-item list-group-item-action">유세빈</a>
      <a href="#" class="list-group-item list-group-item-action">이유진</a>

      </a>
    </div>
  </div>






  <div class="profile" style="margin-top: -270px">
    <h1 style="color: rgba(0,176,217,0.73)"><${StudygroupTitle}></h1>

    <h1 style="color: rgba(0,176,217,0.73)">"${chosenName}"스터디원</h1>

    <legend>(5점 만점)</legend>

    <form name="myform" id="myform" method="post">
      <fieldset>
        <input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label>
        <input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label>
        <input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label>
        <input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label>
        <input type="radio" name="rating" value="1" id="rate5"><label for="rate5">⭐</label>
      </fieldset>
      <div style="margin-top: 30px"></div>
      <legend>해당 점수를 준 이유</legend>
      <textarea type="text" name="ratingContent" class="form-control" style="height: 200px" placeholder="해당기록은 상대방의 평점에 영향을 미칩니다. 부디 공정한 평가를 남겨주시면 감사하겠습니다."></textarea>
      <button type="submit" class="btn btn-info" id ="checkButton" style="color: white; margin-top: 30px; width: 100%" name="cmd" value="submit">제출</button>

    </form>







  </div>
</div>

</body>
<script>

  $("#myform").submit(function (){
    var starRate = document.myform.ratingContent.value;
    if(starRate == null || starRate == ""){
      alert("별점을 입력해주세요")
    }

    var reviewContent = document.myform.rating.value;
    if(reviewContent == null || reviewContent == ""){
      alert("후기내용을 입력해주세요")
    }

    var rv = true;
    if(starRate == null || starRate == "" || reviewContent == null || reviewContent == ""){
      return rv = false
    }

    return rv;
  });

</script>
</html>
