package home.cmm.service;

import java.util.List;

import home.join.service.JoinVO;

public interface CmmService {


	/* 다음 회원 번호 생성 */
	public int nextNo() throws Exception;
	
	/* 시군 목록 조회 */
	public List<SidoVO> getSido() throws Exception;
	
	/* 시군에 해당하는 시군구 조회 */
	public List<SigunguVO> getSigungu(String sidoCd) throws Exception;

}
