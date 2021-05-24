package admin.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.service.BoardArticlePage_jg;
import admin.service.BoardListArticleService_jg;
import mvc.command.CommandHandler_Mj;

public class adm_boardControler_jg implements CommandHandler_Mj {

	// p652 12라인
	private BoardListArticleService_jg listService = new BoardListArticleService_jg();

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
		BoardArticlePage_jg articlePage = listService.getArticlePage(pageNo);
		// 3.모델- session or request
		request.setAttribute("articlePage", articlePage);

		// 4.View
		return "../admin/adm_Board_jg.jsp";

	}
}
