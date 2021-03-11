package home.cmm.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import home.cmm.service.CmmService;

@Service("cmmSevice")
public class CmmServiceImpl extends EgovAbstractServiceImpl implements CmmService {

	@Resource(name="cmmDAO")
	private CmmDAO cmmDAO;
	
	@Override
	public int nextNo(HashMap map) throws Exception {
		return cmmDAO.nextNo(map);
	}

}
