<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<!-- Tạo biến contextPath để chứa tên file project -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<head>
    <meta charset="UTF-8">
    <title>Vocabulary</title>
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
         <!-- ---------->
        
        <div id="admin-wrapper">
            <!-- HEADER -->
             <jsp:include page="../common/header.jsp"></jsp:include>
			
            <!-- CONTENT -->
            <section id="admin-content" class="p-3">
                <h3 class="mb-3">Danh sách Thể Loại</h3>
                <div class="row">
                    <div class="col-md-8">
                        <a href="${contextPath}/category/add" class="btn btn-primary">Thêm mới</a>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Tìm kiếm...">
                            <div class="input-group-append">
                                <span class="input-group-text" id="basic-addon2"><i class="fa fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
                <table class="table table-bordered table-hover mt-3">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Tên Thể Loại</th>
                     
                        </tr>
                    </thead>
                    <tbody>
                     <c:forEach var="item" items="${dtos}">
                     
                     	  <tr>
                            <td>${item.id}</td>
                            <td><a href="${contextPath}/exam/vocabulary/option?cate=${item.id}">${item.name}</a></td>
                        </tr>
                        <tr>

                     </c:forEach> 	
                        
                    </tbody>
                </table>
            </section>
        </div>
    </div>
  
    <script src="${contextPath}/resources/files/js/jquery.slim.min.js"></script>
    <script src="${contextPath}/resources/files/js/popper.min.js"></script>
    <script src="${contextPath}/resources/files/js/bootstrap.min.js"></script>
    
</body>

</html>