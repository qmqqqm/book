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
	   홈 > 관리자페이지 > 회원관리 > 상세보기
	   <br>
	   <hr>
	
	<table >
		<caption>회원 정보 상세보기(detail)</caption>
		<tbody>
			<tr>
				<th width="120">번호</th>
				<td width="480">${articleData.article.mem_no}</td>
			</tr>
			<tr>
				<th width="120">회원id</th>
				<td width="480">${articleData.article.mem_Id}</td>
			</tr>
			
			<tr>
				<th width="120">패스워드</th>
				<td width="480">${articleData.article.mem_Pwd}</td>
			</tr>
			
			<tr>
				<th width="120">이름</th>
				<td width="480">${articleData.article.mem_Name}</td>
			</tr>
			<tr>
				<th width="120">생년월일</th>
				<td width="480">${articleData.article.mem_Birth}</td>
			</tr>
			<tr>
				<th width="120">성별</th>
				<td width="480">${articleData.article.mem_Gender}</td>
			</tr>
			<tr>
				<th width="120">핸드폰번호</th>
				<td width="480">${articleData.article.mem_hp}</td>
			</tr>
			<tr>
				<th width="120">포인트</th>
				<td width="480">${articleData.article.mem_Point}</td>
			</tr>
			<tr>
				<th width="120">회원상태</th>
				<td width="480">${articleData.article.mem_Grade}</td>
			</tr>
			
		</tbody>
	</table>
	<table >
		<tbody>
			<tr>
				<td class="bottom">
				
				<c:set var="nnn" value="${empty param.pageNo? '1':param.pageNo}"/>
				<a class="bottomMenu" href="../admin/member.book?pageNo=${nnn}">목록보기</a>
				<a class="bottomMenu" href="../admin/membermodify.book?no=${articleData.article.mem_no}&pageNo=${nnn}">회원정보수정</a>
				<a class="bottomMenu" href="../admin/memberdelete.book?no=${articleData.article.mem_no}">회원정보삭제</a>
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