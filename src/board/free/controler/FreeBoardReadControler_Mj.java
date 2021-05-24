package board.free.controler;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.free.controler.service.FreeBoardReadService_Mj;
import board.free.controler.service.dto.FreeBoardDTO_Mj;
import board.free.error.ArticleContentNotFoundException;
import board.free.error.ArticleNotFoundException;
import mvc.command.CommandHandler_Mj;


//자유게시판 조회 컨트롤러
public class FreeBoardReadControler_Mj implements CommandHandler_Mj {

	private FreeBoardReadService_Mj  readService =
			new FreeBoardReadService_Mj();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		//1.파라미터가져오기 
		String pageNo = request.getParameter("pageNo");
		String strNo = request.getParameter("no");
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
				session.setAttribute("HIT", list);}//세션시간동안 조회수증가제약유지
			FreeBoardDTO_Mj articleData = readService.getArticle(articleNum,isHit);			
			//3.Model
	     	request.setAttribute("articleData",articleData);		 
	     	request.setAttribute("pageNo",pageNo); //페이지번호. 릴레이용
			//4.View
			return request.getContextPath()+"/board/free/freeRead_Mj.jsp";
			
			}catch(ArticleNotFoundException |
			   ArticleContentNotFoundException e) {
			request.getServletContext().log("no article",e);
			//404에러코드 전송
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
}








