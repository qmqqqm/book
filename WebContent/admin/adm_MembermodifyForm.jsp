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
	   홈 > 관리자페이지 > 회원관리 > 회원정보수정
	   <br>
	   <hr>
			
	<form action="./membermodify.book" method="post">
	<input type="hidden" name="no" value="${no}">
	<input type="hidden" name="pageNo" value="${pageNo}">
		<table>
			<tr>
				<th>
					아이디(id):
				</th>
				<td>
					<input type="text" name="id" value="${articleData.article.id}"/>
				</td>
			</tr>
			<tr>
				<th>
					패스워드(pwd):
				</th>
				<td>	
					<input type="password" name="pwd" value="${articleData.article.pwd}"/>
				</td>
			</tr>
			<tr>
				<th>
					이름(name):
				</th>
				<td>	
					<input type="text" name="name" value="${articleData.article.hp}"/>
				</td>
			</tr>
			<tr>
				<th>
					성별(gender):
				</th>
				<td>
					<input type="text" name="gender" value="${articleData.article.hp}"/>
				</td>
			</tr>
			<tr>
				<th>
					핸드폰번호(hp):
				</th>
				<td>
					<input type="text" name="hp" value="${articleData.article.hp}"/>
				</td>
			</tr>
			<tr>
				<th>
					포인트(point):
				</th>
				<td>
					<input type="text" name="point" value="${articleData.article.hp}"/>
				</td>
			</tr>
			<tr>
				<th>
					회원상태(grade):
				</th>
				<td>	
					<input type="text" name="grade" value="${articleData.article.hp}"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="bottom">				
					<input class="bottomMenu" type="submit" value="회원정보수정">
				</td>
			</tr>
		</table>
	</form>
	</div>
	</div>
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</body>
</html>