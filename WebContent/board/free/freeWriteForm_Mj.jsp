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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_searchlist_Mj2.css">
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
	<jsp:include page="//module/sideBorderMenu_Mj.jsp" flush="true"/>
	<!--메인  -->
		<div class="mypage">
		<br>
		<br>
	   홈 > 고객센터 > 자유게시판 > 글쓰기
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
	<c:if test="${!empty AUTHUSER}">
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

	<form action="/board/freeboardwrite.book" method="post">
		<table>
			<tr>
				<th>
					글쓴이(id) :
				</th>
				<td>	
					<input type="text" class="text" name="id" value="${AUTHUSER.id}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<th>
					글쓴이(name) : 
				</th>
				<td>
					<input type="text" class="text" name="name" value="${AUTHUSER.name}" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<th>
					제목:
				</th>
				<td>
					<input type="text" class="text" name="title" value=""/>
					<c:if test="${errors.title}">제목을 입력하세요</c:if>
				</td>
			</tr>
			<tr>
				<th>
		
					내용:<br/>
				</th>
				<td>
					<textarea class="text" name="content" rows="10" cols="80"></textarea>
				</td>
			</tr>
			<tr>
				<td class="bottom">
					<input class="bottomMenu" type="submit" value="새 글 등록"/>
				</td>
			</tr>
		</table>
	</form>	
 </div>
	   
	</div>

	
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</div>
</body>
</html>