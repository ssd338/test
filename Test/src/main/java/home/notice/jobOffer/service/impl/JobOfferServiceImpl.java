package home.notice.jobOffer.service.impl;


import egovframework.let.sec.rgm.service.EgovAuthorGroupService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import home.cmm.service.CmmService;
import home.notice.jobOffer.service.JobOfferService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



/*  일반회원관리에 관한비지니스클래스를 정의한다. */
@Service("jobOfferService")
public class JobOfferServiceImpl extends EgovAbstractServiceImpl implements JobOfferService {

	/** mberManageDAO */
	@Resource(name="jobOfferDAO")
	private JobOfferDAO jobOfferDAO;

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
		return jobOfferDAO.checkIdDplct(checkId);
	}

}