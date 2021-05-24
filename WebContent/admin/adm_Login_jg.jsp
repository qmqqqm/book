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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/login2_Mj.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>

<body>

	<!--탑 부터 바톰 까지 전체 감싸주는 부모  태그-->
<div class="rab">
	<!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
	<!--메인  단 시작-->
	<div class="maincontainer">	
	<br>
	<br>
	<!-- 네비게이션  -->
	홈>관리자로그인
	<br>
	<hr/>  	

	<h1>관리자로그인</h1>
	<hr class="underLine">
	<br>
	<br>
	<br>
	<br>
	<!--로그인 감싸주는 DIV  -->
	<div  class="bg-img ">
		<div class="formrab" style="position: relative; left:150px; top:100px">
		<form name="loginFrm" id="loginFrm" 
		      class="form-container" action="../admin/admin.book" method="post">
			<!-- <h1>Login</h1> -->
			<table >
			<tr>
			<h2>관리자 로그인</h2>
		    <hr>
		    <br>
		    </tr>
				<tr>
					<td>					
			<!-- <label for="mid">ID</label> -->
			
			<label for="mid">ID</label>
			<input type="text" name="mid" id="mid" 
						 required="required" placeholder="Enter Id"/>
				  </td>
				  <td rowspan="4">
				  	<input type="submit" class="btn" value="로그인"/>
				  </td>
			  <tr>
			  	<td>
				</td>
			<tr>	
				<td>	 
			<label for="mpwd">Password</label>
			<input type="password" name="mpwd" id="mpwd" 
						 required="required" placeholder="Enter Password"/>
			 	</td>
			 </tr>
			<tr>
				<td>
				</td>
			</tr>	 
			
			<!-- <input type="reset"  class="btn cancel" value="cancel" /> -->
			<%-- class="클래스명1 클래스명2 클래스명3..." 
				   class속성의 값으로  여러 클래스명을 명시하여 적용할 수 있다
			--%>		
			</table>	 			 
		</form>
		</div>
	</div>
	</div>

	
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>