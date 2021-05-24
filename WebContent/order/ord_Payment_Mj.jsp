<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBKBOOK</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/frame_Mj.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

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
	   결제소스	
	</div>

	
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>