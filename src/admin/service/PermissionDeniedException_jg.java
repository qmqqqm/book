package admin.service;

//p666
//이 클래스는 권한부족으로 인한 사용자정의 Exception클래스
//여기에서는 글수정시 
//수정하고자하는 user의 id가 글작성자 id와 불일치 했을때
public class PermissionDeniedException_jg extends RuntimeException {

}
