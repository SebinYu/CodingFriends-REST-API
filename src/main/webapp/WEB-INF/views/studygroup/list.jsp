<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
            rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css"
          rel="stylesheet">
    <link href="${path}/static/css/list.css"
          rel="stylesheet">
    <title>Coding Study Friends</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
            integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
            integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/template/header.jsp"></jsp:include>

<%--배너 이미지 및 버튼--%>
<div class="banner" style="	height: 50vh;
	width: 100vw;
	text-align: center;

	/* background:black; */
	background-image: url('../imgs/bannerImg.jpg');
	background-repeat: no-repeat;
	background-size: cover;">
    <br><br><br><br><br><br><br><br>

    <a href="create" class="btn btn-info" style="margin-top: 250px; color: white;">스터디 만들기</a>

</div>


<%--스터디 종류 리스트--%>
<div class="container" >
    <form method="get" action="#" class="form-inline mt-3">
        <select class="dropdown mx-1 mt-2" name="learningMaterial_id"
                style="width: 120px">
            <c:forEach var="d" items="${ learningMaterials }">
                <option value="${ d.learningMaterial_id }"
                    ${ learningMaterial.learningMaterial_id == d.learningMaterial_id ? "selected" : "" }>
                        ${ d.materialType }</option>
            </c:forEach>
        </select>
        <input type="text" name="search" class="form-control mx-1 mt-2" style="width: 200px" placeholder="내용을 입력하세요"/>
        <button type="submit" class="btn btn-primary btn-default mx-1 mt-2">검색</button>
    </form>
</div>

<%--스터디 리스트 조회--%>
<div class="list" style="margin-left: 50px">

    <c:forEach var="studygroup" items="${ studygroups }">
        <div class="" style="display: inline-block; margin: 20px;">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title" style="color: black">
                        <a href="detail?studyGroup_id=${ studygroup.studyGroup_id }"
                           style="color: rgba(0,0,0,0.57);text-decoration: none; font-weight: bold;">${ studygroup.title }</a>
                    </h5>
                    <p class="card-text">
                        BY. ${ studygroup.writer }<br> 정원. ${ studygroup.currentNum }/${ studygroup.totalNum }
                    </p>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<jsp:include page="/template/footer.jsp"></jsp:include>
</body>
</html>


