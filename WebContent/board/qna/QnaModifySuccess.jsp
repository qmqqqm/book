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
		<script>
				/* 삭제성공시 메세지창을 띄워서 user에게 알려준 후 
				목로보기로 이동
				http://localhost/bo/article/list.do
				현재주소값
				http://localhost/bo/article/delete.do?no=11
				*/
		
		  	alert('글 수정에 성공했습니다.');
		</script>

	<script>
	location.href="../board/qnalist.book";
	</script>
</body>
</html>






