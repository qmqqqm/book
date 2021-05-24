package order.controler.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil_Mj;
import order.controler.service.dto.BasketAddDTO;
import order.controler.service.dto.BasketDTO;

//cart table에 access하여 CRUD 작업을 할 수 있는 기능을 제공한다
public class BasketDAO {

	//DB와 연동하기 위해서는
	//1. Driver load
	//2. Connection 얻기
	//3. 쿼리실행(객체준비, 쿼리실행, 결과받기)
	//4. 자원해제
	//의 단계를 거치게 된다
	//여기에서는 환경설정으로 1번과 2번에 대한 것을 이미 진행해놓은 상태이므로 DAO 클래스에서는 각 함수마다 3번과 4번에 해당하는 것만 작성하면 된다.

		//접근제한자 속성 리턴유형 메소드명(매개변수리스트) {}

		public  List<BasketDTO> selectById(Connection conn, String id) throws SQLException {
			System.out.println("DAO 메소드 진입");
			String sql = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				//cart_No,mem_id, pro_No, pro_Name, pro_Author, pro_Publicher, pro_Price, amount, total
			sql = "SELECT cart_No,mem_id,pro_No,pro_Name,pro_Author, "+
					" pro_Publicher,pro_Price,amount,pro_Price*amount as total, pro_image " + 
					" FROM   cart_JI " + 
					" WHERE  mem_id=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,id);	//?셋팅
			
			rs = stmt.executeQuery();
			
			List<BasketDTO>  result  = new ArrayList<BasketDTO>();
			
			while( rs.next() ) {
				//convertBasket() call해서 return받은 Basket객체를
				//List컬렉션에 추가(add)한다
				result.add(convertBasket(rs)); //select해서 return받은 record수만큼 반복
			}
			
			return result;
			}finally{ //p593 32라인
				//리소스 해제 resource 반납
				JdbcUtil_Mj.close(rs);
				JdbcUtil_Mj.close(stmt);
			}
		}//selectById
		
		private BasketDTO convertBasket(ResultSet rs) throws SQLException {
			//참고  rs.getXxx("")
			//Xxx는 java에서 취급할 데이터타입으로
			//클래스타입이 들어 올 수 있다  예) getString() getDate()
	
			//return new 클래스명(); 하면  특정클래스의 객체를 return하겠다는 의미
			//cart_No,mem_id, pro_No, pro_Name, pro_Author, pro_Publicher, pro_Price, amount,total
			return 
				new BasketDTO(rs.getInt("cart_No"),
							rs.getString("mem_id"), 
							rs.getInt("pro_No"), 
							rs.getString("pro_Name"), 
							rs.getString("pro_Author"), 
							rs.getString("pro_Publicher"), 
							rs.getInt("pro_Price"), 
							rs.getInt("amount"),
							rs.getInt("total"),
							rs.getString("pro_Image")); 
		}//convertBasket()
	
//-------------------------------------------------------------------------------------------------------------------------	
		//장바구니 추가
		public int addBasket(Connection conn, String id, int no, int amount) throws SQLException {
			System.out.println("dao들어옴");
			String sql = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			BasketAddDTO addDto = null;
			int result=0;
			int update=0;
			try {
				//기존 담겨있는 제품인지 장바구니 번호 체크
				//cart_No,mem_id, pro_No, pro_Name, pro_Author, pro_Publicher, pro_Price, amount, total
			sql = "SELECT cart_No "+
				  " FROM   cart_JI " + 
				  " WHERE  mem_id=? AND pro_No=?";
	
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,id);	//멤버 아이디 셋팅
			stmt.setInt(2,no);	//제품 번호 셋팅
			
			rs = stmt.executeQuery();
				//같은제품 있을시 update
				if(rs.next()) {
					//같은 장바구니 번호에 수량 플러스(update)
					int cart_no = rs.getInt("cart_No");
					
					sql="UPDATE cart_JI SET amount = amount+? "+
						" WHERE cart_No = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1,amount);	//제품 수량 셋팅
					stmt.setInt(2,cart_no);	//장바구니 번호 셋팅
					
					result = stmt.executeUpdate(); 
					
					return result;
					
				//장바구니에 같은제품이 없을시 select 후 insert
				}else {
					
					//product_book에서 제품 정보 select
					sql="SELECT book_id, book_title, author, publishing_com, book_price, book_image FROM product_book " +
						" WHERE book_id = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1,no);	//제품 번호 셋팅
					rs = stmt.executeQuery();
					if(rs.next()) {
						
						addDto = new BasketAddDTO(
										rs.getInt("book_id"),
										rs.getString("book_title"),
										rs.getString("author"),
										rs.getString("publishing_com"),
										rs.getInt("book_price"),
										rs.getString("book_image"));
								
						//새로운 제품 추가
						sql="INSERT INTO cart_JI(cart_No, mem_id, pro_No, pro_Name, pro_Author, pro_Publicher, pro_Price, amount, pro_Image) "+
							" VALUES(cart_JI_seq.nextval,?,?,?,?,?,?,?,?)";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1,id);	//멤버 아이디 셋팅
						stmt.setInt(2,addDto.getBook_id());	//제품번호 셋팅
						stmt.setString(3,addDto.getBook_title());	//제품 이름 셋팅
						stmt.setString(4,addDto.getAuthor());	//저자 셋팅
						stmt.setString(5,addDto.getPublishing_com());	//출판사 셋팅
						stmt.setInt(6,addDto.getBook_price());	//가격 셋팅
						stmt.setInt(7,amount);	//수량 셋팅
						stmt.setString(8,addDto.getBook_image());	//제품 이미지 셋팅
						
						result = stmt.executeUpdate();
					}//if
					return result;
				}//else
			}finally{ //p593 32라인
				//리소스 해제 resource 반납
				JdbcUtil_Mj.close(rs);
				JdbcUtil_Mj.close(stmt);
			}
		}//addBasket

//-------------------------------------------------------------------------------------------------------------------------	
		//장바구니에서 삭제
		public int delete(Connection conn, String id, int p_no) throws SQLException {
			String sql = null;
			PreparedStatement stmt = null;
			int delete;
			
			try {
				sql = "delete from cart_ji where mem_id=? and pro_no=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,id);	//멤버 아이디 셋팅
				stmt.setInt(2,p_no);	//제품 번호 셋팅
				delete = stmt.executeUpdate();
				
			} finally{
				//리소스 해제 resource 반납
				JdbcUtil_Mj.close(stmt);
			}
			return delete;
		}
			
		//장바구니 전체 삭제
		public int allDelete(Connection conn, String id) throws SQLException {
			String sql = null;
			PreparedStatement stmt = null;
			int allDelete;
			
			try {
				sql = "delete from cart_ji where mem_id=? ";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,id);	//멤버 아이디 셋팅
				allDelete = stmt.executeUpdate();
				
			} finally{
				//리소스 해제 resource 반납
				JdbcUtil_Mj.close(stmt);
			}
			return allDelete;
		}
				
//-------------------------------------------------------------------------------------------------------------------------					
		//장바구니에서 주문(order table로 insert)
		public int addOrder(Connection conn, String id, int[] no) throws SQLException {
			String sql = "SELECT pro_No, amount "+
						 " FROM   cart_JI " + 
						 " WHERE  cart_No=?";
			//cart_No,mem_id, pro_No, pro_Name, pro_Author, pro_Publicher, pro_Price, amount, total
			PreparedStatement stmt = null;
			ResultSet rs = null;
			int result =0;
			int update =0;
			List<BasketDTO> order = new ArrayList<BasketDTO>();
			
			try {
				stmt = conn.prepareStatement(sql);
				for (int i=0; i<no.length; i++) {
					System.out.println("for문 진입");
					stmt.setInt(1,no[i]);	//?셋팅
					rs = stmt.executeQuery();

					if(rs.next()) {
							order.add(new BasketDTO(rs.getInt("pro_No"), rs.getInt("amount")));
						}
				}
				
				//상품번호와 수량 select해서 order로 insert
				sql= "INSERT INTO order_jg (ord_No, book_id, count, user_Id) "+
					 " VALUES(order_jg_seq.nextval,?,?,?)";
				
				stmt = conn.prepareStatement(sql);
				for(int i=0; i<order.size(); i++) {
					int pro_no =  order.get(i).getP_No();
					int amount = order.get(i).getAmount();
					
					stmt.setInt(1,pro_no);	//제품 번호 셋팅
					stmt.setInt(2, amount);	//제품 수량 셋팅
					stmt.setString(3, id);	//유저 아이디 셋팅
					
					
					result = stmt.executeUpdate();
					//order테이블로 insert 완료시 재고수량 마이너스(product_book update)
					if(result != 0) {
									sql="UPDATE product_book SET book_count = book_count-? "+
										" WHERE book_id = ?";
									stmt = conn.prepareStatement(sql);
									stmt.setInt(1,amount);	//뺄 제품 수량 셋팅
									stmt.setInt(2,pro_no);	//제품번호 셋팅
									
								 	update = stmt.executeUpdate();
								}//for안에 if
				}
			}finally{
				//리소스 해제 resource 반납
				JdbcUtil_Mj.close(rs);
				JdbcUtil_Mj.close(stmt);
			}
			return update;
		}//addOrder
		
		//-------------------------------------------------------------------------------------------------------------------------	
		//order테이블에 insert 후 delete
		public int addDelete(Connection conn, String id, int[] no) throws SQLException {
			String sql = null;
			PreparedStatement stmt = null;
			int addDelete = 0; 
			try {

				sql = "DELETE FROM cart_ji where cart_no=?";
			
				stmt = conn.prepareStatement(sql);
				for (int i=0; i<no.length; i ++) {
					stmt.setInt(1,no[i]);	//?셋팅
					addDelete = stmt.executeUpdate();
			}
			
			return addDelete;
			}finally{ //p593 32라인
				//리소스 해제 resource 반납
				JdbcUtil_Mj.close(stmt);
			}
		}//addDelete
		
		//-------------------------------------------------------------------------------------------------------------------------	
		//product_book테이블 재고수량 update
		public int countUpdate(Connection conn, String id, int[] no) throws SQLException {
			String sql = null;
			PreparedStatement stmt = null;
			int addDelete = 0; 
			try {

				sql = "DELETE FROM cart_ji where cart_no=?";
			
				stmt = conn.prepareStatement(sql);
				for (int i=0; i<no.length; i ++) {
					stmt.setInt(1,no[i]);	//?셋팅
					addDelete = stmt.executeUpdate();
			}
			
			return addDelete;
			}finally{ //p593 32라인
				//리소스 해제 resource 반납
				JdbcUtil_Mj.close(stmt);
			}
		}//addDelete
		//바로주문로직
		public int addOrder(Connection conn, String id, int book_id, int count) throws SQLException {
			
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result =0;
		int update =0;
		String sql;	
		try {	
					
			//상품번호와 수량 select해서 order로 insert
			sql= "INSERT INTO order_jg (ord_No, book_id, count, user_Id) "+
				 " VALUES(order_jg_seq.nextval,?,?,?)";
			
			stmt = conn.prepareStatement(sql);		
				
				stmt.setInt(1,book_id);	//제품 번호 셋팅
				stmt.setInt(2, count);	//제품 수량 셋팅
				stmt.setString(3, id);	//유저 아이디 셋팅
				
				
				result = stmt.executeUpdate();
				//order테이블로 insert 완료시 재고수량 마이너스(product_book update)
				if(result != 0) {
								sql="UPDATE product_book SET book_count = book_count-? "+
									" WHERE book_id = ?";
								stmt = conn.prepareStatement(sql);
								stmt.setInt(1,count);	//뺄 제품 수량 셋팅
								stmt.setInt(2,book_id);	//제품번호 셋팅
								
							 	update = stmt.executeUpdate();
				}
			
		}finally{
			//리소스 해제 resource 반납
			JdbcUtil_Mj.close(rs);
			JdbcUtil_Mj.close(stmt);
		}
		return update;
	}//addOrder
		
		
		
		
		
	}


