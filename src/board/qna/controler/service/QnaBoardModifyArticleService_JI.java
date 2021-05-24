package board.qna.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import admin.service.ArticleNotFoundException_jg;
import admin.service.PermissionDeniedException_jg;
import board.qna.controler.model.QnaModifyRequest_JI;
import board.qna.controler.service.dao.QnaListDAO_JI;
import board.qna.controler.service.dto.QnaListDTO_JI;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;


//QnaBoardModifyControler_JI<->QnaBoardModifyArticleService_JI<->
//QnaListDAO_JI<->DB
public class QnaBoardModifyArticleService_JI {

	private QnaListDAO_JI qnaArticleDao = new QnaListDAO_JI();
	
	//p667 17라인
	//특정글 수정처리 요청
	public void modify(QnaModifyRequest_JI modReq) {
		Connection conn = null;
		
	try {
			conn = ConnectionProvider_Mj.getConnection();
			conn.setAutoCommit(false);	//자동 commit x
			
			//p668 23라인 추가작업~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			//DB에서 수정하고자하는 특정 글의 상세 내용을 가져온다
			QnaListDTO_JI article = qnaArticleDao.selectById(conn, modReq.getArticleNumber());
			if(article == null) {
				throw new ArticleNotFoundException_jg();
			}
			
			//수정할 수 있는 권한이 있는지 check
			//==> 로그인한 user의 id와 글작성자의 id가 일치 여부 check
			boolean result = canModify(modReq.getUserId(), article);
			if(!result) {
				throw new PermissionDeniedException_jg(); 
			}
			
			
			qnaArticleDao.update(conn, modReq.getArticleNumber(),
										modReq.getTitle(),
										modReq.getContent());
			conn.commit();//transaction commit
			
		}catch(SQLException e){
			JdbcUtil_Mj.rollback(conn); //transaction rollback
			throw new RuntimeException(e);
		}catch(PermissionDeniedException_jg e) { //p668 39라인
			JdbcUtil_Mj.rollback(conn); //transaction rollback
			throw e;
		} catch (ArticleNotFoundException_jg e) {
			e.printStackTrace();
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		
	}//modify
	
	
	//p668 47라인
	//로그인한 user가 수정을 할 수 있는 권한이 있는지 check
	//글 작성자 id가 로그인한 user의 id와 일치 여부 check
	private boolean canModify(String userId,QnaListDTO_JI article){
		String writerId = article.getId(); //글 작성자 id
		//스트링클래스.equals("비교문자열") : 값 일치 비교
		return writerId.equals(userId);
	}
}
