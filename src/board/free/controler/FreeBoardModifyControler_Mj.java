package board.free.controler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.free.controler.service.FreeBoardModifyService_Mj;
import board.free.controler.service.FreeBoardReadService_Mj;

import board.free.controler.service.dto.FreeBoardDTO_Mj;
import board.free.controler.service.dto.ModifyRequestDTO_Mj;
import board.free.error.ArticleContentNotFoundException;
import board.free.error.ArticleNotFoundException;
import member.controler.model.UserDTO_Mj;
import mvc.command.CommandHandler_Mj;

//자유게시판 글수정 컨트롤러
public class FreeBoardModifyControler_Mj implements CommandHandler_Mj {

	private static final String FORM_VIEW
		= "/board/free/modifyForm.jsp";
	
	//글상세보기를 위한  참조변수 선언
	private FreeBoardReadService_Mj readService
		= new FreeBoardReadService_Mj();
	
	//글수정을 위한  참조변수 선언
	private FreeBoardModifyService_Mj modifyService  
		= new FreeBoardModifyService_Mj();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//METHOD방식을 구분해서 조회와 수정 메소드 호출
		if(request.getMethod().equalsIgnoreCase("GET")) {//글쓰기폼 보여주기 요청(GET방식) 
			return processForm(request, response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}

	//글쓰기처리 요청(POST방식) 
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		//1.파라미터받기
		int no = Integer.parseInt(request.getParameter("no"));//글번호
		int pageNo=Integer.parseInt(request.getParameter("pageNo")); //페이지번호.릴레이용
		String title = request.getParameter("title");	 //제목
		String content = request.getParameter("content"); //내용		
		
		//로그인한 user의 정보가져오기
		HttpSession session=request.getSession();
		UserDTO_Mj authUser = (UserDTO_Mj)session.getAttribute("AUTHUSER");
		
		//비즈니스로직을 수행할 때 필요한 데이터를  ModifyRequestDTO_Mj객체로 생성 
		ModifyRequestDTO_Mj modReq = 
			new ModifyRequestDTO_Mj(authUser.getId(),no,title,content);
		
		//에러처리를위한 해시맵객체생성
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		request.setAttribute("errors", errors); //에러정보
		modReq.validate(errors);
		if(!errors.isEmpty()) { //에러가 있으면(여기에서는 title유효성검사)
			return request.getContextPath()+FORM_VIEW;   //에러정보를 가지고 수정폼으로 가라
		}
		
		
		//2.비즈니스로직수행
		modifyService.modify(modReq);
		
		//3.Model
		request.setAttribute("modReq", modReq); //수정된 글정보
		request.setAttribute("pageNo", pageNo); //페이지번호.릴레이용
		
		//4.View
		return request.getContextPath()+"/board/freeboardlist.book?pageNo="+pageNo;
	}

	//수정폼 보여주기 요청(GET방식) 
	private String processForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		//1.파라미터가져오기 -no = 글번호
		String pageNo = request.getParameter("pageNo");//릴레이용
		String strNo  = request.getParameter("no");
		int no = Integer.parseInt(strNo);

		//2.비즈니스로직수행
		try {			
			FreeBoardDTO_Mj articleData = readService.getArticle(no,false);						
			//로그인한 user의 정보가져오기
			HttpSession session=request.getSession();
			UserDTO_Mj authUser = (UserDTO_Mj)session.getAttribute("AUTHUSER");
			//로그인한 user의 id가   글 작성자id와 일치하는지 check
			boolean reuslt = canModify(authUser,articleData);
			if(!reuslt) {
				//HttpServletResponse.SC_FORBIDDEN은  403error				
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
						
			//ModifyRequestDTO_Mj객체를 Model로 넘긴다
			ModifyRequestDTO_Mj modReq = new ModifyRequestDTO_Mj(
					authUser.getId(),
					no,
					articleData.getTitle(),
					articleData.getContent()
				);
			//3.Model
	     	request.setAttribute("modReq",modReq);
	     	request.setAttribute("pageNo",pageNo); //페이지번호. 릴레이용
			
			//4.View
			return request.getContextPath()+FORM_VIEW;
			
		}catch(ArticleNotFoundException |
			   ArticleContentNotFoundException e) {
			request.getServletContext().log("no article",e);
			//404에러코드를 전송
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}
	//로그인한 user의 id가   글 작성자id와 일치하는지 확인하는 메소드
	private boolean  canModify(UserDTO_Mj authUser,FreeBoardDTO_Mj articleData) {
		String loginUserId = authUser.getId();  //로그인한 user의 id
		String writerId 
		= articleData.getWriter().getId();//글 작성자id	
		return  loginUserId.equals(writerId);
	}

}




