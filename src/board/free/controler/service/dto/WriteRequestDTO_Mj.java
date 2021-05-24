package board.free.controler.service.dto;

import java.util.Map;

import board.free.controler.model.FreeBoardListWriter_Mj;
//로그인한 유저가 작성한 제목, 내용을  보관하기위한 기능을 제공하는 클래스
public class WriteRequestDTO_Mj {
	//field
	private FreeBoardListWriter_Mj writer;	//글쓴이 정보(작성자id, 작성자명)
	private String title;	//제목
	private String content;	//내용

	//constructor
	public WriteRequestDTO_Mj(){}
	public WriteRequestDTO_Mj(FreeBoardListWriter_Mj writer, String title, String content) {
		this.writer = writer;
		this.title = title;
		this.content = content;
	}
	
	//method
	public FreeBoardListWriter_Mj getWriter() {
		return writer;
	}
	public void setWriter(FreeBoardListWriter_Mj writer) {
		this.writer = writer;
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
		return "WriteRequest [writer=" + writer + ", title=" + title + ", content=" + content + "]";
	}
	
	
	//title유효성검사
	public void validate(Map<String, Boolean> errors) {
		if( title==null || title.trim().isEmpty() ) {			
			errors.put("title",Boolean.TRUE);
		}
	}
	
	
}





