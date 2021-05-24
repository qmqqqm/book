package board.qna.controler;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qna.controler.service.QnaBoardDeleteService_JI;
import mvc.command.CommandHandler_Mj;

public class QnaBoardDeleteControler_JI implements CommandHandler_Mj {

	private QnaBoardDeleteService_JI qnaDeleteService = new QnaBoardDeleteService_JI();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Q&A삭제핸들러 프로세스 진입");
		
		//1.파라미터받기
		//no=글번호받기
		int no = Integer.parseInt(request.getParameter("no"));	//글번호
		System.out.println("삭제할 글번호 = "+no); //확인용
		
		
		//2.비즈니스로직 ->DeleteArticleService<->ArticleDAO,ArticleContentDAO<->DB
		int result = qnaDeleteService.delete(no);
		//result변수에는 성공시 1의 값이 저장된다
		
		//3.모델
		request.setAttribute("result", result);
		
		//4.뷰
		return "../board/qna/QnaDeleteSuccess.jsp";
	}


}
