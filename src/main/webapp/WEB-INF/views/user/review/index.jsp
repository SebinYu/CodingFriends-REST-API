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
            width:70%; background: #ffffff; padding: 50px 50px 50px 50px; text-align:center; border: 1px solid rgba(128,128,128,0.35); border-radius: 20px; float:left;
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
    <div class="profile" style="width:25%;">
        <div class="container-p">
            <div class="box">
                <h2 style="text-align: center; margin-bottom: 20px">내 프로필</h2>
                <table>
                    <tr>
                        <td>아이디</td>
                        <td><sec:authentication property="name"/></td>
                    </tr>
                    <tr>
                        <td>이름</td>
                        <td><sec:authentication property="principal.name" /></td>
                    </tr>
                    <tr>
                        <td>이메일</td>
                        <td><sec:authentication property="principal.email" /></td>
                    </tr>

                </table>
            </div>
        </div>
    </div>




    <div class="profile">
        <div class="list-group" style="margin-bottom: 30px">
            <a href="#" class="list-group-item list-group-item-action active" aria-current="true" style="background: #03dac6; border: #03dac6">
                완료된 스터디 조원
                <br>(후기 남기기)
            </a>
            <%--          <c:forEach var="nameList" items="${ names }">--%>
            <%--            <a href="#" class="list-group-item list-group-item-action">${ nameList.name }유세빈</a>--%>
            <%--          </c:forEach>--%>
            <a href="#" class="list-group-item list-group-item-action">유세빈</a>
            <a href="#" class="list-group-item list-group-item-action">이유진</a>

            </a>
        </div>
        <legend>점수 (5점 만점)</legend>

        <form name="myform" id="myform" method="post" action="/reviewProcess">
            <input type="hidden" name="studyGroupPartner" value=<sec:authentication property="name"/>>
            <fieldset>
                <input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label>
                <input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label>
                <input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label>
                <input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label>
                <input type="radio" name="rating" value="1" id="rate5"><label for="rate5">⭐</label>
            </fieldset>
            <div style="margin-top: 30px"></div>
            <legend>상세 내용</legend>
            <textarea type="text" name="ratingContent" class="form-control" style="height: 200px"
                      required></textarea>
            <button type="submit" class="btn btn-info" style="color: white; margin-top: 30px; width: 100%" name="cmd" value="submit">제출</button>

        </form>







    </div>
</div>

</body>
<script>


</script>
</html>
