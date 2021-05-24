package product.review.dto;

import java.sql.Connection;
import java.sql.Date;

public class ReviewDTO{
	//field		-- 도서 테이블 생성
		private int 	review_id;							//리뷰아이디  primary key 번호로 적용
		private int 	product_id;						//책아이디
		private String	review_mem_id;  			//종류	
		private String	review_write;  				//제목	
		private String	review_pro_title;  			//가격	
		private int	review_star; 						//수량 
		private Date	reg_date;    						//저자

		public ReviewDTO() {}

		public ReviewDTO(int review_id, int product_id, String review_mem_id, String review_write,
				String review_pro_title, int review_star, Date reg_date) {
			this.review_id = review_id;
			this.product_id = product_id;
			this.review_mem_id = review_mem_id;
			this.review_write = review_write;
			this.review_pro_title = review_pro_title;
			this.review_star = review_star;
			this.reg_date = reg_date;
		}

		public ReviewDTO(int review_id, int product_id, String review_mem_id, String review_write,
				String review_pro_title, int review_star) {
			this.review_id = review_id;
			this.product_id = product_id;
			this.review_mem_id = review_mem_id;
			this.review_write = review_write;
			this.review_pro_title = review_pro_title;
			this.review_star = review_star;
		}
		
		public ReviewDTO(int product_id, String review_mem_id, String review_write,
				String review_pro_title, int review_star) {
			this.product_id = product_id;
			this.review_mem_id = review_mem_id;
			this.review_write = review_write;
			this.review_pro_title = review_pro_title;
			this.review_star = review_star;
		}

		public int getReview_id() {
			return review_id;
		}

		public void setReview_id(int review_id) {
			this.review_id = review_id;
		}

		public int getProduct_id() {
			return product_id;
		}

		public void setProduct_id(int product_id) {
			this.product_id = product_id;
		}

		public String getReview_mem_id() {
			return review_mem_id;
		}

		public void setReview_mem_id(String review_mem_id) {
			this.review_mem_id = review_mem_id;
		}

		public String getReview_write() {
			return review_write;
		}

		public void setReview_write(String review_write) {
			this.review_write = review_write;
		}

		public String getReview_pro_title() {
			return review_pro_title;
		}

		public void setReview_pro_title(String review_pro_title) {
			this.review_pro_title = review_pro_title;
		}

		public int getReview_star() {
			return review_star;
		}

		public void setReview_star(int review_star) {
			this.review_star = review_star;
		}

		public Date getReg_date() {
			return reg_date;
		}

		public void setReg_date(Date reg_date) {
			this.reg_date = reg_date;
		}

		@Override
		public String toString() {
			return "ReviewDTO [review_id=" + review_id + ", product_id=" + product_id + ", review_mem_id="
					+ review_mem_id + ", review_write=" + review_write + ", review_pro_title=" + review_pro_title
					+ ", review_star=" + review_star + ", reg_date=" + reg_date + "]";
		}

		public void getReviewDelete(Connection conn, ReviewDTO review_Delete) {
			int review_id;
			int product_id;
			String review_mem_id;
			String review_write;
			String review_pro_title;
			int review_star;
			Date reg_date;
		}

		
		
		
}
