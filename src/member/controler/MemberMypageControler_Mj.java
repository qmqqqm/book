package member.controler;

import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.controler.model.UserDTO_Mj;
import member.controler.service.MemberReadService_Mj;
import member.controler.service.dto.MemberDTO_Mj;
import mvc.command.CommandHandler_Mj;

public class MemberMypageControler_Mj implements CommandHandler_Mj {
	//이 클래스는 회원정보상세조회 요청을 담당하는 컨트롤러
	private MemberReadService_Mj  readService =
			new MemberReadService_Mj();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ParseException  {
		
					HttpSession session = request.getSession();
					UserDTO_Mj user = (UserDTO_Mj) session.getAttribute("AUTHUSER");
					String id = user.getId();
				//2.비즈니스로직수행						
					MemberDTO_Mj memData = readService.getMember(id);					
					//3.Model				
			     	request.setAttribute("memData",memData);
			     	String birth= memData.getBirth();
			     	birth=birth.substring(0,10);			     		     	 
			     	request.setAttribute("birth",birth);
			     	 
					//4.View
					return request.getContextPath()+"/member/mem_Mypage_Mj.jsp";
					
				
			}





			
		}