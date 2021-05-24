package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import admin.controler.service.dao.MemberDAO;
import admin.controler.service.dto.MemberArticle;
import admin.controler.service.dto.MemberArticleData;
import board.free.error.ArticleNotFoundException;
import jdbc.connection.ConnectionProvider_Mj;

//p658
//이 클래스는 ReadArticleHandler의 비즈니스로직을 수행하는 클래스
//ReadArticleHandler -> ReadArticleService <-> ArticleContentDAO, ArticleDAO <-> DB
public class MemberReadService_jg {

	private MemberDAO adminDao = new MemberDAO();
	
	//글 상세조회 -p658 17라인
	public MemberArticleData getArticle(int articleNum, boolean incrementReadCount) throws ArticleNotFoundException{
		try {
			Connection conn = ConnectionProvider_Mj.getConnection();
			
			//p659 19라인 -article테이블에서 특정글 번호 조회
			MemberArticle article = adminDao.selectById(conn,articleNum);
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			//Article클래스와 ArticleContent를 파라미터로 가진
			//ArticleData클래스 객체를 return
			return new MemberArticleData(article);
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
