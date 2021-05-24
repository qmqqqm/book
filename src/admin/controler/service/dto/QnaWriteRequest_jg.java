package admin.controler.service.dto;

import java.util.Map;


//p617
//글쓰기 요청처리와 관련하여 
//로그인한 유저가 작성한 제목, 내용을 보관하기위한 기능을 제공하는 클래스
public class QnaWriteRequest_jg {
	
	//field
	private String id;	//글쓴이 정보(작성자 id,작성자명)
	private String title;	//제목
    private String content;	//내용

    
    //constructor
    public QnaWriteRequest_jg() {}
	
	public QnaWriteRequest_jg(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}

	//method
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	//title 유효성 검사
	public void validate(Map<String,Boolean> errors) {
		if(title==null || title.trim().isEmpty()) {
			//put(Object key,Object value); Map에 value 추가
			//여기에서는 매개변수의 errors에서 
			//key는 String으로, value는 Boolean으로 지정함
			errors.put("title",Boolean.TRUE);
		}
	}
}
