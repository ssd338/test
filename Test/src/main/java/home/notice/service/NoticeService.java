package home.notice.service;

import java.util.List;



public interface NoticeService {

	/* 게시판목록을 가져온다.*/
	public List<NoticeVO> getNoticeList()
			  throws Exception;
	
	/* 선택된 게시판의 게시글 목록을 가져온다 */
	public List<NoticeVO> selectNoticeList(NoticeVO NoticeVO)
			  throws Exception;
	
	/*선택된 게시판의 게시글 목록의 갯수를 가져온다. */
	public int selectListCnt(NoticeVO noticeVO) throws Exception;
	
	/* 게시판의 이름을 가져온다 */
	public String selectBbsTitle(NoticeVO noticeVO) throws Exception;
	
}
