package board.free.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import board.free.controler.service.dao.FreeBoardDAO_Mj;
import board.free.controler.service.dto.FreeBoardDTO_Mj;
import board.free.controler.service.dto.WriteRequestDTO_Mj;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;

//글쓰리 처리 로직
public class FreeBoardWriteService_Mj {
	
	private FreeBoardDAO_Mj articleDao = new FreeBoardDAO_Mj();
	

	//글입력요청처리 
	public void write(WriteRequestDTO_Mj writeReq) {
		
		Connection conn = null;  
		
		try{
			conn = ConnectionProvider_Mj.getConnection();
			conn.setAutoCommit(false);
						
			FreeBoardDTO_Mj article = toArticle(writeReq);
			
			FreeBoardDTO_Mj savedArticle = articleDao.insert(conn,article);
		
			if(savedArticle==null) {
				throw new RuntimeException("failt to insert article");
			}
			conn.commit();//DB transaction commit
		
			
		}catch(SQLException e) {
			JdbcUtil_Mj.rollback(conn); 	//DB transaction rollback
			throw new RuntimeException();
		}catch(RuntimeException e) {
			JdbcUtil_Mj.rollback(conn); 	//DB transaction rollback
			throw e;
		}finally {
			JdbcUtil_Mj.close(conn);
		}
	}	
	//WriteRequestDTO_Mj 을 FreeBoardDTO_Mj 로 변경
	private FreeBoardDTO_Mj toArticle(WriteRequestDTO_Mj writeReq) {
		//new Article(Integer number, Writer writer, String title, Date regdate, Date modifiedDate, int readCount)
		Date now = new Date();
		
		return new FreeBoardDTO_Mj(null,
						    writeReq.getWriter(), 
						    writeReq.getTitle(), 
						    now, now,0,writeReq.getContent());
	}

	
	
}







