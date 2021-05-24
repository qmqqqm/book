<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBKBOOK</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/frame_Mj.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main_Ji.css">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/pabi/pabi.ico" type="image/x-icon"/>
<link href="<%=request.getContextPath()%>/css/jquery.bxslider.css" rel="stylesheet" />

<script src="http://code.jquery.com/jquery-latest.min.js"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bxslider/4.2.15/jquery.bxslider.min.js"></script>
<c:set var="topscroll"  value="topscroll()"/>
<script>

$(function(){   
	    $(".bxslider").bxSlider({ // 클래스명 주의!
	    	 auto: true, // 자동으로 애니메이션 시작
	         speed: 500,  // 애니메이션 속도
	        pause: 5000,  // 애니메이션 유지 시간 (1000은 1초)
	        mode: 'horizontal', // 슬라이드 모드 ('fade', 'horizontal', 'vertical' 이 있음)
	        /* autoControls: true, // 시작 및 중지버튼 보여짐 
	        pager: true, // 페이지 표시 보여짐 
	       captions: true // 이미지 위에 텍스트를 넣을 수 있음  */
	    });
	    /*테스트  */
   		
	    /* 상단메뉴고정 */
	  var navOffset = $('.menurab').offset();
	  
	  $( window ).scroll( function() {
	    if ( $(this).scrollTop() > navOffset.top ) {
	      $( '.menurab' ).addClass( 'action' );
	    }
	    else {
	      $( '.menurab' ).removeClass( 'action' );
	    }
	  });
	  
});


</script>
</head>

<body>
<div class="rab">
	<!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
</div> 
<div class="adad">
<div class="ad">
      <!-- 슬라이드 이미지 -->
   		<div class="bxsliderrab">
		    <ul class="bxslider">
		        <li><a href="#"><img class="slideimg" src="/img/1.jpg" alt="" title="이미지1"></a></li>
		        <li><a href="#"><img class="slideimg" src="/img/2.jpg" alt="" title="이미지2"></a></li>
		        <li><a href="#"><img class="slideimg" src="/img/3.jpg" alt="" title="이미지3"></a></li>
		    </ul>
    	</div>
		<div class="banner">
			<div class="dogad"><img class="dogimg" src="/img/seadog.png"/></div>
			<div class="cherryad"><img class="cherryimg" src="/img/cherry.png"/></div>
		</div>
	</div>
</div>                  
<!--메인  -->
<form class="mainForm"  action="/mainSub/bookDetail.book" method="post" >
	<div class="maincontainer">	
		<!--메인 selectALL  -->
		<%-- <jsp:include page="/product/selectAll.jsp" flush="true"/> --%>	
		<div class="sideBar">
		<table class="sideBarT">
		<tr><th class="sideTh"><img src="<%=request.getContextPath()%>/img/today.jpg" width="60"/></th></tr>
		<tr><th class="sideTd"><div class="side"><a href="<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=1"><img src="<%=request.getContextPath()%>/img/bestseller1.jpg" class="barImage"/></a></div></th></tr>
		<tr><th class="sideTd"><div class="side"><a href="<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=2"><img src="<%=request.getContextPath()%>/img/bestseller2.jpg" class="barImage"/></a></div></th></tr>
		<tr><th class="sideTd"><div class="side"><a href="<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=3"><img src="<%=request.getContextPath()%>/img/bestseller3.jpg" class="barImage"/></a></div></th></tr>
		</table></div>
		
		
		<c:if test="${ ! empty product_list }">
			<c:forEach var="product_list_all" items="${product_list}" >
			
			<div class="alllist">
				<table OnClick="location.href='<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=${product_list_all.book_id}'" class="tb" style="cursor:pointer;">
				  <tr>
					<td>
						<div class="divImg"><img src= "img/${product_list_all.book_image}"	class="img" /></div>
						<div class="title"><c:out value="${product_list_all.book_title}"/></div>
						<div class="writer"><c:out value="${product_list_all.author}"/>
					    	   <span class="publish"> | <c:out value="${product_list_all.publishing_com}"/></span></div>
			    	    <div class="div">
			    	    <span class="price"><c:out value="${product_list_all.book_price}"/>원</span><span> | </span>
			    	    <span class="dc">[<c:out value="${product_list_all.discount_rate}"/>%할인]</span></div>
				   </td>
				  </tr>
				</table>
			</div>
			</c:forEach>	
		</c:if>	
			
		<c:if test="${ ! empty product_best_list }">	 
			<c:forEach var="product_list_all" items="${product_best_list}" > 
				<div class="alllist">
				<table OnClick="location.href='<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=${product_list_all.book_id}'" class="tb" style="cursor:pointer;">
					<tr>
					    <td><div class="divImg"><img src= "<%=request.getContextPath()%>/img/${product_list_all.book_image}"	class="img" /></div>
					    	<div class="title"><c:out value="${product_list_all.book_title}"/></div>
					    	 <div class="writer"><c:out value="${product_list_all.author}"/>
					    	 <span class="publish"> | <c:out value="${product_list_all.publishing_com}"/></span></div>
					    	<%--  <span class="date">| <c:out value="${product_list_all.publishing_date}"/></span></div> --%>
					    	<div class="div">
					    	 <span class="price"><c:out value="${product_list_all.book_price}"/>원</span><span> | </span>
					    	 <span class="dc">[<c:out value="${product_list_all.discount_rate}"/>%할인]</span></div>
				   			<%-- <div class="content"><c:out value="${product_list_all.book_content}"/></div> --%>
					    </td>     
					</tr>   
				   
				</table>
				</div>			
			</c:forEach>	
		</c:if>	
			
			
			<c:if test="${ ! empty product_pro_list }">	 
				<c:forEach var="product_list_all" items="${product_pro_list}" > 
				<div class="alllist">
				<table OnClick="location.href='<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=${product_list_all.book_id}'" class="tb" style="cursor:pointer;">
					<tr>
					    <td>
					    	<div class="divImg"><img src= "<%=request.getContextPath()%>/img/${product_list_all.book_image}"	class="img" /></div>
					    	<div class="title"><c:out value="${product_list_all.book_title}"/></div>
					    	 <div class="writer"><c:out value="${product_list_all.author}"/>
					    	 <span class="publish"> | <c:out value="${product_list_all.publishing_com}"/></span></div>
					    	 <%-- <span class="date">| <c:out value="${product_list_all.publishing_date}"/></span></div> --%>
					    	<div class="div">
					    	 <span class="price"><c:out value="${product_list_all.book_price}"/>원</span><span> | </span>
					    	 <span class="dc">[<c:out value="${product_list_all.discount_rate}"/>%할인]</span></div>
					    	<%-- <div class="content"><c:out value="${product_list_all.book_content}"/></div> --%>
				    	</td>     
					</tr> 
					</table>
					</div>
					
				</c:forEach>	
			</c:if>	
			
			<c:if test="${ ! empty product_study_list }">	 
				<c:forEach var="product_list_all" items="${product_study_list}" > 
					<div class="alllist">
					<table OnClick="location.href='<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=${product_list_all.book_id}'" class="tb" style="cursor:pointer;">
							<tr>
					    <td><div class="divImg"><img src= "<%=request.getContextPath()%>/img/${product_list_all.book_image}"	class="img" /></div>
					    	<div class="title"><c:out value="${product_list_all.book_title}"/></div>
					    	 <div class="writer"><c:out value="${product_list_all.author}"/>
					    	 <span class="publish"> | <c:out value="${product_list_all.publishing_com}"/></span></div>
					    	<%--  <span class="date">| <c:out value="${product_list_all.publishing_date}"/></span></div> --%>
					    	<div class="div">
					    	 <span class="price"><c:out value="${product_list_all.book_price}"/>원</span><span> | </span>
					    	 <span class="dc">[<c:out value="${product_list_all.discount_rate}"/>%할인]</span></div>
					   <%--  <div class="content"><c:out value="${product_list_all.book_content}"/></div></td>      --%>
					</tr>    
						</table>
						</div>
					
				</c:forEach>	
			</c:if>	
			
			<c:if test="${ ! empty product_novel_list }">	 
				<c:forEach var="product_list_all" items="${product_novel_list}" > 
					<div class="alllist">
					<table OnClick="location.href='<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=${product_list_all.book_id}'" class="tb" style="cursor:pointer;">
							<tr>
					    <td><div class="divImg"><img src= "<%=request.getContextPath()%>/img/${product_list_all.book_image}"	class="img" /></div>
					    	<div class="title"><c:out value="${product_list_all.book_title}"/></div>
					    	 <div class="writer"><c:out value="${product_list_all.author}"/>
					    	 <span class="publish"> | <c:out value="${product_list_all.publishing_com}"/></span></div>
					    	 <%-- <span class="date">| <c:out value="${product_list_all.publishing_date}"/></span></div> --%>
					    	<div class="div">
					    	 <span class="price"><c:out value="${product_list_all.book_price}"/>원</span><span> | </span>
					    	 <span class="dc">[<c:out value="${product_list_all.discount_rate}"/>%할인]</span></div>
					    <%-- <div class="content"><c:out value="${product_list_all.book_content}"/></div> --%>
					    </td>     
					</tr>   
					</table>
					</div>					
				</c:forEach>	
			</c:if>	
			
			<c:if test="${ ! empty product_manga_list }">	 
				<c:forEach var="product_list_all" items="${product_manga_list}" > 
					<div class="alllist">
					<table OnClick="location.href='<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=${product_list_all.book_id}'" class="tb" style="cursor:pointer;">
							<tr>
					    <td><div class="divImg"><img src= "<%=request.getContextPath()%>/img/${product_list_all.book_image}"	class="img" /></div>
					    <div class="title"><c:out value="${product_list_all.book_title}"/></div>
					    	 <div class="writer"><c:out value="${product_list_all.author}"/>
					    	 <span class="publish"> | <c:out value="${product_list_all.publishing_com}"/></span></div>
					    	<%--  <span class="date">| <c:out value="${product_list_all.publishing_date}"/></span></div> --%>
					    	<div class="div">
					    	 <span class="price"><c:out value="${product_list_all.book_price}"/>원</span><span> | </span>
					    	 <span class="dc">[<c:out value="${product_list_all.discount_rate}"/>%할인]</span></div>
					   <%--  <div class="content"><c:out value="${product_list_all.book_content}"/></div> --%>
					    </td>     
					</tr>   
						</table>
					</div>
				</c:forEach>	
			</c:if>	

	</div>

</form>
<div>
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>	
</body>
</html>