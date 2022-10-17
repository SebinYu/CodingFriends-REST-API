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
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
	<link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css"
		  rel="stylesheet">
	<link href="${path}/resources/static/css/list.css"
		  rel="stylesheet">
	<title>Coding Study Friends</title>
	<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/template/header.jsp"></jsp:include>

<div class="mb-3">
	<label for="exampleFormControlInput1" class="form-label">Email address</label>
	<input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
</div>
<div class="mb-3">
	<label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
	<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
</div>


<jsp:include page="/template/footer.jsp"></jsp:include>
</body>
</html>
