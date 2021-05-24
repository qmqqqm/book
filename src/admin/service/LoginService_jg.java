package admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import admin.controler.service.dao.AdminDAO;
import admin.controler.service.dto.AdminDTO;
import jdbc.JdbcUtil_Mj;
import jdbc.connection.ConnectionProvider_Mj;

public class LoginService_jg {
	//field
		private AdminDAO adminDao = new AdminDAO();
		
		//constructor
		
		//method
		//로그인처리 - p605 14라인
		//LoginHandler로부터 받은 id와 비번을 사용하여 작업
		public User_jg  login(String id, String pwd) {
			System.out.println("LoginService의   login() id="+id);//확인
			
			Connection conn = null;
			try {
				conn = ConnectionProvider_Mj.getConnection();
				AdminDTO member = adminDao.selectById(conn, id);
				System.out.println("login()의 결과 member="+member);

				if(member==null) { //p605 17라인
					//사용자정의Exception인 로그인실패예외 객체로 생성하여
					//예외(Exception)을 처리하도록 한다
					return new User_jg("1");
				}
				
				//user가 입력한 비번(pwd)과  db의 비번(mPwd)가
				//일치하면 true리턴
				//=>여기에서는 일치하지않으면   LoginFailException 발생시킨다
				if( !member.matchPassword(pwd) ) { //p606 20라인
					return new User_jg("1");
				}
				
				//DB에서 select한  회원명,회원id를 가진 객체를 생성해서 return
				return new User_jg(member.getAdm_Id());
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}finally {
				JdbcUtil_Mj.close(conn);		
			}
			
			
		}
	}
