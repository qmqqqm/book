package admin.service;

import java.util.List;

import admin.controler.service.dto.BookDTO;
import admin.controler.service.dto.OrderArticle;

//p648
//게시물의 data와 페이징(paging)관련 정보를 담는 클래스
public class OrderArticlePage_jg {
	
		//field
		//필수변수
		private int total;			//전체 게시물수<-DB의 article 테이블의 전체 record수 
		private int currentPage;	//현재 페이지<-user가 보고싶은 페이지
		
		private List<OrderArticle> content;//user가 보고싶은 페이지에 속한 특정범위의 게시물조회결과 
		//계산에 의해 구해지는 변수
		private int totalPages;		//전체 페이지수
		private int startPage;		//시작 페이지
		private int endPage;		//끝   페이지
		
		//construcotr
		public OrderArticlePage_jg() {}

		/*매개변수의 값은
		  ListArticleService에서
		  total = 0 또는 게시물수가 저장
		  
		  private int size = 3;  //한 페이지에 출력할 게시물수
		  
		  //특정범위의 게시물조회결과 
		  List<Articl> content = articleDao.select(conn, (pageNum-1)*size, size);
		  
		  int currentPage;은    user가 보고싶은 페이지
		 */
		public OrderArticlePage_jg(int total, int currentPage, int size, List<OrderArticle> content) {
			this.total = total;			//전체 게시물수	
			this.currentPage = currentPage;	//현재 페이지<-user가 보고싶은 페이지
			this.content = content;		//user가 보고싶은 페이지에 속한 특정범위의 게시물조회결과 
			
			//계산에 의해 구해지는 value를  field의 value로 setting
			//total매개변수에는 article테이블에 입력된 게시물이 존재하지않으면 0
			if(total==0) {
				this.totalPages=0;		//전체 페이지수
				this.startPage=0;		//시작 페이지
				this.endPage=0;		//끝   페이지
			}else {
				//total매개변수에는 article테이블에 입력된 게시물이 존재한다면 게시물수
				//totalPages = 전체게시물수/한 페이지에 출력할 게시물수;
				//totalPages = total/size;
				//totalPages = total/3;
				this.totalPages=total/size;	//전체 페이지수
				
				//전체게시물를   한 페이지에 출력할 게시물수로  나눈 나머지가 0보다 큰경우
				//-> 나머지가 0이 아닌 경우
				if( total%size > 0 ) {
					totalPages++;  //전체페이수 1증가
				}
				
				//교재 p649 29라인
				//교재에서는  한번에 보여주는 전체 페이지수= 5로 정의
				/*예) [1] [2] [3] [4] [5]  <-user가 보고싶은 페이지가 1,2,3,4,5일때
					  [6] [7] [8] [9] [10]
					  [11][12][13][14][15]<-user가 보고싶은 페이지가 11,12,13,14,15일때
				*/
				
				//시작페이지 구하기 계산
				//user가 보고싶은 페이지(매개변수 currentPage)에 따라 시작번호가 달라진다
				int modVal = currentPage%5;   //11%5은 11을 5로 나눈 나머지가 1
											  //15%5은 15을 5로 나눈 나머지가 0
				
				this.startPage = currentPage/5*5+1;		//시작 페이지
				                       //   11/5*5+1
				                       //   15/5*5+1   3*5+1
				if(modVal==0) {
					this.startPage -= 5;  //this.startPage=startPage-5;
					                      //             11=16-5
				}
				
				//끝페이지 구하는 계산
				//끝페이지는 시작페이지에 따라 달라진다
				this.endPage= startPage+4;		//끝   페이지
				
				//끝페이지가 전체페이지수보다 크면
				//예) 계산결과로  끝페이지15가 되었지만
				//    전체페이지수가 13인경우에는
				//    끝페이지번호를 15가 아닌 13으로 적용한다  의미
				//결과  [11][12][13][14][15] 말고   [11][12][13] 출력하게 된다
				//if( 15>13) { this.endPage= 13;}
				if( endPage>totalPages) { this.endPage= totalPages;}
			}
					  
		}//parameter가 있는 생성자(constructor)
		
		
		//method
		//p649 38라인
		public int getTotal() {
			return total;
		}
		
		public boolean hasNoArticles() {
			//total변수에 담긴 전체게시물수가 0이면 
			// 0==0이라는 것이 참이므로 true를 return한다
			return total==0; 
		}
		
		//p649 46라인
		public boolean hasArticles() {
			//total변수에 담긴 전체게시물수가 1이면 
			//게시물수 1>0인 것이 참이므로 true를 return한다
			return total>0;
		}

		public int getCurrentPage() {
			return currentPage;
		}

		public List<OrderArticle> getContent() {
			return content;
		}

		public int getTotalPages() {
			return totalPages;
		}

		public int getStartPage() {
			return startPage;
		}

		public int getEndPage() {
			return endPage;
		}
		
	}






