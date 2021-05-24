<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBKBOOK</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mem_Mypage_Mj.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_Freelist_Mj.css">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/pabi/pabi.ico" type="image/x-icon"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

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
	   홈 > 관리자페이지 > 공지사항 > 공지사항수정
	   <br>
	   <hr>
	

	<form action="./boardmodify.book" method="post">
	<input type="hidden" name="no" value="${no}">
	<input type="hidden" name="pageNo" value="${pageNo}">
		<table>
		<tr>
			<th>
			제목(title):
			</th>
			</tr>
			<tr>
			<td>
			<input class="title" type="text" name="title" value="${articleData.title}"/>
			</td>
		
		<tr>
			<th>
			내용(content):<br/>
			</th>
			</tr>
			<tr>
			<td>
			<textarea name="content" class="text" rows="20" cols="80">${articleData.content}</textarea>
			</td>
			<tr>
			<td class="bottom">
		
		<input class="bottomMenu" type="submit" value="공지사항수정">
		</td>
		</tr>
		</table>
	</form>	
 </div>
	   
	</div>

	
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>