<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<div class="container sidepmenurab">			
   	<ul>
        <li class="sidemenu"><a  href="<%=request.getContextPath()%>/member/mypage.book?id=${AUTHUSER.id}"><label class="sidemenutext"}>내정보조회</label></a></li>
        <li class="sidemenu"><a href="<%=request.getContextPath()%>/member/changepwd.book"><label class="sidemenutext">비밀번호 변경</label></a></li>
        <li class="sidemenu"><a  href="<%=request.getContextPath()%>/member/memberOrder.book"><label class="sidemenutext">내구매목록</label></a></li>
      
    </ul>		
</div>