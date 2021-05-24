package board.qna.controler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.controler.service.dto.AdminDTO;
import admin.service.User_jg;
import board.qna.controler.model.QnaWriteRequest_JI;
import board.qna.controler.service.QnaWriteService_JI;
import mvc.command.CommandHandler_Mj;

//글쓰기폼 보여주기 요청(GET방식) + 글쓰기처리 요청(POST방식)
public class QnaBoardWriteControler_JI implements CommandHandler_Mj {

	private static final String FORM_VIEW = "../board/qna/QnaWriteForm.jsp";
	QnaWriteService_JI qnaWriteService =  new QnaWriteService_JI(); 
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  System.out.println("QnaBoardWriteControler_JI의 process()진입/method방식="+request.getMethod());
		//METHOD방식을 구분해서
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
		System.out.println("submit진입");
	   //Map 방식으로 error정보를 담는 변수선언
	   Map<String,Boolean> errors = new HashMap<>(); 
	   request.setAttribute("errors", errors);
	   
	  //1.파라미터가져오기
	  String title = request.getParameter("title");   //제목
	  String content = request.getParameter("content");//내용
	  
	  //2.비즈니스로직수행<->WriteArticleService
	  //               <->ArticleDAO,ArticleContentDAO<->DB
	  //글쓴이에 대한 정보는 로그인한 user의 정보가 담긴 session에서 가져온다
	  HttpSession session = request.getSession();
	  User_jg user = (User_jg)session.getAttribute("ADMIN");
	
	  QnaWriteRequest_JI qnaWriteReq = createWriteRequest(user,request);	//user id, title, content 저장
	  qnaWriteReq.validate(errors); //title유효성검사
	  
	  
	  //isEmpty():Returns true if this map contains no key-value mappings.
	  if(!errors.isEmpty()) { //errors객체에 뭔가가 저장되어있으면
		  return FORM_VIEW;		//글 입력폼으로 이동
	  }
	  
	  //qnaWriteService.write(user id, title, content)
	  //insert된 글번호를 받아서 newQnaNo 변수에 저장
	  int newQnaNo = qnaWriteService.write(qnaWriteReq);
	
	  //3.Model
	  request.setAttribute("newQnaNo", newQnaNo);
	  
	  //4.View
	  return "../board/qna/QnaWriteSuccess.jsp";
	  
	}//processSubmit()
	
	//p641 53라인
	private QnaWriteRequest_JI createWriteRequest(User_jg user,HttpServletRequest request) {
	   
	   return 
	   new QnaWriteRequest_JI(user.getId(),
				   request.getParameter("title"),
				   request.getParameter("content"));
	//new WriteRequest(Writer writer, String title, String content)
	//new Writer(String id, String name)
	}
	
	
	//글쓰기폼 보여주기 요청(GET방식) 
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
	  return FORM_VIEW;
	}
	  
		
}
