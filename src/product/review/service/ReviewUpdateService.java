package product.review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import product.review.dao.ReviewDAO;
import product.review.dto.ReviewDTO;

public class ReviewUpdateService {
		//field
		private ReviewDAO  reviewUpdate = new ReviewDAO ();
		
		//모든 상품의 모든 정보 조회
		public void getReviewUpdate(ReviewDTO review_Update) throws SQLException {
			System.out.println("서비스 메소드 진입"); 	//확인용
			System.out.println("review_Update 들고있음 : "+review_Update); 	//확인용
				Connection conn=null;
				try {
				conn = ConnectionProvider_Mj.getConnection();
				reviewUpdate.getReviewUpdate(conn, review_Update);
				System.out.println("reviewUpdate.getReviewUpdate()실행후  ="+reviewUpdate);
				//System.out.println(product);
				}finally {
					JdbcUtil_Mj.close(conn);
				}
		}

}
