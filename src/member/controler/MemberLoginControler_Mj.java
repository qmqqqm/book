package member.controler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import member.controler.model.UserDTO_Mj;
import member.controler.service.MemberLoginService_Mj;
import mvc.command.CommandHandler_Mj;

public class MemberLoginControler_Mj implements CommandHandler_Mj {

	//변수에 로그인 처리뷰페이지저장
	private static final String FORM_VIEW =
	"/member/mem_Login_Mj.jsp";
	//서비스 객체생성
	MemberLoginService_Mj loginService = new  MemberLoginService_Mj();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파라미터을 get으로 받았으면  processForm()호출
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
			
		}else if(request.getMethod().equalsIgnoreCase("POST")){
			//파라미터을 post로 받았으면  processSubmit()호출
			return processSubmit(request,response);
			
		}else {
			//그외는  서블릿 에러로 처리
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	
	//로그인폼의  비즈니스로직 수행	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		//1.유져가 입력한 id 비밀번호 가져오기
		String id  = trim(request.getParameter("mid"));  //user가 입력한 id
		String pwd = trim(request.getParameter("mpwd")); //user가 입력한 비번
				
		//2.비즈니스로직수행
		//error의 종류를 구분하여 처리하기 위해  Map에 에러유형  저장
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		
		//발생에[러에따른 피드백을 뷰단에 송출하기위해 errors를 Model로 처리  
		request.setAttribute("errors", errors);
		
		//id, 비번 미입력 검사		
		if( id==null || id.isEmpty() ) {
			errors.put("id", Boolean.TRUE);
		}
		if( pwd==null || pwd.isEmpty()) {
			errors.put("pwd", Boolean.TRUE);
		}
		if( !errors.isEmpty() ) { //errors가 비어있지않으면=>에러가 있으면
			return request.getContextPath()+FORM_VIEW;     //loginFrm2.jsp로 이동
		}
		
		//서비스에 login 메소드호출후 로직실행 결과를 UserDTO_Mj 객체로받아 user 변수에 저장 저장
		UserDTO_Mj user = loginService.login(id,pwd);
		System.out.println("컨트롤러에서 받은 유저정보"+user);
		//입력한 아이디 비번 데이터와비교후 에러처리 
		if(user==null) { 
			errors.put("usernull", Boolean.TRUE);			
			return request.getContextPath()+FORM_VIEW;
		}
		if( user.getMatchPassword()==false ) { //p606 20라인
			errors.put("notMatch", Boolean.TRUE);			
			return request.getContextPath()+FORM_VIEW;
		}
		
		
		//3.Model - session 또는 request이용 
		//로그인이 성공되면  로그인성공한 user정보를 session에 저장
		HttpSession session = request.getSession();
		session.setAttribute("AUTHUSER", user);	
		
		//4.View -
		//메인페이지로 이동
		try {
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	//파라미터의 value의 좌우 양쪽끝의  공백 제거 
	private String trim(String str) {		
		return str==null? null:str.trim();
	}	
	//로그인폼의 method방식이 get이면  별다른 처리없이  loginFrm2.jsp 로 이동 
	private String processForm(HttpServletRequest request, HttpServletResponse response) {		
		return request.getContextPath()+FORM_VIEW;
	}

}