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
            
                <h3 class="mb-3">Phương thức kiểm tra</h3>
                <div class="btn_options">
                	<div class="btn_english"><a href="${contextPath}/exam/vocabulary/english-vietnam/all">Anh - Việt</a></div>
                	<div class="btn_vietnamese"><a href="${contextPath}/exam/vocabulary/vietnam-english/all">Việt - Anh</a></div>
                </div>
               
            </section>
        </div>
    </div>
  
    <script src="${contextPath}/resources/files/js/jquery.slim.min.js"></script>
    <script src="${contextPath}/resources/files/js/popper.min.js"></script>
    <script src="${contextPath}/resources/files/js/bootstrap.min.js"></script>
    
</body>

</html>