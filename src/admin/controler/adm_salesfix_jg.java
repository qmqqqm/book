package admin.controler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.controler.service.dao.AdminDAO;
import admin.controler.service.dao.IncomeDAO;
import admin.controler.service.dao.OrderDAO;
import jdbc.connection.ConnectionProvider_Mj;
import mvc.command.CommandHandler_Mj;

public class adm_salesfix_jg implements CommandHandler_Mj {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderDAO adminDAO = new OrderDAO();
		IncomeDAO income = new IncomeDAO();
		Connection conn = ConnectionProvider_Mj.getConnection();
		conn.setAutoCommit(false);
		int no = Integer.parseInt(request.getParameter("oderNo"));
		int money = Integer.parseInt(request.getParameter("income"));
		System.out.println(money);
		
		int result = income.update(conn, money);
		adminDAO.delete(conn, no);
		if(result == 0) {
			conn.rollback();
			throw new RuntimeException();
		}
		conn.commit();
		conn.close();
		return "../admin/adm_salesSuccess_jg.jsp";
	}

}
