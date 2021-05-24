package product.controler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.JdbcUtil_Mj;
import product.controler.dto.ProductDTO;

//cart table에 access하여 CRUD 작업을 할 수 있는 기능을 제공한다
public class ProductMasterDeleteDAO {

	//베스트셀러 모든 상품의 모든 정보 조회
	public void getProductDelete(Connection conn,String book_id)  throws SQLException {
		System.out.println("getProductInsert진입");
		String sql = null;
		PreparedStatement stmt = null;
		int book_id_int =Integer.valueOf(book_id);
		try {
			sql = "Delete from product_book where book_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,book_id_int);
			stmt.executeQuery();
		}finally{ //resource 반납
			JdbcUtil_Mj.close(stmt);
		}
	}//update

}
