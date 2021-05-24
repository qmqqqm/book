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

	<!--메인  -->
		<div class="mypage">
	  <br>
	   <br>
	   홈 > 로그인 > ID찾기
	   <br>
	   <hr>
	   <br>
	   <form name="frm" id="frm" action="<%=request.getContextPath()%>/member/findId.book" method="post">
		<table >
	   		<tr>
	        <th class="index">이름</th>
		        <td><input class="intext" type="text" name="name" id="name"  placeholder="이름를 입력하세요">
		        
		        </td>        
		    </tr>
		    <tr>
		        <th class="index">생년월일</th>
		        <td><input class="intext" type="text" value="" name="birth" id="birth" placeholder="19990101 형식으로 생년월일를 입력하세요"> </td>
		    </tr>
		    <tr>
		        <th class="index">전화번호</th>
		        <td><input class="intext" type="text" value="" name="hp" id="hp" placeholder="010-1111-2222 형식으로 핸드폰 번호를 입력하세요"></td>
		    </tr>
		    <tr>
		    	<td class="bottom">
		    	 	<input class="bottomMenu" type="submit" value="아이디찾기" >
		    	 </td>
		    </table>
	   		</form>
	   		
	   		<table>
	   			<tr>
	   				<td class="bottom">
	   				<%-- <c:if test="${userID==null}">
	   				입력하신정보로 가입된 회원이 없습니다
	   				</c:if>   --%>
	   				<c:if test="${!empty userID}">
	   				아이디는 ${userID} 입니다
	   				</c:if>
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