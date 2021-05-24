package board.qna.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.service.User_jg;
import board.free.error.ArticleContentNotFoundException;
import board.free.error.ArticleNotFoundException;
import board.qna.controler.model.QnaModifyRequest_JI;
import board.qna.controler.service.QnaBoardModifyArticleService_JI;
import board.qna.controler.service.QnaBoardReadArticleService_JI;
import board.qna.controler.service.dto.QnaListDTO_JI;
import mvc.command.CommandHandler_Mj;

//컨텍스트패스/board/qnamodify.do?no=글번호&pageNo=페이지번호 요청을 담당하는 클래스
//수정폼 보여주기 요청(GET방식) + 수정처리 요청(POST방식)
public class QnaBoardModifyControler_JI implements CommandHandler_Mj {

	private static final String FORM_VIEW
    = "../board/qna/QnaModifyForm.jsp";

	//글 상세보기를 위한 ReadArticleService 참조변수 선언
	private QnaBoardReadArticleService_JI qnaReadService = new QnaBoardReadArticleService_JI();
	
	//글 수정을 위한 ModifyArticleService 참조변수 선언
	private QnaBoardModifyArticleService_JI qnaModifyService = new QnaBoardModifyArticleService_JI(); 
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	System.out.println("QnaBoardModifyControler_JI의 process()진입/method방식="+request.getMethod());
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
	
	
	//수정처리 요청(POST방식) -p670 66라인
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
			
			//1.파라미터 가져오기
			int no = Integer.parseInt(request.getParameter("no"));//글번호
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));//페이지번호 릴레이용
			
			String title = request.getParameter("title");	//제목
			String content = request.getParameter("content"); //내용
			
			System.out.println("수정processSubmit 진입");
			//로그인한 user의 정보 가져오기
			HttpSession session = request.getSession();
			User_jg admin = (User_jg)session.getAttribute("ADMIN");
			
			//비즈니스로직을 수행할 때 필요한 데이터를 ModifyRequest객체로 생성
			QnaModifyRequest_JI modReq = new QnaModifyRequest_JI(
					admin.getId(), no, title, content);
			System.out.println("왓니?");
			//에러처리 
			Map<String,Boolean> errors = new HashMap<String,Boolean>();
			request.setAttribute("errors", errors);	//에러정보
			
			modReq.validate(errors);
			if(!errors.isEmpty()) {	//에러가 있으면(여기에서는 title유효성 검사)
			return FORM_VIEW;	//에러정보를 가지고 수정폼으로 가라
			}
			
			//p670 84라인
			//2.비즈니스 로직 수행하기<->ModifyArticleService<->
			//						ArticleDAO,ArticleContentDAO<->DB
			qnaModifyService.modify(modReq);
			
			//3.모델
			request.setAttribute("modReq", modReq);	//수정된 글 정보
			request.setAttribute("pageNo", pageNo); //페이지번호 릴레이용
			
			//4.뷰 view/article/modifySuccess.jsp -p675
			return "../board/qna/QnaModifySuccess.jsp";
			}
	
	
	//수정폼 보여주기 요청(GET방식) -p669 38라인 
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
			
			//특정 글번호 내용이 출력되어야한다		
			
			System.out.println("QnaBoardModifyControler_JI의 processform()진입/method방식="+request.getMethod());
			
			//1.파라미터 가져오기
			String strPageNo = request.getParameter("pageNo");
			int pageNo = Integer.parseInt(strPageNo);
			String strNo = request.getParameter("no");
			int no = Integer.parseInt(strNo);
			
			try {
			//2.비즈니스로직 수행 <->QnaBoardModifyArticleService_JI<->QnaListDTO_JI<->DB
			//articleNum는 	조회수 증가시킬 특정글번호
			//true는 			조회수 증가시 true
			//false는 		조회수 증가x(수정할때는 조회수 증가x)
				QnaListDTO_JI qnaArticle = qnaReadService.getArticle(no,false);
			
			//로그인한 user의 정보 가져오기
			HttpSession session = request.getSession();
			User_jg admin = (User_jg)session.getAttribute("ADMIN");
			
			//로그인한 user가 수정을 할 수 있는 권한이 있는지 check
			//로그인한 user의 id가 글 작성자 id와 일치 여부 check
			boolean result = canModify(admin, qnaArticle);
			if(!result) {
				//HttpServletResponse.SC_FORBIDDEN 403error
				//접근이 거부된 문서를 요구할 때
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			//p670 49라인
			//(session)로그인한 user의 id와 (parameter로 받은)글번호
			//+(DB)작성글제목,(DB)글내용을 객체로 생성하여
			//Model로 넘긴다
			QnaModifyRequest_JI modReq = new QnaModifyRequest_JI(
					admin.getId(),
					no,
					qnaArticle.getQ_title(),
					qnaArticle.getQ_content()
					);
				
			
			//p670 53라인
			//3.모델 - 번호,작성자id,작성자이름,제목,내용,조회수,작성일,수정일 request.setAttribute()
			request.setAttribute("modReq", modReq);
			request.setAttribute("pageNo", pageNo);
			
			//4.view(문자열로 넣음) -수정폼
			return FORM_VIEW;
			
			}catch(ArticleNotFoundException | ArticleContentNotFoundException e){
			request.getServletContext().log("no articel",e);
			//404에러코드를 전송한다  p600 27라인
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
			}
			
			}//processForm
			
			
			//p670 61라인
			//로그인한 user가 수정을 할 수 있는 권한이 있는지 check
			//로그인한 user의 id가 글 작성자 id와 일치 여부 check
	
	private boolean canModify(User_jg admin,QnaListDTO_JI qnaArticle){
	
			String loginUserId = admin.getId(); //로그인한 직원의 id
			String writerId = qnaArticle.getId(); //글 작성자 id
			
			//스트링클래스.equals("비교문자열") : 값 일치 비교
			return loginUserId.equals(writerId);
			}


}
