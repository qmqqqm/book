package order.controler.service.dto;

//장바구니 cart table의 data를 담는 클래스
public class BasketDTO {

	//field
	private int 	c_No;		/* 장바구니 번호  */
	private String	id;			/* 회원아이디  */
	private int		p_No;		/* 책(상품)번호  */
	private String 	p_Name;		/* 책(상품)이름  */
	private String	p_Author;	/* 저자  */
	private String 	p_Publicher;/* 출판사  */
	private int 	p_Price;	/* 가격  */
	private int 	amount;		/* 수량  */
	private int 	total;		/* 합계  */
	private String  p_Image;	/* 상품 이미지 */
	
	//constructor
	public BasketDTO() {}
	public BasketDTO(int p_No, int amount) {
		this.p_No = p_No;
		this.amount = amount;
	}
	public BasketDTO(int c_No, String id, int p_No, String p_Name, String p_Author, String p_Publicher, int p_Price,
			int amount, int total, String p_Image) {
		this.c_No = c_No;
		this.id = id;
		this.p_No = p_No;
		this.p_Name = p_Name;
		this.p_Author = p_Author;
		this.p_Publicher = p_Publicher;
		this.p_Price = p_Price;
		this.amount = amount;
		this.total = total;
		this.p_Image = p_Image;
	}
	
	//method
	public int getC_No() {
		return c_No;
	}
	public void setC_No(int c_No) {
		this.c_No = c_No;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getP_No() {
		return p_No;
	}
	public void setP_No(int p_No) {
		this.p_No = p_No;
	}
	public String getP_Name() {
		return p_Name;
	}
	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}
	public String getP_Author() {
		return p_Author;
	}
	public void setP_Author(String p_Author) {
		this.p_Author = p_Author;
	}
	public String getP_Publicher() {
		return p_Publicher;
	}
	public void setP_Publicher(String p_Publicher) {
		this.p_Publicher = p_Publicher;
	}
	public int getP_Price() {
		return p_Price;
	}
	public void setP_Price(int p_Price) {
		this.p_Price = p_Price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getP_Image() {
		return p_Image;
	}
	public void setP_Image(String p_Image) {
		this.p_Image = p_Image;
	}
	@Override
	public String toString() {
		return "BasketDTO [c_No=" + c_No + ", id=" + id + ", p_No=" + p_No + ", p_Name=" + p_Name + ", p_Author="
				+ p_Author + ", p_Publicher=" + p_Publicher + ", p_Price=" + p_Price + ", amount=" + amount + ", total="
				+ total + ", p_Image=" + p_Image + "]";
	}
	
}
