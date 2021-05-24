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
	<!--메인  -->
	<div class="maincontainer">
	<jsp:include page="//module/sideMenu_Mj.jsp" flush="true"/>
	<!--메인  -->
		<div class="mypage">
		<br>
		<br>
	홈 > 마이페이지 > 회원정보수정
	<br>
	<hr>	
	  <form name="changePwdFrm" id="changePwdFrm" 
				action="<%=request.getContextPath()%>/member/changepwd.book" method="post">
		<table>
		<tr>
			<th>
				<label for="curPwd">현재 암호</label>
			</th>
			<td>
				<input type="password" name="curPwd" id="curPwd" />
				<c:if test="${errors.curPwd}">현재 암호를 입력하세요.</c:if>
				<c:if test="${errors.badCurPwd}">현재 암호가 일치하지 않아요.</c:if>
			</td>
		</tr>
		<tr>
			<th>	
				<label for="newPwd">변경할 암호</label>
			</th>
			<td>
				<input type="password" name="newPwd" id="newPwd" />
				<c:if test="${errors.newPwd}">새 암호를 입력하세요.</c:if>
			</td>
		</tr>
		<tr>
			<td class="bottom" colspan="2">
				<input class="bottomMenu" type="submit" value="암호 변경"/>
				<input class="bottomMenu" type="reset"  value="취소"/>
			</td>
		</table>
	</form>	
	</div>

	
<!--바텀 -->
</div>
<div>
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</div>
</body>
</html>