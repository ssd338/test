package home.cmm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import home.cmm.service.SidoVO;
import home.cmm.service.SigunguVO;

@Repository("cmmDAO")
public class CmmDAO extends EgovAbstractDAO{

	public int nextNo() {
		return (Integer)select("cmmDAO.nextNo");
	}

	public List<SidoVO> getSidoList() {
		return (List<SidoVO>) list("cmmDAO.getSidoList");
	}

	public List<SigunguVO> getSigunguList(String sidoCd) {
		return (List<SigunguVO>) list("cmmDAO.getSigunguList",sidoCd);
	}
	
	
}
