package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import admin.controler.service.dao.AdminDAO;
import admin.controler.service.dto.Article;
import admin.controler.service.dto.ArticleData;
import board.free.error.ArticleNotFoundException;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;

//p658
//이 클래스는 ReadArticleHandler의 비즈니스로직을 수행하는 클래스
//ReadArticleHandler -> ReadArticleService <-> ArticleContentDAO, ArticleDAO <-> DB
public class StaffReadService_jg {

	private AdminDAO adminDao = new AdminDAO();
	
	//글 상세조회 -p658 17라인
	public ArticleData getArticle(int articleNum, boolean incrementReadCount) throws ArticleNotFoundException{
		Connection conn = null;
		try {
			conn = ConnectionProvider_Mj.getConnection();
			
			//p659 19라인 -article테이블에서 특정글 번호 조회
			Article article = adminDao.selectById(conn,articleNum);
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			//Article클래스와 ArticleContent를 파라미터로 가진
			//ArticleData클래스 객체를 return
			return new ArticleData(article);
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		
	}

}
