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
	<br>
	<br>
	홈 > 로그인
	<br>
	<hr/>
	<h1>로그인</h1>
	<hr class="underLine">
	<br>
	가입을 환영합니다 로그인후 다양한 혜택을 경험하세요
	<br>
	<br>
	<!--로그인 감싸주는 DIV  -->
	<div  class="bg-img ">
		<div class="formrab">
		<form name="loginFrm" id="loginFrm" 
		      class="form-container" action="<%=request.getContextPath()%>/member/login.book" method="post">
			<!-- <h1>Login</h1> -->
			<table >
			<tr>
			<h2 class="labal">회원로그인</h2>
		    <hr>
		    <br>
		    </tr>
				<tr>
					<td>					
			<!-- <label for="mid">ID</label> -->
			
			<input type="text" name="mid" id="mid" 
						  placeholder="Id를 입력 하세요"/>
				  </td>
				  <td rowspan="4">
				  	<input type="submit" class="btn" value="로그인"/>
				  </td>
			  <tr>
			  	<td>
		    <!-- 컨트롤러에서 처리된 에러 모델값에따른 에러처리  -->
		    
			<c:if test="${errors.id}"><span class="error">ID를 입력하세요</span><br/></c:if>		  
			<c:if test="${errors.usernull}"><span class="error">존재하지 않는 아이디 입니다 </span><br/></c:if>		  
				</td>
			<tr>	
				<td>	 
			<!-- <label for="mpwd">Password</label> -->
			<input type="password" name="mpwd" id="mpwd" placeholder="비밀번호를 입력하세요"/>
			 	</td>
			 </tr>
			 <tr>
			 	<td>
			<c:if test="${errors.pwd}"><span class="error">비밀번호를 입력하세요</span><br/></c:if>	 
			<c:if test="${errors.notMatch}"><span class="error">회원정보를 다시 확인하세요</span><br/></c:if>	 
				</td>
			</tr>	
			<tr>
				<td class="findId" >
					<a href="<%=request.getContextPath()%>/member/findId.book">아이디 </a>/<a href="<%=request.getContextPath()%>/member/findPwd.book"> 비밀번호 찾기</a>
				</td>
			</tr>	 
			
			<!-- <input type="reset"  class="btn cancel" value="cancel" /> -->
			<%-- class="클래스명1 클래스명2 클래스명3..." 
				   class속성의 값으로  여러 클래스명을 명시하여 적용할 수 있다
			--%>		
			</table>	 			 
		</form>
		</div>
		<div class="imagerab">
		<img src="<%=request.getContextPath()%>/img/member/login.gif"/>
		</div>
	</div>
	</div>

	
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>
	 
	
		