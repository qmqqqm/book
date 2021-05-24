<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
	   홈 > 고객센터 > 자유게시판
	   <br>
	   <hr>
	 	
	
	<%-- 글쓰기  --%>
	<table class="write">
		<tbody>
			<tr>
				<td class="write">
					<a href="<%=request.getContextPath()%>/board/freeboardwrite.book">글쓰기</a>
				</td>
			</tr>
		</tbody>
	</table>
	
	<%-- 내용출력부분 --%>
	<table class="main">
		<caption>자유 게시판</caption>
			<thead>
				<tr>
				
					<th  width="60"><label>번호</label></th>
					<th  width="260"><label>제목</label></th>
					<th  width="120"><label>작성자</label></th>
					<th  width="60"><label>조회수</label></th>
				
				
			</thead>
			
			<tbody>
				<%--p653 20라인 
						게시물이 존재x한다면  게시글이 없다는 메세지가 있는 줄 출력--%>
				<c:if  test="${articlePage.hasNoArticles()}">
					<tr>
						<td colspan="4" style="text-aling:center;">
							 아직 작성된 게시글이 없습니다.
						</td>						
					</tr>
				</c:if>
				
				<%--(게시물이 존재한다면 )
				        페이징처리에서 한페이지에 출력할 게시물건수만큼 반복처리					
				 --%>
				<c:forEach var="article" items="${articlePage.content}"> 
					<tr>
					
						<td width="60">${article.number}</td>
						<td width="260"><a href="<%=request.getContextPath()%>/board/freeboardread.book?no=${article.number}&pageNo=${articlePage.currentPage}">${article.title}</a></td>						
						<td  width="120">${article.writer.id}</td>
						<td  width="60">${article.readCount}</td>
					</tr>
				</c:forEach>
				<%-- 반복끝 -------------%>
			</tbody>	
	</table>
	
	<%-- 페이징처리 	--%>

	<c:if test="${articlePage.hasArticles()}">
		<table >
			<tbody >
				<tr >
					<td class="pageNo">
						<%-- 5페이지 초과시   <<prev 출력 --%>						    
						<c:if test="${articlePage.startPage>5}">    
							<a href="<%=request.getContextPath()%>/board/freeboardlist.book?pageNo=${articlePage.startPage-5}"> &lt;&lt;prev </a>				
						</c:if>
						
						<%--페이징처리에서 한번에 출력할 페이수만큼 반복처리
								형식
								<c:forEach begin="0시작값" end="10종료값" step="2증감값" var="i변수명">		
									<a href="#~~~">[${i}]</a>   [0] [2] [4] [6] [8] [10]
								</c:forEach>
								 --%>
						<c:forEach begin="${articlePage.startPage}" 
						           end="${articlePage.endPage}" step="1" 
						           var="pNo">	
							<a href="<%=request.getContextPath()%>/board/freeboardlist.book?pageNo=${pNo}">[${pNo}]</a>
						</c:forEach>
						
						<%--조건문활용하여 다음으로 넘어갈수 있는 경우에는
						    endPage<totalPages
						    next>> 출력 --%>
						<c:if test="${articlePage.endPage<articlePage.totalPages}">   
							<a href="<%=request.getContextPath()%>/board/freeboardlist.book?pageNo=${articlePage.startPage+5}"> next&gt;&gt; </a>
						</c:if> 
					</td>
				</tr>
			</tbody>
		</table>
	</c:if>	
	   	
	   </div>
	   
	</div>

	
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>