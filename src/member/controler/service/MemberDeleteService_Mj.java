package member.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import member.controler.service.dao.MemberDAO_Mj;



//자유게시판 글삭제 비지니스로직처리용 서비스 클래스
public class MemberDeleteService_Mj {

	private MemberDAO_Mj memberDao = new MemberDAO_Mj();
	
	
	//update를 이용해서 노출되지 않게 처리
	public int delete(String id) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider_Mj.getConnection();
			conn.setAutoCommit(false); //자동commit x
			
			//DAO에서 update 성공결과 리턴 받아 result 에저장			
			int result = memberDao.delete(conn,id);
			if( result==1 ) {
				System.out.println("article테이블에서 "+id+"번의 삭제(비노출로 변경)성공");
				conn.commit();
			}else {
				System.out.println("article테이블에서 "+id+"번의 삭제(비노출로 변경)실패");
			}
			
			return result;
			
		}catch (SQLException e) {
			JdbcUtil_Mj.rollback(conn); //transaction rollback
			throw new RuntimeException(e);
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		
	}	
}






