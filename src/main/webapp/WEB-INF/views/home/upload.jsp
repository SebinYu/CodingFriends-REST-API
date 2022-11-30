<%--
  Created by IntelliJ IDEA.
  User: sebin
  Date: 11/29/2022
  Time: 2:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/uploadMultipleFiles" method="post" enctype="multipart/form-data">
  <div>
    <input multiple="multiple" type="file" name="files"/>
  </div>

  <div>
    <button class="btn indigo waves-effect waves-light"
            type="submit" name="save">

      Submit<i class="mdi-content-send right"></i>
    </button>
  </div>
</form>
</body>
</html>
