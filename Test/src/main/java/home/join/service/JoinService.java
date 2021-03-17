package home.join.service;

import java.util.List;


/*  일반회원관리에 관한 인터페이스클래스를 정의한다.  */
public interface JoinService {

	/* 아이디 중복체크 */
	public int checkIdDplct(String checkId) throws Exception;
	
	/* 회원가입 */
	public String insertMber(JoinVO joinVO, String type) throws Exception;
	
}