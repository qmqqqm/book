package admin.controler.service.dto;

import java.util.Date;

public class AdminDTO {
	//field
		private int 	adm_No;		//회원번호
		private String	adm_Id;  		//회원id	
		private String	adm_Pwd;  		//비번	
		private String	adm_Hp;    	//전화번호
		
		//constructor
		public AdminDTO() {}
		
		


		public AdminDTO(int adm_No, String adm_Id, String adm_Pwd, String adm_Hp) {
			this.adm_No = adm_No;
			this.adm_Id = adm_Id;
			this.adm_Pwd = adm_Pwd;
			this.adm_Hp = adm_Hp;
		}




		//p592 35라인
		//user가 입력한 비번(pwd)과  db의 비번(mPwd)의  일치여부 체크
		public boolean matchPassword(String pwd) {
			//일치하면 true리턴
			return adm_Pwd.equals(pwd);
		}
		
		//p619 20라인
		//새로운 비밀번호를 받아서
		//MemberDTO클래스의 mPwd필드의 값을 변경
		public void changePwd(String newPwd) {
			adm_Pwd = newPwd;
		}




		public int getAdm_No() {
			return adm_No;
		}




		public void setAdm_No(int adm_No) {
			this.adm_No = adm_No;
		}




		public String getAdm_Id() {
			return adm_Id;
		}




		public void setAdm_Id(String adm_Id) {
			this.adm_Id = adm_Id;
		}




		public String getAdm_Pwd() {
			return adm_Pwd;
		}




		public void setAdm_Pwd(String adm_Pwd) {
			this.adm_Pwd = adm_Pwd;
		}




		public String getAdm_Hp() {
			return adm_Hp;
		}




		public void setAdm_Hp(String adm_Hp) {
			this.adm_Hp = adm_Hp;
		}




		@Override
		public String toString() {
			return "AdminDTO [adm_No=" + adm_No + ", adm_Id=" + adm_Id + ", adm_Pwd=" + adm_Pwd + ", adm_Hp=" + adm_Hp
					+ "]";
		}

		
	}