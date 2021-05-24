package admin.service;

import java.util.Map;

//p666
//수정폼에 출력될 data들을 처리하는 클래스이다
public class StaffModifyRequest_jg {
	
	private String userId; //로그인한 user의 id
	private int articleNumber; //수정폼에 뿌려질 글번호
	private String pwd; //수정폼에 뿌려질 비밀번호
	private String Hp; //수정폼에 뿌려질 핸드폰번호
	
	
	public StaffModifyRequest_jg(String string, int no, String id, String pwd){}

	public StaffModifyRequest_jg(String userId, int articleNumber, String title, String pwd, String Hp) {
		this.userId = userId;
		this.articleNumber = articleNumber;
		this.pwd = pwd;
		this.Hp = Hp;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getHp() {
		return Hp;
	}

	public void setHp(String hp) {
		Hp = hp;
	}

	@Override
	public String toString() {
		return "ModifyRequest [userId=" + userId + ", articleNumber=" + articleNumber + ", pwd=" + pwd + ", Hp=" + Hp
				+ "]";
	}

	//p667 35라인
	//userId내용 유효성 검사
	public void validate(Map<String,Boolean> errors) {
		if(userId == null || userId.trim().isEmpty()) {
			errors.put("userId", Boolean.TRUE);
		}
	}
	
}
