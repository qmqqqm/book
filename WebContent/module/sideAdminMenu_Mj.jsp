<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<div class="container sidepmenurab">			
   	<ul>
        <li class="sidemenu"><a  href="<%=request.getContextPath()%>/admin/staff.book"><label class="sidemenutext">직원관리</label></a></li>
        <li class="sidemenu"><a href="<%=request.getContextPath()%>/admin/sales.book"><label class="sidemenutext">매출 및 주문관리</label></a></li>
        <li class="sidemenu"><a  href="<%=request.getContextPath()%>/admin/member.book"><label class="sidemenutext">회원관리</label></a></li>
        <li class="sidemenu"><a  href="<%=request.getContextPath()%>/admin/board.book"><label class="sidemenutext">공지사항관리</label></a></li>
        <li class="sidemenu"><a  href="<%=request.getContextPath()%>/board/qnalist.book"><label class="sidemenutext">Q&A관리</label></a></li>
        <li class="sidemenu"><a  href="<%=request.getContextPath()%>/product/selectAllMaster.book""><label class="sidemenutext">상품관리</label></a></li>
    </ul>		
</div>