package home.notice.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import home.notice.service.NoticeService;
import home.notice.service.NoticeVO;

@Service("noticeService")
public class NoticeServiceimpl extends EgovAbstractServiceImpl implements NoticeService {

	@Resource(name="noticeDAO")
	NoticeDAO noticeDAO;
	
	@Override
	public List<NoticeVO> getNoticeList() throws Exception {
		return (List<NoticeVO>)noticeDAO.getNoticeList();
	}

	/* 선택된 게시판의 게시글을 가져온다. */
	@Override
	public List<NoticeVO> selectNoticeList(NoticeVO noticeVO) throws Exception {
		return (List<NoticeVO>)noticeDAO.selectNoticeList(noticeVO);
	}

	@Override
	public int selectListCnt(NoticeVO noticeVO) throws Exception {
		return (Integer)noticeDAO.selectListCnt(noticeVO);
	}
	
	@Override
	public NoticeVO selectBbsTitle(NoticeVO noticeVO) throws Exception {
		return (NoticeVO)noticeDAO.selectBbsTitle(noticeVO);
	}

	@Override
	public void updateReadCnt(NoticeVO noticeVO) throws Exception {
		noticeDAO.updateReadCnt(noticeVO);
	}

	@Override
	public NoticeVO selectDetail(NoticeVO noticeVO) throws Exception {
		return (NoticeVO)noticeDAO.selectDetail(noticeVO);
	}

	@Override
	public Object ntBfList(NoticeVO noticeVO) throws Exception {
		return (List<NoticeVO>)noticeDAO.ntBfList(noticeVO);
	}

}
