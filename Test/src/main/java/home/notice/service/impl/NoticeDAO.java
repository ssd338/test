package home.notice.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import home.notice.service.NoticeVO;

@Repository("noticeDAO")
public class NoticeDAO extends EgovComAbstractDAO{
	
	public List<NoticeVO> selectNoticeList(NoticeVO noticeVO) throws Exception {
		return (List<NoticeVO>)list("noticeDAO.selectNoticeList", noticeVO);
	}

	public Integer selectListCnt(NoticeVO noticeVO)  throws Exception {
		return (Integer)select("noticeDAO.selectListCnt", noticeVO);
	}
	public String selectBbsTitle(NoticeVO noticeVO)  throws Exception {
		return (String)select("noticeDAO.selectBbsTitle", noticeVO);
	}

	public List<NoticeVO> getNoticeList() {
		return (List<NoticeVO>)list("noticeDAO.getNoticeList");
	}
	
}
