<%--
  Created by IntelliJ IDEA.
  User: sebin
  Date: 12/1/2022
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="continer" style="padding: 20px">
    <h1>밑바닥부터 시작하는 딥러닝</h1>
    별점 : ⭐⭐⭐⭐⭐<br><br>
    <textarea style="width: 100%; height: 100px">참여 참여</textarea>
    <button id="objection" style="margin-top: 10px; margin-bottom: 10px">이의신청</button>
    <hr>
 </div>


</body>
<script>    // 'request'라는 id를 가진 버튼 클릭 시 실행.
$('#objection').click(function () {
    alert("이의신청은 1년에 3번까지만 가능합니다.");
});
</script>
</html>
