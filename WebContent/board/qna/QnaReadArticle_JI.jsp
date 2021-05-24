<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--줄바꿈처리 1번째 방법 : 사용자정의태그 사용 
		우리가  /WEB-INF/tags폴더 하위에  저장했던
     	사용자정의태그(pre.tag)를 사용하기위해서  
     tablib지시어(directive)를 작성해야 한다--%>
<%@ taglib tagdir="/WEB-INF/tags"  prefix="u"%>


<%-- 줄바꿈처리 2번째 방법 : JSTL(JSP표준태그)의  function사용
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <% pageContext.setAttribute("replaceChar속성명", "\r\n"대체되어질old내용); %>
  ${fn:replace(출력내용, replaceChar속성명,"<br/>대체할new내용") }	
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("replaceChar", "\r\n"); %>
	
<%-- 글등록일과 글수정일의  날짜시간 포맷을 적용하기위한 taglib추가
     https://www.javatpoint.com/jstl-fmt-formatdate-tag --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>JBKBOOK</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mem_Mypage_Mj.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_QnaRead_JI.css">
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
	   홈 > 고객센터 > Q&A 게시판
	   <br>
	   <hr/>
	
	<%-- 글 상세내용이 출력되는 부분 --%>
	<table class="main">
	<caption>Q&A 게시판</caption>
		<tbody>
			<tr>
				<td colspan="3" width="71%">
				작성일  <fmt:formatDate pattern="yyyy.MM.dd" value="${articleData.q_regdate}"/>
				</td>
				<td width="10%">
				수정일  <fmt:formatDate pattern="yyyy.MM.dd" value="${articleData.q_modifieddate}"/>
				</td>
				<td width="12%">작성자 관리자(${articleData.id})</td>
				<td width="7%">조회수 ${articleData.q_readcount}</td>
			</tr>
			<tr>
				<td colspan="6" height="100px" class="q">Q. <u:pre value="${articleData.q_title}"/></td>
			</tr>
			<tr>
				<td colspan="6" height="100px" class="a">A. <u:pre value="${articleData.q_content}"/></td>
			</tr>
			<tr>
				<td colspan="6" class="button">
					<c:set var="no" 
					       value="${empty param.pageNo?'1':param.pageNo}"/>
					<a href="../board/qnalist.book?pageNo=${no}">목록보기</a>
					<c:if test="${ !empty ADMIN }">
						<a href="../board/qnamodify.book?no=${articleData.q_No}&pageNo=${param.pageNo}">글수정</a>
						<a href="../board/qnadelete.book?no=${articleData.q_No}">글삭제</a>
					</c:if>
				</td>
			</tr>
		</tbody>
	</table>
</div>
</div>
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</body>
</html>










