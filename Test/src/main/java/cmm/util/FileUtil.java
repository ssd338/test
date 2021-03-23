package cmm.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;

import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.idgnr.impl.Base64;

public class FileUtil {

	/** LOGGER */
	private final static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	/** 파일업로드시 사용 */
	public static final String ORDER_FLAG_INSERT = "C";
	public static final String ORDER_FLAG_RETRIEVE = "R";
	public static final String ORDER_FLAG_UPDATE = "U";
	public static final String ORDER_FLAG_DELETE = "D";
	
	public static String getFileExtension(String fileName) {
		String ext = "";
		if (StringUtils.isNotEmpty(fileName) && fileName.indexOf(".") >= 0) {
			ext = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		}
		return ext;
	}
	@SuppressWarnings("unused")
	public static String getImageFileStream(FileVO fvo) throws IOException, InvocationTargetException, SQLException {
		byte[] result = null;
		
		File file = null;
		FileInputStream fis = null;

		BufferedInputStream in = null;
		ByteArrayOutputStream bStream = null;

		try {
		    file = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
		    fis = new FileInputStream(file);

		    in = new BufferedInputStream(fis);
		    bStream = new ByteArrayOutputStream();

		    Base64.encode(bStream.toByteArray());
		    int imgByte;
		    while ((imgByte = in.read()) != -1) {
		    	bStream.write(imgByte);
		    }

			String type = "";
			if (fvo.getFileExtsn() != null && !"".equals(fvo.getFileExtsn())) {
			    if ("jpg".equals(fvo.getFileExtsn().toLowerCase())) {
				type = "image/jpeg";
			    } else {
			    	type = "image/" + fvo.getFileExtsn().toLowerCase();
			    }
			    type = "image/" + fvo.getFileExtsn().toLowerCase();

			} else {
				LOGGER.debug("getImageFileStream() Image fileType is null... fvo={}", fvo);
			}
			result = bStream.toByteArray();
			
		} catch (FileNotFoundException e) {
			LOGGER.error("fail in getImageFileStream() message="+e+", fvo="+fvo);
			
		} catch (IOException e) {
			LOGGER.error("fail in getImageFileStream() message="+e+", fvo="+fvo);
			
		} catch (Exception e) {
			LOGGER.error("fail in getImageFileStream() message="+e+", fvo="+fvo);
			
		} finally {
			if (bStream != null) {
				try {
					bStream.close();
				} catch (IOException ignore) {
					LOGGER.error("IGNORED: {}", ignore);
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException ignore) {
					LOGGER.error("IGNORED: {}", ignore);
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException ignore) {
					LOGGER.error("IGNORED: {}", ignore);
				}
			}
		}
		String encodedResult = result == null ? "" : Base64.encode(result);
		return encodedResult;
	}
	
	public static int validateFileSize(FileVO fileVO, BigDecimal maxFileSize, String fieldName, Errors errors) throws EgovBizException {
		int errorCnt = 0;
		BigDecimal fileSize = null;
		String fileMg = fileVO.getFileMg(); // 파일크기
		if (StringUtils.isNotEmpty(fileMg)) fileSize = new BigDecimal(fileMg);
		if (fileSize == null) return errorCnt; // 더 이상 진행하지 않음
		
		BigDecimal maxSize = maxFileSize.multiply(new BigDecimal(1024*1024)); // byte 단위
		if (fileSize.compareTo(maxSize) > 0) {
//			throw new EgovBizException("파일 업로드 용량을 초과하였습니다... [허용size:"+maxSize+" byte] fileVO="+fileVO);
			errors.rejectValue(fieldName, "", "파일 업로드 용량을 초과하였습니다. [허용size:"+maxFileSize+" Mb]");
			errorCnt++;
		}
		
		if (errorCnt > 0) { 
			LOGGER.debug("validateFileSize() errorCnt={}", errorCnt+", fileSize="+fileSize+", errors="+errors);
		}
		return errorCnt;
	}

	public static int validateFileExt(FileVO fileVO, List<String> extList, String fieldName, Errors errors) throws EgovBizException {
		int errorCnt = 0;
		String fileExt = fileVO.getFileExtsn();
		if (StringUtils.isEmpty(fileExt)) {
			if (StringUtils.isNotEmpty(fileVO.getOrignlFileNm())) {
				String tmp = FileUtil.getFileExtension(fileVO.getOrignlFileNm());
				if (!StringUtils.isEmpty(tmp)) {
					fileExt = tmp;
				} else {
//					throw new EgovBizException("파일 확장자를 확인할 수 없습니다... fileVO="+fileVO);
					errors.rejectValue(fieldName, "", "파일 확장자를 확인할 수 없습니다. [파일명:"+fileVO.getOrignlFileNm()+"]");
					errorCnt++;
				}
			} else {
//				throw new EgovBizException("파일 확장자를 확인할 수 없습니다... fileVO="+fileVO);
				errors.rejectValue(fieldName, "", "파일 확장자를 확인할 수 없습니다. [파일명:"+fileVO.getOrignlFileNm()+"]");
				errorCnt++;
			}
		}
		
		if (errorCnt == 0) {
			if (StringUtils.isEmpty(fileExt) || !extList.contains(fileExt.toLowerCase())) { // 소문자로 비교
//				LOGGER.debug("validateFileExt() 소문자비교... errorCnt={}", errorCnt+", fileExt="+fileExt+", fieldName="+fieldName+", errors="+errors);
//				throw new EgovBizException("허용되지 않는 파일 확장자 입니다... fileVO="+fileVO);
				errors.rejectValue(fieldName, "", "허용되지 않는 파일 확장자 입니다. [파일명:"+fileVO.getOrignlFileNm()+"]");
				errorCnt++;
			}
		}
		
		if (errorCnt > 0) {
			LOGGER.debug("validateFileExt() errorCnt={}", errorCnt+", fileExt="+fileExt+", errors="+errors);
		}
		return errorCnt;
	}
	public static void makeDir(String dirPath) {
		if (StringUtils.isEmpty(dirPath)) {
			LOGGER.warn("makeDir() dirPath is null!!!");
			return;
		}
		
		File file = new File(dirPath);
		//file.setExecutable(false,true);
		//file.setReadable(true);
		//file.setWritable(false, true);
		
	    if (!file.isDirectory()) {
			boolean flag = file.mkdirs(); 
			if (!flag) {
				LOGGER.error("fail in directory creation... file="+file);
//			    throw new IOException("fail in directory creation...");
			}
	    }
	}
	
	public static boolean isExistFile(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

}
