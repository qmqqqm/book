package admin.controler.service.dto;

import java.util.Date;

public class BookDTO {
	//field
		private int 	book_id;		//회원번호
		private String	book_kind;  		//회원id	
		private String	book_title;  		//비번	
		private int	    book_price;    	//전화번호
		private int	    book_count;    	//전화번호
		private String	book_author;    	//전화번호
		private String	book_publishing;    	//전화번호
		private String	book_content;    	//전화번호
		
		public BookDTO(int book_id, String book_kind, String book_title, int book_price, int book_count,
				String book_author, String book_publishing, String book_content) {
			this.book_id = book_id;
			this.book_kind = book_kind;
			this.book_title = book_title;
			this.book_price = book_price;
			this.book_count = book_count;
			this.book_author = book_author;
			this.book_publishing = book_publishing;
			this.book_content = book_content;
		}

		public int getBook_id() {
			return book_id;
		}

		public void setBook_id(int book_id) {
			this.book_id = book_id;
		}

		public String getBook_kind() {
			return book_kind;
		}

		public void setBook_kind(String book_kind) {
			this.book_kind = book_kind;
		}

		public String getBook_title() {
			return book_title;
		}

		public void setBook_title(String book_title) {
			this.book_title = book_title;
		}

		public int getBook_price() {
			return book_price;
		}

		public void setBook_price(int book_price) {
			this.book_price = book_price;
		}

		public int getBook_count() {
			return book_count;
		}

		public void setBook_count(int book_count) {
			this.book_count = book_count;
		}

		public String getBook_author() {
			return book_author;
		}

		public void setBook_author(String book_author) {
			this.book_author = book_author;
		}

		public String getBook_publishing() {
			return book_publishing;
		}

		public void setBook_publishing(String book_publishing) {
			this.book_publishing = book_publishing;
		}

		public String getBook_content() {
			return book_content;
		}

		public void setBook_content(String book_content) {
			this.book_content = book_content;
		}

		@Override
		public String toString() {
			return "BookDTO [book_id=" + book_id + ", book_kind=" + book_kind + ", book_title=" + book_title
					+ ", book_price=" + book_price + ", book_count=" + book_count + ", book_author=" + book_author
					+ ", book_publishing=" + book_publishing + ", book_content=" + book_content + "]";
		}
		
	}