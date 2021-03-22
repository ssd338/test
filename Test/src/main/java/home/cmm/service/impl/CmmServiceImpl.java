package home.cmm.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import home.cmm.service.CmmService;
import home.cmm.service.SidoVO;
import home.cmm.service.SigunguVO;
import home.join.service.JoinVO;

@Service("cmmService")
public class CmmServiceImpl extends EgovAbstractServiceImpl implements CmmService {

	@Resource(name="cmmDAO")
	private CmmDAO cmmDAO;
	
	//다음 회원번호
	@Override
	public int nextNo(
			) throws Exception {	
		return cmmDAO.nextNo();
	}

	@Override
	public List<SidoVO> getSido() throws Exception {
		return cmmDAO.getSidoList();
	}

	@Override
	public List<SigunguVO> getSigungu(String sidoCd) throws Exception {
		return  cmmDAO.getSigunguList(sidoCd);
	}
}
