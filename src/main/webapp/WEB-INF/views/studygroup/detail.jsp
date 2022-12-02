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
      <h1>${ studygroup.title }</h1>
      <div>${ studygroup.updateDate }</div>
      <div>
          신청인원: ${ studygroup.currentNum }/${ studygroup.totalNum }
          <div style="float:right;">
              <a href="edit?studyGroup_id=${ studygroup.studyGroup_id }" style="text-decoration: none; margin-right: 5px">수정</a>
              <a href="delete?studyGroup_id=${ studygroup.studyGroup_id }" style="text-decoration: none;">삭제</a>
          </div>
          <div>
              기간: ${ studygroup.startDate } ~ ${ studygroup.endDate }
          </div>
      </div>

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
        <c:forEach var="apply" items="${ applyList }">
            <button type="button" id ="request" class="btn btn-light" data-toggle="modal" data-target="#exampleModal" style="width: 10rem; margin: 10px">
                ${ apply.name }
            <br>⭐⭐⭐⭐
                    <br><hr>${ apply.application }
            </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header bg-secondary" style="color: white">
                        <h5 class="modal-title-second" id="exampleModalLabel">참여 포부</h5>
                        </button>
                    </div>
                    <div class="modal-body">
                            ${ apply.application }
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
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
