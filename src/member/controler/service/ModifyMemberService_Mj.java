package member.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import board.free.error.PermissionDeniedException;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import member.controler.service.dao.MemberDAO_Mj;
import member.controler.service.dto.MemberDTO_Mj;

public class ModifyMemberService_Mj {
	private MemberDAO_Mj memberDao = new MemberDAO_Mj();
	//회원정보 수정을 처리하는 서비스 클레스
	public int modify(MemberDTO_Mj modReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider_Mj.getConnection();
			conn.setAutoCommit(false); //자동commit x	
			int count=memberDao.update(conn, modReq);
			conn.commit(); //transaction commit
			return count;
			
		}catch (SQLException e) {
			JdbcUtil_Mj.rollback(conn); //transaction rollback
			throw new RuntimeException(e);
		}catch(PermissionDeniedException e) { //p668 39라인
			JdbcUtil_Mj.rollback(conn); //transaction rollback
			throw e; 
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		
	}
	

}
