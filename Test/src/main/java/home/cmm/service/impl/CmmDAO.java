package home.cmm.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("cmmDAO")
public class CmmDAO extends EgovAbstractDAO{

	public int nextNo(HashMap map) {
		return (Integer)select("cmmDAO.nextNo", map);
	}
	
	
}
