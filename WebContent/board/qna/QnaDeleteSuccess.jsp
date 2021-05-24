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
	<c:if test="${result ==1}">
		<script>
				/* 삭제성공시 메세지창을 띄워서 user에게 알려준 후 
				목로보기로 이동
				http://localhost/bo/article/list.do
				현재주소값
				http://localhost/bo/article/delete.do?no=11
				*/
		
		  	alert('글 삭제에 성공했습니다.');
		</script>
	</c:if>

	<c:if test="${result ==0}">
		<script>
		  	alert('글 삭제에 실패했습니다.');
		</script>
	</c:if> 

	<script>
	location.href="../board/qnalist.book";
	</script>
</body>
</html>






