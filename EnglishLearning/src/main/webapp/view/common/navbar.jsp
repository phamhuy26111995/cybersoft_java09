<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
        <!-- SIDE BAR -->
        <div id="side-bar">
            <div class="logo">ENGLISH ELEARNING</div>
            <ul class="list-group rounded-0">
                <li class="dashboard">MENU</li>
                <li>
                    <a href="${pageContext.request.contextPath}/category">
                        <i class="fa fa-user mr-2"></i> Thể loại
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/category">
                        <i class="fa fa-book mr-2"></i> Từ Vựng
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/exam">
                        <i class="fa fa-cogs mr-2"></i> Ôn tập theo chủ đề
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/exam/vocabulary/option/all">
                        <i class="fa fa-slack mr-2"></i> Ôn tập tất cả
                    </a>
                </li>
            </ul>
        </div>