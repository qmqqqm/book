package member.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import member.controler.service.dao.MemberDAO_Mj;


//이 클래스는 회원가입시 id사용가능여부 체크를 처리하는 서비스 클래스이다
public class MemberIdCheckService_Mj {

	MemberDAO_Mj memberDao = new MemberDAO_Mj();
	
	//id중복체크
	public boolean overlappedID(String id) {
	
		Connection conn = null;
		try {
			conn = ConnectionProvider_Mj.getConnection();
					
			MemberDAO_Mj memberDAO = new MemberDAO_Mj();
			boolean overlappedID = memberDAO.overlappedID(conn,id);		
			
			return overlappedID;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JdbcUtil_Mj.close(conn);
		}
		
	}

}






