package board.free.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.free.controler.service.dao.FreeBoardDAO_Mj;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;



//이 클래스는 글삭제컨트롤러에서 사용되는 클래스
public class FreeBoardDeleteService_Mj {

	private FreeBoardDAO_Mj articleDao = new FreeBoardDAO_Mj();
	
	
	//삭제 대신 update를 이용해서 노출허용x
	public int delete(int no) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider_Mj.getConnection();
			conn.setAutoCommit(false); //자동commit x
			
			//DAO에서 update결과 리턴	
			int result = articleDao.delete(conn,no);
			if( result==1 ) {				
				conn.commit();
			}else {				
			}
			
			return result;
			
		}catch (SQLException e) {
			JdbcUtil_Mj.rollback(conn); //transaction rollback
			throw new RuntimeException(e);
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		
	}//delete
	
}






