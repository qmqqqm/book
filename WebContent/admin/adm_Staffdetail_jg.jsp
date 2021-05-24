<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
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
		<br>
		<br>
	   홈 > 관리자페이지 > 직원관리 > 상세보기
	   <br>
	   <hr>
	
	<table >
		<caption>직원정보 상세보기(detail)</caption>
		<tbody>
			<tr>
				<th width="120">번호</th>
				<td width="480">${articleData.article.number}</td>
			</tr>
			<tr>
				<th width="120">작성자id</th>
				<td width="480">${articleData.article.id}</td>
			</tr>
			
			<tr>
				<th width="120">패스워드</th>
				<td width="480">${articleData.article.pwd}</td>
			</tr>
			
			<tr>
				<th width="120">핸드폰번호</th>
				<td width="480">${articleData.article.hp}</td>
			</tr>
			
		</tbody>
	</table>
	<table >
		<tbody>
			<tr>
				<td class="bottom"  ;">
				
				<c:set var="nnn" value="${empty param.pageNo? '1':param.pageNo}"/>
				<a class="bottomMenu" href="../admin/staff.book?pageNo=${nnn}">목록보기</a>
				<a class="bottomMenu" href="../admin/staffmodify.book?no=${articleData.article.number}&pageNo=${nnn}">직원정보수정</a>
				<a class="bottomMenu" href="../admin/staffdelete.book?no=${articleData.article.number}">직원정보삭제</a>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
</div>
</div>
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</body>
</html>