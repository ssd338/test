package home.notice.jobOffer.service.impl;

import java.util.List;

import egovframework.let.uss.umt.service.MberManageVO;
import egovframework.let.uss.umt.service.UserDefaultVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import home.join.service.JoinVO;
import home.join.service.UsrSigunguVO;

import org.springframework.stereotype.Repository;

/* 일반회원관리에 관한 데이터 접근 클래스를 정의한다. */
@Repository("jobOfferDAO")
public class JobOfferDAO extends EgovAbstractDAO{

	/* 회원가입시 아이디 중복체크 */
   public int checkIdDplct(String checkId){
        return (Integer)select("joinDAO.checkUserId", checkId);
    }

}