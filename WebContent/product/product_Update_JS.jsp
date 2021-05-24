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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/upload_Mj.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="<%=request.getContextPath()%>/js/product_Insert_JS.js"></script>

<script type="text/javascript">
$(function(){
	   $("#getfile").change(function(){
	   var fileValue = $("#getfile").val().split("\\");
	   var fileName = fileValue[fileValue.length-1]; // 파일명
	   var fileImgName = fileName.split("."); // 파일명
	   $(".getfile").val(fileImgName[0]);
	   })
	});
</script>

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
	홈 > 관리자 > 상품수정
	<br>
	<hr/>
	<form action="<%=request.getContextPath()%>/product/updateMaster.book" method="post" >
	<input type="hidden" value="${product_Search_list.book_id}" name="book_id"/>
		<div class="imagerab">
			<tr>
			<p>파일 사이즈 200*150px</p>
				<div class="image">
				  	<img id="previewup"  src="<%=request.getContextPath()%>/img/${product_Search_list.book_image}" width="200" ><br>
				</div>
				<input type="file" id="getfileup" name="file" accept="image/jpeg/jpg/*">
			</tr>
		</div>
		<div class="tablerab">
			<table border="0">
			<tr>
			<td width="100">책번호</td>
			<td width="400"><input type="hidden" name="book_id"/>${product_Search_list.book_id}</td>	
			</tr> 
			<tr>
			<td width="100">책종류</td>
			<td width="400">
				 <select name="book_kind" id="book_kind" >
					<option style="display:none" value="">-선택-</option>
					<option value="베스트셀러">베스트셀러</option>
					<option value="전문도서">전문도서</option>
					<option value="학습도서">학습도서</option>
					<option value="소설">소설</option>
					<option value="만화">만화</option>
				</select>
			</td>	
			</tr>
			<tr>
			<td width="100">책이름</td>
			<td width="400"><input type="text" name="book_title" value="${product_Search_list.book_title}"></td>	
			</tr>
			<tr>
			<td width="100">저자</td>
			<td width="400"><input type="text" name="author" value="${product_Search_list.author}"></td>	
			</tr>
			<tr>
			<td width="100">출판사</td>
			<td width="400"><input type="text" name="publishing_com" value="${product_Search_list.publishing_com}"></td>	
			</tr>
			<tr>
			<td width="100">가격</td>
			<td width="400"><input type="text" name="book_price" value="${product_Search_list.book_price}">원</td>	
			</tr>
		<!-- 	<tr>
			<td width="100">포인트</td>
			<td width="400"><input type="text" name="proPoint"></td>	
			</tr> -->
			<tr>
			<td width="100">할인률</td>
			<td width="400"><input type="text" name="discount_rate" value="${product_Search_list.discount_rate}">%</td>	
			</tr>
			<tr>
			<td width="100">책소개</td>
			<td width="400"><input type="text" name="book_content" value="${product_Search_list.book_content}"></td>	
			</tr>
			<tr>
			<td width="100">재고</td>
			<td width="400"><input type="text" name="book_count" value="${product_Search_list.book_count}"></td>	
			</tr>
			<tr>
			<td width="100">출판일</td>
			<td width="400"><input type="text" name="publishing_date" value="${product_Search_list.publishing_date}"></td>	
			</tr>
			</table>
			<input type="submit" class="porduct_Update_add" id="porduct_Update_add" value="Update" onclick="<%=request.getContextPath()%>/product/updateMaster.book" >
			<input type="reset" class="porduct_Update_reset" id="porduct_Update_reset" value="reset">
			<button id="review_write_btn_del">
							<a  href="<%=request.getContextPath()%>/product/delete.book?book_id=${product_Search_list.book_id}">상품삭제</a></button>
		</div>
	<script type="text/javascript">
	      var file = document.querySelector('#getfileup');

	      file.onchange = function () {
	          var fileList = file.files ;

	          // 읽기
	          var reader = new FileReader();
	          reader.readAsDataURL(fileList [0]);

	          //로드 한 후
	          reader.onload = function  () {
	              //로컬 이미지를 보여주기
	              document.querySelector('#previewup').src = reader.result;

	              //썸네일 이미지 생성
	              var tempImage = new Image(); //drawImage 메서드에 넣기 위해 이미지 객체화
	              tempImage.src = reader.result; //data-uri를 이미지 객체에 주입
	              tempImage.onload = function () {

	              };
	          };
	      };
	      		</script>

	</form>
	</div>
</div>
</div>
<!--바텀 -->
<jsp:include page="//module/bottom_Mj.jsp" flush="true"/>
</body>
</html>