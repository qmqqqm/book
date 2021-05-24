package product.review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import product.review.dao.ReviewDAO;
import product.review.dto.ReviewDTO;

public class ReviewInsertService {
		//field
		private ReviewDAO  reviewInsert = new ReviewDAO ();
		
		//모든 상품의 모든 정보 조회
		public void getReviewList(ReviewDTO review_insert) throws SQLException {
			System.out.println("서비스 메소드 진입"); 	//확인용
				Connection conn=null;
				try {
				conn = ConnectionProvider_Mj.getConnection();
				reviewInsert.getReviewInsert(conn, review_insert);
				System.out.println("productProDao.getProductList()실행후  가져온 상품의 수productPro.size()="+reviewInsert);
				//System.out.println(product);
				}finally {
					JdbcUtil_Mj.close(conn);
				}
		}

}
