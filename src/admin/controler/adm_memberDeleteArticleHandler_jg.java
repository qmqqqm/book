package admin.controler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.controler.service.dao.MemberDAO;
import jdbc.connection.ConnectionProvider_Mj;
import mvc.command.CommandHandler_Mj;

public class adm_memberDeleteArticleHandler_jg implements CommandHandler_Mj {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDAO adminDAO = new MemberDAO();
		Connection conn = ConnectionProvider_Mj.getConnection();
		conn.setAutoCommit(false);
		int no = Integer.parseInt(request.getParameter("no"));
		
		int result = adminDAO.delete(conn,no);
		
		if(result == 0) {
			conn.rollback();
			throw new RuntimeException();
		}
		conn.commit();
		conn.close();
		return "../admin/adm_DeleteSuccess_jg.jsp";
	}

}
