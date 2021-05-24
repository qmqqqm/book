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

<script src="http://code.jquery.com/jquery-latest.min.js"></script>


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
	홈 > 관리자 > 상품관리
	<br>
	<hr/>
	<form action="<%=request.getContextPath()%>/upload/upload" method="post" enctype="multipart/form-data">
		<div class="imagerab">
			
			<p>파일 사이즈 200*150px
			<div class="image">
			  	<img id="preview" src=""  alt=""><br>
			</div>
			<input type="file" id="getfile" accept="image/*">
			<input type="submit" value="전송">
		</div>
		</form>
		<!-- <div class="tablerab">
			<table border="1">
			<tr>
			<td width="100">책이름</td>
			<td width="400"><input type="text" name="book_title"></td>	
			</tr>
			<tr>
			<td width="100">저자</td>
			<td width="400"><input type="text" name="author"></td>	
			</tr>
			<tr>
			<td width="100">출판사</td>
			<td width="400"><input type="text" name="publishing_com"></td>	
			</tr>
			<tr>
			<td width="100">가격</td>
			<td width="400"><input type="text" name="book_price"></td>	
			</tr>
			<tr>
			<td width="100">포인트</td>
			<td width="400"><input type="text" name="proPoint"></td>	
			</tr>
			<tr>
			<td width="100">할인률</td>
			<td width="400"><input type="text" name="discount_rate"></td>	
			</tr>
			<tr>
			<td width="100">책소개</td>
			<td width="400"><input type="text" name="book_content"></td>	
			</tr>
			<tr>
			<td width="100">카테고리</td>
			<td width="400"><input type="text" name="book_kind"></td>	
			</tr>
			<tr>
			<td width="100">재고</td>
			<td width="400"><input type="text" name="book_count"></td>	
			</tr>
			<tr>
			<td width="100">출판일</td>
			<td width="400"><input type="text" name="publishing_date"></td>	
			</tr>
			<tr>
			<td> -->
			<input type="submit" value="전송">
			</table>
		</div>
		<script type="text/javascript">
		var file = document.querySelector('#getfile');

		file.onchange = function () {
		    var fileList = file.files ;

		    // 읽기
		    var reader = new FileReader();
		    reader.readAsDataURL(fileList [0]);

		    //로드 한 후
		    reader.onload = function  () {
		        document.querySelector('#preview').src = reader.result ;
		    };
		};
		</script>

	

	
	</div>
	</div>
	
	
		
	<!--바텀 -->
	<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>

</body>
</html>
