<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>


    <div class="container">
        <a class="logo" href="<%=request.getContextPath()%>/main.book"><img class="logo1" src="<%=request.getContextPath()%>/img/logo1.png"/></a>
        <form action="<%=request.getContextPath()%>/product/mainSearch.book">
        <input id="usersearch" name="usersearch" class="find" type="text">
        <input  id="find" name="find" class="findbutton" type="submit" value="">           
    </form>
    </div>
</header>
<nav>
	<div class="rab menurab">
		<div class="container secmenu">
		    <ul>
		        <li class="menu"><a href="<%=request.getContextPath()%>/mainSub/bookBest.book"><label class="menutext">베스트셀러</label></a></li>
		        <li class="menu"><a  href="<%=request.getContextPath()%>/mainSub/bookPro.book"><label class="menutext">전문도서</label></a></li>
		        <li class="menu"><a  href="<%=request.getContextPath()%>/mainSub/bookStudy.book"><label class="menutext">학습도서</label></a></li>
		        <li class="menu"><a  href="<%=request.getContextPath()%>/mainSub/bookNovel.book"><label class="menutext">소설</label></a></li>
				<li class="menu"><a  href="<%=request.getContextPath()%>/mainSub/bookManga.book"><label class="menutext">만화</label></a></li>
		    </ul>
		</div>
	</div>
</nav>
