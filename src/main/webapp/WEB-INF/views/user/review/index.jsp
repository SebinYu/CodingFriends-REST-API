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
    <div class="profile" style="width:28%;">
        <div class="container-p">
            <div class="box">
                <h2 style="text-align: center; margin-bottom: 20px">스터디 정보</h2>
                <table>
                    <tr>
                        <td>스터디명</td>
                        <td><sec:authentication property="name"/></td>
                    </tr>
                    <tr>
                        <td>기간</td>
                        <td><sec:authentication property="principal.name" /></td>
                    </tr>
                    <tr>
                        <td>조직장</td>
                        <td><sec:authentication property="principal.email" /></td>
                    </tr>

                </table>
            </div>
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




    <div class="profile" style="margin-top: -250px">
        <div class="list-group" style="margin-bottom: 30px;">
            <a href="#" class="list-group-item list-group-item-action active" aria-current="true" style="background: #03dac6; border: #03dac6">
                조원 후기 남기기
            </a>
            <c:forEach var="exCompanyName" items="${exCompanyNames}">
                <a href="detail?StudygroupTitle=${StudygroupTitle}&&chosenName=${exCompanyName}" class="list-group-item list-group-item-action">${exCompanyName}</a>
              </c:forEach>
            </a>
        </div>
    </div>
</div>

</body>
<script>

</script>
</html>
