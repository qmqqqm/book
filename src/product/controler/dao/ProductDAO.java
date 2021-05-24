package product.controler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.JdbcUtil_Mj;
import product.controler.dto.ProductDTO;

//cart table에 access하여 CRUD 작업을 할 수 있는 기능을 제공한다
public class ProductDAO {

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
	public List<ProductDTO> getProductList(Connection conn)  throws SQLException {
	      String sql = "SELECT book_id,book_kind,book_title,book_price, " + 
	      				"	book_count, author, publishing_com, publishing_date, "+
      					" book_image, book_content, discount_rate, reg_date " + 
      					" FROM   product_book ";
      					//+"where book_id =?";
	      
	      PreparedStatement stmt = null;
	      ResultSet rs = null;   
	      
	      try {
	         //쿼리실행-객체준비,실행
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery();
	         
	         List<ProductDTO> list = new ArrayList<ProductDTO>();
	         while( rs.next() ) {
	        	 
	        	 ProductDTO productDTO=new ProductDTO(
	        			 rs.getInt("book_id"), 
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
	    				rs.getDate ( "reg_date"));
	        	 list.add(productDTO);	
	        	 System.out.println("productDTO ="+productDTO);
	        	 System.out.println();
	         }
	         
	         return list;
	      }finally {//자원반납
	    	  JdbcUtil_Mj.close(rs);
	    	  JdbcUtil_Mj.close(stmt);
	      }
	   }//select()

	

}
