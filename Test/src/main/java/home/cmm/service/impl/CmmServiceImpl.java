package home.cmm.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import home.cmm.service.CmmService;

@Service("cmmService")
public class CmmServiceImpl extends EgovAbstractServiceImpl implements CmmService {

	@Resource(name="cmmDAO")
	private CmmDAO cmmDAO;
	
	@Override
	public int nextNo(String cm, String table) throws Exception {
		HashMap map = new HashMap();
		map.put("cm", cm);
		map.put("tb", table);
		
		return cmmDAO.nextNo(map);
	}

}
