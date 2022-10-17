
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<%@include file="../include/head.jsp"%>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!-- Navbar -->
  <jsp:include page="../include/main_header.jsp"></jsp:include>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <jsp:include page="../include/left_column.jsp"></jsp:include>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">Starter Page</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Starter Page</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="content">
      <div class="container-fluid">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header">
              <h3 class="card-title">게시글 목록</h3>
            </div>
            <div class="card-body">
              <table class="table table-bordered">
                <tbody>
                <tr>
                  <th style="width: 30px">#</th>
                  <th>제목</th>
                  <th style="width: 100px">작성자</th>
                  <th style="width: 150px">작성시간</th>
                  <th style="width: 60px">조회</th>
                </tr>
                <c:forEach items="${StudyGroups}" var="article">
                  <tr>
                    <td>${StudyGroups.studyGroup_id}</td>
                    <td><a href="${path}/article/read?article_no=${StudyGroup.studyGroup_id}">${StudyGroup.title}</a></td>
                    <td>${StudyGroup.writer}</td>
                    <td><fmt:formatDate value="${StudyGroup.regDate}" pattern="yyyy-MM-dd a HH:mm"/></td>
                    <td>총 인원: ${StudyGroup.totalNum}</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
            <div class="card-footer">
              <div class="float-right">
                <button type="button" class="btn btn-success btn-flat" id="writeBtn">
                  <i class="fa fa-pencil"></i> 글쓰기
                </button>
              </div>
            </div>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
    <div class="p-3">
      <h5>Title</h5>
      <p>Sidebar content</p>
    </div>
  </aside>
  <!-- /.control-sidebar -->

  <!-- Main Footer -->
  <jsp:include page="../include/main_footer.jsp"></jsp:include>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<jsp:include page="../include/plugin_js.jsp"></jsp:include>

</body>
</html>
