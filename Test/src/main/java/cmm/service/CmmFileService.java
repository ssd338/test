package cmm.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

public interface CmmFileService {

	public String uploadFile(HttpServletRequest request, FileManagementVO fileManagementVO) throws IOException, EgovBizException;
	public List<FileDetailVO> selectFileDetailList(String fileMstNo) throws IOException, EgovBizException;
	public FileDetailVO selectFileDetail(FileDetailVO fileDetailVO) throws IOException, EgovBizException;
	public FileDetailVO selectFileDetailFirst(String fileMstNo) throws IOException, EgovBizException;
	public boolean deleteAllFileDetailAndMaster(String fileMstNo) throws IOException, EgovBizException;
    public List<FileDetailVO> selectIconFileDownLoad(String atchFileId) throws IOException, EgovBizException;
    public String uploadFileOneAttMulti(HttpServletRequest request, FileManagementVO fileManagementVO, int cnt) throws IOException, EgovBizException;
    String uploadFileOneAttMulti(MultipartFile multipartFile, FileManagementVO fileManagementVO, int fileSn, String fileMstNo, String ctrlStr,  String fileDtlNo, String path) throws IOException, EgovBizException;
}