package board.qna.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.free.error.ArticleContentNotFoundException;
import board.free.error.ArticleNotFoundException;
import board.qna.controler.service.dao.QnaListDAO_JI;
import board.qna.controler.service.dto.QnaListDTO_JI;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;

// QnaBoardReadControler_JI의 비즈니스로직을 수행하는 클래스
/*QnaBoardReadControler_JI<->QnaBoardReadArticleService_JI <->QnaListDAO_JI<->DB								
*/
public class QnaBoardReadArticleService_JI {

	private QnaListDAO_JI qnaArticleDao = new QnaListDAO_JI();
	
	//글 상세조회
	public QnaListDTO_JI getArticle(int articleNum, 
			               boolean incrementReadCount) throws SQLException {
		System.out.println("QnaReadArticleService_JI의 process()진입");	//확인용
		Connection conn = ConnectionProvider_Mj.getConnection();
		try {
			
			//qnaboard_JI테이블에서  특정글번호 조회
			QnaListDTO_JI article = qnaArticleDao.selectById(conn, articleNum);
			if( article==null ) { 
				throw new ArticleNotFoundException();
			}
			
			//qnaboard_JI테이블에서 특정글번호 조회수증가
			if(incrementReadCount) {
				qnaArticleDao.incrementReadCount(conn,articleNum);
			}
			
			//QnaListDTO_JI의 article에 내용 저장하여 return
			return  article;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil_Mj.close(conn);
		}
	}
	
}
