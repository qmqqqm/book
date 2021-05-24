package board.free.controler.service.dto;

import java.util.Date;

import board.free.controler.model.FreeBoardListWriter_Mj;

//자유게시판  테이블관련 data를 set,get기능을 제공
public class FreeBoardDTO_Mj {
	//field-p631 7라인
	private Integer number; 
	private FreeBoardListWriter_Mj  writer; 							
	private String  title;	
	private Date    regdate;
	private Date 	modifiedDate;
	private int 	readCount;   
	private String  content;


	//constructor
	public FreeBoardDTO_Mj() {
	}
	public FreeBoardDTO_Mj(int number, FreeBoardListWriter_Mj writer, String title, Date regdate, Date modifiedDate, int readCount, String content) {
		System.out.println("dto컨스트럭터 진입");
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.regdate = regdate;
		this.modifiedDate = modifiedDate;
		this.readCount = readCount;
		this.content = content;
		
	}
	public FreeBoardDTO_Mj(Integer number, FreeBoardListWriter_Mj writer, String title, Date regdate, Date modifiedDate, int readCount,
			String content) {
		
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.regdate = regdate;
		this.modifiedDate = modifiedDate;
		this.readCount = readCount;
		this.content = content;
	}
	//겟 셋 메소드
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

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

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	@Override
	public String toString() {
		return "FreeBoardListDTO_Mj [number=" + number + ", writer=" + writer + ", title=" + title + ", regdate="
				+ regdate + ", modifiedDate=" + modifiedDate + ", readCount=" + readCount + ", content=" + content
				+ "]";
	}


	
	
}
