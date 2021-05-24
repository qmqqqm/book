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
	<script>
		$(function(){
	
		});
	</script>
</head>
<body>
	<a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
	<hr/>
	<h2>deleteSuccess.jsp</h2>

	<%-- 컨트롤러(DeleteArticleHandler)에서 
			(삭제)성공시 1의 값이 저장된 result를 모델로 넘겼다
			int result = deleteService.delete(no);
			request.setAttribute("result", result);
			
			삭제가 진행되면
			WebContent>view>article>deleteSuccess.jsp에서
			삭제성공여부를 
			user에게   alert창을 통해 메세지를 보여주고
			목록보기로 이동
	 --%>	
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
		location.href=<%=request.getContextPath()%>"/board/freeboardlist.book"; 
	  //location.href="list.do";			
	</script>			
</body>
</html>








