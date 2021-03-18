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
		// TODO Auto-generated method stub
		return (Integer)noticeDAO.selectListCnt(noticeVO);
	}
	
	@Override
	public String selectBbsTitle(NoticeVO noticeVO) throws Exception {
		// TODO Auto-generated method stub
		return (String)noticeDAO.selectBbsTitle(noticeVO);
	}

}
