
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
          <form role="form" id="writeForm" method="post" action="${path}/article/modify">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">게시글 작성</h3>
              </div>
              <div class="card-body">
                <input type="hidden" name="article_no" value="${StudyGroup.studyGroup_id}">
                <div class="form-group">
                  <label for="title">제목</label>
                  <input class="form-control" id="title" name="title" placeholder="제목을 입력해주세요" value="${StudyGroup.title}">
                </div>
                <div class="form-group">
                  <label for="content">내용</label>
                  <textarea class="form-control" id="content" name="content" rows="30"
                            placeholder="내용을 입력해주세요" style="resize: none;">${StudyGroup.content}</textarea>
                </div>
                <div class="form-group">
                  <label for="writer">작성자</label>
                  <input class="form-control" id="writer" name="writer" value="${StudyGroup.writer}" readonly>
                </div>
              </div>
              <div class="card-footer">
                <button type="button" class="btn btn-primary"><i class="fa fa-list"></i> 목록</button>
                <div class="float-right">
                  <button type="button" class="btn btn-warning cancelBtn"><i class="fa fa-trash"></i> 취소</button>
                  <button type="submit" class="btn btn-success modBtn"><i class="fa fa-save"></i> 수정 저장</button>
                </div>
              </div>
            </div>
          </form>
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
