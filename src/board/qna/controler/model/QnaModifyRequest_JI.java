package board.qna.controler.model;

import java.util.Map;

//수정폼에 출력될 data들을 처리하는 클래스이다
public class QnaModifyRequest_JI {

	
	//field
	private String userId;		//(로그인한) 관리자 아이디
	private int articleNumber;	//수정폼에 뿌려질 작성글
	private String title;		//제목
	private String content;		//내용
	
	
	//constructor
	public QnaModifyRequest_JI(){}
	public QnaModifyRequest_JI(String userId, int articleNumber, String title, String content) {
		super();
		this.userId = userId;
		this.articleNumber = articleNumber;
		this.title = title;
		this.content = content;
	}
	
	//method
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
		return "QnaModifyRequest_JI [userId=" + userId + ", articleNumber=" + articleNumber + ", title=" + title
				+ ", content=" + content + "]";
	}
	
	//userId내용 유효성 검사
		public void validate(Map<String,Boolean> errors) {
			if(userId == null || userId.trim().isEmpty()) {
				errors.put("userId", Boolean.TRUE);
			}
		}
}
