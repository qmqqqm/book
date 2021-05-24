package admin.controler.service.dto;


//p657
//이 클래스는 Article클래스와 ArticleContent를 field로 가진 클래스이다
public class ArticleData {
	//field
	private Article article;

	//constructor
	public ArticleData() {}

	public ArticleData(Article article) {
		this.article = article;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "ArticleData [article=" + article + "]";
	}
	
}
