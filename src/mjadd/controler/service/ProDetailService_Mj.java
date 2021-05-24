package mjadd.controler.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import mjadd.controler.service.dao.ProSearchDAO_Mj;
import mjadd.controler.service.dto.ProSearchDTO_Mj;
//상품상세조회을 위한 서비스 클래스
public class ProDetailService_Mj {
	
	private ProSearchDAO_Mj detailhDao = new ProSearchDAO_Mj();
	

	public ProSearchDTO_Mj getDetail(int bookid) throws SQLException {		
		Connection conn=null;
		//컨넥션얻기
		try{
		conn = ConnectionProvider_Mj.getConnection();
		//상품상세정보 조회을 위해 DAO호출
		ProSearchDTO_Mj data = detailhDao.selectID(conn, bookid);
		
		return data;
		}finally {
			JdbcUtil_Mj.close(conn);
		}
	}



}
