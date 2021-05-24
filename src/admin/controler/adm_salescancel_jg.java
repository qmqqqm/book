package admin.controler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.controler.service.dao.AdminDAO;
import admin.controler.service.dao.BookDAO;
import admin.controler.service.dao.OrderDAO;
import jdbc.connection.ConnectionProvider_Mj;
import mvc.command.CommandHandler_Mj;

public class adm_salescancel_jg implements CommandHandler_Mj {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderDAO adminDAO = new OrderDAO();
		BookDAO bookDao = new BookDAO();
		Connection conn = ConnectionProvider_Mj.getConnection();
		conn.setAutoCommit(false);
		int no = Integer.parseInt(request.getParameter("oderNo"));
		int count = Integer.parseInt(request.getParameter("count"));
		int book_id = Integer.parseInt(request.getParameter("book"));
		
		bookDao.update(conn, book_id, count);
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
