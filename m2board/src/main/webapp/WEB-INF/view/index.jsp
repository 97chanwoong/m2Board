<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="kr">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>M2Board ParkChanWoong Portfolio</title>
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
                                <h1 class="card-title">${loginMember.memberName}님 정보</h1>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table student-data-table m-t-10">
                                            <tr>
                                                <th>아이디</th>
                                                <td>${loginMember.memberId}</td>
                                            </tr>
                                             <tr>
                                                <th>이름</th>
                                                <td>${loginMember.memberName}</td>
                                            </tr>
                                            <tr>
                                                <th>주소</th>
                                                <td>${loginMember.memberAddr} / ${loginMember.memberDetailAddr}</td>
                                            </tr>
                                            <tr>
                                                <th>주소</th>
                                                <td>${loginMember.memberPhone}</td>
                                            </tr>
                                    </table>
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