package admin.service;

public class User_jg {
	private String	Id;  		//회원id	
	
	public User_jg() {}
	public User_jg(String Id) {
		this.Id = Id;
	}
	public String getId() {
		return Id;
	}
	public void setmId(String Id) {
		this.Id = Id;
	}
	@Override
	public String toString() {
		return "User [Id=" + Id + "]";
	}
	
	
	
	
}

