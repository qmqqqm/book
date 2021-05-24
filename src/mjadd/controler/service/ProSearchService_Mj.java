package mjadd.controler.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import mjadd.controler.service.dao.ProSearchDAO_Mj;
import mjadd.controler.service.dto.ProSearchDTO_Mj;
//상품검색을 처리하기위한 서비스 클래스
public class ProSearchService_Mj {
	
	private ProSearchDAO_Mj searchDao = new ProSearchDAO_Mj();
	
	public List<ProSearchDTO_Mj> getSrachList(String search) throws SQLException {
			
		Connection conn=null;
		try {
		
			conn = ConnectionProvider_Mj.getConnection();
			//디비테이블에 상품조회을 위해 DAO호출
			List<ProSearchDTO_Mj> data = searchDao.proSearch(conn, search);
			
			return data;
		}finally {
			JdbcUtil_Mj.close(conn);
		}
	}
}
