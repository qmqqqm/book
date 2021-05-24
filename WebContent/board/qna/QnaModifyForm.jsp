<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags"  prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("replaceChar", "\r\n"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>JBKBOOK</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mem_Mypage_Mj.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_QnaWrite_JI.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<div class="rab">
	<!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
	<div class="maincontainer">	
	<c:if test="${empty ADMIN}">
	<jsp:include page="//module/sideBorderMenu_Mj.jsp" flush="true"/>
	</c:if>
	<c:if test="${!empty ADMIN}">
	<jsp:include page="//module/sideAdminMenu_Mj.jsp" flush="true"/>
	</c:if>
	<!--메인  -->
		<div class="mypage">
		<br>
		<br>
	   홈 > 고객센터 > 글 수정
	   <hr/>
  	<c:if test="${empty ADMIN}">
		<script >
		alert('관리자로 로그인해주세요');
		 location.href="/main.book"
		</script>
	</c:if>
   <form action="../board/qnamodify.book" method="post">
   <input type="hidden" name="no" value="${modReq.articleNumber}"/>
   <input type="hidden" name="pageNo" value="${pageNo}"/>
	<table class="main">
	<caption>게시글 수정</caption>
		<tr>
			<th>글 번호 </th><td>${modReq.articleNumber}</td>
		</tr>
		<tr>
			<th>작성자</th><td><input type="text" class="text" name="mid" value="관리자(${modReq.userId})" disabled="disabled"/></td>
		</tr>
		<tr>
			<th>제목</th><td><input type="text" class="text" name="title" value="${modReq.title}"/>
			         		  <c:if test="${errors.title}"> 제목을 입력하세요</c:if></td>
		</tr>
		<tr>
			<th>내용</th><td><textarea name="content">${modReq.content}</textarea></td>
		</tr>
		<tr>
	   		<td colspan="2" class="button">
	   		<input type="reset" name="btn1" value="다시 작성" />
	   		<input type="submit" name="btn1" value="수정 글 등록"></td>
	    </tr>
	</table>
   </form>
</div>	
</div>
</div>
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</body>
</html>




