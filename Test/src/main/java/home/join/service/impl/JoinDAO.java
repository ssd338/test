package home.join.service.impl;

import java.util.List;

import egovframework.let.uss.umt.service.MberManageVO;
import egovframework.let.uss.umt.service.UserDefaultVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/* 일반회원관리에 관한 데이터 접근 클래스를 정의한다. */
@Repository("joinDAO")
public class JoinDAO extends EgovAbstractDAO{

	/* 회원가입시 아이디 중복체크 */
   public int checkIdDplct(String checkId){
        return (Integer)select("joinDao.checkUserId", checkId);
    }
	
	

}