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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_Freelist_Mj.css">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
$(function(){
})
</script>
</head>
<body>
<div class="rab">
	<!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
	<div class="maincontainer">	
	<jsp:include page="//module/sideAdminMenu_Mj.jsp" flush="true"/>
	<!--메인  -->
		<div class="mypage">
	게시글을 성공적으로 수정했습니다
	<br/>
	
	
	직원의 정보가 수정되었습니다!!<br/>
	<a href="${ctxPath}/admin/staff.book">[직원목록보기]</a>
	<a href="${ctxPath}/admin/staffdetail.book?no=${no}&pageNo=${pageNo}">[수정된 직원 바로가기]</a>
	</div>
	</div>
	
</body>
</html>