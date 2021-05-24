package order.controler.service.dto;

public class BasketAddDTO {
	
	private int book_id;  
	private String book_title;
	private String author;
	private String publishing_com; 
	private int book_price; 
	private String book_image; 
	
	public BasketAddDTO() {}

	public BasketAddDTO(int book_id, String book_title, String author, String publishing_com, int book_price,
			String book_image) {
		this.book_id = book_id;
		this.book_title = book_title;
		this.author = author;
		this.publishing_com = publishing_com;
		this.book_price = book_price;
		this.book_image = book_image;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishing_com() {
		return publishing_com;
	}

	public void setPublishing_com(String publishing_com) {
		this.publishing_com = publishing_com;
	}

	public int getBook_price() {
		return book_price;
	}

	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}

	public String getBook_image() {
		return book_image;
	}

	public void setBook_image(String book_image) {
		this.book_image = book_image;
	}

	@Override
	public String toString() {
		return "BasketAddDTO [book_id=" + book_id + ", book_title=" + book_title + ", author=" + author
				+ ", publishing_com=" + publishing_com + ", book_price=" + book_price + ", book_image=" + book_image
				+ "]";
	}

}
