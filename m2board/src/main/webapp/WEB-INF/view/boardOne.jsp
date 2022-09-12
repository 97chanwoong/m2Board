<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Focus - Bootstrap Admin Dashboard </title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/bootdstrap/images/favicon.png">
    <link href="${pageContext.request.contextPath}/bootdstrap/vendor/pg-calendar/css/pignose.calendar.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootdstrap/vendor/chartist/css/chartist.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootdstrap/css/style.css" rel="stylesheet">

</head>

<body>
       <!--**********************************
        Main wrapper start
    ***********************************-->
    <div id="main-wrapper">

		<!-- header -->
 		<jsp:include page="./inc/header.jsp" />
       	<!-- sidebar -->
       	<jsp:include page="./inc/sidebar.jsp" />

        <!--**********************************
            Content body start
        ***********************************-->
        <div class="content-body">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-header">
                                <h1 class="card-title">전체 게시글</h1>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table student-data-table m-t-10">
                                    	<tr>
											<th>번호</th>
											<td>${map.boardNo}</td>
										</tr>
										<tr>
											<th>제목</th>
											<td>${map.boardTitle}</td>
										</tr>
										<tr>
											<th>작성자</th>
											<td>${map.memberId}</td>
										</tr>
										<tr>
											<th>작성일</th>
											<td>${map.createDate}</td>
										</tr>
										<tr>
											<th>내용</th>
											<td>${map.boardContents}</td>
										</tr>
										<tr>
											<th>조회</th>
											<td>${map.boardViews}</td>
										</tr>
										<tr>
											<th>
												<a class="btn btn-rounded btn-success" type="button" href="${pageContext.request.contextPath}/boardOneNice?boardNo=${map.boardNo}">
													좋아요
												</a>
											</th>
											<td>${map.boardNice}</td>
										</tr>
									</table>
									<div style="text-align:right;">
										<c:if test="${loginMember.memberId eq map.memberId}">
											<a type="btn" class="btn btn-primary" href="${pageContext.request.contextPath}/updateBoard?boardNo=${map.boardNo}">수정</a>
											<a type="btn" class="btn btn-warning" href="${pageContext.request.contextPath}/deleteBoard?boardNo=${map.boardNo}">삭제</a>
									    </c:if>
								    </div>		
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--**********************************
            Content body end
        ***********************************-->


       	<!-- footer -->
       	<jsp:include page="./inc/footer.jsp" />

        <!--**********************************
           Support ticket button start
        ***********************************-->

        <!--**********************************
           Support ticket button end
        ***********************************-->


    </div>
    <!--**********************************
        Main wrapper end
    ***********************************-->

    <!--**********************************
        Scripts
    ***********************************-->
    <!-- Required vendors -->
    <script src="${pageContext.request.contextPath}/bootdstrap/vendor/global/global.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootdstrap/js/quixnav-init.js"></script>
    <script src="${pageContext.request.contextPath}/bootdstrap/js/custom.min.js"></script>

    <script src="${pageContext.request.contextPath}/bootdstrap/vendor/chartist/js/chartist.min.js"></script>

    <script src="${pageContext.request.contextPath}/bootdstrap/vendor/moment/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootdstrap/vendor/pg-calendar/js/pignose.calendar.min.js"></script>


    <script src="${pageContext.request.contextPath}/bootdstrap/js/dashboard/dashboard-2.js"></script>
    <!-- Circle progress -->

</body>

</html>