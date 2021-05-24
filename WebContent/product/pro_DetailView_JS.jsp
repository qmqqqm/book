<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBKBOOK</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/frame_Mj.css">
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_Freelist_Mj.css"> --%>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/pabi/pabi.ico" type="image/x-icon"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="<%=request.getContextPath()%>/js/pro_DetailView_JS.js"></script> 
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<c:set var="total" value="total()"/>
<c:set var="procount" value="procount()"/>
<script type="text/javascript">
IMP.init('imp88822222'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
//제품총결제금액
function total(){	
		var sum = ${ProductDetailService.book_price*(1-ProductDetailService.discount_rate*0.01)}*$("#quantity").val();
			return 	sum
};
//제품선택수량
function procount(){
	count=$("#quantity").val();
	return count
};
//결제API
function requestPay() {
	IMP.request_pay({
	    pg : 'html5_inicis', // version 1.1.0부터 지원.
	    pay_method : 'card',
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : '주문명:결제테스트',
	    amount :${total},
	    buyer_email : 'iamport@siot.do',
	    buyer_name : '구매자이름',
	    buyer_tel : '010-1234-5678',
	    buyer_addr : '서울특별시 강남구 삼성동',
	    buyer_postcode : '123-456',
	    m_redirect_url : 'https://www.yourdomain.com/payments/complete'
	}, function(rsp) {
	    if ( rsp.success ) {
	        var msg = '결제가 완료되었습니다.';
	        msg += '고유ID : ' + rsp.imp_uid;
	        msg += '상점 거래ID : ' + rsp.merchant_uid;
	        msg += '결제 금액 : ' + rsp.paid_amount;
	        msg += '카드 승인번호 : ' + rsp.apply_num;
	        //주문성공시 처리할 로직
	        location.href="../product/order.book?book_id="+${ProductDetailService.book_id}+"&count="+${procount};
	    } else {
	        var msg = '결제에 실패하였습니다.';
	        msg += '에러내용 : ' + rsp.error_msg;
	    }
	    alert(msg);
	});
} 
</script>
<script>
$(function(){
	$(".material-icons").click(function(){
	var rkqt = ${ProductDetailService.book_price*(1-ProductDetailService.discount_rate*0.01)}*$("#quantity").val()+" WON";
		$("#total").html(rkqt);
	});
	//죽시구매 결제없이 테스트용
	/* $('#porduct_detail_btn_go_buy').click(function(){
		location.href="../product/order.book?book_id="+${ProductDetailService.book_id}+"&count="+${procount};		
	}); */
});
</script>
<style>
textarea{
	resize:none;
	width:100%;
	height:350px;
	border:0;
}
</style>
</head>

<body>

<div class="rab">
	<!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
	<!--메인  -->
	<div class="maincontainer">	
	  	   <br>
	<br>
	홈 > 상세정보
	<br>
	<hr/>
	   

	<table >
					<tr>
					    <td rowspan="9" style="width:200px;height:300px;border:1px solid gray;"><img src= "/img/${ProductDetailService.book_image}"	 /></td>   
					<th>책번호 </th>
					<td colspan="2"> <c:out value="${ProductDetailService.book_id}"/></td>
					
					</tr>
					
					<tr>
					    <th>책제목 </th>
					    <td colspan="2"><c:out value="${ProductDetailService.book_title}"/></td>
					    
					</tr>
					
					<tr>
					    <th>정가 </th>
					    <td> <c:out value="${ProductDetailService.book_price}"/></td> 
					    <td><b>할인률 : </b><c:out value="${ProductDetailService.discount_rate}%"/></td>    
					</tr>
			
					
					<tr>       
					    <th>저자 </th>
					    <td colspan="2"> <c:out value="${ProductDetailService.author}"/></td>
					       
					</tr>    
					
					<tr>       
					    <th>출판사 </th>
					    <td colspan="2"> <c:out value="${ProductDetailService.publishing_com}"/></td>
					       
					</tr>    
					
					<tr>       
					    <th>출판일 </th>
					    <td > <c:out value="${ProductDetailService.publishing_date}"/></td>
					      
					</tr>    
					
					<tr>
						<th > 주문수량 :</th> 
							<td colspan="3">	 <span class="material-icons" id="minus" onclick='count("minus")' style="cursor: pointer; ">
								 <i class="material-icons" id="minus">remove</i> </span>
								
								 <input type="text" class="quantity" id="quantity" value="1">
								 <span class="material-icons" id="plus" onclick='count("plus")' style="cursor: pointer;">
								 <i class="material-icons" id="plus">add</i></span>
						 </td>
					</tr>
					<tr>
						<th> TOTAL </th>
							 <td id="total" colspan="3"><fmt:formatNumber pattern="0">${ProductDetailService.book_price*(1-ProductDetailService.discount_rate*0.01)}</fmt:formatNumber> 
						 </td>
					</tr>
					<tr>
						<td colspan="3" class="right">
						    <button class="porduct_detail_btn_go_cart" id="porduct_detail_btn_go_cart" onclick="location.href='../order/addBasket.book?no=${ProductDetailService.book_id}&quantity='+document.getElementById('quantity').value;">ADD TO CART</button>
						    <button class="porduct_detail_btn_go_buy" id="porduct_detail_btn_go_buy" onclick="requestPay()">BUY NOW</button>
					    </td>
					</tr>
					<tr>
					<th colspan="4">책소개</th>
					</tr>
					<tr>
					<td  colspan="4"><textarea class="text" rows="15" cols="140" readonly="readonly">${ProductDetailService.book_content}</textarea> 
					</td>
					</tr>
					<c:if test="~~~~product_Master_Only">
						<tr id="productMasterOnly">
							<td>
								<td><b>보유수량 :</b> <c:out value="${ProductDetailService.book_count}"/></td> 
								<td><b>등록일 :</b> <c:out value="${ProductDetailService.date}"/></td> 
							</td>
						</tr>
					</c:if>
	</table>
		<!--리뷰  -->
		<!-- 	<div>
				<h2>상품 Review</h2>
				<p>* 별점 및 리뷰 작성 후 작성하기 버튼을 클릭해 주세요.</p>
			</div> -->
			<br> 
			<div style="float: left;width=20%;">${ProductDetailService.book_title}을 구매한 분들의 리뷰</div>
			<div style="float: right;width=20%;">
				<%-- <button id="review_write_btn" OnClick="location.href='<%=request.getContextPath()%>/review/writer.book?book_id=${ProductDetailService.book_id}&review_pro_title=${ProductDetailService.book_title}'" style="cursor:pointer;">리뷰 작성</button> --%>
 			</div>
			<br>
			<hr />
			<div class="product_review">
				<table class="table" id="review_write" width=100%>
					<tr>
						<th width="15%">ID</th>
						<th width="40%">리뷰내용</th>
						<th width="15%">상품명</th>
						<th width="7%">별점</th>
						<th width="10%">작성일</th>
						<th width="18%"></th>
					</tr>
				</table>
			</div>

			<c:if test="${review_list.hasArticles()}">
				<c:forEach var="review_list_all" items="${review_list.content}">
					<table class="table" name="review_writer" width=100%>
						<tr>
							<th width="15%"><c:out value="${review_list_all.review_mem_id}" /></th>
							<th width="40%"><c:out value="${review_list_all.review_write}" /></th>
							<th width="15%"><c:out value="${review_list_all.review_pro_title}" /></th>
							<c:set var="reviewStar" value="${review_list_all.review_star}" />
								<%-- 별점 --%>
						<c:choose>
							<%-- if(a == 1){ --%>
							<c:when test="${reviewStar == 1}">
								<td width="7%">★☆☆☆☆</td>
							</c:when>
							<%-- if(a == 2){ --%>
							<c:when test="${reviewStar == 2}">
								<td width="7%">★★☆☆☆</td>
							</c:when>
							<%-- if(a == 3){ --%>
							<c:when test="${reviewStar == 3}">
								<td width="7%">★★★☆☆</td>
							</c:when>
							<%-- if(a == 4){ --%>
							<c:when test="${reviewStar == 4}">
								<td width="7%">★★★★☆</td>
							</c:when>
							<%-- if(a == 5){ --%>
							<c:when test="${reviewStar == 5}">
								<td width="7%">★★★★★</td>
							</c:when>
						</c:choose>
							
							<th width="10%"><c:out value="${review_list_all.reg_date}" /></th>
							<th width="18%">
								<c:if test="${AUTHUSER.id==review_list_all.review_mem_id||!empty ADMIN}">
									<button  id="reviewUpdateBtn" OnClick="location.href='<%=request.getContextPath()%>/review/update.book?book_id=${ProductDetailService.book_id}&review_mem_id=${review_list_all.review_mem_id}&review_pro_title=${review_list_all.review_pro_title}&review_write=${review_list_all.review_write}&review_star=${review_list_all.review_star}&review_id=${review_list_all.review_id}'" style="cursor:pointer;">수정</button>
									<button id="reviewDeleteBtn" OnClick="location.href='<%=request.getContextPath()%>/review/delete.book?book_id=${ProductDetailService.book_id}&review_pro_title=${ProductDetailService.book_title}&review_id=${review_list_all.review_id}'" style="cursor:pointer;" >삭제</button>
								</c:if>
							</th>
						</tr>
					</table>
				</c:forEach>
				<c:if test="${review_list.hasArticles()}">
		<table border="0" width="90%" style="text-align:center; margin-right:auto;margin-left:auto;">
			<tbody>

				<tr>
					<td class="pageNo">
						<%-- p653 41라인
								 조건문활용하여 이전으로 넘어갈수 있는 경우에는
						         시작페이지(startPage가 0보다 큰 경우)
						    <<prev 출력 --%>
						    
						<c:if test="${review_list.startPage>5}">    
							<a href="<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=${ProductDetailService.book_id }&pageNo=${review_list.startPage-5}"> &lt;&lt;prev </a>				
						</c:if>
						
						<%--p653 43라인
								페이징처리에서 한번에 출력할 페이수만큼 반복처리
								형식
								<c:forEach begin="0시작값" end="10종료값" step="2증감값" var="i변수명">		
									<a href="#~~~">[${i}]</a>   [0] [2] [4] [6] [8] [10]
								</c:forEach>
								 --%>
						<c:forEach begin="${review_list.startPage}" 
						           end="${review_list.endPage}" step="1" 
						           var="pNo">	
							<a href="<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=${ProductDetailService.book_id}&pageNo=${pNo}">[${pNo}]</a>
						</c:forEach>
						
						<%--조건문활용하여 다음으로 넘어갈수 있는 경우에는
						    endPage<totalPages
						    next>> 출력 --%>
						<c:if test="${review_list.endPage<review_list.totalPages}">   
							<a href="<%=request.getContextPath()%>/mainSub/bookDetail.book?book_id=${ProductDetailService.book_id}&pageNo=${review_list.startPage+5}"> next&gt;&gt; </a>
						</c:if> 
					</td>
				</tr>
			</tbody>
		</table>
	</c:if>	
			</c:if>
		</div>

		<!--바텀 -->
		<jsp:include page="//module/bottom_Mj.jsp" flush="true" />
	</div>
</body>
</html>