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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
textarea{
	resize:none;
	width:90%;
	height:200px;
	margin:5px 5px;
}
.ttl{
	width:90%;
	margin:5px 5px;
}
</style>
</head>

<body>

<div class="rab">
	<!-- 탑메뉴 -->
	<jsp:include page="//module/topMenu_Jg.jsp" flush="true"/>
	<!-- 세컨메뉴 -->
	<jsp:include page="//module/secMenu_Mj.jsp" flush="true"/>	
	<div class="maincontainer">	
	<jsp:include page="//module/sideAdminMenu_Mj.jsp" flush="true"/>
	<!--메인  -->
		<div class="mypage">
		<br>
		<br>
	   홈 > 관리자페이지 > 공지사항 > 글쓰기
	   <br>
	   <hr>
	<%-- 
	<p>보고싶은페이지(user가 선택한 페이지)=${articlePage.currentPage }</p>
 --%>
<%-- 
  //로그인한 유저의 정보를 session에서 가져와 화면에 출력할 예정
	/*임시로... 예를 들면...
	 * session.setAttribute("MID",  "hongid");
	 * session.setAttribute("MNAME", "홍GD");
	 * session.setAttribute("MNICKNAME", "의적");
	 */
	//우리는 session.setAttribute("AUTHUSER", user);  --%>
	<c:if test="${!empty ADMIN}">
	<%-- *세션에 담긴 로그인한 유저정보 = ${AUTHUSER}<br/>
	${AUTHUSER.id}님 접속중  --%>
	</c:if>
	
	<%--
		readonly : 선택 및 copy가능. server로 data보내기 가능
		The value of a read-only input field will be sent when submitting the form!
		
		disabled : server로 data보내기 가능x
		The value of a disabled input field will not be sent when submitting the form!
	
		----------------------
	 //WriteArticleHandler.java에서 
	 //Map방식으로 error정보를 담는 Model를 넘겨주었다
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
	 --%>

	<form action="./boardWrite.book" method="post">
		<p>
			글쓴이(id) : <input type="text" name="id" value="${ADMIN.id}" readonly="readonly"/>
		</p>
		<p>
			제목<br/>
			<input type="text" class="ttl" name="title" value=""/>
			<c:if test="${errors.title}">제목을 입력하세요</c:if>
		</p>
		<div>내용<br/>
		<textarea name="content" rows="5" cols="30"></textarea>
		</div>
		<div>
		<input type="submit" value="새 글 등록"/></div>
	</form>	
 </div>
	   
	</div>

	
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>