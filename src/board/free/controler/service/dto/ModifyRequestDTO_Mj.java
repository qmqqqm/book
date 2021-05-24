package board.free.controler.service.dto;

import java.util.Map;
//수정폼에 출력될  data들을 처리하는 클래스
public class ModifyRequestDTO_Mj {
	
	private String userId;	//(로그인한)userid
	private int articleNumber;	//수정폼에 뿌려질 작성글번호
	private String title;		//제목
	private String content;		//내용
	
	public ModifyRequestDTO_Mj() {}
	public ModifyRequestDTO_Mj(String userId, int articleNumber, String title, String content) {
		this.userId = userId;
		this.articleNumber = articleNumber;
		this.title = title;
		this.content = content;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getArticleNumber() {
		return articleNumber;
	}
	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "ModifyRequest [userId=" + userId + ", articleNumber=" + articleNumber + ", title=" + title
				+ ", content=" + content + "]";
	}
		
	//title내용 유효성검사
	public void validate(Map<String,Boolean> errors) {
		if(title==null || title.trim().isEmpty()) {
			errors.put("title",Boolean.TRUE);
		}
	}
	
}





