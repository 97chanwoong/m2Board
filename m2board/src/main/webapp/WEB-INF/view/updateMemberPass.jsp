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

        <!--**********************************
            Nav header start
        ***********************************-->
        <div class="nav-header">
             <a href="${pageContext.request.contextPath}/index" class="brand-logo" style="font-size:25px;">
            	<span>Chanwoong Cafe</span>
            </a>

            <div class="nav-control">
                <div class="hamburger">
                    <span class="line"></span><span class="line"></span><span class="line"></span>
                </div>
            </div>
        </div>
        <!--**********************************
            Nav header end
        ***********************************-->

        <!--**********************************
            Header start
        ***********************************-->
        <div class="header">
            <div class="header-content">
                <nav class="navbar navbar-expand">
                    <div class="collapse navbar-collapse justify-content-between">
                        <div class="header-left">
                            <div class="search_bar dropdown">
                                <span class="search_icon p-3 c-pointer" data-toggle="dropdown">
                                    <i class="mdi mdi-magnify"></i>
                                </span>
                                <div class="dropdown-menu p-0 m-0">
                                    <form>
                                        <input class="form-control" type="search" placeholder="Search" aria-label="Search">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <ul class="navbar-nav header-right">
                            <li class="nav-item dropdown header-profile">
                                <a class="nav-link" href="#" role="button" data-toggle="dropdown">
                                    <i class="mdi mdi-account"></i>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a href="${pageContext.request.contextPath}/index" class="dropdown-item">
                                        <i class="icon-user"></i>
                                        <span class="ml-2">Profile </span>
                                    </a>
                                    <a href="${pageContext.request.contextPath}/logout" class="dropdown-item">
                                        <i class="icon-key"></i>
                                        <span class="ml-2">Logout </span>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <!--**********************************
            Header end ti-comment-alt
        ***********************************-->

        <!--**********************************
            Sidebar start
        ***********************************-->
         <div class="quixnav">
            <div class="quixnav-scroll">
                <ul class="metismenu" id="menu">
                    <li class="nav-label first">My Menu</li>
                    <!-- <li><a href="index.html"><i class="icon icon-single-04"></i><span class="nav-text">Dashboard</span></a>
                    </li> -->
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-single-04"></i><span class="nav-text">Profile</span></a>
                        <ul aria-expanded="false">
                            <li><a href="${pageContext.request.contextPath}/updateMember">내정보 수정</a></li>
                            <li><a href="${pageContext.request.contextPath}/updateMemberPass">비밀번호 변경</a></li>
                            <li><a href="${pageContext.request.contextPath}/deleteMember">회원 탈퇴</a></li>
                        </ul>
                    </li>

                    <li class="nav-label">Board</li>
                    <li><a class="has-arrow" href="javascript:void()" aria-expanded="false"><i
                                class="icon icon-app-store"></i><span class="nav-text">게시판</span></a>
                        <ul aria-expanded="false">
                            <li><a href="${pageContext.request.contextPath}/boardList">전체 게시글</a></li>
                            <li><a href="${pageContext.request.contextPath}/boardListMember">내가 작성한 게시글</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <!--**********************************
            Sidebar end
        ***********************************-->

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


        <!--**********************************
            Footer start
        ***********************************-->
        <div class="footer">
            <div class="copyright">
                <p>Copyright © Designed &amp; Developed by <a href="#" target="_blank">Quixkit</a> 2019</p>
                <p>Distributed by <a href="https://themewagon.com/" target="_blank">Themewagon</a></p>
            </div>
        </div>
        <!--**********************************
            Footer end
        ***********************************-->

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