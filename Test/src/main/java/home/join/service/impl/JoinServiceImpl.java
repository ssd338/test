package home.join.service.impl;

import java.util.List;

import egovframework.let.uss.umt.service.EgovMberManageService;
import egovframework.let.uss.umt.service.MberManageVO;
import egovframework.let.uss.umt.service.UserDefaultVO;
import egovframework.let.utl.sim.service.EgovFileScrty;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import home.join.service.JoinService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/*  일반회원관리에 관한비지니스클래스를 정의한다. */
@Service("joinService")
public class JoinServiceImpl extends EgovAbstractServiceImpl implements JoinService {

	/** mberManageDAO */
	@Resource(name="joinDAO")
	private JoinDAO joinDAO;

	/** egovUsrCnfrmIdGnrService */
	@Resource(name="egovUsrCnfrmIdGnrService")
	private EgovIdGnrService idgenService;

	
	/* 아이디 중복체크 */
	@Override
	public int checkIdDplct(String checkId) {
		return joinDAO.checkIdDplct(checkId);
	}


}