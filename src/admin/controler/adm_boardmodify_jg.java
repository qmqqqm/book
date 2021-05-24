package admin.controler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.controler.service.dao.BoardDAO;
import admin.controler.service.dto.BoardArticle;
import admin.controler.service.dto.MemberArticleData;
import admin.service.ArticleNotFoundException_jg;
import admin.service.BoardModifyArticleService_jg;
import admin.service.BoardModifyRequest_jg;
import admin.service.User_jg;
import jdbc.connection.ConnectionProvider_Mj;
import mvc.command.CommandHandler_Mj;


// 컨택스트패스/article/modify.do=article.command.ModifyArticleHandler
public class adm_boardmodify_jg implements CommandHandler_Mj {

	private static final String FORM_VIEW = "../admin/adm_boardmodify_jg.jsp";

	// 글상세보기를 위한 ReadArticleService 참조변수선언
	private BoardDAO boardDao = new BoardDAO();

	// 글수정을 위한 ModifyArticleService 참조변수선언
	private BoardModifyArticleService_jg modifyService = new BoardModifyArticleService_jg();
	
	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("WriteArticleHandler의 process()진입/method방식=" + request.getMethod());
		// METHOD방식을 구분해서
		if (request.getMethod().equalsIgnoreCase("GET")) {// 글쓰기폼 보여주기 요청(GET방식)
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}

	}

	// 글수정처리 요청(POST방식) - p670 66라인
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		System.out.println("글수정처리 요청 post방식 진입");
   //1.파라미터가져오기
   int no = Integer.parseInt(request.getParameter("no")); //글번호
   int pageNo = Integer.parseInt(request.getParameter("pageNo")); //페이지 번호 --> 릴레이용
   String title = request.getParameter("title"); //id
   String content = request.getParameter("content"); //pwd
   
   




   //String mid = request.getParameter("mid"); //id
   // 로그인한 user의 정보 가져오기
   HttpSession session = request.getSession();
   User_jg authUser = (User_jg) session.getAttribute("ADMIN");
   System.out.println("로그인한 user의 정보 가져오기");
   //비즈니스로직을 수행할 때 필요한 data를 ModifyRequest 객체로 생성
   BoardModifyRequest_jg modifyReq = new BoardModifyRequest_jg(authUser.getId(),no,title,content);
   System.out.println("비즈니스로직을 수행할 때 필요한 data를 ModifyRequest 객체로 생성");
   //p670 77라인 
   Map<String,Boolean> errors = new HashMap<String, Boolean>();
   modifyReq.validate(errors);
   request.setAttribute("errors", errors);
 
   
   
   //2.비즈니스로직수행<->WriteArticleService<->ArticleDAO,ArticleContentDAO<->DB
   modifyService.modify(modifyReq,no,title,content);
   System.out.println("비즈니스로직수행");
   //3.Model
   request.setAttribute("modifyReq", modifyReq);
   request.setAttribute("pageNo", pageNo);
   request.setAttribute("no", no);
   
   
   //4.View
   return "../admin/adm_BoardModifySuccess_jg.jsp";
   
}//processSubmit()

	// 수정폼 보여주기 요청(GET방식)
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ArticleNotFoundException_jg, SQLException {
		System.out.println("ModifyArticleHandler의 process() 진입");

		// 1.파라미터가져오기 - no = 글번호
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String strNo = request.getParameter("no");
		int no = Integer.parseInt(strNo);
		System.out.println("GET방식 pageNo="+pageNo);
		
			// 2.비즈니스로직수행 <-> ReadArticleService <-> ArticleDAO + ArticleContentDAO <-> DB
			// p660 22라인
			// articleNum는 조회수 증가시킬 특정글번호
			// true는 조회수 증가시 true
			// false는 조외수증가x(수정할때는 조회수증가x)
			// 조회수증가 -> 변경된 조회수를 포함한 특정글조회
			// ArticleData의 객체는 Article클래스와 ArticleContent클래스를 field로 가졌음
		    
			Connection conn =  ConnectionProvider_Mj.getConnection();
		    BoardArticle articleData = boardDao.selectById(conn,no);

			// p670 44라인 ~~~~~~~~~~추가
			// 로그인한 user의 정보 가져오기
			HttpSession session = request.getSession();
			User_jg authUser = (User_jg) session.getAttribute("ADMIN");
			
			conn.close();

			// p670 53라인
			// 3.Model - 번호,작성자id,작성자명,제목,내용,작성일,수정일,조회수
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("no", no);
			request.setAttribute("articleData", articleData);
			// 4.view - 수정폼으로
			return FORM_VIEW;
	}// processForm
	
	//p670 61라인
	// 로그인한 user가 수정을 할 수 있는 권한이 있는지 check
	// ==> 로그인한 user의 id가 글작성자id와 일치하는지 check
	private boolean canModify(User_jg authUser, MemberArticleData articleData) {

		boolean result = (authUser!=null);
		return result;

	}// canModify

}
