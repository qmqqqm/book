package member.controler;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import member.controler.service.MemberJoinRequestService_Mj;
import member.controler.service.MemberJoinService_Mj;
import member.error.DuplicateIdException_Mj;
import mvc.command.CommandHandler_Mj;


//회원가입처리 컨트롤러
public class MemberJoinControler_Mj implements CommandHandler_Mj {


	private MemberJoinService_Mj joinService = new MemberJoinService_Mj();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		//겍방식요청이면 processForm 메소드 호출
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		//포스트방식 요청시 processSubmit 메서드 호출
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		//기타 방식호출시 에러처리
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	//아무작업 하지말고 요청한 페이지로 돌려보냄
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return request.getContextPath()+"/member/mem_Join_Mj.jsp";
	}
	//회원가입 로직 실행
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		MemberJoinRequestService_Mj joinReq = new MemberJoinRequestService_Mj();
		
		//유져가 입력한 파라미터 받아joinReq 객체에 저장
		joinReq.setId(request.getParameter("id"));
		joinReq.setName(request.getParameter("name"));
		joinReq.setPwd(request.getParameter("pwd"));
		joinReq.setConfirmPwd(request.getParameter("confirmPwd"));
		joinReq.setBirth(request.getParameter("birth"));
		joinReq.setGender(request.getParameter("gender"));
		joinReq.setHp(request.getParameter("hp"));
		
		//에러처리를 위해 해쉬맵 생성
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		//에러처리
		joinReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return request.getContextPath()+"/member/mem_Join_Mj.jsp";
		}
		//서비스 로직 실행을 위해 유저가입력한정보을 저장한 joinReq 객체을 가지고 서비스 클래스 호출
		try {
			joinService.join(joinReq);
			return request.getContextPath()+"/member/mem_JoinSuccess.jsp";
		} catch (DuplicateIdException_Mj e) {
			errors.put("duplicateId", Boolean.TRUE);
			return request.getContextPath()+"/member/mem_Join_Mj.jsp";
		}
	}

}
