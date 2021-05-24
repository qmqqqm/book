package admin.controler.service.dto;


//p657
//이 클래스는 Article클래스와 ArticleContent를 field로 가진 클래스이다
public class MemberArticleData {
	//field
	private MemberArticle article;

	//constructor
	public MemberArticleData() {}

	public MemberArticleData(MemberArticle article) {
		this.article = article;
	}

	public MemberArticle getArticle() {
		return article;
	}

	public void setArticle(MemberArticle article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "ArticleData [article=" + article + "]";
	}
	
}
