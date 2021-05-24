package member.controler.service.dto;

//장바구니 cart table의 data를 담는 클래스
public class MemberOrderDTO_Mj {

	//field
	private int 	no;		/* 주문 번호  */	
	private int	id;			/* 회원아이디  */	
	private int	book_id;			/* 회원아이디  */	
	
	private String 	title;		/* 책(상품)이름  */
	private String	author;	/* 저자  */
	private String 	publicher;/* 출판사  */
	private int 	price;	/* 가격  */
	private int 	discount;	/* 할인률  */
	private int 	count;		/* 수량  */
	
	
	//constructor
	public MemberOrderDTO_Mj() {}


	public MemberOrderDTO_Mj(int no,int book_id, String title, String author, String publicher, int price, int discount,
			int count) {
		
		this.no = no;
		this.book_id = book_id;		
		this.title = title;
		this.author = author;
		this.publicher = publicher;
		this.price = price;
		this.discount = discount;
		this.count = count;
	}
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getBook_id() {
		return book_id;
	}


	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}



	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPublicher() {
		return publicher;
	}


	public void setPublicher(String publicher) {
		this.publicher = publicher;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public String toString() {
		return "MemberOrderDTO_Mj [No=" + no + ", id=" + id + ", title=" + title + ", author=" + author + ", publicher="
				+ publicher + ", price=" + price + ", discount=" + discount + ", count=" + count + "]";
	}

	

}
