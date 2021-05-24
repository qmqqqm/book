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

</head>
<body>
	<!--장바구니 입력 성공시 장바구니 보기 페이지이동  -->
 	<c:if test="${result == 1}">
		<script>
		  	alert('상품 담기에 성공했습니다.\n장바구니 페이지로 이동합니다.');
			location.href="../order/basket.book";
		</script>
	<!-- 입력실패시 상품 디테일 페이지 이동 -->
	</c:if>
	<c:if test="${result == 0}">
		<script>
		  	alert('상품 담기에 실패했습니다.\n상품 페이지로 이동합니다.');
			location.href="../mainSub/bookDetail.book?book_id=${book_no}";
		</script>
 	</c:if>
</body>
</html>






