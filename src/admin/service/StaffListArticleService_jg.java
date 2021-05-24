package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import admin.controler.service.dao.AdminDAO;
import admin.controler.service.dto.Article;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;

//p651
//이 클래스는 ListArticleHandler의 비즈니스로직을 수행하는 클래스
//ListArticleHandler->ListArticleServie<->ArticleDAO<->DB
public class StaffListArticleService_jg {

	//p651 13라인
	private AdminDAO articleDao = new AdminDAO();
	private int size = 2;  //한 페이지에 출력할 게시물수
	
	
	//(페이지별로 원하는 특정 범위의)글목록 조회 -  p651 19라인
		public StaffArticlePage_jg getArticlePage(int pageNum) {
			Connection conn = null;
			try {
				List<Article> content = null;
				//1page 글번호 1,2,3 -> 0,1,2
				//2page 글번호 4,5,6 -> 3,4,5
				//3page 글번호 7,8,9 -> 6,7,8
				conn = ConnectionProvider_Mj.getConnection();
				
				//전체 게시물수<-DB의 article 테이블의 전체 record수 
				//article테이블에 입력된 게시물이 존재한다면 게시물수를 return
				//article테이블에 입력된 게시물이 존재하지않으면 0을 return
				int total = articleDao.selectCount(conn);
				content = articleDao.select(conn, (pageNum-1)*size, size);
				
				//p651 21라인
				return new StaffArticlePage_jg(total, pageNum, size, content);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}finally {
				JdbcUtil_Mj.close(conn);
			}
		}
	
	
	
	//(페이지별로 원하는 특정 범위의)글목록 조회 -  p651 19라인 변경
	public List<Article> getArticlePage2(int pageNum) {
		List<Article> content = null;
		Connection conn = null;
		try {
			//1page 글번호 1,2,3 -> 0,1,2
			//2page 글번호 4,5,6 -> 3,4,5
			//3page 글번호 7,8,9 -> 6,7,8
			conn = ConnectionProvider_Mj.getConnection();
			content = articleDao.select(conn, (pageNum-1)*size, size);
			//List<Article>  21라인으로 수정예정이지만
			//임시로
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		return content;
	}

	
}



