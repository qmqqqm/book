package admin.service;

import java.util.Date;
import java.util.Map;

//p666
//수정폼에 출력될 data들을 처리하는 클래스이다
public class MemberModifyRequest_jg {
	private String userId;
	private int 	mem_No;		//회원번호
	private String	mem_Id;  		//회원id	
	private String	mem_Pwd;  		//비번	
	private String	mem_Name;    	//전화번호
	private String	mem_Gender;    	//전화번호
	private String	mem_hp;    	//전화번호
	private int	mem_Point;    	//전화번호
	private int	mem_Grade;    	//전화번호
	
	
	
	public MemberModifyRequest_jg(String userId,int mem_No, String mem_Id, String mem_Pwd, String mem_Name, String mem_Gender, String mem_hp, int mem_Point, int mem_Grade) {
		this.mem_No = mem_No;
		this.mem_Id = mem_Id;
		this.mem_Pwd = mem_Pwd;
		this.mem_Name = mem_Name;
		this.mem_Gender = mem_Gender;
		this.mem_hp = mem_hp;
		this.mem_Point = mem_Point;
		this.mem_Grade = mem_Grade;
	}



	public int getMem_No() {
		return mem_No;
	}



	public void setMem_No(int mem_No) {
		this.mem_No = mem_No;
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
		return "MemberModifyRequest_jg [mem_No=" + mem_No + ", mem_Id=" + mem_Id + ", mem_Pwd=" + mem_Pwd
				+ ", mem_Name=" + mem_Name  + ", mem_Gender=" + mem_Gender + ", mem_hp="
				+ mem_hp + ", mem_Point=" + mem_Point + ", mem_Grade=" + mem_Grade + "]";
	}



	//p667 35라인
	//userId내용 유효성 검사
	public void validate(Map<String,Boolean> errors) {
		if(userId == null || userId.trim().isEmpty()) {
			errors.put("userId", Boolean.TRUE);
		}
	}
	
	
	
}
