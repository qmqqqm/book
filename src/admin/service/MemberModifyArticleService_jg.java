package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import admin.controler.service.dao.MemberDAO;
import admin.controler.service.dto.Article;
import admin.controler.service.dto.MemberArticle;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;

//p667 17라인
//특정글 수정하기
//ModifyArticHandler<-> ModifyArticleService<->ArticleDAO,ArticleContentDAO<->DB
public class MemberModifyArticleService_jg {
	
	private MemberDAO articleDAO = new MemberDAO();
	Connection conn = null;
	
	public void modify(MemberModifyRequest_jg modreq, int articleNumber,String id, String pwd,String name,String gender, String hp,int point,int grade) throws Exception {
		
		System.out.println("modreq="+modreq);
		try {
			conn = ConnectionProvider_Mj.getConnection();
			conn.setAutoCommit(false);
			
			//p668 23라인
			MemberArticle article = articleDAO.selectById(conn, articleNumber);
			if(article==null){
				throw new ArticleNotFoundException_jg();
			}
			
			
			
			//p668 31라인
			articleDAO.update(conn, articleNumber, id,pwd,name,gender,hp,point,grade);
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil_Mj.rollback(conn);
			throw new RuntimeException();
		}catch (PermissionDeniedException_jg e) {//p668 39라인
			JdbcUtil_Mj.rollback(conn);
			throw e;
		}finally {
			JdbcUtil_Mj.close(conn);
		}
	}//modify
	
	//p668 23라인
	//수정할 수 있는 권한이 있는지 check
	//==>로그인한 user id와 작성자의 id가 일치여부 check
	private boolean canModify(String authUser, Article article) {
		System.out.println("canModify()진입");
		String loginUserId = authUser; // 로그인한 user의 id
		String writerId = article.getId(); // 작성한 user의 id
		System.out.println("loginUserId="+loginUserId);
		System.out.println("writerId="+writerId);
		boolean result = writerId.equals(loginUserId);
		return result;

	}// canModify
	
		
	}
		

