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
<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/pabi/pabi.ico" type="image/x-icon"/>
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
	홈 > 구매목록
	<br>
 	<hr/>
 
 	
<%-- 	<c:if test="${empty AUTHUSER}">
	 	<script>
	 	alert("로그인 해주세요");
		location.href="../member/login.book";
		</script>
	</c:if> --%>
	
 	<h2>${AUTHUSER.id}님 구매목록</h2>
	<table border="1" width="100%" style="text-align:center;">
	<caption>
	<p style="text-align:left;">구매 내역</p></caption>
		<tr>
			<th>상품정보</th>
			<th>
				가격
			</th>
			<th>
				수량
			</th>
		</tr>
		
	<%-- 	<c:if test="${ ! empty }">	
			<c:forEach var="" items=""> 
				<tr>
					<td><input type="checkbox" name="check" class="check" value="${basketlist.total}" checked="checked" data-cartNo="${basketlist.c_No}"/>
					</td>
					<td><img src= "/img/${basketlist.p_Image}.jpg" width="60" /></td> 
					<td>${basketlist.p_Name}</td> 
					<td>${basketlist.p_Author} / ${basketlist.p_Publicher}</td>
					data는  forEach부분에서 data변수를 의미하고
						   data변수에는 Ariticle객체가 들어가 있다
						      따라서
						   data.writer.name는
						   Ariticle.getWriter().getName()을 의미한다
						   
						   data.getWriter()를 호출하면 Writer객체를 받는다
						     이렇게 받은 Writer클래스의 name속성의 값을 받기위해서는
						   Writer클래스의 getName()를 호출해야 한다. 	   
					
					<td>${basketlist.p_Price}원</td>
					<td>${basketlist.amount}개</td>
					<td>${basketlist.total}원 </td>
					<td><button type="button" class="dltbtn" value="${basketlist.p_No}" />삭제하기</td>
				</tr>
			</c:forEach>
			<tr>
				<td>
				</td>
			</tr>
		</c:if> --%>
	</form>
	</table>
	</div>


	
	
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>