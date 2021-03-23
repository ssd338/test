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
	
	/* 글 등록 */
	@Override
	public String insertNotice(NoticeVO noticeVO) throws Exception {
	// 게시글번호 생성
		String bbs_no = String.valueOf(nextNoticeNo(noticeVO));
		noticeVO.setBbs_no(bbs_no);
		noticeDAO.insertNotice(noticeVO);
		return bbs_no;
	}

	/* 게시판의 다음 게시글번호 */
	@Override
	public int nextNoticeNo(NoticeVO noticeVO) throws Exception {
		return (Integer)noticeDAO.nextNoticeNo(noticeVO);
	}

	@Override
	public int updateNotice(NoticeVO noticeVO) throws Exception {
		return (Integer)noticeDAO.updateNotice(noticeVO);
	}

	@Override
	public int deleteNotice(NoticeVO noticeVO) throws Exception {
		return (Integer)noticeDAO.deleteNotice(noticeVO);
	}


	
	
}
