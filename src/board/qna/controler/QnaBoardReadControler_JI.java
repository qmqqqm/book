package board.qna.controler;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.service.User_jg;
import board.free.error.ArticleContentNotFoundException;
import board.free.error.ArticleNotFoundException;
import board.qna.controler.service.QnaBoardReadArticleService_JI;
import board.qna.controler.service.dto.QnaListDTO_JI;
import mvc.command.CommandHandler_Mj;

//이 클래스는 글상세조회 요청을 담당하는 컨트롤러
//컨택스트패스/article/read.do?no=글번호&pageNo=페이지번호
public class QnaBoardReadControler_JI implements CommandHandler_Mj{
	
	private QnaBoardReadArticleService_JI  qnaReadService =
			new QnaBoardReadArticleService_JI();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("QnaBoardReadControler_JI의 process()진입");	//확인용
	
		//1.파라미터가져오기 -no = 글번호 , pageNo=페이지번호
		String pageNo = request.getParameter("pageNo");//릴레이용
		String strNo  = request.getParameter("no");
		int articleNum = Integer.parseInt(strNo);
		//2.비즈니스로직수행
		try {
			//조회수 증가 제약
			boolean isHit = false; //조회수 증가할지 말지 여부를 저장하는 변수
			HttpSession session = request.getSession(); //session얻기
			
			//user가 본 게시물의 글번호를 누적해서 저장하기위한 list준비
			ArrayList<Integer> list 
				= (ArrayList)session.getAttribute("HIT");
			
			if(list==null || list.size()==0) {
				//조회리스트가 비어있는경우 조회한글번호 리스트 추가 조회수증가
				list = new ArrayList<Integer>();
				list.add(articleNum); 
				isHit = true;
				session.setAttribute("HIT", list);
			}else if( list.contains(articleNum) ) { 
				//리스트목록존재하고 조회한 글번호가 있는경우 조회수 미증가 
				isHit = false;
			}else {
				//리스트목록존재하고 조회한 글번후 없는경우 글번호 추가후 조회수증가
				list.add(articleNum); //list.add(글번호)
				isHit = true;
				session.setAttribute("HIT", list);}
			QnaListDTO_JI articleData = qnaReadService.getArticle(articleNum,isHit);
			
			//3.Model- 번호,작성자id,작성자명,제목,내용,작성일,수정일, 조회수
	     	request.setAttribute("articleData",articleData);
	     	request.setAttribute("pageNo",pageNo); //페이지번호. 릴레이용
			
     	   
     	   User_jg user = (User_jg)session.getAttribute("ADMIN");
	     	
			//4.View
			return "../board/qna/QnaReadArticle_JI.jsp";
			
		}catch(ArticleNotFoundException |
			   ArticleContentNotFoundException e) {
			request.getServletContext().log("no article",e);
			//404에러코드를 전송한다 
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	
	
}
