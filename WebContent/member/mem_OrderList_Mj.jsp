<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBKBOOK</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mem_Mypage_Mj.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_orderlist_Mj.css">

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
	<jsp:include page="//module/sideMenu_Mj.jsp" flush="true"/>
	<!--메인  -->
		<div class="mypage">
	   <br>
	<br>
	홈 > 마이페이지 > 주문목록
		<br>
	<hr/>
	
	   <br>
 
 	<h2>${AUTHUSER.id}님 주문목록</h2>
	<table >
	
	
		<tr>
			<th>
				주문번호
			</th>			
			<th class="title">
				책이름
			</th>
			<th>
				저자/출판사
			</th>
			<th>
				가격
			</th>
			<th>
				수량
			</th>
			<th>
				합계
			</th>
			<th>리뷰</th>
		</tr>
			<%-- 페이징처리에서 한페이지에 출력할 게시물건수만큼 반복처리
						 컨트롤러(ListArticleHandler)에서      model로 받아왔다
						 List<BasketDTO> basket = basketService.getBasketList();
						 request.setAttribute("basket",basket);
						 
						 <c:forEach var="변수명" items="${모델의 속성명}">
						 	 반복해서 처리할 code
						 </c:forEach>
				 --%>
<c:if test="${empty order}">
	<tr>
		<td colspan="7">주문하신 상품이 없습니다.</td>
	</tr>
<c:set var="sum" value=""/>
</c:if>
		<c:if test="${ ! empty order }">	
			<c:forEach var="memOrder" items="${order}"> 
				<tr>	
					<c:set var="sum" value="${sum+(memOrder.price*memOrder.count*((100-memOrder.discount)/100))}" />
													
					<td>${memOrder.no}</td> 
					<td>${memOrder.title}</td> 
					<td>${memOrder.author} / ${memOrder.publicher}</td>					
					<td><fmt:formatNumber value="${(memOrder.price*((100-memOrder.discount)/100))}" pattern="\#,###"/>원</td>
					<td>${memOrder.count}개</td>
					<td><fmt:formatNumber value="${memOrder.price*((100-memOrder.discount)/100)*memOrder.count}" pattern="\#,###"/>원</td>
					<td><a href="<%=request.getContextPath()%>/review/writer.book?book_id=${memOrder.book_id}&review_pro_title=${memOrder.title}"><input type="button" value="리뷰작성"/></a></td>
				</tr>
			</c:forEach>
		</c:if>
	<tr>
		<th colspan="3">
		구매합계 
		</th>
		<td class="total" colspan="3">
		<fmt:formatNumber value="${sum}" pattern="\#,###"/>원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
		<td>
		</td>
	</tr>
	</table>
	

	   

	</div>
	</div>

	
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>