package member.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import member.controler.service.dao.MemberDAO_Mj;

public class FindPwdService_Mj {
	String pwd=null;
	MemberDAO_Mj memDao=new MemberDAO_Mj();
	public String findPwd(String id, String hp, String birth) throws SQLException {
		Connection conn=null;
		try {
			conn=ConnectionProvider_Mj.getConnection();
			pwd=memDao.getPwd(conn,id,hp,birth);
			
			
			
			return pwd;
		}finally {
			JdbcUtil_Mj.close(conn);
		}
	}

}
