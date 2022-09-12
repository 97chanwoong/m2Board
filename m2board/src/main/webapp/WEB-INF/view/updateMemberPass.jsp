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
                                <h1 class="card-title">${loginMember.memberName}님 비밀번호 변경</h1>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <form id="updateMemberPassForm" action="<%=request.getContextPath()%>/updateMemberPass" method="post">
	                                    <table class="table student-data-table m-t-10">
	                                             <tr>
	                                                <th>현재 비밀번호</th>
	                                                <td>
	                                                	<input type="hidden" class="form-control" name="memberId" value="${loginMember.memberId}">
	                                                	<input type="password" class="form-control" name="memberPw" id="memberPw">
	                                                </td>
	                                            </tr>
												<tr>
	                                                <th>새로운 비밀번호</th>
	                                                <td>
	                                                	 <input type="password" class="form-control" name="memberNewPw" id="memberNewPw">
	                                                </td>
	                                            </tr>
	                                            <tr>
	                                                <th>새로운 비밀번호 확인</th>
	                                                <td>
	                                                	 <input type="password" class="form-control" name="memberNewPwCk" id="memberNewPwCk">
	                                                </td>
	                                            </tr>
	                                    </table>
										<br>
										<!-- 버튼 -->
										<div style="text-align:right;">
											<button id="updatePassBtn" class="btn btn-primary" type="button">정보수정</button>
											<button class="btn btn-danger" type="reset">취소</button>
											<a class="btn btn-secondary" type="button" href="${pageContext.request.contextPath}/index">이전</a>
										</div>
	                            	</form>        
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
		// 회원 빈칸검사
		$('#updatePassBtn').click(function() {
			if ($('#memberPw').val() == '') {
				alert('현재 비밀번호를 입력하세요');
			} else if ($('#memberNewPw').val() == '') {
				alert('변경할 비밀번호를 입력하세요');
			} else if($('#memberNewPwCk').val() == ''){
				alert('변경할 비밀번호를 재입력하세요');
			}	else {
				updateMemberPassForm.submit();
			}
		});
		$('#memberNewPw').blur(function(){
			if( $('#memberPw').val() == $('#memberNewPw').val() ) {
				$('#memberNewPw').val(''); 
				alert('변경할 비밀번호가 현재 비밀번호와 같습니다.');
				$('#memberNewPw').focus();
			}
		});
		
		$('#memberNewPwCk').blur(function(){
			if( $('#memberNewPw').val() != $('#memberNewPwCk').val() ) {
				$('#memberNewPw').val(''); 
				$('#memberNewPwCk').val(''); 
				alert('변경할 비밀번호가 다릅니다');
				$('#memberNewPwCk').focus();
			}
		});
	</script>
</body>

</html>