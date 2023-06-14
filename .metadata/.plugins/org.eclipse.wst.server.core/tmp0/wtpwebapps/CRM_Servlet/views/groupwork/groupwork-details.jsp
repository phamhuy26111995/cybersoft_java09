<%@page import="java.awt.event.ItemEvent"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.List"%>
<%@ page import="cybersoft.java09.entity.Job"%>
<%@ page import="cybersoft.java09.dto.JobDto"%>




<!DOCTYPE html>
<html lang="en">

<head>

<% String contextPath = request.getContextPath() ;%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16"
	href="<%=contextPath %>/static/plugins/images/favicon.png">
<title>Pixel Admin</title>
<!-- Bootstrap Core CSS -->
<link
	href="<%=contextPath %>/static/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Menu CSS -->
<link
	href="<%=contextPath %>/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"
	rel="stylesheet">
<!-- animation CSS -->
<link href="<%=contextPath %>/static/css/animate.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="<%=contextPath %>/static/css/style.css" rel="stylesheet">
<!-- color CSS -->
<link href="<%=contextPath %>/static/css/colors/blue-dark.css"
	id="theme" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<!-- Preloader -->
	<div class="preloader">
		<div class="cssload-speeding-wheel"></div>
	</div>

	<jsp:include page="../layout/navbar.jsp"></jsp:include>


	<!-- Page Content -->

	<% List<JobDto> jobDtos = (List<JobDto>)request.getAttribute("jobDtos");
  	%>
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row bg-title">
				<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
					<h4 class="page-title">Chi tiết công việc</h4>
				</div>
				<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
					<ol class="breadcrumb">
						<li><a href="#">Dashboard</a></li>
						<li class="active">Blank Page</li>
					</ol>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			

			<!-- BEGIN DANH SÁCH CÔNG VIỆC -->
			
			<c:forEach items="<%= jobDtos %>" var="item">
			
			<div class="row">
				<div class="col-xs-12">
					<a href="#" class="group-title"> <img width="30"
						src="<%=contextPath %>/static/plugins/images/users/pawandeep.jpg" class="img-circle" /> <span>${item.user.getFullName()}</span>
					</a>
				</div>
				<div class="col-md-4">
					<div class="white-box">
						<h3 class="box-title">Chưa thực hiện</h3>
						<div class="message-center">

							<c:forEach items="${item.getTaskNotDone() }" var="item">


								<a href="#">
									<div class="mail-contnet">
										<h5>${item.name }</h5>
										<span class="mail-desc"></span> <span class="time">Bắt
											đầu: ${item.startDateToString()}</span> <span class="time">Kết
											thúc: ${item.endDateToString()}</span>
									</div>
								</a>

							</c:forEach>


						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="white-box">
						<h3 class="box-title">Đang thực hiện</h3>
						<div class="message-center">
							<c:forEach items="${item.getTaskPending() }" var="item">


								<a href="#">
									<div class="mail-contnet">
										<h5>${item.name }</h5>
										<span class="mail-desc"></span> <span class="time">Bắt
											đầu: ${item.startDateToString()}</span> <span class="time">Kết
											thúc: ${item.endDateToString()}</span>
									</div>
								</a>

							</c:forEach>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="white-box">
						<h3 class="box-title">Đã hoàn thành</h3>
						<div class="message-center">
							<c:forEach items="${item.getTaskDone() }" var="item">


								<a href="#">
									<div class="mail-contnet">
										<h5>${item.name }</h5>
										<span class="mail-desc"></span> <span class="time">Bắt
											đầu: ${item.startDateToString()}</span> <span class="time">Kết
											thúc: ${item.endDateToString()}</span>
									</div>
								</a>

							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			
			</c:forEach>
			

			<!-- END DANH SÁCH CÔNG VIỆC -->
		</div>
		<!-- /.container-fluid -->
		<footer class="footer text-center"> 2018 &copy; myclass.com </footer>
	</div>
	<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	<!-- jQuery -->
	<script
		src="<%=contextPath %>/static/plugins/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=contextPath %>/static/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Menu Plugin JavaScript -->
	<script
		src="<%=contextPath %>/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
	<!--slimscroll JavaScript -->
	<script src="js/jquery.slimscroll.js"></script>
	<!--Wave Effects -->
	<script src="<%=contextPath %>/static/js/waves.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="<%=contextPath %>/static/js/custom.min.js"></script>
</body>

</html>