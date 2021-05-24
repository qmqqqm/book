package board.free.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.free.controler.service.dao.FreeBoardDAO_Mj;
import board.free.controler.service.dto.FreeBoardDTO_Mj;

import board.free.error.ArticleNotFoundException;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;

//이 클래스는 자유게시판 조회 로직을 수행하는 클래스
public class FreeBoardReadService_Mj {
	
	private FreeBoardDAO_Mj articleDao = new FreeBoardDAO_Mj();
	
	
	//자유게시판 글 조회
	public FreeBoardDTO_Mj getArticle(int articleNum,boolean incrementReadCount) {
		Connection conn=null;
		try {
			conn = ConnectionProvider_Mj.getConnection();
			
			//특정글번호 조회
			FreeBoardDTO_Mj article = articleDao.selectById(conn, articleNum);
			
			if( article==null ) {
				throw new ArticleNotFoundException();
			}
		
			// 조회수증가
			if(incrementReadCount) {
				articleDao.incrementReadCount(conn,articleNum);
				
			}
			
			//FreeBoardDTO_Mj클래스  return
			return  article;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil_Mj.close(conn);
		}
	}
	
}



