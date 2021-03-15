package home.cmm.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("cmmDAO")
public class CmmDAO extends EgovAbstractDAO{

	public int nextNo() {
		return (Integer)select("cmmDAO.nextNo");
	}
	
	
}
