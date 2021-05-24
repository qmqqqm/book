package board.qna.controler.service.dto;

import java.util.Date;

import board.qna.controler.model.QnaBoardListWriter_JI;

//QnA 게시판 관련  정보를 담는 클래스
public class QnaListDTO_JI {

	//field
	private int		q_No;			/* 게시판번호  */
	private String	id;			/* 직원아이디  */
	private String	q_title;		/* 게시판제목  */
	private String	q_content;		/* 게시판내용  */
	private Date	q_regdate;		/* 작성날짜  */
	private Date	q_modifieddate;	/* 수정날짜  */
	private int		q_readcount;	/* 조회수  */
	private int 	q_rnum;			/*게시판 번호*/
	//constructor
	public QnaListDTO_JI() {}
	public QnaListDTO_JI(int q_No, String id, String q_title, String q_content, Date q_regdate, Date q_modifieddate,
			int q_readcount) {
		this.q_No = q_No;
		this.id = id;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_regdate = q_regdate;
		this.q_modifieddate = q_modifieddate;
		this.q_readcount = q_readcount;
	}
	public QnaListDTO_JI(int q_No, String id, String q_title, String q_content, Date q_regdate, Date q_modifieddate,
			int q_readcount, int q_rnum) {
		this.q_No = q_No;
		this.id = id;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_regdate = q_regdate;
		this.q_modifieddate = q_modifieddate;
		this.q_readcount = q_readcount;
		this.q_rnum = q_rnum;
	}

	public int getQ_No() {
		return q_No;
	}

	public void setQ_No(int q_No) {
		this.q_No = q_No;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQ_title() {
		return q_title;
	}

	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}

	public String getQ_content() {
		return q_content;
	}

	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}

	public Date getQ_regdate() {
		return q_regdate;
	}

	public void setQ_regdate(Date q_regdate) {
		this.q_regdate = q_regdate;
	}

	public Date getQ_modifieddate() {
		return q_modifieddate;
	}

	public void setQ_modifieddate(Date q_modifieddate) {
		this.q_modifieddate = q_modifieddate;
	}

	public int getQ_readcount() {
		return q_readcount;
	}

	public void setQ_readcount(int q_readcount) {
		this.q_readcount = q_readcount;
	}

	public int getQ_rnum() {
		return q_rnum;
	}

	public void setQ_rnum(int q_rnum) {
		this.q_rnum = q_rnum;
	}

	@Override
	public String toString() {
		return "QnaListDTO_JI [q_No=" + q_No + ", id=" + id + ", q_title=" + q_title + ", q_content=" + q_content
				+ ", q_regdate=" + q_regdate + ", q_modifieddate=" + q_modifieddate + ", q_readcount=" + q_readcount
				+ ", q_rnum=" + q_rnum + "]";
	}
}