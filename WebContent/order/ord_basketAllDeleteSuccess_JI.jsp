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
	<c:if test="${allDelete>0}">
		<script>
		  	alert('장바구니 전체 삭제가 되었습니다.');
			location.href="../order/basket.book";
		</script>
	</c:if>
	<c:if test="${allDelete == 0}">
		<script>
		  	alert('상품 삭제에 실패했습니다.\n장바구니 페이지로 이동합니다.');
			location.href="../order/basket.book";
		</script>
	</c:if>

</body>
</html>






