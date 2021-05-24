package board.qna.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.qna.controler.service.dao.QnaListDAO_JI;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;


//삭제요청에 의해 호출되는 담당컨트롤러
//컨텍스트패스/board/qna/board/qnadelete.book?no=글번호
public class QnaBoardDeleteService_JI {
	
	private QnaListDAO_JI QnaDao = new QnaListDAO_JI();
	
	//삭제이지만 우리는 update를 이용해서 노출 허용하지 않음
	public int delete(int no) {

		Connection conn = null; 
		try{
			conn = ConnectionProvider_Mj.getConnection();
			conn.setAutoCommit(false);	//자동 commit x
			
			//DAO에서 update(즉 삭제에 해당하는 부분이) 성공되면
			//1를 return 받는다
			//실패시 0을 return 받는다
			int result = QnaDao.delete(conn,no);
			
			if(result==1) {
				System.out.println("Q&A게시판의 "+no+"번 비노출로 변경성공");
			}else {
				System.out.println("Q&A게시판의 "+no+"번 비노출로 변경실패");
			}
			
			conn.commit();
			return result;
			
		}catch(SQLException e){
			JdbcUtil_Mj.rollback(conn); //transaction rollback
			throw new RuntimeException(e);
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		
	}//delete
}
