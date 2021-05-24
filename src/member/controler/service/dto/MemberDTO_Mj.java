package member.controler.service.dto;

import java.util.Date;

//P591참고
//이 클래스는 member table의 data를 담을 때 사용하는 클래스이다
public class MemberDTO_Mj {
	
	//field
	private int 	no;		//회원번호	
	private String	img;  		//회원이미지	
	private String	id;  		//회원id	
	private String	pwd;  		//비번	
	private String	name;  	//회원명	
	private String	birth; 	//생년원일
	private String	gender;  //성별
	private String hp;//핸드폰			//생년월일	date
	private int point;  //포인트
	private int grade; //회원상태
	//constructor
	public MemberDTO_Mj() {}
		
	public MemberDTO_Mj(int no, String id, String pwd, String name, String birth, String gender, String hp, 
			int point,int grade) {
		this.no = no;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.hp = hp;
		this.point = point;	
		this.grade=grade;
		//System.out.println("DTO"+id+pwd);
	}
	

	public MemberDTO_Mj(String name, String pwd, String id, String birth, String gender, String hp) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.hp = hp;
	}

	public MemberDTO_Mj(String id, String name, String birth, String gender, String hp) {
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.hp = hp;		
	}



	public MemberDTO_Mj(int no, String img,String id, String pwd, String name, String birth, String gender, String hp, 
			int point,int grade) {
		this.no = no;
		this.img = img;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.hp = hp;
		this.point = point;	
		this.grade=grade;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPwd() {
		return pwd;
	}



	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getBirth() {
		return birth;
	}



	public void setBirth(String birth) {
		this.birth = birth;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getHp() {
		return hp;
	}



	public void setHp(String hp) {
		this.hp = hp;
	}



	public int getPoint() {
		return point;
	}



	public void setPoint(int point) {
		this.point = point;
	}



	public int getGrade() {
		return grade;
	}



	public void setgrade(int grade) {
		this.grade = grade;
	}



	public boolean matchPassword(String pwd) {
		//일치하면 true리턴
		return this.pwd.equals(pwd);
	}
	
	//getter&setter
	
	
	public void changePwd(String newPwd) {
		pwd=newPwd;
	}

	@Override
	public String toString() {
		return "MemberDTO_Mj [no=" + no + ", img=" + img + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", birth="
				+ birth + ", gender=" + gender + ", hp=" + hp + ", point=" + point + ", grade=" + grade + "]";
	}


	
}






