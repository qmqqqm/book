package member.controler.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil_Mj;
import member.controler.service.dto.MemberOrderDTO_Mj;

//cart table에 access하여 CRUD 작업을 할 수 있는 기능을 제공
public class MemberOrderDAO_Mj {

	

			//아이디로 주문정보을 조회
			public  List<MemberOrderDTO_Mj> selectById(Connection conn,String id) throws SQLException {
			System.out.println("DAO 메소드 진입"+id);
			String sql = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				//cart_No, mem_No, mem_Name, mem_id, pro_No, pro_Name, pro_Author, pro_Publicher, pro_Price, amount, total
			sql = "select ord.ord_no,ord.book_id,ord.count,ord.user_id, "
					+ " pro.book_title,pro.author,pro.publishing_com,pro.book_price, "
					+ " pro.discount_rate  from order_jg ord,product_book pro  "
					+ " where ord.book_id=pro.book_id and ord.user_id=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,id);	//?셋팅
			
			rs = stmt.executeQuery();
			
			List<MemberOrderDTO_Mj>  result  = new ArrayList<MemberOrderDTO_Mj>();
			
			while( rs.next() ) {
				//convertBasket() call해서 return받은 Basket객체를
				//List컬렉션에 추가(add)한다
				result.add(convertBasket(rs)); //select해서 return받은 record수만큼 반복
			}			
			return result;
			}finally{ 
				JdbcUtil_Mj.close(rs);
				JdbcUtil_Mj.close(stmt);
			}
		}
	//디비에서 추출한 오더정보을 MemberOrderDTO_Mj에 세팅하는 메소드	
	private MemberOrderDTO_Mj convertBasket(ResultSet rs) throws SQLException {
		
		/*
		 * "select ord.ord_no,ord.book_id,ord.count,ord.user_id, " +
		 * " pro.book_title,pro.author,pro.publishing_com,pro.book_price, " +
		 * " pro.discount_rate  from order_jg ord,product_book pro  " +
		 * " where ord.book_id=pro.book_id and ord.user_id=?";
		 */
		return 
			new MemberOrderDTO_Mj(
						rs.getInt("ord_no"), 
						rs.getInt("book_id"), 						
						rs.getString("book_title"), 
						rs.getString("author"), 
						rs.getString("publishing_com"), 
						rs.getInt("book_price"), 
						rs.getInt("discount_rate"),
						rs.getInt("count"));
	}
			
		
	}


