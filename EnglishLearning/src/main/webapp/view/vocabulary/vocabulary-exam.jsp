<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html lang="en">
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<head>
	
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Awesome Quiz App | CodingNepal</title>
    <link rel="stylesheet" href="${contextPath}/resources/files/vendor/css/style.css">
    <!-- FontAweome CDN Link for Icons-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    
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
                <h3 class="mb-4 text-center">Thêm mới thể loại</h3>
                  <!-- start Quiz button -->
    <div class="start_btn"><button>Start Quiz</button></div>

    <!-- Info Box -->
    <div class="info_box">
        <div class="info-title"><span>Some Rules of this Quiz</span></div>
        <div class="info-list">
            <div class="info">1. You will have only <span>15 seconds</span> per each question.</div>
            <div class="info">2. Once you select your answer, it can't be undone.</div>
            <div class="info">3. You can't select any option once time goes off.</div>
            <div class="info">4. You can't exit from the Quiz while you're playing.</div>
            <div class="info">5. You'll get points on the basis of your correct answers.</div>
        </div>
        <div class="buttons">
            <button class="quit">Exit Quiz</button>
            <button class="restart">Continue</button>
        </div>
    </div>

    <!-- Quiz Box -->
    <div class="quiz_box">
        <header>
            <div class="title">Awesome Quiz Application</div>
            <div class="timer">
                <div class="time_left_txt">Time Left</div>
                <div class="timer_sec">15</div>
            </div>
            <div class="time_line"></div>
        </header>
        <section>
            <div class="que_text">
                <!-- Here I've inserted question from JavaScript -->
            </div>
            <div class="option_list">
                <!-- Here I've inserted options from JavaScript -->
            </div>
        </section>

        <!-- footer of Quiz Box -->
        <footer>
            <div class="total_que">
                <!-- Here I've inserted Question Count Number from JavaScript -->
            </div>
            <button class="next_btn">Next Que</button>
        </footer>
    </div>

    <!-- Result Box -->
    <div class="result_box">
        <div class="icon">
            <i class="fas fa-crown"></i>
        </div>
        <div class="complete_text">You've completed the Quiz!</div>
        <div class="score_text">
            <!-- Here I've inserted Score Result from JavaScript -->
        </div>
        <div class="buttons">
            <button class="restart">Replay Quiz</button>
            <button class="quit">Quit Quiz</button>
        </div>
    </div>

            </section>
        </div>
    </div>

	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
 	<script src="${contextPath}/resources/files/js/jquery.slim.min.js"></script>
    <script src="${contextPath}/resources/files/js/popper.min.js"></script>
    <script src="${contextPath}/resources/files/js/bootstrap.min.js"></script>
    <!-- Inside this JavaScript file I've inserted Questions and Options only -->
    <script src="${contextPath}/resources/files/vendor/js/questions.js"></script>

    <!-- Inside this JavaScript file I've coded all Quiz Codes -->
      <script src="${contextPath}/resources/files/vendor/js/script.js"></script>

</body>
</html>