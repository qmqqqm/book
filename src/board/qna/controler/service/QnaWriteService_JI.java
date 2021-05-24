package board.qna.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import board.qna.controler.model.QnaWriteRequest_JI;
import board.qna.controler.service.dao.QnaListDAO_JI;
import board.qna.controler.service.dto.QnaListDTO_JI;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;


public class QnaWriteService_JI {
	private QnaListDAO_JI qnaArticleDao = new QnaListDAO_JI();
	
	//글입력요청처리 
	public int write(QnaWriteRequest_JI qnaWriteReq) {
		System.out.println("writeservice진입");
		Connection conn = null;
		try{
			conn = ConnectionProvider_Mj.getConnection();
			conn.setAutoCommit(false);
			
			//매개변수로 받은 WriteRequest클래스의 객체를
			//Article객체로 변환하는 toArticle method호출
			QnaListDTO_JI qnaArticleDto = toArticle(qnaWriteReq);
			
			//article테이블에 insert완료
			//saved된 article정보를 받았다.
			QnaListDTO_JI savedArticle = qnaArticleDao.insert(conn,qnaArticleDto);
			
			if(savedArticle==null) {
				throw new RuntimeException("fail to insert article");
			}
			conn.commit(); 		//transaction commit
			System.out.println("commit완료");
			
			return savedArticle.getQ_No();
			
		}catch(SQLException e){
			JdbcUtil_Mj.rollback(conn); 	//transaction rollback
			throw new RuntimeException();
		}catch(RuntimeException e){
			JdbcUtil_Mj.rollback(conn); 	//transaction rollback
			throw e;
		}finally {
			JdbcUtil_Mj.close(conn);
		}
	}//write
	
	//p639 52라인
	//매개변수로 받은 qnaWriteReq클래스의 객체를
	//QnaListDTO_JI객체로 변환
	private QnaListDTO_JI toArticle(QnaWriteRequest_JI qnaWriteReq) {
		//new Article(q_no,id,q_title,q_content,q_regdate,q_modifieddate,q_readcount)
		Date now = new Date();
		return new QnaListDTO_JI(0,
								qnaWriteReq.getId(),
								qnaWriteReq.getTitle(),
								qnaWriteReq.getContent(),
								now,
								now,
								0,
								0);
		}
	
}
