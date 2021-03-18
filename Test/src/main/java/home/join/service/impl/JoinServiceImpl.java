package home.join.service.impl;


import egovframework.let.sec.rgm.service.AuthorGroup;
import egovframework.let.sec.rgm.service.EgovAuthorGroupService;
import egovframework.let.utl.sim.service.EgovFileScrty;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import home.cmm.service.CmmService;
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

	@Resource(name = "cmmService")
  	private CmmService cmm;
	
	@Resource(name = "egovAuthorGroupService")
  	private EgovAuthorGroupService egovAuthorGroupService;
  	
	
	/** egovUsrCnfrmIdGnrService */
	@Resource(name="egovUsrCnfrmIdGnrService")
	private EgovIdGnrService idgenService;

	
	/* 아이디 중복체크 */
	@Override
	public int checkIdDplct(String checkId) {
		return joinDAO.checkIdDplct(checkId);
	}

	/* 회원가입 */
	@Override
	public String insertMber(
			JoinVO joinVO
			
			) throws Exception  {
		//고유아이디 셋팅
		String uniqId = idgenService.getNextStringId();
		joinVO.setESNTLID(uniqId);
		
		// 컬럼 명과 테이블 명을 주면 다음 숫자를 주는 기능
		String usrNo =  String.valueOf(cmm.nextNo());
		joinVO.setUsrNo(usrNo);

		//패스워드 암호화
		String pass = EgovFileScrty.encryptPassword(joinVO.getEncUsrPw(), joinVO.getUsrId());
		joinVO.setEncUsrPw(pass);
		
		// db에 회원 등록
		String result = joinDAO.insertMber(joinVO);
		
		//기관회원이라면 시군구 등록
		if(joinVO.getJoinType().equals("center")) {
			joinDAO.insertUsrSigungu(joinVO);
		}
		AuthorGroup authorGroup = new AuthorGroup();
		authorGroup.setUniqId(joinVO.getESNTLID());
		authorGroup.setAuthorCode("ROLE_USER_MEMBER");
		authorGroup.setMberTyCode("GNR");
		
		egovAuthorGroupService.insertAuthorGroup(authorGroup);
		
		
		return result;
	}

	
}