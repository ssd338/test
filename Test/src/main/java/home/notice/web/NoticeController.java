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
		
		noticeService.updateReadCnt(noticeVO);
		NoticeVO notice = noticeService.selectBbsTitle(noticeVO);	 // 게시판 정보를 가져온다.
		noticeVO = noticeService.selectDetail(noticeVO);
		
		model.addAttribute("notice", notice);
		model.addAttribute("searchVO", noticeVO);
		model.addAttribute("ntBfList", noticeService.ntBfList(noticeVO));
		model.addAttribute("user", user);
		
		String path = notice.getBbs_title();
		path += "/"+ path+"Detail";
		return "home/notice/" + path;
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
		
		String path = notice.getBbs_title();
		path += "/"+ path+"Regist";
		return "home/notice/" + path;
	}
	
	 @RequestMapping("/noticeRegist.do")
	    public String noticeRegist(
	    		final MultipartHttpServletRequest multiRequest				// 첨부파일을 위한...
	            , @ModelAttribute("searchVO") NoticeVO noticeVO
	            , BindingResult bindingResult)
	            throws Exception {
//
//	    	beanValidator.validate(noticeVO, bindingResult);
//
//			if(bindingResult.hasErrors()){
//
//				return "/uss/olh/wor/EgovFaqCnRegist";
//
//			}
		 	NoticeVO notice = noticeService.selectBbsTitle(noticeVO);
		 	String path = notice.getBbs_title();
			path += "/" + path + "List";

	    	// 첨부파일 관련 첨부파일ID 생성
			List<FileVO> _result = null;
			String _atchFileId = "";
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			
			if(!files.isEmpty()){
			 _result = fileUtil.parseFileInf(files, path, 0, "", "");			// 실제 로컬에 저장되는건 이부분인듯
			 _atchFileId = fileMngService.insertFileInfs(_result);  			// 디비에 저장, 파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
			}
			System.out.println("허허");

	    	// 리턴받은 첨부파일ID를 셋팅한다..
			noticeVO.setAtch_file_id(_atchFileId);			// 첨부파일 ID

	    	// 로그인VO에서  사용자 정보 가져오기
	    	LoginVO	loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

	    	String	frstRegisterId = loginVO.getUniqId();
	    	String usrNm = loginVO.getName();
	    	
	    	
	    	noticeVO.setUsr_no(frstRegisterId);					// 최초등록자ID
	    	noticeVO.setEnc_usr_nm(usrNm);						// 최초등록자 이름

	    	noticeService.insertNotice(noticeVO);


	        return "home/notice/" + path;
	    }
	
}
