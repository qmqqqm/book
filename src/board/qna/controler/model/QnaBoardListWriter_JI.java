package board.qna.controler.model;


//작성자에 대한 data 관련 기능을 제공하는 클래스
public class QnaBoardListWriter_JI {
	private String name;
	private String id;

	public QnaBoardListWriter_JI() {}
	public QnaBoardListWriter_JI(String name, String id) {
		this.name = name;
		this.id = id;
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
		return "QnaBoardListWriter_JI [name=" + name + ", id=" + id + "]";
	}
}
