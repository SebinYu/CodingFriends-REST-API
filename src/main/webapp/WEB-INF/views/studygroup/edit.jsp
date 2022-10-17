<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
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
    <link
            href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
            rel="stylesheet">
    <link href="${path}/resources/static/css/edit.css" rel="stylesheet">
    <title>Coding Study Friends</title>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="black">
<nav class="navbar bg-info fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="list"
           style="color: white; font-weight: bold; margin-left: 10px; font-size: 30px">Coding
            Study Friends</a>
        <button class="navbar-toggler" type="button" style="float: right;"
                data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
                aria-controls="offcanvasNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="offcanvas offcanvas-end bg-info" style="color: white;"
             tabindex="-1" id="offcanvasNavbar"
             aria-labelledby="offcanvasNavbarLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasNavbarLabel">회원정보</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                        aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                    <li class="nav-item"><a class="nav-link active"
                                            aria-current="page" href="#">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="#">Link</a></li>
                    <li class="nav-item dropdown"><a
                            class="nav-link dropdown-toggle" href="#" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false"> Dropdown </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#">Something else
                                here</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search"
                           placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </div>
</nav>


<div style="padding: 50px"></div>

<div class="inputForm"
     style="clear: both; height: 900px; width: 950px; padding: 115px; border: 2px solid gray; border-radius: 20px; margin: auto;">
<%--    메서드 방식 - 컨트롤러 명 일치해야함--%>
    <form method="post" class="formBox">
        <h1 class="title">코딩 스터디</h1>
        <p style="margin-bottom: 70px">
        <table>
            <tr>
                <td>id</td>
                <td><input type="text" value="${ studygroup.studyGroup_id }"
                           readonly disabled/></td>
            </tr>

            <tr>
                <td>스터디명</td>
                <td><input type="text" name="title"
                           value="${ studygroup.title }" required/></td>
            </tr>
            <tr>
                <td>상세설명</td>
                <td><input type="text" name="content"
                           value="${ studygroup.content }" required/></td>
            </tr>
            <tr>
                <td>조직장 이름</td>
                <td><input type="text" name="writer"
                           value="${ studygroup.writer }" required/></td>
            </tr>
            <tr>
                <td>최대 모집인원</td>
                <td><input type="number" name="totalNum" min="1"
                           value="${ studygroup.totalNum }" required/></td>
            </tr>
            <tr>
                <td>스터디 사용자료</td>
                <td><select name="learningMaterial_id" required>
                    <option value="" selected disabled hidden>--자료선택--</option>
                    <c:forEach var="d" items="${ learningMaterials }">
                        <option value="${ d.learningMaterial_id }"
                            ${ learningMaterial.learningMaterial_id == d.learningMaterial_id ? "selected" : "" }>
                                ${ d.materialType }</option>
                    </c:forEach>
                </select></td>
            <tr>
                <td>스터디 기간</td>
                <td>
                    <table border="1">
                        <tr>
                            <td><input type="date" name="startDate" value="${ studygroup.startDate }" required> ~ <input type="date" name="endDate" value="${ studygroup.endDate }" required></td>
                        </tr>
                    </table>
                </td>
            </tr>


        </table>


        <p style="margin-bottom: 20px"></p>
        <button type="submit" class="btn btn-info">저장</button>
        <c:if test="${ studygroup.studyGroup_id > 0 }">
            <a href="delete?studyGroup_id=${ studygroup.studyGroup_id }"
               class="btn btn-danger">삭제</a>
        </c:if>
    </form>

</div>
</div>

<script>
    (function mainScript() {
        "use strict";
        const offcanvasToggle = document.querySelector(
            '[data-bs-toggle="offcanvas"]',
        );
        const offcanvasCollapse = document.querySelector(".offcanvas-collapse");
        offcanvasToggle.addEventListener("click", function () {
            offcanvasCollapse.classList.toggle("open");
        });
    })();
</script>

<!-- 부트스트랩 : JS 설정  -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>
