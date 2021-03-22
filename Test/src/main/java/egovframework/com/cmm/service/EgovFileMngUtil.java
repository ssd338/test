package egovframework.com.cmm.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import egovframework.let.utl.fcc.service.EgovStringUtil;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
//import java.util.HashMap;

/**
 * @Class Name  : EgovFileMngUtil.java
 * @Description : 메시지 처리 관련 유틸리티
 * @Modification Information
 *
 *     수정일         수정자                   수정내용
 *     -------          --------        ---------------------------
 *   2009.02.13       이삼섭                  최초 생성
 *   2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 02. 13
 * @version 1.0
 * @see
 *
 */
@Component("EgovFileMngUtil")
public class EgovFileMngUtil {

    public static final int BUFF_SIZE = 2048;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name = "egovFileIdGnrService")
    private EgovIdGnrService idgenService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovFileMngUtil.class);

    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     *
     * @param files
     * @return
     * @throws Exception
     */
    public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
	int fileKey = fileKeyParam;

	String storePathString = "";				// 저장위치?
	String atchFileIdString = "";				// 파일 아이디?

	if ("".equals(storePath) || storePath == null) {								// storePath가 비어있다면
	    storePathString = propertyService.getString("Globals.fileStorePath");		// Globals.fileStorePath에 설정되어있는 주소를 가져온다. 
	} else {
	    storePathString = propertyService.getString(storePath);						// storePath에 주소값이 있다면 그 주소를 가져온다. storePathString = 파일의 저장위치
	}

	if ("".equals(atchFileId) || atchFileId == null) {								
	    atchFileIdString = idgenService.getNextStringId();							// 파일 id가 없을경우 String형식의 다음아이디 만들어줌
	} else {
	    atchFileIdString = atchFileId;												// 파일 id 있으면 그거 사용
	}

	File saveFolder = new File(storePathString);									// 저장주소를 매개로 file															

	if (!saveFolder.exists() || saveFolder.isFile()) {								
	    saveFolder.mkdirs();
	}

	Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();		// 파일 갯수만큼 배열을 도는건가?
	MultipartFile file;
	String filePath = "";															// 저장위치?
	List<FileVO> result  = new ArrayList<FileVO>();
	FileVO fvo;

	/* 받아온 맵의 길이만큼 배열을 돌면서 파일을 저장하고 list에 담는다. */
	while (itr.hasNext()) {
	    Entry<String, MultipartFile> entry = itr.next();							// 다음 파일 값을 가져옴

	    file = entry.getValue();													// MultipartFile을 가져옴
	    String orginFileName = file.getOriginalFilename();							// 파일의 원래 이름을 가져옴

	    //--------------------------------------
	    // 원 파일명이 없는 경우 처리
	    // (첨부가 되지 않은 input file type)
	    //--------------------------------------
	    if ("".equals(orginFileName)) {	
	    	System.out.println("엥?");
		continue;
	    }
	    ////------------------------------------

	    int index = orginFileName.lastIndexOf(".");									// 파일이름에서 확장자를 추출
	    //String fileName = orginFileName.substring(0, index);
	    String fileExt = orginFileName.substring(index + 1);			
	    String newName = KeyStr + EgovStringUtil.getTimeStamp() + fileKey;			// 저장용 이름 <= (받아온 문자열 +  시간 + 숫자)
	    long _size = file.getSize();

	    if (!"".equals(orginFileName)) {											// 파일 이름이 있다면
		filePath = storePathString + File.separator + newName;						// 파일 저장위치 (File.separator는 파일구분자로 저장위치를 적을때 'cmm/service'라면 '/' 역할을 해주는것  storePathString는 저장위치, 			
		file.transferTo(new File(filePath));										// transferTo는 파일데이터를 지정한 file로 저장해준다 
	    }
	    //
	    fvo = new FileVO();
	    fvo.setFileExtsn(fileExt);													// 파일 확장자
	    fvo.setFileStreCours(storePathString);										// 파일저장경로
	    fvo.setFileMg(Long.toString(_size));										// 파일 크기
	    fvo.setOrignlFileNm(orginFileName);											// 파일의 원래이름
	    fvo.setStreFileNm(newName);													// 저장파일명
	    fvo.setAtchFileId(atchFileIdString);										// 첨부파일 아이디
	    fvo.setFileSn(String.valueOf(fileKey));										// 파일연번?

	    //writeFile(file, newName, storePathString);
	    result.add(fvo);															// list에 담는다

	    fileKey++;
	}

	return result;
    }

    /**
     * 첨부파일을 서버에 저장한다.
     *
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected void writeUploadedFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
	InputStream stream = null;
	OutputStream bos = null;
	String stordFilePathReal = (stordFilePath==null?"":stordFilePath).replaceAll("..","");
	
	try {
	    stream = file.getInputStream();												// 파일 정보를 읽어 담는다.
	    File cFile = new File(stordFilePathReal);									// 저장위치에 파일을 만든다?								
	    
	    /* 저장전 파일의 저장위치에 디렉토리 유무 여부를 확인하고 없다면 만들어준다.*/
	    if (!cFile.isDirectory()) {													// 폴더가 없다면? 폴더가 아니라면?
		boolean _flag = cFile.mkdir();												// 디렉토리를 생성
			if (!_flag) {																// 디렉토리 생성 실패시 예외 전달
			    throw new IOException("Directory creation Failed ");
			}
	    }
	    
	    bos = new FileOutputStream(stordFilePathReal + File.separator + newName);	// 데이터를 파일에 바이트스트림으로 저장하기 위해 사용. FileOutputStream	(저장위치 / 새이름)

	    int bytesRead = 0;
	    byte[] buffer = new byte[BUFF_SIZE];

	    while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {				// 읽어와서 저장
		bos.write(buffer, 0, bytesRead);
	    }
	} catch (FileNotFoundException fnfe) {
		LOGGER.debug("fnfe: {}", fnfe);
	} catch (IOException ioe) {
		LOGGER.debug("ioe: {}", ioe);
	} catch (Exception e) {
		LOGGER.debug("e: {}", e);
	} finally {
	    if (bos != null) {															// FileOutputStream이 사용됐다면  닫아준다
		try {
		    bos.close();
		} catch (Exception ignore) {
			LOGGER.debug("IGNORED: {}", ignore.getMessage());
		}
	    }
	    if (stream != null) {														// InputStream이 사용됐다면  닫아준다
		try {
		    stream.close();
		} catch (Exception ignore) {
			LOGGER.debug("IGNORED: {}", ignore.getMessage());
		}
	    }
	}
    }

    /**
     * 서버의 파일을 다운로드한다.
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public static void downFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

    String downFileName = EgovStringUtil.isNullToString(request.getAttribute("downFile")).replaceAll("..","");
    String orgFileName = EgovStringUtil.isNullToString(request.getAttribute("orgFileName")).replaceAll("..","");

	/*if ((String)request.getAttribute("downFile") == null) {
	    downFileName = "";
	} else {
	    downFileName = EgovStringUtil.isNullToString(request.getAttribute("downFile"));
	}*/

	/*if ((String)request.getAttribute("orgFileName") == null) {
	    orgFileName = "";
	} else {
	    orgFileName = (String)request.getAttribute("orginFile");
	}*/

	File file = new File(downFileName);

	if (!file.exists()) {
	    throw new FileNotFoundException(downFileName);
	}

	if (!file.isFile()) {
	    throw new FileNotFoundException(downFileName);
	}

	byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.
    String fName = (new String(orgFileName.getBytes(), "UTF-8")).replaceAll("\r\n","");
	response.setContentType("application/x-msdownload");
	response.setHeader("Content-Disposition:", "attachment; filename=" + fName);
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	BufferedInputStream fin = null;
	BufferedOutputStream outs = null;

	try {
		fin = new BufferedInputStream(new FileInputStream(file));
	    outs = new BufferedOutputStream(response.getOutputStream());
	    int read = 0;

		while ((read = fin.read(b)) != -1) {
		    outs.write(b, 0, read);
		}
	} finally {
	    if (outs != null) {
			try {
			    outs.close();
			} catch (Exception ignore) {
				LOGGER.debug("IGNORED: {}", ignore.getMessage());
			}
		    }
		    if (fin != null) {
			try {
			    fin.close();
			} catch (Exception ignore) {
				LOGGER.debug("IGNORED: {}", ignore.getMessage());
			}
		    }
		}
    }

    /**
     * 첨부로 등록된 파일을 서버에 업로드한다.
     *
     * @param file
     * @return
     * @throws Exception

    public static HashMap<String, String> uploadFile(MultipartFile file) throws Exception {

	HashMap<String, String> map = new HashMap<String, String>();
	//Write File 이후 Move File????
	String newName = "";
	String stordFilePath = EgovProperties.getProperty("Globals.fileStorePath");
	String orginFileName = file.getOriginalFilename();

	int index = orginFileName.lastIndexOf(".");
	//String fileName = orginFileName.substring(0, _index);
	String fileExt = orginFileName.substring(index + 1);
	long size = file.getSize();

	//newName 은 Naming Convention에 의해서 생성
	newName = EgovStringUtil.getTimeStamp() + "." + fileExt;
	writeFile(file, newName, stordFilePath);
	//storedFilePath는 지정
	map.put(Globals.ORIGIN_FILE_NM, orginFileName);
	map.put(Globals.UPLOAD_FILE_NM, newName);
	map.put(Globals.FILE_EXT, fileExt);
	map.put(Globals.FILE_PATH, stordFilePath);
	map.put(Globals.FILE_SIZE, String.valueOf(size));

	return map;
    }
*/
    /**
     * 파일을 실제 물리적인 경로에 생성한다.
     *
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
	InputStream stream = null;
	OutputStream bos = null;
	newName = EgovStringUtil.isNullToString(newName).replaceAll("..", "");
	stordFilePath = EgovStringUtil.isNullToString(stordFilePath).replaceAll("..", "");
	try {
	    stream = file.getInputStream();													// 파일 정보를 읽어와서 담는다
	    File cFile = new File(stordFilePath);											// 저장경로로 파일을 만든다

	    if (!cFile.isDirectory())														// 디렉터리가 없다면
		cFile.mkdir();																	// 디텍터리를 만들어준다

	    bos = new FileOutputStream(stordFilePath + File.separator + newName);			// 데이터를 파일에 바이트스트림으로 저장하기 위해 사용 - FileOutputStream	(저장위치 / 새이름)

	    int bytesRead = 0;
	    byte[] buffer = new byte[BUFF_SIZE];

	    while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {					// 데이터를 읽어와서 저장
		bos.write(buffer, 0, bytesRead);
	    }
	} catch (FileNotFoundException fnfe) {
		LOGGER.debug("fnfe: {}", fnfe);
	} catch (IOException ioe) {
		LOGGER.debug("ioe: {}", ioe);
	} catch (Exception e) {
		LOGGER.debug("e: {}", e);
	} finally {
	    if (bos != null) {
		try {
		    bos.close();
		} catch (Exception ignore) {
			LOGGER.debug("IGNORED: {}", ignore.getMessage());
		}
	    }
	    if (stream != null) {
		try {
		    stream.close();
		} catch (Exception ignore) {
			LOGGER.debug("IGNORED: {}", ignore.getMessage());
		}
	    }
	}
    }

    /**
     * 서버 파일에 대하여 다운로드를 처리한다.
     *
     * @param response
     * @param streFileNm
     *            : 파일저장 경로가 포함된 형태
     * @param orignFileNm
     * @throws Exception
     */
    public void downFile(HttpServletResponse response, String streFileNm, String orignFileNm) throws Exception {
    //	String downFileName = EgovStringUtil.isNullToString(request.getAttribute("downFile")).replaceAll("..","");
    //	String orgFileName = EgovStringUtil.isNullToString(request.getAttribute("orgFileName")).replaceAll("..","");
    String downFileName = EgovStringUtil.isNullToString(streFileNm).replaceAll("..","");
	String orgFileName = EgovStringUtil.isNullToString(orignFileNm).replaceAll("..","");

	File file = new File(downFileName);
	//log.debug(this.getClass().getName()+" downFile downFileName "+downFileName);
	//log.debug(this.getClass().getName()+" downFile orgFileName "+orgFileName);

	if (!file.exists()) {
	    throw new FileNotFoundException(downFileName);
	}

	if (!file.isFile()) {
	    throw new FileNotFoundException(downFileName);
	}

	//byte[] b = new byte[BUFF_SIZE]; //buffer size 2K.
	int fSize = (int)file.length();
	if (fSize > 0) {
	    BufferedInputStream in = null;

	    try {
		in = new BufferedInputStream(new FileInputStream(file));

    	    	String mimetype = "text/html"; //"application/x-msdownload"

    	    	response.setBufferSize(fSize);
		response.setContentType(mimetype);
		response.setHeader("Content-Disposition:", "attachment; filename=" + orgFileName);
		response.setContentLength(fSize);
		//response.setHeader("Content-Transfer-Encoding","binary");
		//response.setHeader("Pragma","no-cache");
		//response.setHeader("Expires","0");
		FileCopyUtils.copy(in, response.getOutputStream());
	    } finally {
		if (in != null) {
		    try {
			in.close();
		    } catch (Exception ignore) {
		    	LOGGER.debug("IGNORED: {}", ignore.getMessage());
		    }
		}
	    }
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	}

	/*
	String uploadPath = propertiesService.getString("fileDir");

	File uFile = new File(uploadPath, requestedFile);
	int fSize = (int) uFile.length();

	if (fSize > 0) {
	    BufferedInputStream in = new BufferedInputStream(new FileInputStream(uFile));

	    String mimetype = "text/html";

	    response.setBufferSize(fSize);
	    response.setContentType(mimetype);
	    response.setHeader("Content-Disposition", "attachment; filename=\""
					+ requestedFile + "\"");
	    response.setContentLength(fSize);

	    FileCopyUtils.copy(in, response.getOutputStream());
	    in.close();
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	} else {
	    response.setContentType("text/html");
	    PrintWriter printwriter = response.getWriter();
	    printwriter.println("<html>");
	    printwriter.println("<br><br><br><h2>Could not get file name:<br>" + requestedFile + "</h2>");
	    printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
	    printwriter.println("<br><br><br>&copy; webAccess");
	    printwriter.println("</html>");
	    printwriter.flush();
	    printwriter.close();
	}
	//*/


	/*
	response.setContentType("application/x-msdownload");
	response.setHeader("Content-Disposition:", "attachment; filename=" + new String(orgFileName.getBytes(),"UTF-8" ));
	response.setHeader("Content-Transfer-Encoding","binary");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Expires","0");

	BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
	BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
	int read = 0;

	while ((read = fin.read(b)) != -1) {
	    outs.write(b,0,read);
	}
	log.debug(this.getClass().getName()+" BufferedOutputStream Write Complete!!! ");

	outs.close();
    	fin.close();
	//*/
    }
}
