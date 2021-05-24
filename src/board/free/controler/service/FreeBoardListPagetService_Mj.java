package board.free.controler.service;

import java.util.List;

import board.free.controler.service.dto.FreeBoardDTO_Mj;


//게시물의 data와 페이징(paging)관련 정보를 담는 클래스
public class FreeBoardListPagetService_Mj {
	//필수변수
	private int total;			//전체 게시물수<-DB의  테이블의 전체 record수 
	private int currentPage;	//현재 페이지<-user가 요청한 페이지
	
	private List<FreeBoardDTO_Mj> content;//user가 요청한 페이지에 속한 특정범위의 게시물조회결과 
	
	//계산에 의해 구해지는 변수
	private int totalPages;		//전체 페이지수
	private int startPage;		//시작 페이지
	private int endPage;		//끝   페이지
	
	//construcotr
	public FreeBoardListPagetService_Mj() {}

	/*매개변수의 값
	  total = 0 또는 게시물수가 저장
	  private int size = 10;  //한 페이지에 출력할 게시물수
	  
	  //특정범위의 게시물조회결과 
	   
	  int currentPage;은    user가 요청한 페이지
	 */
	public FreeBoardListPagetService_Mj(int total, int currentPage, int size, List<FreeBoardDTO_Mj> content) {
		this.total = total;			//전체 게시물수	
		this.currentPage = currentPage;	//현재 페이지<-user가 보고싶은 페이지
		this.content = content;		//user가 요청한 페이지에가 속한 범위의 게시물조회결과 
		
		//계산에 의해 구해지는 value를  field의 value로 setting
		//if문을 이용해 total매개변수에는 article테이블에 입력된 게시물이 존재하지않으면 0 으로셋팅
		if(total==0) {
			this.totalPages=0;		//전체 페이지수
			this.startPage=0;		//시작 페이지
			this.endPage=0;		//끝   페이지
		}else {
			//total매개변수에는 데이터베이스에 입력된 게시물이 존재한다면 게시물수
			//totalPages = 전체게시물수/한 페이지에 출력할 게시물수;
			//totalPages = total/10;
			this.totalPages=total/size;	//전체 페이지수
			
			//전체게시물를   한 페이지에 출력할 게시물수로  나눈 나머지가 0보다 큰경우
			if( total%size > 0 ) {
				totalPages++;  //전체페이수 1증가
			}
					
			//시작페이지 구하기 계산
			//user가 보고싶은 페이지(매개변수 currentPage)에 따라 시작번호가 달라진다
			int modVal = currentPage%5;   			
			this.startPage = currentPage/5*5+1;		//시작 페이지
			//if문을 사용해 나머지가 0인경우 처리                        
			if(modVal==0) {
				this.startPage -= 5;  
			}
			
			//끝페이지는 시작페이지에 따라 달라진다
			this.endPage= startPage+4;		//끝   페이지
			
			//끝페이지가 전체페이지수보다 크면 끝페이지를 전체페이지값으로 설정				
			if( endPage>totalPages) { this.endPage= totalPages;}
		}
				  
	}//parameter가 있는 생성자(constructor)
	
	
	//모듈화를 위한 겟메소등 method 설정
	public int getTotal() {
		return total;
	}
	
	public boolean hasNoArticles() {
		//total변수에 담긴 전체게시물수가 0이면 
		// 0==0이라는 것이 참이므로 true를 return한다
		return total==0; 
	}
	
	
	public boolean hasArticles() {
		//total변수에 담긴 전체게시물수가 1이면 
		//게시물수 1>0인 것이 참이므로 true를 return한다
		return total>0;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<FreeBoardDTO_Mj> getContent() {
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





