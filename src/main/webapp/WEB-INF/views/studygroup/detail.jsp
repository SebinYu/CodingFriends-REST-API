<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:url var="R" value="/" />
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css"
	rel="stylesheet">
	<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="${path}/resources/static/css/detail.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<title>Coding Study Friends</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body class="black">
<jsp:include page="/template/header.jsp"></jsp:include>



    <div class = "container" style="margin-top: 100px; clear: both; text-decoration: none;">
        <input type="hidden" value=${error}>
      <h1>${ studygroup.title }</h1>
      <div>${ studygroup.updateDate }</div>
      <div>
          <div style="float:right;">
              <a href="edit?studyGroup_id=${ studygroup.studyGroup_id }" style="text-decoration: none; margin-right: 5px">수정</a>
              <c:if test="${ error == 1 }">
                  <a href="#" style="text-decoration: none;"></a><br>
                  * 신청자/참여확정자가 있는 경우 삭제 불가
              </c:if>
              <c:if test="${ error == null }">
                  <a href="delete?studyGroup_id=${ studygroup.studyGroup_id }" style="text-decoration: none;">삭제</a>
              </c:if>
          </div>
          <div>
              기간: ${ studygroup.startDate } ~ ${ studygroup.endDate }
          </div>

      </div>
        <br>
      <hr><br>
      <div class="content" style="height: 200px">${ studygroup.content }</div>
      <br><br>
        <!-- Button trigger modal -->
        <sec:authorize access="not authenticated">
        <a type="button" class="btn btn-primary w-100" href="/login">
            <스터디 신청><br>로그인 하러가기
        </a>
        </sec:authorize>
        <sec:authorize access="authenticated">
        <button type="button" class="btn btn-primary w-100" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
            스터디 신청하기
        </button>
        </sec:authorize>


        <%--스터디 신청하기 모달창--%>
        <form class="modal fade" id="staticBackdrop" method="post" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-info" style="color: white">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">스터디 신청하기</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body" style="padding: 35px">
                        <p>스터디 번호 <textarea  class="form-control" name="studygroupId" style="height: 20px" value=" ${apply.studygroupId }" readonly>${studygroup.studyGroup_id }</textarea></p>
                        <p>스터디 명칭 <textarea class="form-control" name="title" style="height: 20px" value=" ${apply.title }" readonly>${studygroup.title }</textarea></p>

                        <sec:authorize access="authenticated">
                            <p>사용자 번호<textarea class="form-control" name="userId" style="height: 20px" value=" ${apply.userId }" readonly><sec:authentication property="principal.userId"/></textarea></p>
                            <input name="name" type="hidden" value=<sec:authentication property="principal.name"/>>
                            <input name="email" type="hidden" value=<sec:authentication property="principal.email"/>>


                        </sec:authorize>

                        <hr style="margin-bottom: 30px">


                        <div class="mb-3">
                            <label for="exampleFormControlTextarea1" class="form-label"><h2>참여 포부</h2></label>
                            <textarea class="form-control" name="application"  id="exampleFormControlTextarea1"  style="height: 200px" value="${ apply.application }" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                        <button type="submit" id="request" class="btn btn-primary">제출</button>
                    </div>
                </div>
            </div>
        </form>

<%--        참여자 명단--%>
        <div style="height: 20px"></div>
        <!-- Button trigger modal -->
        <div style="margin-left: 20px; font-weight: bold; font-size: 20px;">참여 확정자 명단</div><br>
        <c:forEach var="ViewReviewInfo" items="${ ViewReviewInfos }">
            <button herf="/user/review/checkOutside?name=${ ViewReviewInfo.name }" id ="request" class="btn btn-" style="background:#78d2ff;color:white; width: 15rem; margin: 10px">
                ${ ViewReviewInfo.name }
                <br>참여도: ${ ViewReviewInfo.totalLectureScore }점
                <br>
                    <c:if test="${ ViewReviewInfo.totalReviewScore == 1 }">
                        후기(5점): ${ViewReviewInfo.totalReviewScore}점 ⭐
                    </c:if>
                    <c:if test="${ ViewReviewInfo.totalReviewScore == 2 }">
                        후기(5점): ${ViewReviewInfo.totalReviewScore}점 ⭐⭐
                    </c:if>
                    <c:if test="${ ViewReviewInfo.totalReviewScore == 3 }">
                        후기(5점): ${ViewReviewInfo.totalReviewScore}점 ⭐⭐⭐
                    </c:if>
                    <c:if test="${ ViewReviewInfo.totalReviewScore == 4 }">
                        후기(5점): ${ViewReviewInfo.totalReviewScore}점 ⭐⭐⭐⭐
                    </c:if>
                    <c:if test="${ ViewReviewInfo.totalReviewScore == 5 }">
                        후기(5점): ${ViewReviewInfo.totalReviewScore}점 ⭐⭐⭐⭐⭐
                    </c:if>
            </button>
        </c:forEach>





<jsp:include page="/template/footer.jsp"></jsp:include>
        <script>
            // 'request'라는 id를 가진 버튼 클릭 시 실행.
            $('#request').click(function () {
                $.ajax({
                    type: "POST",
                    url: "/studygroup/detail?studyGroup_id=${studygroup.studyGroup_id }",
                    data: { param: 'string' },
                    success: function (data) {
                        console.log(data);
                        alert('성공');
                    }
                }).done(function (data) {
                    alert(data);
                });
            });
        </script>
</body>

</html>
