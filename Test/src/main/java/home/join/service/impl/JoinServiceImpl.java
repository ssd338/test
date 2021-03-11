package home.join.service.impl;


import egovframework.let.utl.sim.service.EgovFileScrty;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import home.join.service.JoinService;
import home.join.service.JoinVO;

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

	@Override
	public String insertMber(JoinVO joinVO) throws Exception  {
		//고유아이디 셋팅
		String uniqId = idgenService.getNextStringId();
		joinVO.setESNTLID(uniqId);
		joinVO.setUsrNo("1");
		//패스워드 암호화
		String pass = EgovFileScrty.encryptPassword(joinVO.getEncUsrPw(), joinVO.getUsrId());
		joinVO.setEncUsrPw(pass);

		String result = joinDAO.insertMber(joinVO);
		return result;
	}
}