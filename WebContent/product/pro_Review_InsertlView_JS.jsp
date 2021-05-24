<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBKBOOK</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/frame_Mj.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_Freelist_Mj.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="<%=request.getContextPath()%>/js/pro_DetailView_JS.js"></script>
<style>
textarea{
	resize:none;
	width:100%;
	height:auto;
}
.form-control{
	width:90%;
}
</style>
</head>

<body>

	<div class="rab">
		<!-- 탑메뉴 -->
		<jsp:include page="//module/topMenu_Jg.jsp" flush="true" />
		<!-- 세컨메뉴 -->
		<jsp:include page="//module/secMenu_Mj.jsp" flush="true" />
		<!--메인  -->
		<div class="maincontainer">	
		  	   <br>
	<br>
	홈 > 리뷰작성
	<br>
	<hr/>
			<%-- form --%>
					<form action="<%=request.getContextPath()%>/review/writer.book" class="form-horizontal" role="form"  method="post">
						<table>
							<tr>
							<input name="book_id" type="hidden" value=<%=request.getParameter("book_id")%>>
							<th>작성자(ID):</th>
							<td>
								<input type="text" class="form-control" id="review_mem_id"
									name="review_mem_id" placeholder="ID" value="${AUTHUSER.id}" readonly="readonly">
							</td>
							</tr>
							<tr>
							<th>상품명</th>
							<td>
								<input type="text" class="form-control" id="review_pro_title"
									name="review_pro_title" placeholder="상품명" value="${review_pro_title}" readonly="readonly">
							</td>
							</tr>
							<tr>
						<th>내용</th>
							<td>
								<textarea class="form-control" rows="15" cols="100"	placeholder="review_content" name="review_write" id="review_write"></textarea>
							</td>
							</tr>
							<tr>
							<td colspan="2">
						
								
									<label class="radio-inline"> <input type="radio" name="review_star" id="review_star" value="1" checked="checked">★☆☆☆☆</label>
									<label class="radio-inline"> <input type="radio" name="review_star" id="review_star" value="2">★★☆☆☆</label>
									<label class="radio-inline"> <input type="radio" name="review_star" id="review_star" value="3">★★★☆☆</label>
									<label class="radio-inline"> <input type="radio" name="review_star" id="review_star" value="4">★★★★☆</label>
									<label class="radio-inline"> <input type="radio" name="review_star" id="review_star" value="5">★★★★★</label>
									
								</td>
								<tr>
								<td class="bottom" colspan="2">							
								<button class="bottomMenu" type="submit" class="btn btn-success">작 성</button>
								<button class="bottomMenu" type="reset" class="btn btn-danger">초기화</button>
							</td>
						
						</table>
					</form>

		<!--바텀 -->
		</div>
		<jsp:include page="//module/bottom_Mj.jsp" flush="true" />
	</div>
</body>
</html>