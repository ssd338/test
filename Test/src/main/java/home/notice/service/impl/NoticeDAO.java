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
	//게시판 정보
	public NoticeVO selectBbsTitle(NoticeVO noticeVO)  throws Exception {
		return (NoticeVO)select("noticeDAO.selectBbsTitle", noticeVO);
	}

	public List<NoticeVO> getNoticeList() {
		return (List<NoticeVO>)list("noticeDAO.getNoticeList");
	}
	//조회수 증가
	public void updateReadCnt(NoticeVO noticeVO) {
		update("noticeDAO.updateReadCnt", noticeVO);
		
	}

	public NoticeVO selectDetail(NoticeVO noticeVO) {
		return (NoticeVO)select("noticeDAO.selectDetail", noticeVO);
	}

	public List<NoticeVO> ntBfList(NoticeVO noticeVO) {
		return (List<NoticeVO>)list("noticeDAO.ntBfList", noticeVO);
	}

	public Integer insertNotice(NoticeVO noticeVO) {
		return (Integer)insert("noticeDAO.insertNotice", noticeVO);
	}

	public Integer nextNoticeNo(NoticeVO noticeVO) {
		return (Integer)select("noticeDAO.nextNoticeNo", noticeVO);
	}

	public Integer updateNotice(NoticeVO noticeVO) {
		return (Integer)update("noticeDAO.updateNotice", noticeVO);
	}

	public Integer deleteNotice(NoticeVO noticeVO) {
		return (Integer)update("noticeDAO.deleteNotice", noticeVO);
	}
	
}
