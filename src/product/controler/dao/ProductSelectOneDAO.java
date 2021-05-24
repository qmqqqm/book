package product.controler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import product.controler.dto.ProductDTO;

//cart table에 access하여 CRUD 작업을 할 수 있는 기능을 제공한다
public class ProductSelectOneDAO {

	//DB와 연동하기 위해서는
	//1. Driver load
	//2. Connection 얻기
	//3. 쿼리실행(객체준비, 쿼리실행, 결과받기)
	//4. 자원해제
	//의 단계를 거치게 된다
	//여기에서는 환경설정으로 1번과 2번에 대한 것을 이미 진행해놓은 상태이므로 DAO 클래스에서는 각 함수마다 3번과 4번에 해당하는 것만 작성하면 된다.

		//접근제한자 속성 리턴유형 메소드명(매개변수리스트) {}

			public  ProductDTO getProductSelectOneList(Connection conn,String findbook) throws SQLException {
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
					"FROM product_book "+
					"where book_id=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,findbook);
			rs = stmt.executeQuery();
			
			ProductDTO  result  = new ProductDTO();
			
			if( rs.next() ) {
				result=new ProductDTO(
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
			}
			System.out.println(result);	//확인용
			return result;
		
			}finally{ //p593 32라인
				//리소스 해제 resource 반납
				JdbcUtil_Mj.close(rs);
				JdbcUtil_Mj.close(stmt);
			
		}//selectById
	}		

			
}
