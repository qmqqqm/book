package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//CommandHandler  인터페이스를 구현하는 클래스
//CommandHandler  인터페이스에서 선언한 
//process method를 반드시   Override하고 있다
public class NullHandler_Mj implements CommandHandler_Mj {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
	throws Exception {
		//HttpServletResponse.SC_NOT_FOUND는
		//Status code (404) indicating that the requested resource is not available.
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

}



