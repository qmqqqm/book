package admin.controler.service.dto;


//p657
//이 클래스는 Article클래스와 ArticleContent를 field로 가진 클래스이다
public class BoardArticleData {
	//field
	private BoardArticle article;

	//constructor
	public BoardArticleData() {}

	public BoardArticleData(BoardArticle article) {
		this.article = article;
	}

	public BoardArticle getArticle() {
		return article;
	}

	public void setArticle(BoardArticle article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "ArticleData [article=" + article + "]";
	}
	
}
