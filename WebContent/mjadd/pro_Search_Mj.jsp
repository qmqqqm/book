<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%> 
<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/pabi/pabi.ico" type="image/x-icon"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBKBOOK</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/frame_Mj.css"> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_searchlist_Mj2.css"> 

<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/pabi/pabi.ico" type="image/x-icon"/>


<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	

</script>
</head>

<body>

<div class="rab">
	<!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
	<div class="maincontainer">	

	<!--메인  -->
		<div class="maincontainer">	
	   	<br>
		<br>
		홈 > 상품검색
		<br>
		<hr/>
	    <br>
		<h2>검색목록</h2>
		
			<div class="prolist">
				
					<!-- 모델이 비었으면  검색하신 상품이 없습니다. 출력-->		
					<c:if test="${empty proSearch}">
					<tr>
						<td colspan="5">검색하신 상품이 없습니다.</td>
					</tr>
					</c:if>
					<!-- 상품이 있다면 모델에 있는 상품을 출력 -->
					<c:if test="${!empty proSearch}">
					<!-- 반목분으로 모델에 담긴 객체 을  searchList 변수에 담기-->	
					<c:forEach var="searchList" items="${proSearch}"> 
					<!--가격과 할인률을 변수에담아 판매가 계산  -->
					<c:set var="price" value="${searchList.book_price}"/><!-- 정가 -->
					<c:set var="discount" value="${searchList.discount_rate}"/><!-- 할인률  -->
					<c:set var="total" value="${price*((100-discount)/100)}"/><!-- 판매가 -->
							
					<table >			
										
						<td class="imgrab" rowspan="9"><div class="image"><a href="<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=${searchList.book_id}"><img src="<%=request.getContextPath()%>/img/${searchList.book_image}"/></a></div></td>
					
					<tr>
						<th>책이름 </th>
					</tr>
					<tr>
						<td class="title"><a class="title" href="<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=${searchList.book_id}">${searchList.book_title}</a></td>
					</tr>
					<tr>
						<th>저자/출판사 </th>
					</tr>
					<tr>
						<td>	${searchList.publishing_com} / ${searchList.author}</td>
					</tr>
					<tr>
						<td>					출판일 : 	${searchList.publishing_date}</td>
					</tr>
					<tr>
						<td>					정가 : ${searchList.book_price}원</td>
					</tr>
					<tr>
						<td>					할인 : ${searchList.discount_rate}%</td>
					</tr>
					<tr>
						<td>판매가 :<fmt:formatNumber value="${total}" pattern="\#,###"/></td>
					</tr>		
							
							
					</table>	
						
						<%-- data는  forEach부분에서 data변수를 의미하고
							   data변수에는 Ariticle객체가 들어가 있다
							      따라서
							   data.writer.name는
							   Ariticle.getWriter().getName()을 의미한다
							   
							   data.getWriter()를 호출하면 Writer객체를 받는다
							     이렇게 받은 Writer클래스의 name속성의 값을 받기위해서는
							   Writer클래스의 getName()를 호출해야 한다. 	   
						--%>
						
					
					</c:forEach>
					</c:if>
				
	   		</div>
	
		</div>
		
		<!--바텀 -->
		<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
	</div>
</div>
</body>
</html>