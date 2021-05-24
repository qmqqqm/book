package member.controler.service;

import java.util.Map;

public class MemberJoinRequestService_Mj {

	private String id;
	private String name;
	private String pwd;
	private String confirmPwd;
	private String birth;
	private String Gender;
	private String getHp;
	
	public String getId() {
		return id;
	}
	
	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getHp() {
		return getHp;
	}

	public void setHp(String getHp) {
		this.getHp = getHp;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getConfirmpwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public boolean isPasswordEqualToConfirm() {
		return pwd != null && pwd.equals(confirmPwd);
	}
	//회원가입시 에러처리를 위한 메소드
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, id, "id");
		checkEmpty(errors, name, "name");
		checkEmpty(errors, pwd, "pwd");
		checkEmpty(errors, confirmPwd, "confirmPwd");
		if (!errors.containsKey("confirmPwd")) {
			if (!isPasswordEqualToConfirm()) {
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}
	//필수입력사항 미입력 확인
	private void checkEmpty(Map<String, Boolean> errors, 
			String value, String fieldName) {
		if (value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}

	@Override
	public String toString() {
		return "JoinRequestService_Mj [id=" + id + ", name=" + name + ", pwd=" + pwd + ", confirmPwd=" + confirmPwd
				+ ", birth=" + birth + ", Gender=" + Gender + ", getHp=" + getHp + "]";
	}
	

}
