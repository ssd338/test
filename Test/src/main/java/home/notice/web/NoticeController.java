package home.notice.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import cmm.service.CmmFileService;
import cmm.service.FileManagementVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.let.uss.olh.faq.service.FaqManageDefaultVO;
import egovframework.let.uss.olh.faq.service.FaqManageVO;
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
	
	@Resource(name = "cmmFileService")
  	private CmmFileService cmmFileService;
	// 첨부파일 관련
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	@Autowired
	private DefaultBeanValidator beanValidator;

	
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
		model.addAttribute("paginationInfo", paginationInfo);	 // 페이징
		model.addAttribute("searchVO", noticeVO);	 
		model.addAttribute("notice", notice);				 	 // 게시판 정보
		model.addAttribute("user", user);				 	 	 // 로그인 정보
		
		
		if(noticeVO.getBbs_section_cd().equals("1") || noticeVO.getBbs_section_cd().equals("2")) {	//구인,구직 게시판 일 경우
			model.addAttribute("sidoResult", cmmService.getSido()); 		// 지역정보
			model.addAttribute("workType",Constant.WORK_TYPE);				// 근무타입
			model.addAttribute("workJobType",Constant.WORK_JOB_TYPE);       // 근무직종
			model.addAttribute("workTime",Constant.WORK_TIME);    			// 근무직종
		}
		
		String path = stringPath(noticeVO, "List");
		return path;
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
		}else {
			user = new LoginVO();
			user.setUniqId("anonymous");
		}
		
		noticeService.updateReadCnt(noticeVO);
		NoticeVO notice = noticeService.selectBbsTitle(noticeVO);	 // 게시판 정보를 가져온다.
		noticeVO = noticeService.selectDetail(noticeVO);
		
		model.addAttribute("notice", notice);
		model.addAttribute("searchVO", noticeVO);
		model.addAttribute("ntBfList", noticeService.ntBfList(noticeVO));
		model.addAttribute("user", user);
		model.addAttribute("fileDetailVOList", cmmFileService.selectFileDetailList(noticeVO.getAtch_file_id()));
		
		String path = stringPath(noticeVO, "Detail");
		return path;
	}
	
	// 게시물 등록 뷰
	@RequestMapping("/insertNotice.do")
	public String insertNoticeView(
			@ModelAttribute("searchVO") NoticeVO noticeVO
			, ModelMap model
			, HttpServletRequest request
			) throws Exception {
		LoginVO user = new LoginVO();
		if (EgovUserDetailsHelper.isAuthenticated()) {
			user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		}
		
		NoticeVO notice = noticeService.selectBbsTitle(noticeVO);	 		 // 게시판 정보를 가져온다.
		model.addAttribute("notice", notice);
		model.addAttribute("user", user);
		model.addAttribute("sido_result", cmmService.getSido());			 // 지역
		if(noticeVO.getBbs_section_cd().equals("1") || noticeVO.getBbs_section_cd().equals("2")) {	//구인,구직 게시판 일 경우
			model.addAttribute("workType",Constant.WORK_TYPE);				 // 근무타입
			model.addAttribute("workJobType",Constant.WORK_JOB_TYPE);    	 // 근무직종
			model.addAttribute("workTime",Constant.WORK_TIME);    			 // 근무직종
		}
		
		String path = stringPath(noticeVO, "Regist");
		return path;
	}
	/* 게시글 등록 */
	 @RequestMapping("/noticeRegist.do")
	    public String noticeRegist(
	    		 HttpServletRequest req				// 첨부파일을 위한...
	            , @ModelAttribute("searchVO") NoticeVO noticeVO
	            , BindingResult bindingResult)
	            throws Exception {	 	
	    	// 첨부파일 관련 첨부파일ID 생성
			noticeVO.setAtch_file_id(cmmFileService.uploadFile(req, new FileManagementVO(true)));

	    	// 로그인VO에서  사용자 정보 가져오기
	    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	noticeVO.setUsr_no(loginVO.getUniqId());					// 최초등록자ID
	    	noticeVO.setEnc_usr_nm(loginVO.getName());					// 최초등록자 이름

	    	String bbs_no = noticeService.insertNotice(noticeVO);
	    	if(bbs_no != null) {
	    		return "redirect:/selectNoticeDetail.do?bbs_no=" + bbs_no;
	    
	    	}else {
	    		return stringPath(noticeVO, "List");
	    	}
	 }
	 
	 /* 게시글 수정 */
	 @RequestMapping("/NoticeUpdateView.do")
		public String NoticeUpdateView(
				@ModelAttribute("searchVO") NoticeVO noticeVO
				, ModelMap model
				, HttpServletRequest request
				) throws Exception {
			if (EgovUserDetailsHelper.isAuthenticated()) {
				NoticeVO notice = noticeService.selectBbsTitle(noticeVO);	 // 게시판 정보를 가져온다.
				noticeVO = noticeService.selectDetail(noticeVO);
				
				model.addAttribute("notice", notice);
				model.addAttribute("searchVO", noticeVO);
				model.addAttribute("sido_result", cmmService.getSido());
				model.addAttribute("fileDetailVOList", cmmFileService.selectFileDetailList(noticeVO.getAtch_file_id()));
				if(noticeVO.getBbs_section_cd().equals("1") || noticeVO.getBbs_section_cd().equals("2")) {	//구인,구직 게시판 일 경우
					model.addAttribute("workType",Constant.WORK_TYPE);				 // 근무타입
					model.addAttribute("workJobType",Constant.WORK_JOB_TYPE);    	 // 근무직종
					model.addAttribute("workTime",Constant.WORK_TIME);    			 // 근무직종
				}
				String path = stringPath(noticeVO, "Update");
				return path;
			}else {
				return stringPath(noticeVO, "List");
			}
		}
	 
	 /* 게시글 수정 */
	 @RequestMapping("/noticeUpdate.do")
	    public String noticeUpdate(
	    		 HttpServletRequest req				// 첨부파일을 위한...
	            , @ModelAttribute("searchVO") NoticeVO noticeVO
	            , BindingResult bindingResult)
	            throws Exception {	 	
	    	// 첨부파일 관련 첨부파일ID 생성
			noticeVO.setAtch_file_id(cmmFileService.uploadFile(req, new FileManagementVO(true)));
	    	// 로그인VO에서  사용자 정보 가져오기
	    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    	noticeVO.setUdt_usr_no(loginVO.getUniqId());					// 최초등록자ID
	    	int s = -1;
	    	s = noticeService.updateNotice(noticeVO);
	    	if(s > 0) {
	    		return "redirect:/selectNoticeDetail.do?bbs_no=" + noticeVO.getBbs_no();
	    	}else {
	    		return stringPath(noticeVO, "List");
	    	}
	 }
	 
	 /* 삭제 */
	 @RequestMapping("/noticeDelete.do")
	    public String noticeDelete(
	    		 HttpServletRequest req				// 첨부파일을 위한...
	            , @ModelAttribute("searchVO") NoticeVO noticeVO
	            , BindingResult bindingResult)
	            throws Exception {	 	
			cmmFileService.deleteAllFileDetailAndMaster(noticeVO.getAtch_file_id());	// 첨부파일 삭제 boolean 반환
	    	noticeService.deleteNotice(noticeVO);
	    	
	    	return forwardPath("selectNoticeList");
	 }
	 
	 
	 // 경로지정
	 public String stringPath(NoticeVO noticeVO, String pathType) throws Exception {
		 NoticeVO notice = noticeService.selectBbsTitle(noticeVO);
		 String fPath = "home/notice/";
		 String path = notice.getBbs_title();
		 fPath += path + "/" + path + pathType;
		 return fPath;
	 }
	 
	 // 경로지정 forward
	 public String forwardPath(String path) throws Exception{
		 String fPath ="forward:/" + path + ".do"; 
		 return fPath;
	 }
}
