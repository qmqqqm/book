<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jdbc.connection.ConnectionProvider_Mj" %>    
<%@ page import="java.sql.Connection,java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>dbconnTest.jsp(p586)</h2>
	<p>이 문서는 db connection test 중</p>
	<%
		//Connection이 필요할 때는
			//ConnectionProvider.java파일의 getConnection()호출하면된다
			try{
		Connection conn = ConnectionProvider_Mj.getConnection();
		out.println("getConnection success! *^^*");
			}catch(SQLException e){
		out.println("getConnection fail="+e.getMessage()); //browser에 출력
		application.log("getConnection fail",e);
			}
	%>
</body>
</html>








