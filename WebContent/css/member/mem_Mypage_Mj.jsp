<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBKBOOK</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/normalize.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mem_Mypage_Mj.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boa_Freelist_Mj.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/upload_Mj.css">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/pabi/pabi.ico" type="image/x-icon"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#file").change(function(){
	var fileValue = $("#file").val().split("\\");
	var fileName = fileValue[fileValue.length-1]; // 파일명
	$(".getfile").val(fileName);
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
	<jsp:include page="//module/sideMenu_Mj.jsp" flush="true"/>
	<!--메인  -->
		<div class="mypage">
	   	<br>
		<br>
		홈 > 마이페이지
		<br>
		<hr/>
	    <br>
	    <!-- 프로필 사진 확인및 변경-->
		<form action="<%=request.getContextPath()%>/upload" method="post" enctype="multipart/form-data">
			<div class="imagerab">
			
				
				<input type="hidden" name="id" value=${AUTHUSER.id}>
				<input type="hidden" class="getfile" name="getfile" >
				
				
							
						<div class="image">
					  	<img id="preview" src="<%=request.getContextPath()%>/img/${memData.img}"  alt=""><br>
						</div>				  	
				  	
						<input class="bottomMenu" type="file" id="file" name="file" accept="image/*"><br>
					
				
			<!-- 	<tr>
					<td>
						<input type="file" id="file" name="file" accept="image/*">					</td>
				</tr> -->
					
							<input class="bottomMenu" type="submit" id="senfFile" value="사진변경">
						
					
					
			</div>		
			</form>
			
			<script type="text/javascript">
				var file = document.querySelector('#file');
		
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
			<div class="formrab">
			<!-- 회원정보 변경및 수정  -->
			<form name="frm" id="frm" action="<%=request.getContextPath()%>/member/modify.book" method="get">
				<table >   
			    <tr>	    
			        <th class="index">ID</th>
			        <td><input type="text" name="id" id="id" value="${AUTHUSER.id}" readonly="readonly">
			        </td>	    	
			    </tr>	   
			    <tr>
			        <th class="index">이름</th>
			        <td><input type="text" name="name" id="name" value="${memData.name}" placeholder="이름를 입력하세요">
			        <c:if test="${errors.name}"> 이름를 입력하세요</c:if>
			        </td>	        
			    </tr>
			    <tr>
			        <th class="index">생년월일</th>
			        <td>        
			        <%-- <fmt:parseDate value="${memData.birth}" var="date" pattern="yyyy-MM-dd"/> --%>
			        <input type="text" value="${birth}" name="birth" id="birth">  </td>
			        <%-- <fmt:formatDate pattern="yyyyMMdd" value="birth"/> --%>
			    </tr>
			    <tr>
			        <th class="index">성별</th>
			        <td>      <input type="text" value="${memData.gender}" name="gender" id="gender" ></td>
			    </tr>
			    <tr>
			        <th class="index">전화번호</th>
			        <td><input type="text" value="${memData.hp}" name="hp" id="hp" ></td>
			    </tr>
			    <tr>
			        <th class="index">포인트</th>
			        <td><input type="text" name="point" id="point" value="${memData.point}" readonly="readonly">	        
			        </td>    	
			    </tr>
			    <tr>
			        <td class="bottom" colspan="2" class="index">
			        	<input class="bottomMenu" type="submit" value="수정">
			        	<a class="bottomMenu" href="<%=request.getContextPath()%>/member/delete.book?id=${AUTHUSER.id}">탈퇴</a>
			        </td>    	
			    </tr>	   
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