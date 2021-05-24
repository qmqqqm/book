package admin.controler.service.dto;

import java.util.Date;

//P631
//article 테이블관련 data를 set,get기능을 제공하는 클래스(=>DTO)
//P631
//article 테이블관련 data를 set,get기능을 제공하는 클래스(=>DTO)
public class MemberArticle {
	//field-p631 7라인
	private Integer mem_no; //article_no 컬럼;
	private String	mem_Id;  		//회원id	
	private String	mem_Pwd;  		//비번	
	private String	mem_Name;    	//전화번호
	private Date	mem_Birth;    	//전화번호
	private String	mem_Gender;    	//전화번호
	private String	mem_hp;    	//전화번호
	private int	mem_Point;    	//전화번호
	private int	mem_Grade;    	//전화번호	//hp 컬럼;

	
	//constructor
	public MemberArticle() {}


	public MemberArticle(Integer mem_no, String mem_Id, String mem_Pwd, String mem_Name, Date mem_Birth,
			String mem_Gender, String mem_hp, int mem_Point, int mem_Grade) {
		this.mem_no = mem_no;
		this.mem_Id = mem_Id;
		this.mem_Pwd = mem_Pwd;
		this.mem_Name = mem_Name;
		this.mem_Birth = mem_Birth;
		this.mem_Gender = mem_Gender;
		this.mem_hp = mem_hp;
		this.mem_Point = mem_Point;
		this.mem_Grade = mem_Grade;
	}


	public Integer getMem_no() {
		return mem_no;
	}


	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}


	public String getMem_Id() {
		return mem_Id;
	}


	public void setMem_Id(String mem_Id) {
		this.mem_Id = mem_Id;
	}


	public String getMem_Pwd() {
		return mem_Pwd;
	}


	public void setMem_Pwd(String mem_Pwd) {
		this.mem_Pwd = mem_Pwd;
	}


	public String getMem_Name() {
		return mem_Name;
	}


	public void setMem_Name(String mem_Name) {
		this.mem_Name = mem_Name;
	}


	public Date getMem_Birth() {
		return mem_Birth;
	}


	public void setMem_Birth(Date mem_Birth) {
		this.mem_Birth = mem_Birth;
	}


	public String getMem_Gender() {
		return mem_Gender;
	}


	public void setMem_Gender(String mem_Gender) {
		this.mem_Gender = mem_Gender;
	}


	public String getMem_hp() {
		return mem_hp;
	}


	public void setMem_hp(String mem_hp) {
		this.mem_hp = mem_hp;
	}


	public int getMem_Point() {
		return mem_Point;
	}


	public void setMem_Point(int mem_Point) {
		this.mem_Point = mem_Point;
	}


	public int getMem_Grade() {
		return mem_Grade;
	}


	public void setMem_Grade(int mem_Grade) {
		this.mem_Grade = mem_Grade;
	}


	@Override
	public String toString() {
		return "MemberArticle [mem_no=" + mem_no + ", mem_Id=" + mem_Id + ", mem_Pwd=" + mem_Pwd + ", mem_Name="
				+ mem_Name + ", mem_Birth=" + mem_Birth + ", mem_Gender=" + mem_Gender + ", mem_hp=" + mem_hp
				+ ", mem_Point=" + mem_Point + ", mem_Grade=" + mem_Grade + "]";
	}


	
}
