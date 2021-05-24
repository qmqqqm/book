package product.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.JdbcUtil_Mj;
import product.controler.dto.ProductDTO;
import product.review.dto.ReviewArticle;
import product.review.dto.ReviewDTO;

//cart table에 access하여 CRUD 작업을 할 수 있는 기능을 제공한다
public class ReviewDAO {

	//DB와 연동하기 위해서는
	//1. Driver load
	//2. Connection 얻기
	//3. 쿼리실행(객체준비, 쿼리실행, 결과받기)
	//4. 자원해제
	//의 단계를 거치게 된다
	//여기에서는 환경설정으로 1번과 2번에 대한 것을 이미 진행해놓은 상태이므로 DAO 클래스에서는 각 함수마다 3번과 4번에 해당하는 것만 작성하면 된다.

		//접근제한자 속성 리턴유형 메소드명(매개변수리스트) {}

			public  List<ProductDTO> selectAll(Connection conn,String findbook) throws SQLException {
			System.out.println("DAO 메소드 진입");
			String sql = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
		/*
		  		-- 도서 테이블 생성 
				 --책아이디         book_id  number(10) primary key,                                 
				 --종류            	book_kind varchar2(3) not null,                    
				 --제목            	book_title varchar2(100) not null,                 
				 --가격            	book_price number(10) not null,                    
				 --수량            	book_count number(5) not null,                     
				 --저자            	author varchar2(20),                               
				 --출판사           publishing_com varchar2(30),                       
				 --출판일           publishing_date varchar2(15),                      
				 --이미지(책)    book_image varchar2(16) default 'nothing.jpg',     
				 --책설명           book_content varchar2(500),                        
				 --할인률           discount_rate number(3),                           
				 --등록일           reg_date date 
		 */
			sql = "SELECT book_id,book_kind,book_title,book_price,book_count,author,publishing_com, "+
					"publishing_date,book_image,book_content,discount_rate,reg_date " + 
					"FROM product_book";
			
			stmt = conn.prepareStatement(sql);
			//stmt.setString(1,"wjsaudwo");	//?셋팅
//			stmt.setString(1,'${findbook-form.findbook}');	//?셋팅
			stmt.setString(1,findbook);
			rs = stmt.executeQuery();
			
			List<ProductDTO>  result  = new ArrayList<ProductDTO>();
			
			while( rs.next() ) {
				//convertProduct() call해서 return받은 Basket객체를
				//List컬렉션에 추가(add)한다
				result.add(convertProduct(rs)); //select해서 return받은 record수만큼 반복
			}
			System.out.println(result);	//확인용
			return result;
			}finally{ //p593 32라인
				//리소스 해제 resource 반납
				JdbcUtil_Mj.close(rs);
				JdbcUtil_Mj.close(stmt);
			}
		}//selectById
			
	private ProductDTO convertProduct(ResultSet rs) throws SQLException {
	return 
			new ProductDTO(rs.getInt("book_id"),
						rs.getString("book_kind"),
						rs.getString("book_title"), 
						rs.getInt("book_price"), 
						rs.getInt("book_count"), 
						rs.getString("author"), 
						rs.getString("publishing_com"), 
						rs.getString("publishing_date"), 
						rs.getString("book_image"),
						rs.getString("book_content"),
						rs.getInt("discount_rate"),
						rs.getDate("reg_date"));
	}//convertProduct()

	public boolean overlappedID(Connection conn, String id) throws SQLException {
		boolean result = false;
		String sql = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			//Oracle
			//DECODE함수는 오직 동등비교(==)
			//DECODE(컬럼,값,'조건이 일치할 경우의 VALUE','조건이 일치하지 않을경우의 VALUE')
			/*sql = "SELECT decode(count(*),1,'true','false') as result "+
				    "FROM   member "+
				    "WHERE  mid=?";*/
			
			//MySQL용
			//비교연산이 가능
			//IF(조건,'조건이 일치할 경우의 VALUE','조건이 일치하지 않을경우의 VALUE')
			sql = "SELECT IF(count(*)>=1,'true','false') as result "+
			      "FROM   member "+
			      "WHERE  mid=?";
			System.out.println("prepareStatememt: " + sql);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				//result 컬럼의 값을 가져와 Boolean으로 변환
				result = Boolean.parseBoolean(rs.getString("result"));
			}

			return result;
		}finally{ 
			//resource 반납
			JdbcUtil_Mj.close(rs);
			JdbcUtil_Mj.close(stmt);
		}
	}//overlappedID

	//모든 상품의 모든 정보 조회
	public List<ReviewDTO> getReviewList(Connection conn, int book_id,int startRow,int size)  throws SQLException {
	      String sql = "SELECT * FROM (SELECT ROWNUM num, A.* " + 
					"          FROM review a where product_id=? order by reg_date) " + 
					" WHERE NUM BETWEEN ? AND ? ";
	      PreparedStatement stmt = null;
	      ResultSet rs = null;   
	      
	      try {
	         //쿼리실행-객체준비,실행
	         stmt = conn.prepareStatement(sql);
	         stmt.setInt(1, book_id);
	         stmt.setInt(2, startRow+1);
	         stmt.setInt(3, startRow + size);
	         rs = stmt.executeQuery();
	         
	         List<ReviewDTO> list = new ArrayList<ReviewDTO>();
	         while( rs.next() ) {
	        	 
	        	 ReviewDTO reviewDTO= new ReviewDTO(
	        			 rs.getInt("review_id"), 
	        			 rs.getInt("product_id"), 
	        			 rs.getString("review_mem_id"), 
	        			 rs.getString("review_write"), 
	    				rs.getString("review_pro_title"),
	    				rs.getInt("review_star"),
	    				rs.getDate("reg_date"));
	        	
	        	 list.add(reviewDTO);	
	        	 System.out.println("reviewDTO ="+reviewDTO);
	        	 System.out.println();
	         }
	         
	         return list;
	      }finally {//자원반납
	    	  JdbcUtil_Mj.close(rs);
	    	  JdbcUtil_Mj.close(stmt);
	      }
	   }//select()

	public void getReviewInsert(Connection conn, ReviewDTO reviewInsert)  throws SQLException {
	      	String sql =null;
			System.out.println("getProductInsert진입");
		      PreparedStatement pstmt = null; //insert를 싱행하기위한 객체
		      Statement stmt = null; //마지막 insert된 글번호를 조회하기위한 객체
		      ResultSet rs = null; //마지막 insert된 글번호를 담기위한 객체
		      
		      
		      try {
		         sql = "INSERT INTO review VALUES(review_seq.nextval,?,?,?,?,?,SYSDATE)";
		         
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setInt(1,reviewInsert.getProduct_id());   //?세팅 
		         pstmt.setString(2,reviewInsert.getReview_mem_id());   //?세팅
		         pstmt.setString(3,reviewInsert.getReview_write());   //?세팅
		         pstmt.setString(4,reviewInsert.getReview_pro_title());   //?세팅 
		         pstmt.setInt(5,reviewInsert.getReview_star());   //?세팅 

		         pstmt.executeUpdate();
		         
		      }finally{ //resource 반납
		    	 JdbcUtil_Mj.close(rs);
		    	 JdbcUtil_Mj.close(pstmt);
		    	 JdbcUtil_Mj.close(stmt);
		      }
		}//insert

	public void getReviewDelete(Connection conn, String review_id, String product_id) throws SQLException {
		System.out.println("getReviewDelete진입");
		String sql = null;
		int review_id_int=Integer.parseInt(review_id);
		int product_id_int=Integer.parseInt(product_id);
		PreparedStatement stmt = null;
		try {
			sql = "Delete from review where review_id=? and product_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,review_id_int);
			stmt.setInt(2,product_id_int);
//			stmt.setString(1,review_id);
//			stmt.setString(2,product_id);
			stmt.executeQuery();
		}finally{ //resource 반납
			JdbcUtil_Mj.close(stmt);
		}
	}//delete
	
	public void getReviewUpdate(Connection conn,ReviewDTO reviewUpdate)  throws SQLException {
		System.out.println("getReviewUpdate진입");
		String sql = null;
		
		PreparedStatement stmt = null;
		try {
			sql = "UPDATE review  "+
			      "SET 	review_write=?, review_star=? "+
					" where product_id=? "
				+ " and review_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, reviewUpdate.getReview_write());	//?세팅
			stmt.setInt(2, reviewUpdate.getReview_star());	//?세팅
			stmt.setInt(3, reviewUpdate.getProduct_id());	//?세팅
		stmt.setInt(4, reviewUpdate.getReview_id());	//?세팅
			stmt.executeUpdate();
		}finally{ //resource 반납
			JdbcUtil_Mj.close(stmt);
		}
	}//update

	public int selectCount(Connection conn, int book_id) throws SQLException {
		String sql = "SELECT  COUNT(*) as cnt "+
				 "FROM review where product_id =?";
	PreparedStatement stmt = null;
	System.out.println("selectCount 진행중");
	ResultSet rs = null;
	
	try {
		//쿼리실행-객체준비,실행
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, book_id); 
		rs = stmt.executeQuery();
		
		//article테이블에 입력된 게시물이 존재한다면 게시물수를 return
		if( rs.next() ) {
			return rs.getInt("cnt"); //rs.getInt(1);와 동일
		}
	
		//article테이블에 입력된 게시물이 존재하지않으면 0을 return
		return 0;
	
	}finally {//자원반납
		JdbcUtil_Mj.close(rs);
		JdbcUtil_Mj.close(stmt);
	}
}//selectCount
	
}

