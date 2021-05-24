package product.controler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import product.controler.dto.ProductDTO;

//cart table에 access하여 CRUD 작업을 할 수 있는 기능을 제공한다
public class ProductMasterUpdateDAO {

	//베스트셀러 모든 상품의 모든 정보 조회
	public void getProductUpdate(Connection conn,ProductDTO productDTO)  throws SQLException {
		System.out.println("getProductInsert진입");
		String sql = null;

		PreparedStatement stmt = null;
		try {
			sql = "UPDATE product_book  "+
			      "SET 	book_kind=?, book_title=?, book_price=?, book_count=?, author=?, publishing_com=?, publishing_date=?, book_image=?, book_content=?, discount_rate=? " 
					+"where book_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, productDTO.getBook_kind());	//?세팅
			stmt.setString(2, productDTO.getBook_title());	//?세팅
			stmt.setInt(3, productDTO.getBook_price());
			stmt.setInt(4, productDTO.getBook_count());
			stmt.setString(5, productDTO.getAuthor());
			stmt.setString(6, productDTO.getPublishing_com());
			stmt.setString(7, productDTO.getPublishing_date());
			stmt.setString(8, productDTO.getBook_image());
			stmt.setString(9, productDTO.getBook_content());
			stmt.setInt(10, productDTO.getDiscount_rate());
			stmt.setInt(11, productDTO.getBook_id());
			stmt.executeUpdate();
		}finally{ //resource 반납
			JdbcUtil_Mj.close(stmt);
		}
	}//update
	
	

}
