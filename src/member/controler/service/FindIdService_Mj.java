package member.controler.service;



import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import member.controler.service.dao.MemberDAO_Mj;

public class FindIdService_Mj {
	String id=null;
	MemberDAO_Mj memDao=new MemberDAO_Mj();
	public String findId(String name, String hp, String birth) throws SQLException {
		Connection conn=null;
		try {
		conn=ConnectionProvider_Mj.getConnection();
		id=memDao.getId(conn,name,hp,birth);
		
		
		return id;
		}finally {
			JdbcUtil_Mj.close(conn);
		}
	}

}
