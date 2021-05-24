package board.free.controler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.free.controler.service.FreeBoardListPagetService_Mj;
import board.free.controler.service.FreeBoardListService_Mj;
import mvc.command.CommandHandler_Mj;

//이 클래스는 자유게시판list조회 요청을 담당하는 컨트롤러
public class FreeBoardListControler_Mj implements CommandHandler_Mj {
	//서비스 객체생성	
	private FreeBoardListService_Mj listService = new FreeBoardListService_Mj();
	//컨트롤러 실행메소드
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받기 
		//user가 보고요청하는 페이지를 pageNo 변수에담다 받기
		String pageNoVal = request.getParameter("pageNo");
		
		//요청 페이지가 넘어오지않을 때는 1page로 지정
		int pageNo = 1;
		//요청페이지가 넘어오는경우 페이지 세팅
		if(pageNoVal!=null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		//2.비즈니스로직수행 수행을 위해 boa_freeboardlist_Mj.jsp 서비스 호출 서비스에서 dao호출하게 처리
		//(페이지별로 원하는 특정 범위의)글목록 조회 
		FreeBoardListPagetService_Mj articlePage = listService.getArticlePage(pageNo);
				
		//3.모델 패키지에 지정된 값을 뷰단에서 호출가능하도록 리퀘스트 어트리뷰트에저장
		request.setAttribute("articlePage",articlePage);
		request.setAttribute("pageNo",pageNo);
		
		//4.View 페이지 호출
		return request.getContextPath()+"/board/free/freeList_Mj.jsp";
		
	}

}
