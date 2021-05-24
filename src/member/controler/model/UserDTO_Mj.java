package member.controler.model;

//p604
//로그인에 성공한 user의 id와 이름정보를 다루는 객체
public class UserDTO_Mj {
	
	private String	name;  	//회원명	
	private String	id;  		//회원id	
	private boolean	matchPassword;  		//회원id	
	
	
	
	public UserDTO_Mj() {}
	public UserDTO_Mj(String name, String id,boolean matchPassword ) {
		this.name = name;
		this.id = id;
		this.setMatchPassword(matchPassword);
	}
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User_Mj [name=" + name + ", id=" + id + "]";
	}
	public boolean getMatchPassword() {
		return matchPassword;
	}
	public void setMatchPassword(boolean matchPassword) {
		this.matchPassword = matchPassword;
	}
	
	
	
}
