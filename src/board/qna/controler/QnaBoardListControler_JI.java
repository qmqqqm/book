package board.qna.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//DTO들어가야함
import board.qna.controler.service.QnaBoardListPageService_JI;
import board.qna.controler.service.QnaBoardListService_JI;
import mvc.command.CommandHandler_Mj;

public class QnaBoardListControler_JI implements CommandHandler_Mj{

	private QnaBoardListService_JI qnaListService = new QnaBoardListService_JI();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			//1.파라미터받기 -p651 17라인
			//user가 보고싶은 페이지
			String pageNoVal = request.getParameter("pageNo");
			
			//user가 보고싶은 페이지가 넘어오지않을 때는 기본적으로 1page
			int pageNo = 1;
			if(pageNoVal!=null) {
				pageNo = Integer.parseInt(pageNoVal);
			}
			
			//2.비즈니스로직수행<->QnaBoardListService_JI<->QnaDAO<->DB
			//(페이지별로 원하는 특정 범위의)글목록 조회 -p652 22라인
			QnaBoardListPageService_JI articlePage = qnaListService.getArticlePage(pageNo);
					
			//3.모델- session or request
			request.setAttribute("articlePage",articlePage);
			HttpSession session = request.getSession();
			session.getAttribute("ADMIN");

			
			//4.View
			return "../board/qna/boa_Qnalist_JI.jsp";
		}

}
