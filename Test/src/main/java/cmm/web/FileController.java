package cmm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cmm.service.CmmFileService;
import cmm.service.FileDetailVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;

/**  
 * @Class Name : CmmController.java
 * @author ecplaza
 * @since 2018-04-17
 * @version 1.0
 */
@Controller
public class FileController {
	/** LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	@Resource(name = "cmmFileService")
	private CmmFileService cmmFileService;
	

    /** EgovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService cmmUseService;
	

	
    @RequestMapping(value="/cmm/downloadFile.do")
	public void cvplFileDownload(HttpServletRequest request, HttpServletResponse response) throws IOException, EgovBizException {
		String atchFileId = request.getParameter("atchFileId");
		String fileSn = request.getParameter("fileSn");

		FileDetailVO paramVO = new FileDetailVO();
		paramVO.setAtchFileId(atchFileId);
		paramVO.setFileSn(fileSn);
		FileDetailVO fileDetailVO = cmmFileService.selectFileDetail(paramVO);

		File uFile = new File(fileDetailVO.getFileStreCours(), fileDetailVO.getStreFileNm());
		long fSize = uFile.length();

		if (fSize > 0) {
			String mimetype = "application/x-msdownload";

			response.setContentType(mimetype);
			setDisposition(fileDetailVO.getOrignlFileNm(), request, response);
			//response.setContentLength(fSize);

			BufferedInputStream in = null;
			FileInputStream fis = null;
			BufferedOutputStream out = null;

			try {
				
				fis = new FileInputStream(uFile);
				in = new BufferedInputStream(fis);
				out = new BufferedOutputStream(response.getOutputStream());

				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ioe) {
				LOGGER.error("Error in cvplFileDownload()", ioe);
			} catch (NullPointerException npe) {
				LOGGER.error("Error in cvplFileDownload()", npe);
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException ioe) {
						LOGGER.error("Error in cvplFileDownload()", ioe);
					}
				}
				if (in != null) {
					try {
						in.close();
					} catch (IOException ioe) {
						LOGGER.error("Error in cvplFileDownload()", ioe);
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException ioe) {
						LOGGER.error("Error in cvplFileDownload()", ioe);
					}
				}
			}

		} else {
			//MessageAlertUtil.alertBack("파일이 존재하지 않습니다." , response);
		}
	}
    private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}
 /****/
	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
    /**
     * response Cache
     * @param response
     */
    @SuppressWarnings("unused")
	private void setResponseCacheControl(HttpServletResponse response){
    	response.setContentType("text/html; charset=utf-8");
		response.setDateHeader("Expires", -1);
		response.setHeader("Pragma", "no-cahce");
		response.setHeader("Cache-Control", "no-store"); // HTTP 1.0
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
    }
    
    
    
}
