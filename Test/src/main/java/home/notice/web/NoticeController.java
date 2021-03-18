package home.notice.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import home.cmm.service.CmmService;
import home.notice.service.NoticeService;
import home.notice.service.NoticeVO;

@Controller
public class NoticeController {
	
	@Resource(name = "cmmService")
	private CmmService cmmService;

	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	@RequestMapping("/selectNoticeList.do")
	public String selectBoardArticles(
			@ModelAttribute("searchVO") NoticeVO noticeVO
			, ModelMap model
			, HttpServletRequest request
			) throws Exception {
		// 메인화면에서 넘어온 경우 메뉴 갱신을 위해 추가
		request.getSession().setAttribute("bbsSectionCd", "1");

		LoginVO user;
		if (EgovUserDetailsHelper.isAuthenticated()) {
			user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		} else {
			user = new LoginVO();
			user.setUniqId("anonymous");
		}
	
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(noticeVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(noticeVO.getPageUnit());
		paginationInfo.setPageSize(noticeVO.getPageSize());

		noticeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		noticeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		noticeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<NoticeVO> list = noticeService.selectNoticeList(noticeVO);
		int totCnt = noticeService.selectListCnt(noticeVO);

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList",list);
		model.addAttribute("resultCnt", totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		String path = "home/notice/";
		path += noticeService.selectBbsTitle(noticeVO) + "/"+noticeService.selectBbsTitle(noticeVO)+"List";
		System.out.println("하하"+path);
		return path;
	}
}
