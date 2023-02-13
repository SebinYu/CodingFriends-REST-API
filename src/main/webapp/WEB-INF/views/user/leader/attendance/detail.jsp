<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>

        textarea{
            width:80px;
            border: none;
            outline: none;
            height: 50px;
        }

        textarea:focus {outline: none;}


        table {
            margin-top: 20px;
            margin-bottom: 20px;
            margin-left: 20px;
        }

        table.list { display: inline-block; }

        td {
            text-align: center;
            min-width: 320px;
            height: 65px;
            border: 1px #1e1f26;

        }

        td:nth-child(1) { text-align: center; min-width: 180px; }
        th:nth-child(1) {
            height: 105px;
            padding: 20px 10px 20px 18px;
            border-radius: 25px 0px 0px 25px;
        }

        th:nth-child(4) {
            height: 55px;
            border-radius: 0px 25px 25px 0px;
        }

        td:nth-child(1) {
            height: 55px;
            padding: 20px 10px 20px 18px;
            border-radius: 25px 0px 0px 25px;
        }

        td:nth-child(4) {
            height: 55px;
            border-radius: 0px 25px 25px 0px;
        }

        td:nth-child(4) {
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
            border:1px solid black;
            font-weight: bold;
            color: #838994;
        }

        td:nth-child(1) { background: rgba(233, 234, 238, 0.4);}
        td:nth-child(2) { background: rgba(233, 234, 238, 0.4);}
        td:nth-child(3) { background: rgba(233, 234, 238, 0.4);}
        td:nth-child(4) { background: rgba(233, 234, 238, 0.4);}


        input::placeholder {
            color: red;
            font-style: italic;
        }
    </style>
    <script src="https://kit.fontawesome.com/c30dd58b89.js" crossorigin="anonymous"></script>

</head>
<body style="background: rgba(243,243,243,0.22);">
<jsp:include page="/template/header.jsp"></jsp:include>
<jsp:include page="/template/footer.jsp"></jsp:include>
<jsp:include page="/template/left_column.jsp"></jsp:include>

<div class="container" style="margin: 100px 0px 0px 20px; width: 70%; float:left; padding: 50px 50px 50px 50px;">
    <div><p style="color: #0dc9ef; font-size: 70px; font-weight: bold; margin: 0px 0px 10px 13px;">주차별 참여율</p>

<%--     만든 스터디 조회--%>
        <c:forEach var="StudygroupTitle" items="${ StudygroupTitleList }">
        <div class="" style="display: inline-block; margin: 20px;">
            <div class="card" style="width: 25rem; text-align: center">
                <div class="card-body">
                    <h5 class="card-title" style="color: black">
                        <a href="detail?StudygroupTitle=${StudygroupTitle.title}" style="color: rgba(0,0,0,0.57);text-decoration: none; font-weight: bold;">${StudygroupTitle.title}</a>
                    </h5>
                </div>
            </div>
        </div>
        </c:forEach>
<hr>
       <h2 style="font-weight: bold; margin-left: 50px; margin-top:50px; color: rgba(101,101,101,0.65)" name="StudygroupTitle" id="StudygroupTitle">${StudygroupTitlePara}<span id="result"></span></h2>
        <div class="container" style="margin-left: 70px; margin-top: 30px ">
            <div class="dropdown">
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a href="detail?StudygroupTitle=${StudygroupTitle.title}" style="color: rgba(0,0,0,0.57);text-decoration: none; font-weight: bold;">${StudygroupTitle.title}</a>
                    <c:forEach var="WeekInfo" items="${ WeekInfoList }">
                        <a href="detail?week=${ WeekInfo.week }&&StudygroupTitle=${StudygroupTitlePara}" class="dropdown-item">${ WeekInfo.week }주차</a>
                        <br>
                    </c:forEach>
                </div>
            </div>
<%--        스터디 지원자_ 신청 허가창--%>
        <div class="container">
            <form method="post" action="/attendanceProcess" onsubmit="return false">
                <div style="margin-left: 30px">
                    <input type="number" oninput="myFunction()" name="weekInput" id="weekInput" placeholder="주차 입력" min="1" class="form-control" style="width:120px;font-size: 15px; float: left; text-align:center; font-weight: bold" ;
                            required/>
                </div>
                <div style="color: #0d6efc; float: left; margin-left: 20px">
                    ***몇 주차 내역인지 입력해주세요***
                </div>
                <table class="list"  style="text-align: center">
                <thead style="">
                <tr>
                    <th>스터디원 ID</th>
                    <th>이름</th>
                    <th>출석</th>
                    <th>과제실행</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="Participant" items="${ ParticipationList }">
                <tr>
                        <td>${Participant.userId}</td>
                        <td>${ Participant.name }</td>
                        <td><input type="checkbox" style="accent-color: green; zoom:1.8;" name="attendanceChecked" id="input_attendanceCheck" value=${Participant.userId}></td>
                        <td><input type="checkbox" style="accent-color: red; zoom:1.8;" name="homeworkChecked" id="input_homeworkCheck" value=${Participant.userId}></td>
                        <td><input type="hidden" class="form-control" id="studentId" value="${Participant.userId}" readonly studentId></td>
                        <td><input type="hidden" class="form-control" id="studygroupID" value="${studygroupID}" readonly studygroupID></td>
                    </tr>
                    </c:forEach>

                </tbody>
            </table>
                <button id="checkButton" class="btn btn-info" style="margin-left: 320px; color: white; font-weight: bold; width: 50%" name="cmd" value="check" checkButton>등록</button>
            </form>
        </div>


</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
<script>
    $(function() {

        $("[data-url]").click(function() {
            var url = $(this).attr("data-url");
            location.href = url;
        })

        $("[data-confirm-delete]").click(function() {
            return confirm("삭제하시겠습니까?");
        })

    });


    function myFunction(){
        var x = document.getElementById("weekInput").value;
        document.getElementById("result").innerHTML = ": " + x + "주차";
    }




        $("[checkButton]").click(function () {


            var studentId = [];
            $("[studentId]").each(function (index) {
                studentId.push($(this).val());
            });

            var studygroupID = [];
            $("[studygroupID]").each(function (index) {
                studygroupID.push($(this).val());
            });


            var attendanceCheckedArr = [];
            var homeworkCheckedArr = [];

            $('input:checkbox[name="attendanceChecked"]:not(:checked)').each(function (index) {
                attendanceCheckedArr.push(0);
            })

            $('input:checkbox[name="attendanceChecked"]:checked').each(function (index) {
                attendanceCheckedArr.push($(this).val());
            })

            $('input:checkbox[name="homeworkChecked"]:not(:checked)').each(function (index) {
                homeworkCheckedArr.push(0);
            })

            $('input:checkbox[name="homeworkChecked"]:checked').each(function (index) {
                homeworkCheckedArr.push($(this).val());

            })

            var xWeekInput = document.getElementById("weekInput").value;
            var StudygroupTitle = document.getElementsByName("StudygroupTitle").value;
            var overlapError = 0;


            var data = {"attendanceCheckedArr":attendanceCheckedArr,
                        "homeworkCheckedArr": homeworkCheckedArr,
                        "studentId":studentId,
                        "studygroupID":studygroupID,
                        "xWeekInput": xWeekInput,
                        "StudygroupTitle": StudygroupTitle,
                        }

            $.ajax({
                url : "/user/leader/attendance/detail", //요청 할 URL
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


        });
</script>
</html>
