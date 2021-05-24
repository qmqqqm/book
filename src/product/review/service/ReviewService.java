package product.review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;
import product.review.dao.ReviewDAO;
import product.review.dto.ReviewArticle;
import product.review.dto.ReviewDTO;

public class ReviewService {
		//field
		private ReviewDAO reviewDao = new ReviewDAO();
		
		//모든 상품의 모든 정보 조회
		public ReviewArticle getReviewList(int book_id,int pageNo) throws SQLException {
			System.out.println("서비스 메소드 진입"); 	//확인용
				Connection conn=null;
				int size = 5;
				try {
				conn = ConnectionProvider_Mj.getConnection();
				int total = reviewDao.selectCount(conn,book_id);
				System.out.println("getReviewList list전");
				List<ReviewDTO> content = reviewDao.getReviewList(conn,book_id, (pageNo-1)*size, size);
				//System.out.println(product);
				
				System.out.println("total="+total);
				System.out.println("content="+content);
				
				return new ReviewArticle(total, pageNo, size, content);
				}finally {
					JdbcUtil_Mj.close(conn);
				}
		}

}
