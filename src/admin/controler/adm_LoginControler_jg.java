package admin.controler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.service.LoginService_jg;
import admin.service.User_jg;
import mvc.command.CommandHandler_Mj;

public class adm_LoginControler_jg implements CommandHandler_Mj {


	//p606 16라인
	//로그인폼을 상수로 정의
	private static final String FORM_VIEW =
			"/admin/adm_Login_jg.jsp";
	
	LoginService_jg loginService = new  LoginService_jg();
	
	
	//p606 20라인
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request.getMethod() : 요청정보전송방식구하기 
		//로그인폼의 method방식이 get이면
		//loginFrm2.jsp로 이동(여기에서는 processForm()호출)
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
			
		}else if(request.getMethod().equalsIgnoreCase("POST")){
			System.out.println("post방식 진입확인");
			//로그인폼의 method방식이 post이면
			//로그인을 위한 비즈니스로직 수행(여기에서는 processSubmit()호출)
			return processSubmit(request,response);
			
		}else {
			//그외는  허용된 메소드가 아니다라고 처리
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	//p607 36라인
	//로그인폼의 method방식이 post이면  로그인을 위한 비즈니스로직 수행
	//로그인이 성공되면   index.jsp문서로 이동
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("post방식");
		
		//1.parameter값가져오기
		String id  = trim(request.getParameter("mid"));  //user가 입력한 id
		String pwd = trim(request.getParameter("mpwd")); //user가 입력한 비번
		System.out.println("processSubmit()-id,pwd="+id+","+pwd); //console에 출력. 확인용
		
		//2.비즈니스로직수행
		//여기에서는  id와 비번값을 check부분이 추가-p607 41~51라인
		//error의 종류를  Map형태로 저장
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		
		//request를 이용해서  errors를 Model로 처리  
		request.setAttribute("errors", errors);
		
		//id, 비번 검사
		//isEmpty() : Returns true if, and only if, length() is 0.
		if( id==null || id.isEmpty() ) {
			errors.put("id", Boolean.TRUE);
		}
		if( pwd==null || pwd.isEmpty()) {
			errors.put("pwd", Boolean.TRUE);
		}
		
		//isEmpty() : Returns true if this map contains no key-value mappings.
		if( !errors.isEmpty() ) { //errors가 비어있지않으면=>에러가 있으면
			return FORM_VIEW;     //loginFrm2.jsp로 이동
		}
		
		//비즈니스로직 : 로그인처리<-> LoginService <-> MemberDAO <-> DB
		User_jg user = loginService.login(id,pwd);
		
		if(user.getId()=="1") {
			return FORM_VIEW;
		}
	
		//3.Model - session 또는 request이용 
		//로그인이 성공되면  로그인성공한 user정보를 session에 저장
		HttpSession session = request.getSession();
		/*임시로... 예를 들면...
		 * session.setAttribute("MID",  "hongid");
		 * session.setAttribute("MNAME", "홍GD");
		 * session.setAttribute("MNICKNAME", "의적");
		 */
		session.setAttribute("ADMIN", user);
		
		//4.View - p607 56라인
		/*현재위치 url 컨텍스트패스/login.do
		     위치	     컨텍스트패스/index.jsp*/
		try {
			response.sendRedirect(request.getContextPath()+"/admin/staff.book");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	//파라미터의 value의 좌우 양쪽끝의  공백 제거 -p607 64라인
	private String trim(String str) {
		//trim(): Returns a string whose value is this string, with any leading and trailing whitespace removed. 
		return str==null? null:str.trim();
	}

	//p607 32라인
	//로그인폼의 method방식이 get이면   loginFrm2.jsp로 이동 
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("get방식");
		//교재에서는 loginForm.jsp문서가 WEB-INF/view/loginForm.jsp 존재하지만
		//우리는 컨텍스트패스/view/member/loginFrm2.jsp
		return FORM_VIEW;
	}

}

