package member.controler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.controler.model.UserDTO_Mj;
import member.controler.service.MemberChangePwdService_Mj;
import member.error.InvalidPwdException_Mj;
import member.error.MemberNotFoundException_Mj;
import mvc.command.CommandHandler_Mj;


//회원의 비번을 변경하는 담당컨트롤러
public class MemberChangePwdControler_Mj implements CommandHandler_Mj{

	//뷰페이지 변수선언
	private static final String FORM_VIEW = "/member/mem_ChangePwd_Mj.jsp";
	
	//서비스클래스 객체 생성
	MemberChangePwdService_Mj changePwdSvc = new MemberChangePwdService_Mj();
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ChangePwdHandler의 process()진입");
		
		//겟방식요청시 processForm 메소드호출
		if(request.getMethod().equalsIgnoreCase("GET")) { 			
			return processForm(request,response);
		//포스트방식요청시 processSubmit 메소드호출
		}else if(request.getMethod().equalsIgnoreCase("POST")) { 		
			return processSubmit(request,response);
		}else {
			//기타 에러처리
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}

	
	//겟방식 요청식 로직수행없이 암호변경페이지 재 호출
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return request.getContextPath()+FORM_VIEW;
	}

	
	//포스트방식 요청시 실제 암호변경 비즈니스로직 수행
	private String processSubmit(HttpServletRequest request, 
			                     HttpServletResponse response)
								throws Exception {
		
		//1.파라미터 가져오기
		String curPwd = request.getParameter("curPwd");
		String newPwd = request.getParameter("newPwd");
		System.out.println("processSubmit() curPwd/newPwd="
		                   +curPwd+"/"+newPwd);
		
		//Map방식으로 error정보를 담아 관리 
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		//FORM_VIEW에서 에러정보를 확인하고 출력하기 위해 Model로 처리한다
		request.setAttribute("errors", errors);
		
		//현재암호와 새암호 필수입력 체크
		if(curPwd==null || curPwd.isEmpty()) {
			errors.put("curPwd",Boolean.TRUE);
		}
		
		if(newPwd==null || newPwd.isEmpty()) {
			errors.put("newPwd",Boolean.TRUE);
		}
		
		if(!errors.isEmpty()){
			return request.getContextPath()+FORM_VIEW;
		}
		
		
		
		//세션정보에서 로그인한 유저의 id를 가져오기		 
		HttpSession session = request.getSession();		
		//하지만 Object 클래스에는 유저의 정보를 가져올수 있는 method가 존재x
		//User type으로  down class casting
		UserDTO_Mj user = (UserDTO_Mj)session.getAttribute("AUTHUSER");
		String mId = user.getId(); //로그인한 user의 id
		//받은파라미터와 유저아이디 를 MemberChangePwdService_Mj 로 보낸다 
		try {
			changePwdSvc.changePwd(mId, curPwd, newPwd);
			//3.Model
			//4.View 비번변경 성공시   mem_ChangePwdSuccess.jsp이동
			return "./mem_ChangePwdSuccess.jsp";
		}catch(InvalidPwdException_Mj e) {
			errors.put("badCurPwd", Boolean.TRUE);
			//비번변경 실패시  mem_ChangePwd_Mj.jsp 이동
			return request.getContextPath()+FORM_VIEW;
		}catch(MemberNotFoundException_Mj e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			//비번변경 실패시  mem_ChangePwd_Mj.jsp 이동
			return null;
		}
		
	}
	
}






