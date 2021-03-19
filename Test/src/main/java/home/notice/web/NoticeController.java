package home.notice.web;

import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.LoginVO;
import egovframework.let.cop.bbs.service.BoardMasterVO;
import egovframework.let.cop.bbs.service.BoardVO;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import home.cmm.service.CmmService;
import home.cmm.web.Constant;
import home.notice.service.NoticeService;
import home.notice.service.NoticeVO;

@Controller
public class NoticeController {
	
	@Resource(name = "cmmService")
	private CmmService cmmService;

	@Resource(name = "noticeService")
	private NoticeService noticeService;
	
	// 게시물 목록 페이지
	@RequestMapping(value="/selectNoticeList.do")
	public String selectBoardArticles(
			@ModelAttribute("searchVO") NoticeVO noticeVO
			, ModelMap model
			, HttpServletRequest request
			) throws Exception {
		// 메인화면에서 넘어온 경우 메뉴 갱신을 위해 추가
		request.getSession().setAttribute("bbsSectionCd", "1");
		
		NoticeVO notice = noticeService.selectBbsTitle(noticeVO);	 // 게시판 정보를 가져온다.
		
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

		int totCnt = noticeService.selectListCnt(noticeVO);

		paginationInfo.setTotalRecordCount(totCnt);
		List<NoticeVO> list = noticeService.selectNoticeList(noticeVO);
		model.addAttribute("resultList",list);					 // 게시물 목록
		model.addAttribute("resultCnt", totCnt);				 // 게시물 갯수
		model.addAttribute("paginationInfo", paginationInfo);	 // 페이징
		model.addAttribute("searchVO", noticeVO);	 
		model.addAttribute("notice", notice);				 // 게시판 정보
		
		if(noticeVO.getBbs_section_cd().equals("1") || noticeVO.getBbs_section_cd().equals("2")) {	//구인,구직 게시판 일 경우
			model.addAttribute("sidoResult", cmmService.getSido()); 		// 지역정보
			model.addAttribute("workType",Constant.WORK_TYPE);				// 근무타입
			model.addAttribute("workJobType",Constant.WORK_JOB_TYPE);     // 근무직종
			model.addAttribute("workTime",Constant.WORK_TIME);     // 근무직종
		}
		String path = notice.getBbs_title();	// 넘어온 게시판 번호에 따라서 주소를 찾아준다.
		path += "/"+path+"List";
		return "home/notice/" + path;
	}
	
	
	// 상세 게시물 페이지
	@RequestMapping("/selectNoticeDetail.do")
	public String selectNoticeDetail(
			@ModelAttribute("searchVO") NoticeVO noticeVO
			, ModelMap model
			, HttpServletRequest request
			) throws Exception {
		
		LoginVO user = new LoginVO();
		if (EgovUserDetailsHelper.isAuthenticated()) {
			user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		}
		
		// 조회수 증가 여부 지정 - 수정시에는 안올린다.
		noticeService.updateReadCnt(noticeVO);
		NoticeVO notice = noticeService.selectBbsTitle(noticeVO);	 // 게시판 정보를 가져온다.
		noticeVO = noticeService.selectDetail(noticeVO);
		
		model.addAttribute("notice", notice);
		model.addAttribute("searchVO", noticeVO);
		model.addAttribute("ntBfList", noticeService.ntBfList(noticeVO));
		String path = notice.getBbs_title();
		path += "/"+ path+"Detail";
		System.out.println("하하 "+ path);
		return "home/notice/" + path;
	}

}
