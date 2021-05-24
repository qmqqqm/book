package admin.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler_Mj;

//p611
//이 클래스는 logout 기능을 구현한다
public class adm_LogoutHandler_jg implements CommandHandler_Mj {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//1.파라미터 값 가져오기
		//2.비즈니스로직수행<->Service<->DAO<->DB
		HttpSession session = request.getSession();
		if(session!=null) {
			session.invalidate(); //session 무효화
		}
		//3.Model
		//4.View - index.jsp로 이동
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		
		return null;
	}

}





