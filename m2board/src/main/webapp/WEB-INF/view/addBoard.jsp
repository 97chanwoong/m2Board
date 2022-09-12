<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="kr">

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
                                <h1 class="card-title">게시글 작성</h1>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                   <form id="addBoardForm" action="<%=request.getContextPath()%>/addBoard" method="post">
										<table class="table student-data-table m-t-10">
											<tr>
												<th>제목</th>
												<td><input type="text" id="boardTitle" name="boardTitle"></td>
											</tr>
											<tr>
												<th>작성자</th>
												<td><input type="text" name="memberId"
													value="${loginMember.memberId}" readonly="readonly">
												</td>
											</tr>
											<tr>
												<th>내용</th>
												<td><textarea rows="5" cols="100" id="boardContents" name="boardContents"></textarea>
												</td>
											</tr>
										</table>
										<div style="text-align:right;">
										<button id="addBoardBtn" class="btn btn-primary" type="button">작성</button>
                                    	</div>
                                    </form>
                                     <div>
                                     <ul class="pagination">
								      <c:if test="${currentPage > 1}">
								        <li class="page-item" > <a class="page-link" href="${pageContext.request.contextPath}/boardList?currentPage=${currentPage-1}">이전</a></li>
								      </c:if>
								      <c:if test="${currentPage < lastPage}">
								       <li class="page-item">  <a class="page-link" href="${pageContext.request.contextPath}/boardList?currentPage=${currentPage+1}">다음</a></li>
								      </c:if>
								      </ul>
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
	<script>
		// 고객 빈칸검사
		$('#addBoardBtn').click(function(){		
			if($('#boardTitle').val() == ''){
				alert('게시글 제목을 입력하세요.');
			} else if($('#boardContents').val() == ''){
				alert('게시글 내용을 입력하세요.');
			} else {
				$('#addBoardForm').submit();
			}
		});
	</script>
</body>

</html>