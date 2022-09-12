<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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