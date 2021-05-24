package admin.controler.service.dto;

import java.util.Date;

public class MemberDTO {
	//field
		private int 	mem_No;		//회원번호
		private String	mem_Id;  		//회원id	
		private String	mem_Pwd;  		//비번	
		private String	mem_Name;    	//전화번호
		private Date	mem_Birth;    	//전화번호
		private String	mem_Gender;    	//전화번호
		private String	mem_hp;    	//전화번호
		private int	mem_Point;    	//전화번호
		private int	mem_Grade;    	//전화번호
		
		//constructor
		
		
		public MemberDTO(int mem_No, String mem_Id, String mem_Pwd, String mem_Name, Date mem_Birth, String mem_Gender,
				String mem_hp, int mem_Point, int mem_Grade) {
			this.mem_No = mem_No;
			this.mem_Id = mem_Id;
			this.mem_Pwd = mem_Pwd;
			this.mem_Name = mem_Name;
			this.mem_Birth = mem_Birth;
			this.mem_Gender = mem_Gender;
			this.mem_hp = mem_hp;
			this.mem_Point = mem_Point;
			this.mem_Grade = mem_Grade;
		}
		





		//p592 35라인
		//user가 입력한 비번(pwd)과  db의 비번(mPwd)의  일치여부 체크
		public boolean matchPassword(String pwd) {
			//일치하면 true리턴
			return mem_Pwd.equals(pwd);
		}
		

		//p619 20라인
		//새로운 비밀번호를 받아서
		//MemberDTO클래스의 mPwd필드의 값을 변경
		public void changePwd(String newPwd) {
			mem_Pwd = newPwd;
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
			return "MemberDTO [mem_No=" + mem_No + ", mem_Id=" + mem_Id + ", mem_Pwd=" + mem_Pwd + ", mem_Name="
					+ mem_Name + ", mem_Birth=" + mem_Birth + ", mem_Gender=" + mem_Gender + ", mem_hp=" + mem_hp
					+ ", mem_Point=" + mem_Point + ", mem_Grade=" + mem_Grade + "]";
		}


		
	}