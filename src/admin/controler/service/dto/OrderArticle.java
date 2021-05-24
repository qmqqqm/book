package admin.controler.service.dto;

import java.util.List;

//P631
//article 테이블관련 data를 set,get기능을 제공하는 클래스(=>DTO)
//P631
//article 테이블관련 data를 set,get기능을 제공하는 클래스(=>DTO)
public class OrderArticle {
	//field-p631 7라인
	private Integer mem_no; //article_no 컬럼;
	private int book_no;
	private int count;  		//비번	
	private String	mem_id;    	//전화번호
	private List<BookDTO> book; // 책정보
	
	//constructor
	public OrderArticle() {}


	public OrderArticle(Integer mem_no, int book_no, int count, String mem_id) {
		this.mem_no = mem_no;
		this.book_no = book_no;
		this.count = count;
		this.mem_id = mem_id;
	}
	
	public OrderArticle(Integer mem_no, int book_no, int count, String mem_id,List<BookDTO > book) {
		this.mem_no = mem_no;
		this.book_no = book_no;
		this.count = count;
		this.mem_id = mem_id;
		this.book = book;
	}


	public Integer getMem_no() {
		return mem_no;
	}


	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}


	public int getBook_no() {
		return book_no;
	}


	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getMem_id() {
		return mem_id;
	}


	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}


	@Override
	public String toString() {
		return "OrderArticle [mem_no=" + mem_no + ", book_no=" + book_no + ", count=" + count + ", mem_id=" + mem_id
				+ "]";
	}


	
	
}
