<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mem_Mypage_Mj.css">
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_Freelist_Mj.css"> --%>
<script type="text/javascript"> 
//var fileValue = $("#excelFile").val().split("\\");
//fileValue = fileValue.split(".");


</script> 
<style>
#review_write{
vertical-align: bottom;
    border-bottom: 1px solid gray;
}    
.search { 
  display: table; 
  margin-left: auto; 
  margin-right: auto; 
}

#function(){
	var 
}

</style>

</head>
<body>
<!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
	<div class="maincontainer">	
	<jsp:include page="//module/sideAdminMenu_Mj.jsp" flush="true"/>
	<!--메인  -->
		<div class="mypage">
		<br>
		
<!--메인  -->
		<div>
		<!-- 	<h2>관리자게시판</h2> -->
		</div>
		
		<br>
	  
	  홈 > 상세정보 > 관리자게시판
	  <br>
	  <hr>
	  
	  
	  <form class="proMasterForm_search"  action="<%=request.getContextPath()%>/product/search.book?book_title=${product_master_list_all.book_title}" method="post" >
		  <div class="search" style="float:center;width:60%;margin:0 30% 0 0">
	     	 <input type="text" name="searchText" placeholder="검색어를 입력해주세요." size=80 maxlength=20 style="text-align:center "> 
	     	 <button type="submit"  id="review_write_btn_update">상품검색	</button>     	 
		</div>
	</form>
	  
	  <div  style="float:right;width:20%;text-align:right">
	  	<button id="review_write_btn_insert"><a  href="<%=request.getContextPath()%>/product/insertMaster.book"> 상품등록</a></button>
	  </div>
	   <br>
	   <hr/>
<div  class="product_review">
		<table class="table" id="review_write" width=100%>
				<tr>
					<th width="2%">#</th><%-- (책 번호) --%>
					
					<th width="5%">이미지</th>
					<!-- <th width="5%">책 장르</th> -->
					<th width="9%">Title</th>
					<th width="7%">가격</th>
					<th width="5%">재고</th>
					<th width="4%">저자</th>
					<th width="7%">출판사</th>
					<!-- <th width="7%">출판일</th> -->
					<!-- <th width="15%">책설명</th> -->
					<th width="3%">할인률</th>
					<th width="7%">등록일</th>
					<th width="5%">배송</th>
					<th width="6%">수정</th>
				</tr>
		</table>	
</div>
<form class="proMasterForm"  action="<%=request.getContextPath()%>/product/selectAllMaster.book" method="post" >
	<div class="proMasterContainer" >
		<c:if test="${ ! empty product_master_select_list }">
			<c:forEach var="product_master_list_all" items="${product_master_select_list}" > 
					<table class="table" id="review_write"  width=100% OnClick="location.href='<%=request.getContextPath()%>/product/selectOne.book?book_id=${product_master_list_all.book_id}'" class="tb" style="cursor:pointer;">
						<tr>
							<td width="2%">${product_master_list_all.book_id}</td><%-- (책 번호) --%>
							<td width="5%"><img src= "<%=request.getContextPath()%>/img/${product_master_list_all.book_image}"	style="width:80px;height:60px;border:1px solid gray;" /></td>
						<%-- 	<th width="5%">${product_master_list_all.book_kind}</td> --%>
							<td width="9%">${product_master_list_all.book_title}</td>
							<td width="7%">${product_master_list_all.book_price}</td>
							<td width="5%">${product_master_list_all.book_count}</td>
							<td width="4%">${product_master_list_all.author}</td>
							<td width="7%">${product_master_list_all.publishing_com}</td>
							<%-- <th width="7%">${product_master_list_all.publishing_date}</th> --%>
							<%-- <th width="15%">${product_master_list_all.book_content}</th> --%>
							<td width="3%">${product_master_list_all.discount_rate}</td>
							<td width="7%">${product_master_list_all.date}</td>
							<td width="5%">배송관련</th>
							<td width="6%">
								<button id="review_write_btn_update">
								<a  href="<%=request.getContextPath()%>/product/selectOne.book?book_id=${product_master_list_all.book_id}">상품수정</a></button>
								<button id="review_write_btn_del">
								<a  href="<%=request.getContextPath()%>/product/delete.book?book_id=${product_master_list_all.book_id}">상품삭제</a></button>
							</th>
						</tr>
					</table>	
			</c:forEach>	
		</c:if>	
	</div>
</form>
</div>
</div>
</div>
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</body>
</html>