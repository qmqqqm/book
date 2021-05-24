<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>JBKBOOK</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mem_Mypage_Mj.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/upload_Mj.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>


</head>

<body>

<div class="rab">
	<!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
	<div class="container">	
	<jsp:include page="//module/sideAdminMenu_Mj.jsp" flush="true"/>
	<!--메인  -->
		<div class="mypage">
	  <br>
	<br>
	홈 > 관리자 > 상품관리
	<br>
	<hr/>
	<form action="<%=request.getContextPath()%>/upload/upload" method="post" enctype="multipart/form-data">
		<div class="imagerab">
			<tr>
			<p>파일 사이즈 1024 * 1024 파일 용량 10M로 제한
			<div class="image">
			  	<img id="preview" src=""  alt=""><br>
			</div>
			<input type="file" name="fileName1" />
		</div>
		<div class"tablerab">
			<table border="1">
			<tr>
			<td width="100">책이름</td>
			<td width="400"><input type="text" name="proName"></td>	
			</tr>
			<tr>
			<td width="100">저자</td>
			<td width="400"><input type="text" name="proAuthor"></td>	
			</tr>
			<tr>
			<td width="100">출판사</td>
			<td width="400"><input type="text" name="proPublisher"></td>	
			</tr>
			<tr>
			<td width="100">가격</td>
			<td width="400"><input type="text" name="proPrice"></td>	
			</tr>
			<tr>
			<td width="100">포인트</td>
			<td width="400"><input type="text" name="proPoint"></td>	
			</tr>
			<tr>
			<td width="100">할인률</td>
			<td width="400"><input type="text" name="proDiscount"></td>	
			</tr>
			<tr>
			<td width="100">책소개</td>
			<td width="400"><input type="text" name="proInformation"></td>	
			</tr>
			<tr>
			<td width="100">카테고리</td>
			<td width="400"><input type="text" name="proInformation"></td>	
			</tr>
			</table>
		</div>
		
	</form>
	</div>
	
		
	<!--바텀 -->
	<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>
