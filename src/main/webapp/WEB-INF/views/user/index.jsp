<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:url var="R" value="/" />
<!DOCTYPE html>
<>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        .profile {
            width:30%; background: #ffffff; padding: 50px 50px 50px 50px; border: 1px solid rgba(128,128,128,0.35); border-radius: 20px; float:left;
            margin:10px
        }
        td{
            width: 120px;
        }
        .textRight{
            text-align: right;
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
<div class="profile" style="width:28%;">
    <div class="container-p">
        <div class="box">
            <h2 style="text-align: center; margin-bottom: 20px;color: #00b0d9; margin-bottom:20px;font-weight: bold">"<sec:authentication property="principal.name" />"님<br>프로필</h2>
            <table>
                <tr>
                    <td class="textRight">아이디: </td>
                    <td style="text-align: center"><sec:authentication property="name"/></td>
                    <td><br></td>
                </tr>
                <tr>
                    <td class="textRight">이름: </td>
                    <td style="text-align: center"><sec:authentication property="principal.name" /></td>
                    <td><br></td>
                </tr>
                <tr>
                    <td class="textRight">이메일: </td>
                    <td style="font-size: 16px; text-align: center;"> <sec:authentication property="principal.email" /></td>
                </tr>

            </table>
        </div>
    </div>
</div>




<div class="profile">
    <div class="list-group" style="margin-bottom: 30px">
        <a href="#" class="list-group-item list-group-item-action active" aria-current="true" style="background: #03dac6; border: #03dac6">
            승인대기 중
        </a>
        <c:forEach var="ApplyTitle" items="${ ApplyTitles }">
            <a href="#" class="list-group-item list-group-item-action">${ ApplyTitle.title }</a>
        </c:forEach>
    </div>

    <div class="list-group" >
        <a href="#" class="list-group-item list-group-item-action active" aria-current="true">
            참여완료
        </a>
        <c:forEach var="EndDateTitleList" items="${ EndDateTitleLists }">
        <a href="review/index?StudygroupTitle=${EndDateTitleList.title}" class="list-group-item list-group-item-action" style="font-weight: bold">"${ EndDateTitleList.title }" 후기 남기기 </a>
        </c:forEach>
    </div>
</div>
<div class="profile">
    <div class="list-group" >
        <a href="#" class="list-group-item list-group-item-action active" aria-current="true" style="background: #59be7e; border: #59be7e">
            전체 스터디 평점
        </a>
        <a href="#" class="list-group-item"  >
            <table>
                <tr style="margin-bottom: 10px">
                    <td>참여도 점수</td>
                    <td><sec:authentication property="name"/></td>
                </tr>
                <tr style="margin-bottom: 10px">
                    <td><br></td>
                </tr>
                <tr>
                    <td>스터디원 평가</td>
                    <td><sec:authentication property="principal.name" />⭐⭐⭐⭐⭐</td>
                </tr>
            </table>
            </a>
        <button id="request" class="list-group-item list-group-item-action" style="text-align: center; color: #0d6efc; font-weight: bold">내 후기 더보기</button>


    </div>

</div>

</div>

</body>
<script>

    var url = "review/check";
    var windowTargetName = "targetName";
    var features = "scrollbars=yes,width=400,height=600,location=no, resizable=yes";


    // 'request'라는 id를 가진 버튼 클릭 시 실행.
    $('#request').click(function () {
        window.open(url, windowTargetName, features);
    });

</script>
</html>
