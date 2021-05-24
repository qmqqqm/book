package product.review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import product.review.dao.ReviewDAO;

public class ReviewDeleteService {
		//field
		private ReviewDAO reviewDeleteDao = new ReviewDAO();
		
		//모든 상품의 모든 정보 조회
		public void getReviewDelete(String review_id, String product_id) throws SQLException {
			System.out.println("서비스 메소드 진입"); 	//확인용
				Connection conn=null;
				try {
				conn = ConnectionProvider_Mj.getConnection();
				reviewDeleteDao.getReviewDelete(conn,review_id,product_id);
				}finally {
					JdbcUtil_Mj.close(conn);
				}
		}

}
