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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_Qnalist_JI.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>

<body>
<div class="rab">
	<!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
	<div class="maincontainer">	
	<jsp:include page="//module/sideAdminMenu_Mj.jsp" flush="true"/>
	<!--메인  -->
		<div class="mypage">
		<br>
		<br>
	   홈 > 관리자페이지 > 매출 및 주문관리
	   <br>
	   <hr>
	 	
		
	<%-- 내용출력부분 --%>
	<table class="main">
		<caption>매출 및 주문관리</caption>
			<thead>
		<h3>기대매출:${result}</h3>
		<h3>실현매출:${monney}</h3>
		<h3>총매출(기대+실현):${result+monney}</h3>
		
				<tr>
					<th width="50">주문번호</th>
					<th width="80">구입자id</th>
					<th width="70">책번호</th>
					<th width="150">책제목</th>
					<th width="50">1권당가격</th>
					<th width="50">구입수량</th>
					<th width="100">남은수량</th>
					<th width="70">매출</th>
					<th width="80">취소</th>
					<th width="100">구매확정</th>
				</tr>
			</thead>
			<tbody>
				<%--p653 20라인 
						게시물이 존재x한다면  게시글이 없다는 메세지가 있는 줄 출력--%>
				<c:if  test="${articlePage.hasNoArticles()}">
					<tr>
						<td colspan="4" >
							 아직 작성된 게시글이 없습니다.
						</td>
					</tr>
				</c:if>
				
				<%--(게시물이 존재한다면 )
				        페이징처리에서 한페이지에 출력할 게시물건수만큼 반복처리
						 컨트롤러(ListArticleHandler)에서      model로 받아왔다
						//(페이지별로 원하는 특정 범위의)글목록 조회 -p652 22라인
						QnaBoardListPageService_JI articlePage = qnaListService.getArticlePage(pageNo);
								
						//3.모델- session or request
						request.setAttribute("articlePage",articlePage);
						 					 
						 <c:forEach var="변수명" items="${모델의 속성명}">
						 	 반복해서 처리할 code
						 </c:forEach>
				 --%>
				 
				<c:forEach var="article" items="${articlePage.content}" varStatus="stutus" > 
					<tr>
						<td width="50">${article.mem_no}</td>
						<td width="80">${article.mem_id}</td>
						<td width="70">${article.book_no}</td>
						  <td width="150">${book[stutus.index].book_title}</td> <!-- //제목 -->
						<td width="50">${book[stutus.index].book_price}</td> <!-- //가격 -->
						 <td width="50">${article.count}</td> <!-- //구입수량 -->
						<td width="100">${book[stutus.index].book_count}</td><!-- //남은수량 -->
						<td width="70">${book[stutus.index].book_price*article.count}</td><!-- //매출 -->
						<td width="80"><button ><a href="./salescancel.book?oderNo=${article.mem_no}&count=${article.count}&book=${article.book_no}">취소</a></button></td><!-- //매출 -->
						<td width="100"><button ><a href="./salesfix.book?oderNo=${article.mem_no}&income=${book[stutus.index].book_price*article.count}">구매확정</a></button></td><!-- //매출 -->
						
					</tr>
				</c:forEach>
				<%-- 반복끝 -------------%>
			</tbody>	
	</table>
	
	<%-- 페이징처리 p653 37라인 
			 전체게시물수가 0보다 크면 
			 -> 게시물수가 존재한다면 페이징을 출력하여라 
	--%>
<%-- 	articlePage.hasArticles()=${articlePage.hasArticles()}<br/>
	articlePage.startPage=${articlePage.startPage}<br/>
	articlePage.endPage=${articlePage.endPage}<br/>
	articlePage.totalPages=${articlePage.totalPages} --%>
	<c:if test="${articlePage.hasArticles()}">
		<table border="0" width="90%">
			<tbody>
				<tr>
					<td class="pageNo">
						<%-- p653 41라인
								 조건문활용하여 이전으로 넘어갈수 있는 경우에는
						         시작페이지(startPage가 0보다 큰 경우)
						    <<prev 출력 --%>
						    
						<c:if test="${articlePage.startPage>5}">    
							<a href="<%=request.getContextPath()%>/admin/sales.book?pageNo=${articlePage.startPage-5}"> &lt;&lt;prev </a>				
						</c:if>
						
						<%--p653 43라인
								페이징처리에서 한번에 출력할 페이수만큼 반복처리
								형식
								<c:forEach begin="0시작값" end="10종료값" step="2증감값" var="i변수명">		
									<a href="#~~~">[${i}]</a>   [0] [2] [4] [6] [8] [10]
								</c:forEach>
								 --%>
						<c:forEach begin="${articlePage.startPage}" 
						           end="${articlePage.endPage}" step="1" 
						           var="pNo">	
							<a href="<%=request.getContextPath()%>/admin/sales.book?pageNo=${pNo}">[${pNo}]</a>
						</c:forEach>
						
						<%--조건문활용하여 다음으로 넘어갈수 있는 경우에는
						    endPage<totalPages
						    next>> 출력 --%>
						<c:if test="${articlePage.endPage<articlePage.totalPages}">   
							<a href="<%=request.getContextPath()%>/admin/sales.book?pageNo=${articlePage.startPage+5}"> next&gt;&gt; </a>
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