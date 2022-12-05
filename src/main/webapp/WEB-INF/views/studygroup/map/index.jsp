<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

  <style>
    select{
      width: 1000px; /* 원하는 너비설정 */
      padding: .3em .10em; /* 여백으로 높이 설정 */
      border: 1px solid #999;
      border-radius: 10px; /* iOS 둥근모서리 제거 */
      margin-right: 10px;
    }
    .pac-container{
      z-index: 1500 !important;
    }

    html,
    body,
    #google-map {
      width: 100%;
      height: 100%;
      margin: 0;
      padding: 0
    }

    #search-panel {
      position: absolute;
      top: 10px;
      left: 25%;
      z-index: 5;
      background-color: #FFFFFF;
      padding: 5px;
      border: 1px solid black;
      text-align: center;
      padding: left: 10px
    }
  </style>
</head>
<body>
<jsp:include page="/template/header.jsp"></jsp:include>

<%--배너 이미지 및 버튼--%>
<div class="banner" style="	height: 50vh;
	width: 100vw;
	text-align: center;

	/* background:black; */
	background-image: url('../../imgs/bannerImg.jpg');
	background-repeat: no-repeat;
	background-size: cover;">
  <br><br><br><br><br><br><br><br><br><br><br>
  <sec:authorize access="not authenticated">
    <a href="/login" class="btn btn-info" style="margin-top: 115px; color: white;" >
      <스터디 만들기><br>로그인 하러가기
    </a>
  </sec:authorize>
  <sec:authorize access="authenticated">
    <a href="create" class="btn btn-info" style="margin-top: 115px; color: white;">스터디 만들기</a>
  </sec:authorize>
</div>


<%--스터디 종류 리스트--%>
<div class="container"  >
  <form method="get" style=" float: left; width: 430px" action = "/studygroup/search" class="form-inline mt-1">
    <select class="dropdown mx-1 mt-2" name="learningMaterial_id"
            style="width: 120px">
      <option value="" disabled selected>== 종류 ==</option>
      <c:forEach var="d" items="${ learningMaterials }">
        <option value="${ d.learningMaterial_id }"
          ${ learningMaterial.learningMaterial_id == d.learningMaterial_id ? "selected" : "" }>
            ${ d.materialType }</option>
      </c:forEach>
    </select>
    <input type="text" name="keyword" class="form-control mx-1 mt-2" style="width: 200px;" placeholder="내용을 입력하세요"/>
    <button type="submit" class="btn btn-primary btn-default mx-1 mt-2">검색</button>
  </form>
  <a href="/studygroup/map/index" class="btn btn-secondary"  style="float: left; margin-top: 12px; margin-right: 7px">지역별 오프라인 스터디</a>
  <a href="/studygroup/sequence/startDate" class="btn btn-secondary" style="float: left; margin-top: 12px; margin-right: 7px">스터디 시작일 최신순</a>
  <a href="/studygroup/sequence/updateDate" class="btn btn-secondary" style="float: left; margin-top: 12px">작성일 최신순</a>
  <div style="clear: both"></div>
</div>
<hr>
<h1 style="text-align:center; margin-bottom: 20px; font-weight: bold" >[거리순 검색] : 오프라인 스터디</h1>
<div id="map" style="width:30%; height:30%; float: left; margin-left: 40px"></div>
<div style="width:5%; height:100%;float: left"></div>

<input type="text" class="form-control mx-1 mt-2" id="address" value="" placeholder="주소를 입력해주세요." style="width: 400px; margin-left: 20px; float: left">
<input type="button" class="btn btn-primary btn-default mx-1 mt-2" value="가까운 스터디 검색" onclick="addressChk()">
<div id="coordXY"></div>

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a47714ab440386647ecccccabec88a49&libraries=services"></script>
<script>
  var address      = document.getElementById("address");
  var mapContainer = document.getElementById("map");
  var coordXY   = document.getElementById("coordXY");
  var mapOption;
  var map;
  var x,y          = "";

  if (address.value=="") {

    mapOption = {
      center: new daum.maps.LatLng(33.450701, 126.570667), // 임의의 지도 중심좌표 , 제주도 다음본사로 잡아봤다.
      level: 4            // 지도의 확대 레벨

    };
  }

  // 지도 생성
  map = new daum.maps.Map(mapContainer, mapOption);


  function addressChk() {
    var gap = address.value; // 주소검색어
    if (gap=="") {
      alert("주소 검색어를 입력해 주십시오.");
      address.focus();
      return;
    }

    // 주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();



    // 주소로 좌표를 검색
    geocoder.addressSearch(gap, function(result, status) {

      // 정상적으로 검색이 완료됐으면,
      if (status == daum.maps.services.Status.OK) {

        var coords = new daum.maps.LatLng(result[0].y, result[0].x);

        y = result[0].x;
        x = result[0].y;



        // 결과값으로 받은 위치를 마커로 표시합니다.
        var marker = new daum.maps.Marker({
          map: map,
          position: coords
        });



        // 인포윈도우로 장소에 대한 설명표시
        var infowindow = new daum.maps.InfoWindow({
          content: '<div style="width:150px;text-align:center;padding:5px 0;">좌표위치</div>'
        });

        infowindow.open(map,marker);

        // 지도 중심을 이동
        map.setCenter(coords);

        coordXY.innerHTML = "<br>X좌표 : " + x + "<br><br>Y좌표 : " + y;
      }
    });
  }



</script>

<%--스터디 리스트 조회--%>
<div class="list" style="margin-left: 85px">

  <c:forEach var="studygroup" items="${ studygroups }">
    <div class="" style="display: inline-block; margin: 20px;">
      <div class="card" style="width: 18rem;">
        <div class="card-body">
          <h5 class="card-title" style="color: black">
            <a href="detail?studyGroup_id=${ studygroup.studyGroup_id }"
               style="color: rgba(0,0,0,0.57);text-decoration: none; font-weight: bold;">${ studygroup.title }</a>
          </h5>
          <p class="card-text">
            BY. ${ studygroup.writer }<br>
              <%--                        정원. ${ studygroup.currentNum }/${ studygroup.totalNum }--%>
            스터디 시작일. ${ studygroup.startDate }<br>
            작성일. ${ studygroup.updateDate }
          </p>
        </div>
      </div>
    </div>
  </c:forEach>
</div>

<jsp:include page="/template/footer.jsp"></jsp:include>
</body>
<script>

</script>
</html>


