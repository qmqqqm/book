<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mem_Mypage_Mj.css">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
$(function(){
})
</script>
</head>
<body>
!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
	<div class="maincontainer">	
	<jsp:include page="//module/sideAdminMenu_Mj.jsp" flush="true"/>
	<!--메인  -->
		<div class="mypage">
		<br>
		<br>
	   홈 > 관리자페이지 > 회원관리 > 회원정보수정
	   <br>
	   <hr>
	회원정보를 성공적으로 수정했습니다
	<br/>
	<br/>
	<br/>

	<a href="${ctxPath}/admin/member.book">[회원목록보기]</a>
	<a href="${ctxPath}/admin/memberdetail.book?no=${no}&pageNo=${pageNo}">[수정된 회원 바로가기]</a>
	</div>
</body>
</html>