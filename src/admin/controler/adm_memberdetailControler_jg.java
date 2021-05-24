package admin.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.controler.service.dto.MemberArticleData;
import admin.service.MemberReadService_jg;
import mvc.command.CommandHandler_Mj;

//p651
//이 클래스는 글상세조회 요청을 담당하는 컨트롤러
//컨텍스트패스/article/read.do
public class adm_memberdetailControler_jg implements CommandHandler_Mj{
	
	private MemberReadService_jg readService = new MemberReadService_jg();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReadArticleHandler의 process() 진입");
		
		//1.파라미터가져오기 - no = 글번호, pageNo = 사용자가 보고있던 페이지번호
		//컨텍스트패스/read.do?no=글번호&pageNo=페이지번호"
		String pageNo = request.getParameter("pageNo"); //다시 listArticle에 돌려보내줄 Parameter
		String strNo = request.getParameter("no");
		int articleNum = Integer.parseInt(strNo);
		//2.비즈니스로직수행 <-> ReadArticleService <-> ArticleDAO + ArticleContentDAO <-> DB
		//p660 22라인
		//articleNum는 조회수 증가시킬 특정글번호
		//true는 조회수 증가시 true
		//조회수증가 -> 변경된 조회수를 포함한 특정글조회
		//ArticleData의 객체는 Article클래스와 ArticleContent클래스를 field로 가졌음
		MemberArticleData articleData = readService.getArticle(articleNum,true);
		
		//3.Mode - 번호,작성자id,작성자명,제목,내용,작성일,수정일,조회수
		request.setAttribute("articleData", articleData);
		request.setAttribute("pageNo", pageNo); //페이지 번호를 받아왔던 곳으로 다시 보내주는 용도
		
		//4.view
		return "../admin/adm_Memberdetail_jg.jsp";
	}

}
