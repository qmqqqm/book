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
<script src="<%=request.getContextPath()%>/js/product_Insert_JS.js"></script>
<script>
$(function(){
	$("#porduct_Update_add").click(function(){
		document.porduct_Update_add.submit();
		document.porduct_Update_add.action="/product/updateMaster.book"		
	});
	
	$("#porduct_Delete_btn").click(function(){
		//alert($("#book_id").val());
		$("#frm").prop("action","/product/delete.book?book_id="+$("#book_id").val())
		
	});
	
})

</script>
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
	<form id="frm" action="<%=request.getContextPath()%>/product/updateMaster.book" enctype="multipart/form-data"  method="post" >
	<input type="hidden" value="${product_SelectOne_list.book_id}" name="book_id"/>
		<div class="imagerab">
			<p>파일 사이즈 200*150px</p>
			<div class="image">
			  	<img id="preview"  src="<%=request.getContextPath()%>/img/${product_SelectOne_list.book_image}" width="200" ><br>
			</div>
			<input type="file" id="getfile" name="file" accept="image/jpeg/jpg/*">
	
		</div>
		<div class="tablerab">
			<table border="0">
			<tr>
			<th width="100">책번호</th>
			<td width="400"><input type="text" name="book_id"  id="book_id" value="${product_SelectOne_list.book_id}" readonly="readonly"></td>	
			</tr> 
			<tr>
			<th>
			책이미지
			</th>
			<td>
			<input class="getfile" type="text" name="book_image" value="${product_SelectOne_list.book_image}">
			</td>
			</tr>			
			<tr>
			<th width="100">책종류</th>
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
			<th width="100">책이름</th>
			<td width="400"><input type="text" name="book_title" value="${product_SelectOne_list.book_title}"></td>	
			</tr>
			<tr>
			<th width="100">저자</th>
			<td width="400"><input type="text" name="author" value="${product_SelectOne_list.author}"></td>	
			</tr>
			<tr>
			<th width="100">출판사</th>
			<td width="400"><input type="text" name="publishing_com" value="${product_SelectOne_list.publishing_com}"></td>	
			</tr>
			<tr>
			<th width="100">가격</th>
			<td width="400"><input type="text" name="book_price" value="${product_SelectOne_list.book_price}">원</td>	
			</tr>
		<!-- 	<tr>
			<td width="100">포인트</td>
			<td width="400"><input type="text" name="proPoint"></td>	
			</tr> -->
			<tr>
			<th width="100">할인률</th>
			<td width="400"><input type="text" name="discount_rate" value="${product_SelectOne_list.discount_rate}">%</td>	
			</tr>
			<tr>
			<th width="100">책소개</th>
			<td width="400"><input type="text" name="book_content" value="${product_SelectOne_list.book_content}"></td>	
			</tr>
			<tr>
			<th width="100">재고</th>
			<td width="400"><input type="text" name="book_count" value="${product_SelectOne_list.book_count}"></td>	
			</tr>
			<tr>
			<th width="100">출판일</th>
			<td width="400"><input type="text" name="publishing_date" value="${product_SelectOne_list.publishing_date}"></td>	
			</tr>
			<tr>
			<td colspan="2" class="bottom">
			<input type="submit" class="porduct_Update_add bottomMenu" id="porduct_Update_add" name="porduct_Update_add" value="Update" >
			<input type="reset" class="porduct_Update_reset bottomMenu" id="porduct_Update_reset" value="reset">
			<input type="submit" class="porduct_Delete_btn bottomMenu" id="porduct_Delete_btn" name="porduct_Delete_btn" value="상품삭제"  >
			</td>
			</tr>
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
	              //로컬 이미지를 보여주기
	              document.querySelector('#preview').src = reader.result;

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
