package board.free.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.free.controler.service.dao.FreeBoardDAO_Mj;
import board.free.controler.service.dto.FreeBoardDTO_Mj;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;

//이 클래스는 비즈니스로직을 수행하는 클래스
public class FreeBoardListService_Mj {

	//dao객체생성
	private FreeBoardDAO_Mj freeListDao = new FreeBoardDAO_Mj();
	private int size = 10;  //한 페이지에 출력할 게시물수
	
	
	//글목록 조회 
		public FreeBoardListPagetService_Mj getArticlePage(int pageNum) {
			Connection conn = null;
			try {
				//모델변환용 dto 객체을 content 변수로 선언후 초기화
				List<FreeBoardDTO_Mj> content = null;				
				//컨넥션 얻기
				conn = ConnectionProvider_Mj.getConnection();
				
				//전체 게시물수
				int total = freeListDao.selectCount(conn);
				content = freeListDao.select(conn, (pageNum-1)*size, size);				
				
				return new FreeBoardListPagetService_Mj(total, pageNum, size, content);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			
		}finally {
			JdbcUtil_Mj.close(conn);
		}
		}
	
}



