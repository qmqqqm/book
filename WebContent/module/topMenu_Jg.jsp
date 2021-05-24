<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<nav>
	<div class="rab topmenurab">
		<div class="container topmenu">
			<c:if test="${!empty AUTHUSER}">
			 
			
		    	<ul>
		    		<li class="menutop"><label class="usertexttop">${AUTHUSER.id}님 접속중 </label></li>
			        <li class="menutop"><a href="<%=request.getContextPath()%>/member/logout.book"><label class="menutexttop">로그아웃</label></a></li>
			        <li class="menutop"><a  href="<%=request.getContextPath()%>/member/mypage.book"><label class="menutexttop">마이페이지</label></a></li>
			        <li class="menutop"><a  href="<%=request.getContextPath()%>/order/basket.book"><label class="menutexttop">장바구니</label></a></li>
			        <li class="menutop"><a  href="<%=request.getContextPath()%>/board/freeboardlist.book"><label class="menutexttop">게시판</label></a></li>		
			    </ul>
		
		    </c:if>
		    
		    <c:if test="${!empty ADMIN}">
			
		    	<ul>
		    		<li class="menutop"><label class="usertexttop">${ADMIN.id}님 접속중 </label></li>
			        <li class="menutop"><a href="<%=request.getContextPath()%>/admin/logout.book"><label class="menutexttop">로그아웃</label></a></li>
			        <li class="menutop"><a href="<%=request.getContextPath()%>/admin/staff.book"><label class="menutexttop">관리페이지</label></a></li>
			        <li class="menutop"><a  href="<%=request.getContextPath()%>/board/freeboardlist.book"><label class="menutexttop">고객센터</label></a></li>		
			    </ul>
		
		    </c:if>
		    
		    <c:if test="${empty AUTHUSER&&empty ADMIN}">
			    <ul>			        
			        <li class="menutop"><a href="<%=request.getContextPath()%>/member/login.book"><label class="menutexttop">로그인</label></a></li>
					<%-- <li class="menutop"><a href="<%=request.getContextPath()%>/admin/admin.book"><label class="menutexttop">관리자로그인</label></a></li> --%>
			        <li class="menutop"><a  href="<%=request.getContextPath()%>/member/join.book"><label class="menutexttop">회원가입</label></a></li>
			  <%--       <li class="menutop"><a  href="<%=request.getContextPath()%>/order/basket.book"><label class="menutexttop">장바구니</label></a></li> --%>
			        <li class="menutop"><a  href="<%=request.getContextPath()%>/board/freeboardlist.book"><label class="menutexttop">고객센터</label></a></li>		
			    </ul>
		    </c:if>
		</div>
	</div>
</nav>