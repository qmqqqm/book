package board.free.controler.model;

//P630
//이 클래스는   작성자에 대한 DATA 관련 기능을 제공하는 클래스
//현재로서는 Article클래스의  멤버필드 private Writer  writer;에서 사용
/*현재로서는 Article 테이블의
  writer_id ,writer_name 컬럼과 연결.. */
public class FreeBoardListWriter_Mj {
	
	private String id;    //writer_id 컬럼
	private String name;  //writer_name 컬럼
	
	public FreeBoardListWriter_Mj() {}

	public FreeBoardListWriter_Mj(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
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

	@Override
	public String toString() {
		return "Writer [id=" + id + ", name=" + name + "]";
	}
	
	
}






