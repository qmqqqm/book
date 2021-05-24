package admin.controler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.controler.service.dao.BookDAO;
import admin.controler.service.dao.IncomeDAO;
import admin.controler.service.dao.OrderDAO;
import admin.controler.service.dto.BookDTO;
import admin.controler.service.dto.OrderArticle;
import admin.service.OrderArticlePage_jg;
import admin.service.OrderListArticleService_jg;
import jdbc.connection.ConnectionProvider_Mj;
import mvc.command.CommandHandler_Mj;

public class adm_orderControler_jg implements CommandHandler_Mj {
	// p652 12라인
	private OrderListArticleService_jg listService = new OrderListArticleService_jg();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1.파라미터받기 -p651 17라인
		// user가 보고싶은 페이지
		String pageNoVal = request.getParameter("pageNo");

		// user가 보고싶은 페이지가 넘어오지않을 때는 기본적으로 1page
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}

		// 2.비즈니스로직수행<->ListArticleServie<->ArticleDAO<->DB
		// (페이지별로 원하는 특정 범위의)글목록 조회 -p652 22라인
		OrderArticlePage_jg articlePage = listService.getArticlePage(pageNo);
		Iterator it = articlePage.getContent().iterator();
		List<BookDTO > book = new ArrayList<BookDTO>();
		BookDAO bookDao = new BookDAO();
		OrderDAO oderDao = new OrderDAO();
		IncomeDAO income = new IncomeDAO();
		Connection conn = ConnectionProvider_Mj.getConnection();
		int monney = income.selectincome(conn);
		int result = oderDao.selectcountid(conn);
		while(it.hasNext()) {
			OrderArticle oder = (OrderArticle)it.next();
			book.add(bookDao.selectByno(conn,oder.getBook_no()));
		}
		conn.commit();
		conn.close();
		
		// 3.모델- session or request
		request.setAttribute("articlePage", articlePage); //order
		request.setAttribute("book", book); //book
		request.setAttribute("result", result); //
		request.setAttribute("monney", monney);
		System.out.println(articlePage);
		System.out.println(book);
		System.out.println(result);
		// 4.View
		return "../admin/adm_Sales_jg.jsp";

	}
}
