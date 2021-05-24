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
	<div class="maincontainer">	
	<jsp:include page="//module/sideBorderMenu_Mj.jsp" flush="true"/>
	<!--메인  -->
		<div class="mypage">
		<br>
		<br>
	   홈 > 고객센터 > 자유게시판 > 글수정
	   <br>
	   <hr>
	
	<%--
	 //ModifyArticleHandler.java에서 
	 //Map방식으로 error정보를 담는 Model를 넘겨주었다
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		
		//(session)로그인한 user의 id+(parameter로 받은)글번호
		//+(DB)글제목,(DB)글내용 을 객체로 생성해서 Model로 넘긴다
			ModifyRequest modReq = new ModifyRequest(
								authUser.getmId(),
								no,articleData.getArticle().getTitle(),
								articleData.getContent().getContent());		
	   	request.setAttribute("modReq",modReq);	
	 --%>
	
	<%-- <hr/>*pageNo = ${pageNo}<hr/> --%>
	<form action="modify.book" method="post">
		<input type="hidden" name="no" value="${modReq.articleNumber}"/>
		<input type="hidden" name="pageNo" value="${pageNo}"/>
		<input type="hidden" name="mid" value="${modReq.userId}"/>
		<table>
			<tr>
				<th>
					번호  
				</th>
				<td>
					${modReq.articleNumber}				
				</td>
			</tr>
			<tr>
				<th>
					ID
				</th>
				<td>
					${modReq.userId}
				</td>
			</tr>
			<tr>
				<th>
					이름 
				</th>
				<td>
					${AUTHUSER.name}
				</td>
			</tr>
			<tr>
				<th >
					제목:
				</th>
				<td >
					<input class="title" type="text" name="title" value="${modReq.title}"/>
					<c:if test="${errors.title}">제목을 입력하세요</c:if>
				</td>
			</tr>
			<tr>
				<th>
					내용:
				</th>
				<td>
					<textarea name="content" class="text" rows="20" cols="80">${modReq.content}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="bottom">
					<input class="bottomMenu" type="submit" value="글 수정"/>
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