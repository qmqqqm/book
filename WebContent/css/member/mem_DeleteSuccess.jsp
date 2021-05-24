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
	<!--삭제성공여부을 얼랏창으로 알린후 메인 페이지로 이동  -->
	 <c:if test="${result eq 1}">
		<script>
			alert("삭제가 성공되었습니다");			      
		</script>
	 </c:if>
	 	
	 <c:if test="${result eq 0}">	
		<script>
			alert("삭제가 실패되었습니다");
		</script>
	 </c:if>			
	<script>		
		location.href=<%=request.getContextPath()%>"/main.book"; 				
	</script>			
</body>
</html>








