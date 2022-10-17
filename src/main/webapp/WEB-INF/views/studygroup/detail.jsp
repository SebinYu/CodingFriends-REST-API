<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
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
      <div>${ studygroup.regDate }</div>
      <div>
          신청인원: ${ studygroup.currentNum }/${ studygroup.totalNum }
          <div style="float:right;">
              <a href="edit?studyGroup_id=${ studygroup.studyGroup_id }" style="text-decoration: none; margin-right: 5px">수정</a>
              <a href="delete?studyGroup_id=${ studygroup.studyGroup_id }" style="text-decoration: none;">삭제</a>
          </div>
          <div>
              ${ studygroup.startDate }
          </div>
      </div>



      <hr><br>
      <div class="content">${ studygroup.content }</div>
      <br><br>
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary w-100" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
            스터디 신청하기
        </button>

        <!-- Modal -->
        <form class="modal fade" id="staticBackdrop" method="post" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-info" style="color: white">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">스터디 신청하기</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body">
                        <p>스터디 아이디 <textarea class="form-control" name="studygroupId" value=" ${apply.studygroupId }" readonly>${studygroup.studyGroup_id }</textarea></p>
                        <p>사용자 아이디<textarea class="form-control" name="userId" value=" ${apply.userId }"> ${students.student_id }</textarea></p>
                        <p>스터디 이름<textarea class="form-control" value=" ${studygroup.title }" readonly disabled> </textarea></p>


                        <div class="mb-3">
                            <label for="exampleFormControlTextarea1" class="form-label"><h2>참여 포부</h2></label>
                            <textarea class="form-control" name="application"  id="exampleFormControlTextarea1"  value="${ apply.application }" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">제출</button>
                    </div>
                </div>
            </div>
        </form>

<%--        참여자 명단--%>
<%--        <c:forEach var="apply" items="${ applyList }">--%>
<%--                <div style="background: #0dc9ef; width:20px; margin-right: 10px">${ apply.name }</div>--%>
<%--        </c:forEach>--%>
        <c:forEach var="apply" items="${ applyList }">
        <div class="" style="display: inline-block; margin: 20px;">
            <div class="card" style="width: 10rem;">
                <div class="card-body">
                    <h5 class="card-title" style="color: black; text-align: center">
                            ${ apply.name }
<%--                        <a href="detail?studyGroup_id=${ studygroup.studyGroup_id }"--%>
<%--                           style="color: rgba(0,0,0,0.57);text-decoration: none; font-weight: bold;">${ studygroup.title }</a>--%>
                    </h5>
                </div>
            </div>
        </div>
        </c:forEach>


<jsp:include page="/template/footer.jsp"></jsp:include>

</body>

</html>
