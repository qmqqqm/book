<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<div class="container sidepmenurab">			
   	<ul>
        <li class="sidemenu"><a  href="<%=request.getContextPath()%>/admin/board.book"><label class="sidemenutext">공지사항</label></a></li>
        <li class="sidemenu"><a href="<%=request.getContextPath()%>/board/qnalist.book"><label class="sidemenutext">Q&A</label></a></li>
        <li class="sidemenu"><a  href="<%=request.getContextPath()%>/board/freeboardlist.book"><label class="sidemenutext">자유게시판</label></a></li>
      
    </ul>		
</div>