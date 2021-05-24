package board.free.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import board.free.controler.service.dao.FreeBoardDAO_Mj;
import board.free.controler.service.dto.FreeBoardDTO_Mj;
import board.free.controler.service.dto.ModifyRequestDTO_Mj;
import board.free.error.ArticleNotFoundException;
import board.free.error.PermissionDeniedException;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;


public class FreeBoardModifyService_Mj {

	private FreeBoardDAO_Mj articleDao = new FreeBoardDAO_Mj();
	
	
	//자유게시판글 글 수정
	public void modify(ModifyRequestDTO_Mj modReq) throws PermissionDeniedException {
		Connection conn = null;
	
		try {
			conn = ConnectionProvider_Mj.getConnection();
			conn.setAutoCommit(false); //자동commit x
			
			//DB에서  수정하고자하는 글 상세내용을 가져온다
			FreeBoardDTO_Mj article 
				= articleDao.selectById(conn, modReq.getArticleNumber());
			if(article==null) {
				throw new ArticleNotFoundException();
			}
			
			//수정권한 체크
			boolean reuslt = canModify(modReq.getUserId() ,article);
			if(!reuslt) {
				throw new PermissionDeniedException();
			}		
			//수정처리
			articleDao.update(conn, modReq.getArticleNumber(), modReq.getTitle(), modReq.getContent());
			conn.commit(); //transaction commit
			
		}catch (SQLException e) {
			JdbcUtil_Mj.rollback(conn); //transaction rollback
			throw new RuntimeException(e);
		}catch(PermissionDeniedException e) { 
			JdbcUtil_Mj.rollback(conn); //transaction rollback
			throw e; 
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		
	}
	//수정권한 체크
	private boolean  canModify(String userId,FreeBoardDTO_Mj article) {
		String writerId 
		= article.getWriter().getId();//글 작성자id
		
		return  writerId.equals(userId);
	}
	
}













