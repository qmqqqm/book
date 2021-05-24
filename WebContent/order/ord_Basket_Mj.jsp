<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBKBOOK</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/frame_Mj.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/cartList_Ji.css">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/pabi/pabi.ico" type="image/x-icon"/>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<c:set var="qq" value="qq()"/>
<script type="text/javascript">
IMP.init('imp88822222'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용

function qq(){	
		//전체 총합구하기 함수
		 var sum = 0;
		 $("input[name='check']:checked").each(function() {
			  for(var i=0; i<$("input[name='check']").val.length;i++) {
			        if ($("input[name='check']").is(':checked') == true) {
			        	var price= parseInt($(this).val());
			            sum += price;
			        } //if
			  }//for
			  
		})//each
		console.log(sum); 
	 return sum; 
 }


function requestPay() {
	IMP.request_pay({
	    pg : 'html5_inicis', // version 1.1.0부터 지원.
	    pay_method : 'card',
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : '주문명:결제테스트',
	    amount :${qq},
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
	        $("#orderGo").submit();
	    } else {
	        var msg = '결제에 실패하였습니다.';
	        msg += '에러내용 : ' + rsp.error_msg;
	    }
	    alert(msg);
	});
}
</script>
<script>
function totalSum(){	//전체 총합구하기 함수
	var sum = 0;
	$("input[name='check']:checked").each(function() {
		  for(var i=0; i<$("input[name='check']").val.length;i++) {
		        if ($("input[name='check']").is(':checked') == true) {
		        	var price= parseInt($(this).val());
		            sum += price;
		        } //if
		  }//for
	})//each
	//console.log(sum); //확인용
	$("input[name='total']").val(sum+'원');
}
</script>
<script>
$(function(){
	totalSum();
	$(".dltbtn").click(function (){
		if(confirm('삭제하시겠습니까?')){ //확인
			var pro_no = $(this).attr('value');
			
			/*var tr = $("1번tr").val();
			if(tr == null){
				$("담긴 상품이 없습니다 tr").show();
			}
			*/
			location.href="./deletebasket.book?pro_no="+pro_no;
		}else{ //취소
			return;
		}
	})//$(".dltbtn").click
	
	$(".alldltBtn").click(function (){
		if(confirm('장바구니에 담긴 상품을 전체 삭제하시겠습니까?')){ //확인
			location.href="./allDelete.book";
		}else{ //취소
			return;
		}
	})//$(".allDltBtn").click
	
/*$(".orderBtn").click(function (){
	if(confirm('장바구니에 담긴 상품을 전체 주문하시겠습니까?')){ //확인
		location.href="./payment.book";
	}else{ //취소
		return;
	}
})//전체주문 */
	

	$("#allCheck").change(function(){
		var is_check = $(this).is(":checked");
		$("input.check").prop("checked",is_check);
		totalSum();
	})
	
	$("input[name='check']").click(function(){
		totalSum();
	 })//check된 상품 합계
	 
	 $("input[name='check']").click(function(){
		$('#allCheck').prop("checked",false);
	 }) //all체크박스 풀기

	 $(".orderBtn").click(function(){
		var chkArr = new Array();
		if($("input[name='check']").is(":checked")==false){
			alert('상품을 선택하여 주십시오.')
			return false;
		}
			$("input[name='check']:checked").each(function(){
				chkArr.push($(this).attr("data-cartNo"));
			});
			$("#chk").val(chkArr);
			
			if(confirm('주문하시겠습니까?')){				
				requestPay();
				//결제추가
				
				//$("#orderGo").submit();
			}else{
				return;
			}
	}) //배열에 cart_No 저장
	


})

</script>
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
	홈 > 장바구니
	<br>
 	<hr/>
 
 	
<c:if test= "${!empty AUTHUSER}"> 
 	<h2>${AUTHUSER.id}님 장바구니</h2>
 	<form name="frm" method="post" id="orderGo" action="./order.book">
	<table style="text-align:center;" class="cart">
	<caption>
	<p style="text-align:left;">주문하실 상품을 선택해주세요.</p></caption>
		<tr>
			<th>
				<input type="checkbox" name="allCheck" id="allCheck" checked="checked"/>
			</th>
			<th colspan="3" style=" width:60%;"> 상품정보</th>
			<th>
				가격
			</th>
			<th>
				수량
			</th>
			<th>
				합계
			</th>
			<th>
				삭제
			</th>
		</tr>
			<%-- 페이징처리에서 한페이지에 출력할 게시물건수만큼 반복처리
						 컨트롤러(ListArticleHandler)에서      model로 받아왔다
						 List<BasketDTO> basket = basketService.getBasketList();
						 request.setAttribute("basket",basket);
						 
						 <c:forEach var="변수명" items="${모델의 속성명}">
						 	 반복해서 처리할 code
						 </c:forEach>
				 --%>
		<c:if test="${ ! empty basket }">	
			<c:forEach var="basketlist" items="${basket}"> 
				<tr>
					<td><input type="checkbox" name="check" class="check" value="${basketlist.total}" checked="checked" data-cartNo="${basketlist.c_No}"/>
					</td>
					<td><img src= "/img/${basketlist.p_Image}" width="60" /></td> 
					<td>${basketlist.p_Name}</td> 
					<td>${basketlist.p_Author} / ${basketlist.p_Publicher}</td>
					<%-- data는  forEach부분에서 data변수를 의미하고
						   data변수에는 Ariticle객체가 들어가 있다
						      따라서
						   data.writer.name는
						   Ariticle.getWriter().getName()을 의미한다
						   
						   data.getWriter()를 호출하면 Writer객체를 받는다
						     이렇게 받은 Writer클래스의 name속성의 값을 받기위해서는
						   Writer클래스의 getName()를 호출해야 한다. 	   
					--%>
					<td>${basketlist.p_Price}원</td>
					<td>${basketlist.amount}개</td>
					<td>${basketlist.total}원 </td>
					<td><button type="button" class="dltbtn" value="${basketlist.p_No}" />삭제하기</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="8" class="lastTd">
				  <input type="hidden" name="chk[]" id="chk" value="" />
				 <span>전체 합계 : </span><input type="text" name="total" disabled="disabled" size="5"/>
				  <button type="button" class="orderBtn" />결제하기
				  <button type="button" class="alldltBtn" />전체 삭제
				</td>
			</tr>
		</c:if>
		<c:if test="${empty basket}">
			<tr>
				<td colspan="8">현재 장바구니가 비었습니다.</td>
			</tr>
		</c:if>
	</form>
	</table>
	</div>
 </c:if> 

 	<c:if test="${empty AUTHUSER}">
	 	<script>
	 	alert("로그인 해주세요");
		location.href="../member/login.book";
		</script>
	</c:if>
	
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>