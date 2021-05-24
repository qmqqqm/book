package admin.controler.service.dto;

import java.util.Date;

//P631
//article 테이블관련 data를 set,get기능을 제공하는 클래스(=>DTO)
//P631
//article 테이블관련 data를 set,get기능을 제공하는 클래스(=>DTO)
public class Article {
	//field-p631 7라인
	private Integer number; //article_no 컬럼;
	private String  id;	//id 컬럼;
	private String  pwd;	//pwd 컬럼;
	private String  hp;	//hp 컬럼;

	
	//constructor
	public Article() {}


	public Article(Integer number, String id, String pwd, String hp) {
		this.number = number;
		this.id = id;
		this.pwd = pwd;
		this.hp = hp;
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
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


	public String getHp() {
		return hp;
	}


	public void setHp(String hp) {
		this.hp = hp;
	}


	@Override
	public String toString() {
		return "Article [number=" + number + ", id=" + id + ", pwd=" + pwd + ", hp=" + hp + "]";
	}
	
}
