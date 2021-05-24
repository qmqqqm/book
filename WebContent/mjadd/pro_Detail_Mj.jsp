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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/frame_Mj.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_searchlist_Mj2.css">

<script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>

<body>

<div class="rab">
	<!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
	<div class="maincontainer">	

	<!--메인  -->
		<div class="mypage">
	   <br>
	<br>
	홈 > 상품검색 > 상품상세
	<br>
	<hr/>
    <br>
	<h2>상품상세조회</h2>
	
	<form action="#">	 
		<table  >
			  
		   
		    <tr>
		    
		        
		        <td class="image" rowspan="6"><img src="<%=request.getContextPath()%>/img/${SCITEM.book_image}.jpg"/>
		        </td>
		    	<th >책제목</th>
		        <td><input class="title input" type="text" name="name" id="name" value="${SCITEM.book_title}" readonly="readonly">
		        </td>
		    </tr>
		   
		    <tr>
		        <th >저자</th>
		        <td >        
		        <%-- <fmt:parseDate value="${memData.birth}" var="date" pattern="yyyy-MM-dd"/> --%>
		        <input class="index" type="text" value="${SCITEM.author}" name="birth" id="birth" readonly="readonly">  </td>
		        <%-- <fmt:formatDate pattern="yyyyMMdd" value="birth"/> --%>
		        
		    </tr>
		    
		    <tr>
		        <th >출판사</th>
		        <td>      <input class="index" type="text" value="${SCITEM.publishing_com}" name="gender" id="gender" readonly="readonly"></td>
		    </tr>
		    <tr>
		        <th >정가</th>
		        <td><input class="index" type="text" value="${SCITEM.book_price}원" name="hp" id="hp" readonly="readonly" ></td>
		    </tr>
		    <tr>
		        <th >할인</th>
		        <td><input class="index" type="text" name="point" id="point" value="${SCITEM.discount_rate}%" readonly="readonly">
		        
		        </td>
		        
		    	
		    </tr>
		   
		    <tr>
		        <th >판매가</th>
		        <c:set var="price" value="${SCITEM.book_price}"/>
				<c:set var="discount" value="${SCITEM.discount_rate}"/>
				<c:set var="total" value="${price*((100-discount)/100)}"/>
		        <td class="price"><input class="index" type="text" name="point" id="point" value= <fmt:formatNumber value="${total}" pattern="\#,###"/> readonly="readonly">
		        
		        </td>
		        
		    	
		    </tr>
		  
	        <tr>
		        <td class="bottom" colspan="3">
		        <textarea class="text" rows="10" cols=110>${SCITEM.book_content}</textarea>        
		        </td>
		        
		    	
	    	</tr>
		    <tr>
		        <td class="bottom" colspan="3" class="index">
		        	<input class="bottomMenu" type="submit" value="구매">
		        	<a class="bottomMenu" href="<%=request.getContextPath()%>">장바구니</a>
		        </td>
		        
		        
		    	
		    </tr>
		   
		   
		</table>
	</form>
	   	
	   </div>
	   
	</div>

	
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>