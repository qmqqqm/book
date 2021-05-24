package board.free.controler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.free.controler.model.FreeBoardListWriter_Mj;
import board.free.controler.service.FreeBoardWriteService_Mj;

import board.free.controler.service.dto.WriteRequestDTO_Mj;
import member.controler.model.UserDTO_Mj;
import mvc.command.CommandHandler_Mj;

// 글쓰기 처리 컨트롤러
public class FreeBoardWriteControler_Mj implements CommandHandler_Mj {
	
	private static final String FORM_VIEW
			= "/board/free/freeWriteForm_Mj.jsp";
	FreeBoardWriteService_Mj writeService = 
		new FreeBoardWriteService_Mj(); 
			
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WriteArticleHandler의 process()진입/method방식="+request.getMethod());
		//METHOD방식을 구분해서
		if(request.getMethod().equalsIgnoreCase("GET")) {//글쓰기폼 보여주기 요청(GET방식) 
			return processForm(request, response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {//작성한글을 저장
			return processSubmit(request, response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
				
	}
	
	//작성한글 저장 요청(POST방식)
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		//Map방식으로 error정보를 담는 변수선언
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		
		//1.파라미터가져오기
		//String mid = request.getParameter("mid");		
		//글쓴이에 대한 정보는  로그인한 user의 정보가 담긴 session에서 가져온다
		HttpSession session = request.getSession();
		UserDTO_Mj user = (UserDTO_Mj)session.getAttribute("AUTHUSER");
		
		//2.비즈니스로직수행
		
		WriteRequestDTO_Mj writeReq = createWriteRequest(user,request);
		writeReq.validate(errors); //title유효성검사
	
		if( !errors.isEmpty()) { //errors객체에 뭔가가 저장되어있으면
			return request.getContextPath()+FORM_VIEW;	 //글입력폼으로 이동
		}
		writeService.write(writeReq);
				
		//4.View
		return request.getContextPath()+"/board/freeboardlist.book";
		
	}
	
	
	private WriteRequestDTO_Mj createWriteRequest(UserDTO_Mj user,
						 HttpServletRequest request) {
		
		return
		new WriteRequestDTO_Mj(
				new FreeBoardListWriter_Mj(user.getId(), user.getName()), 
				request.getParameter("title"), 
				request.getParameter("content"));
	
	}
	
	
	//글쓰기폼 보여주기 요청(GET방식) 
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return request.getContextPath()+FORM_VIEW;
	}
		
	
}






