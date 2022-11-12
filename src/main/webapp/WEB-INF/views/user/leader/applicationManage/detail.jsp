<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        .profile {
            width:30%; background: #ffffff; padding: 50px 50px 50px 50px; border: 1px solid rgba(128,128,128,0.35); border-radius: 20px; float:left;
            margin:10px
        }
        td{
            width: 70px;
        }
        textarea{
            width:80px;
            border: none;
            outline: none;
            height: 50px;
        }

        textarea:focus {outline: none;}

        .dashboard {
            background: #0dc9ef;
            height: 100vh;
            width: 20%;
            float:left;
            text-align: left;
            padding: 50px 100px 50px 100px;
        }

        .link {
            color: rgb(255,255,255);
            text-decoration: none;
            font-weight: bold;
            margin-top: 30px;
            font-size: 25px;
        }

        table {
            margin-top: 20px;
            margin-bottom: 20px;
        }

        table.list { display: inline-block; }
        td { text-align: center; min-width: 190px; }
        td:nth-child(1) { text-align: center; min-width: 180px; }
        th:nth-child(1) {
            height: 55px;
            padding: 20px 10px 20px 18px;
            border-radius: 25px 0px 0px 25px;
        }

        th:nth-child(4) {
            height: 55px;
            border-radius: 0px 25px 25px 0px;
        }

        thead{
            background: #93e7f8;
            border-radius: 20px;
            text-align: center;
            border-radius: 20px;
            min-width: 300px;
            color: white;
            font-size: 22px;
        }

        tbody {
            font-size: 20px;

        }

        td:nth-child(1) { background: white;}
        td:nth-child(2) { background: white;}
        td:nth-child(3) { background: white;}
        td:nth-child(4) { background: white;}



    </style>
</head>
<body style="background: rgba(243,243,243,0.22);">
<jsp:include page="/template/header.jsp"></jsp:include>
<jsp:include page="/template/footer.jsp"></jsp:include>

<div class="dashboard" style="">
    <div style="height: 35%; margin-top: 350px">
        <hr>
        <a class="link" href="/user/leader">주차별 참여율</a>
        <br><br>
        <a href="detail?studyGroup_id=${ studygroup.studyGroup_id }" class="link">스터원 관리</a>
        <br><br>
        <a href="/user/leader" class="link" >스터디 지원자 리스트</a>

    </div>

</div>
<div class="container" style="margin-top: 100px;width: 70%; float:left; padding: 50px 50px 50px 50px;">
    <div><h1>스터디 지원자</h1>

<%--     만든 스터디 조회--%>
        <c:forEach var="StudygroupTitle" items="${ StudygroupTitleList }">
        <div class="" style="display: inline-block; margin: 20px;">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title" style="color: black">
                        <a href="detail?StudygroupTitle=${StudygroupTitle.title}" style="color: rgba(0,0,0,0.57);text-decoration: none; font-weight: bold;">${StudygroupTitle.title}</a>
                    </h5>
                </div>
            </div>
        </div>
        </c:forEach>


<%--        스터디 지원자_ 신청 허가창--%>
        <div class="container" style="text-align: center">
            <form method="post" style="width: 50%">
                <table class="list" >
                    <thead style="">
                    <tr>
                        <th>
                            <button type="submit" class="btn btn-info" style="color: white" name="cmd" value="save">수락</button>
                            <button type="submit" class="btn btn-danger"  name="cmd" value="delete" >거절</button>
                        </th>
                        <th>지원자</th>
                        <th>후기평점</th>
                        <th>지원일자</th>
                    <%--                        <th>지원자 ID번호</th>--%>
<%--                        <th>스터디 번호</th>--%>
<%--                        <th>스터디 조직장 이름</th>--%>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="Applier" items="${ ApplierList }">
                        <tr>
                            <td><input type="checkbox" name="idChecked" value="${ department.id }" /></td>
                            <td>${ Applier.name }</td>
                            <td>⭐⭐⭐⭐⭐</td>
                            <td>${ Applier.updateDate }</td>
                            <td><input type="hidden" class="form-control" name="studentId" value="${Applier.userId}" readonly></td>
                            <td><input type="hidden" class="form-control" name="studygroupId" value="${studygroupID}" readonly></td>
                            <td><input type="hidden" class="form-control" name="studyGroup_Leader" value="<sec:authentication property="name"/>" readonly></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

        </div>


</body>
</html>
