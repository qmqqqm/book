<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#review_write{
vertical-align: bottom;
    border-bottom: 1px solid gray;
}    

</style>
</head>
<body>
<!--메인  -->
		<div>
			<h2>상품 Review</h2>
			<p>* 별점 및 리뷰 작성 후 작성하기 버튼을 클릭해 주세요.</p>
		</div>
		<br>
		<br>
	  <div style="float:left;width=20%;">홈 > 상세정보 > 리뷰게시판</div>
	  <div style="float:right;width=20%;"><button id="review_write_btn">리뷰 작성</button></div>
	   <br>
	   <hr/>
<div  class="product_review">
		<table class="table" id="review_write" width=100%>
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>Title</th>
					<th>상품명</th>
					<th>구매여부</th>
					<th>별점</th>
					<th>작성일</th>
				</tr>
		</table>	
</div>
<form class="riewForm"  action="/mainSub/bookDetail.book" method="post" >
</form>

