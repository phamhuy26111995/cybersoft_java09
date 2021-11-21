<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>

<!-- Tạo biến contextPath để chứa tên file project -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="id" value="${dto.id}" />

<head>
    <meta charset="UTF-8">
    <title>Thêm mới Category</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/files/css/bootstrap.min.css">
    <link rel="stylesheet" href='${contextPath}/resources/files/css/style.css'>
</head>


<body>

    <div class="d-flex justify-content-between">
        <!-- SIDE BAR -->
         <jsp:include page="../common/navbar.jsp"></jsp:include>
           <!-- ----- -->

        <div id="admin-wrapper">
            <!-- HEADER -->
            <jsp:include page="../common/header.jsp"></jsp:include>

            <!-- CONTENT -->
            <section id="admin-content" class="p-3">
                <h3 class="mb-4 text-center">Edit thể loại</h3>
                <form method="post" action="${contextPath}/category/edit/?id=${id}">
                    <div class="row">
                        <div class="col-md-6 m-auto">
                            <div class="form-group">
                                <label>Tên thể loại</label>
                                <input type="text" name="name" class="form-control" placeholder="name" />
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success">Lưu lại</button>
                                <a class="btn btn-secondary" href="${contextPath}/category">Quay lại</a>
                            </div>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
      <script src="${contextPath}/resources/files/js/jquery.slim.min.js"></script>
    <script src="${contextPath}/resources/files/js/popper.min.js"></script>
    <script src="${contextPath}/resources/files/js/bootstrap.min.js"></script>
</body>

</html>