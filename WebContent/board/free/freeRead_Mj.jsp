<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%> 
<%@ taglib tagdir="/WEB-INF/tags"  prefix="u"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBKBOOK</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mem_Mypage_Mj.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_Freelist_Mj.css">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/pabi/pabi.ico" type="image/x-icon"/>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>

<body>

<div class="rab">
	<!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
	<div class="maincontainer">	
	<jsp:include page="//module/sideBorderMenu_Mj.jsp" flush="true"/>
	
	<!--메인  -->
	<div class="mypage">
			<br>
	<br>
	 홈 > 고객센터 > 자유게시판 > 글 상세조회
	<br>
	<hr>
	<br>
	<br> 
	
	
	<%-- 보고싶은페이지(user가 선택한 페이지)pageNo = ${pageNo} <hr/> --%>
	
	
	
	<%-- 글 상세내용이 출력되는 부분 --%>
	<table  >
		
		<tbody>
			<tr>
				<th >번호</th>
				<td >${articleData.number}</td>
			</tr>
			<tr>
				<th >작성자id</th>
				<td >${articleData.writer.id}</td>
			</tr>
			<tr>
				<th >작성자명</th>
				<td >${articleData.writer.name}</td>
			</tr>
		
			<tr>
				<th >제목</th>
				<td>${articleData.title}</td>
			</tr>
			<tr>
				<th >글등록일</th>
				<td >
					
            <fmt:formatDate pattern="yyyy/MM/dd(E)" 
                value="${articleData.regdate}"/><br/>                                           
				</td>
			</tr>
			<tr>
				<th >글수정일</th>
				<td >
					${articleData.modifiedDate}
					
				</td>
			</tr>
			<tr>
				<th  >조회수</th>
				<td  >${articleData.readCount}</td>
			</tr>
			<tr>
				<th >내용</th>
				<td  >
				<textarea class="text" rows="20" cols="80" readonly="readonly">${articleData.content}</textarea>					
														
				</td>
			</tr>
		</tbody>
	</table>
	
	<%-- 기타항목이 출력되는 부분 : 목록보기/글수정/글삭제--%>
	<table  >
		<tbody>
			<tr>
				<td class="bottom" >				
					<c:set var="nnn" 
					       value="${empty param.pageNo?'1':param.pageNo}"/>
						<a class="bottomMenu" href="<%=request.getContextPath()%>/board/freeboardlist.book?pageNo=${nnn}">목록보기</a>
					<c:if test="${AUTHUSER.id == articleData.writer.id}">	
						<a class="bottomMenu" href="<%=request.getContextPath()%>/board/modify.book?no=${articleData.number}&pageNo=${pageNo}">글수정</a>
						<a class="bottomMenu" href="<%=request.getContextPath()%>/board/delete.book?no=${articleData.number}">글삭제</a>
					</c:if>
				</td>
			</tr>
		</tbody>
	</table>
   </div>
	   
</div>

	
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>











