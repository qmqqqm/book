<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/frame_Mj.css">
<style>
h3{
	margin: 0 auto 0 auto;
	color:#5e6b9e;
	font-weight:bold;
	font-size:3rem;
}
input[type="button"]{
	border:1px solid gray;
	border-radius:10px;
	background:#eff2f5;
	height:30px;
	margin:5px;
}
.success{
	width:300px;
	margin: 50px auto;
}
</style>
<script>
$(function(){
	$('#list').click(function(){
		location.href="../main.book";
	})	
})
</script>
</head>
<body>
<div class="rab">
	<!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
	<!--메인  -->
	<div class="maincontainer">	
	<br>
	<br>
	홈 > 결제
	<br>
 	<hr/>
 	<c:if test="${result>0}"> 
	<script>
		alert('결제가 완료되었습니다.');
	</script>
	<div class="success">
	<h3>구매 완료!</h3>
	<br/>
	<input type="button" id="list" value="쇼핑계속하기"/>	
	</div>
 	</c:if>
	<c:if test="${result<1}">
	<script>
		alert('구매에 실패하였습니다.\상품 페이지로 이동합니다.');
		location.href="../mainSub/bookDetail.book";
	</script>
	</c:if> 
	</div>
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>






