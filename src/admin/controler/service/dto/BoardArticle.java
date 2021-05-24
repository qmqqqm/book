package admin.controler.service.dto;

import java.util.Date;

//P631
//article 테이블관련 data를 set,get기능을 제공하는 클래스(=>DTO)
//P631
//article 테이블관련 data를 set,get기능을 제공하는 클래스(=>DTO)
public class BoardArticle {
	//field-p631 7라인
	private Integer number; //article_no 컬럼;
	private String  name;	//id 컬럼;
	private String  id;	//id 컬럼;
	private String  title;	//id 컬럼;
	private Date  regDate;	//id 컬럼;
	private Date  modifiedDate;	//id 컬럼;
	private int  ReadCount;	//id 컬럼;
	private String  Content;	//pwd 컬럼;
	private int  isShow;	//hp 컬럼;

	
	//constructor
	public BoardArticle() {}


	public BoardArticle(Integer number, String name, String id, String title, Date regDate, Date modifiedDate,
			int readCount, String content, int isShow) {
		this.number = number;
		this.name = name;
		this.id = id;
		this.title = title;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.ReadCount = readCount;
		this.Content = content;
		this.isShow = isShow;
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


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


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public Date getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public int getReadCount() {
		return ReadCount;
	}


	public void setReadCount(int readCount) {
		ReadCount = readCount;
	}


	public String getContent() {
		return Content;
	}


	public void setContent(String content) {
		Content = content;
	}


	public int getIsShow() {
		return isShow;
	}


	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}


	@Override
	public String toString() {
		return "BoardArticle [number=" + number + ", name=" + name + ", id=" + id + ", title=" + title + ", regDate="
				+ regDate + ", modifiedDate=" + modifiedDate + ", ReadCount=" + ReadCount + ", Content=" + Content
				+ ", isShow=" + isShow + "]";
	}


	
}
