package cmm.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cmm.service.CmmFileService;
import cmm.service.FileDetailVO;
import cmm.service.FileManagementVO;
import cmm.service.FileMasterVO;
import cmm.util.DateUtil;
import cmm.util.FileUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

@Service("cmmFileService")
public class CmmFileServiceImpl extends EgovAbstractServiceImpl implements CmmFileService {

	@Resource(name = "cmmFileDAO")
	private CmmFileDAO cmmFileDAO;
	
	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService idgenService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CmmFileServiceImpl.class);
	

	@SuppressWarnings("unused")
	public String uploadFile(HttpServletRequest request, FileManagementVO fileManagementVO) throws IOException, EgovBizException {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;	// 파일에 대한 정보가 request에 들어있다.
		
		//final Map<String, MultipartFile> files = multiRequest.getFileMap();
		String fileMstNo = null;
		// 경로지정
		String path = propertiesService.getString("file.upload.path") + DateUtil.getNowString(DateUtil.DATE_DB_FORMAT)+"/";
		File file = new File(path);
		file.setExecutable(false, true);			// setExecutable - 파일의 실행 권한을 소유자 또는 모두에 대해 설정한다. 
		file.setReadable(true);						// setReadable   - 파일 소유자의 읽기 권한을 설정한다.
		file.setWritable(false,true);				// setWritable	 - 파일의 쓰기 권한을 소유자 또는 모두에 대해 설정한다.
		if (!file.exists()) {						// file.exists() - 파일이 경로에 존재 하는지
			file.mkdirs();							// file.mkdirs() - 존재하지 않는 부모 폴더까지 포함하여 해당 경로에 폴더를 만든다. , mkdir()하면 그냥 해당경로에 폴더를 만든다임
		}
		
		List<MultipartFile> fileList = multiRequest.getFiles(fileManagementVO.getFdFile());
		
		int index = fileManagementVO.getIndex();	// 기본값은 0	아마도 파일의 갯수를 나타내는 인덱스 같다. 파일이 하나면 0번지, ... 이런식?
		if (fileList == null) {						// 파일이 없다면
			LOGGER.debug("No file attached.");
			return null;
		}

		LOGGER.debug("CHANG CHECK POINT FILE LIST : " + fileList + ", fdFile : " + fileManagementVO.getFdFile());
		
		if (StringUtils.isNotEmpty(fileManagementVO.getFdMstNo())) {		// atchFileNo 파일 번호가 비어있지 않다면
			// fileManagementVO.getFdMstNo() -> 'atchFileNo' 라는 파라미터 이름으로 전송된 값을 String 배열 fileMstNoArr에 반환
			String[] fileMstNoArr = multiRequest.getParameterValues(fileManagementVO.getFdMstNo());
			if (fileMstNoArr != null) {
				if (StringUtils.isNotEmpty(fileMstNoArr[index])) {
					fileMstNo = fileMstNoArr[index];	// fileMstNo에  atchFileNo가 들어가게됨
				}
			}
		}
		if (fileManagementVO.isMulti() == false) {		// 기본은 false, 아마도 파일이 여러개인지를 뭍는거같음
			String ctrlStr = null;						// 파일의 관리 반향을 체크하는 변수  ex d하면 지우고 이런거
			String title = null;
			if (fileManagementVO.getFdCtrl() != null) {	// fdCtrl -> 파일을 관리? 동작시킬 내용
				ctrlStr = multiRequest.getParameterValues(fileManagementVO.getFdCtrl())[index];
			//	ctrlStr에 multiRequest.getParameterValues("atchCtrl")[0]의 결과가 들어감
			}
			if (fileManagementVO.getFdTitle() != null) {
				title = multiRequest.getParameterValues(fileManagementVO.getFdTitle())[index];
			}
			if (FileUtil.ORDER_FLAG_DELETE.equals(ctrlStr)) { // 삭제할건지 ORDER_FLAG_DELETE의 값은 'D'
				FileDetailVO fileDetailVO = cmmFileDAO.selectFileDetailFirst(fileMstNo);	// fileMstNo는 atchFileId
				if (fileDetailVO != null) {	
					// getFileStreCours는 파일 저장 위치, getStreFileNm는 저장되어있는 파일 이름  -> 파일을 지운다.
					this.deleteFile(fileDetailVO.getFileStreCours(), fileDetailVO.getStreFileNm());
					this.deleteFileDetail(fileDetailVO); // sql문으로 db에 해당 파일을 지운다.
				}
			} else if (FileUtil.ORDER_FLAG_RETRIEVE.equals(ctrlStr)) {	// RETRIEVE 'R' - 검색
				
			} else { // INSERT와 UPDATE
				
				FileDetailVO fileDetailVONew = null;
				// 요청으로 넘어온 파일의 갯수가 1개 이상시 
				if (fileList.size() > 0) {	
					MultipartFile multipartFile = fileList.get(index);
					fileDetailVONew = this.saveFile(multipartFile, fileManagementVO, fileMstNo, path, null); // saveFile에서 fileDetailVo를 반환해줌
				}
				if (fileDetailVONew != null) {	// fileDetailVo가 잘 온 경우(파일이 안간경우 null이 반환되어옴)
					fileMstNo = fileDetailVONew.getAtchFileId();
					FileDetailVO fileDetailVO = cmmFileDAO.selectFileDetailFirst(fileMstNo);	// 해당 fileId를 가진 fileDetaileVo
					if (fileDetailVO != null) {	// db에 해당 file 정보가 있을경우
						// 아마도 같은 이름의 파일이 있을 경우 지우고 다시 쓰기 위해 지우는 건듯?
						this.deleteFile(fileDetailVO.getFileStreCours(), fileDetailVO.getStreFileNm());
						//fileSn은 파일의 순번?
						fileDetailVONew.setFileSn(fileDetailVO.getFileSn());
						// fileDetaile을 db에 update함
						this.alternateFileDetail(fileDetailVONew);
					} else {
						//fileDetailVONew.setAtchFileId(idgenService.getNextStringId());
						int newMaxFileSN = cmmFileDAO.getMaxFileSN(fileDetailVONew);
						fileDetailVONew.setFileSn(Integer.toString(newMaxFileSN - 1));
						// fileDetaile을 db에 insert함
						this.createNewFileDetail(fileDetailVONew);
					}
				}
			}
		} else {		//파일이 여러개인 경우
			for (int i = 0; i < fileList.size(); i++) {
				String ctrlStr = multiRequest.getParameterValues(fileManagementVO.getFdCtrl())[i];
				String title = null;
				String fileDtlNo = null;
				if (fileManagementVO.getFdTitle() != null) {
					title = multiRequest.getParameterValues(fileManagementVO.getFdTitle())[i];
				}
				if (fileManagementVO.getFdDtlNo() != null) {
					fileDtlNo = multiRequest.getParameterValues(fileManagementVO.getFdDtlNo())[i];
				}
				if (FileUtil.ORDER_FLAG_INSERT.equals(ctrlStr)) {
					MultipartFile multipartFile = fileList.get(i);
					FileDetailVO fileDetailVO = this.saveFile(multipartFile, fileManagementVO, fileMstNo, path, title);
					if (fileDetailVO == null) {
						continue;
					}
					fileMstNo = fileDetailVO.getAtchFileId();
					int newMaxFileSN = cmmFileDAO.getMaxFileSN(fileDetailVO);
					fileDetailVO.setFileSn(Integer.toString(newMaxFileSN));
					this.createNewFileDetail(fileDetailVO);
				} else if (FileUtil.ORDER_FLAG_UPDATE.equals(ctrlStr)) {
					MultipartFile multipartFile = fileList.get(i);
					FileDetailVO fileDetailVONew = this.saveFile(multipartFile, fileManagementVO, fileMstNo, path, title);
					FileDetailVO fileDetailVO = null;
					if (fileDetailVONew == null) {
						
					} else {
						fileMstNo = fileDetailVONew.getAtchFileId();
						fileDetailVONew.setFileSn(fileDtlNo);
						fileDetailVO = cmmFileDAO.selectFileDetail(fileDetailVONew);
						if (fileDetailVO != null) {
							this.deleteFile(fileDetailVO.getFileStreCours(), fileDetailVO.getStreFileNm());
							fileDetailVONew.setAtchFileId(fileDetailVO.getAtchFileId());
							this.alternateFileDetail(fileDetailVONew);
						} else {
							int newMaxFileSN = cmmFileDAO.getMaxFileSN(fileDetailVONew);
							fileDetailVONew.setFileSn(Integer.toString(newMaxFileSN - 1));
							this.createNewFileDetail(fileDetailVONew);
						}
					}
				} else if (FileUtil.ORDER_FLAG_DELETE.equals(ctrlStr)) {
					FileDetailVO fileDetailVO = new FileDetailVO();
					fileDetailVO.setAtchFileId(fileMstNo);
					fileDetailVO.setFileSn(fileDtlNo);
					fileDetailVO = cmmFileDAO.selectFileDetail(fileDetailVO);
					if(fileManagementVO.isSaveToDisk()) {
						this.deleteFile(fileDetailVO.getFileStreCours(), fileDetailVO.getStreFileNm());
					}
					this.deleteFileDetail(fileDetailVO);
				}
			}
		}
		return fileMstNo;
	}
	public String uploadFileOneAttMulti(HttpServletRequest request, FileManagementVO fileManagementVO, int cnt) throws IOException, EgovBizException {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		
		String fileMstNo = null;
		String fileReturnMstNo = null;
		String path = propertiesService.getString("file.upload.path") + DateUtil.getNowString(DateUtil.DATE_DB_FORMAT)+"/";
		File file = new File(path);
		file.setExecutable(false, true);
		file.setReadable(true);
		file.setWritable(false,true);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		for(int i = 1; i <= cnt; i++){
			MultipartFile multipartFile = multiRequest.getFile("atchFile0"+i);

			String ctrlStr = multiRequest.getParameter("atchCtrl0"+i);
			String fileDtlNo = null;
			fileDtlNo = multiRequest.getParameter("atchDtlNo0"+i);
			if(multipartFile != null){
				if((fileManagementVO.getFdMstNo() != null) && (!"".equals(fileManagementVO.getFdMstNo()))){
					fileMstNo = fileManagementVO.getFdMstNo();
				}
				fileMstNo = uploadFileOneAttMulti(multipartFile, fileManagementVO, i, fileMstNo, ctrlStr, fileDtlNo, path);
				
				if(!"".equals(fileMstNo)){
					fileReturnMstNo = fileMstNo;
				}
				
			}
		}
		
		return fileReturnMstNo;
	}
	
	public String uploadFileOneAttMulti(MultipartFile multipartFile, FileManagementVO fileManagementVO, int fileSn, String fileMstNo, String ctrlStr,  String fileDtlNo, String path) throws IOException, EgovBizException {
		
		String fileReturnMstNo = fileMstNo;
		if (FileUtil.ORDER_FLAG_DELETE.equals(ctrlStr)) {
			FileDetailVO fileDetailVONew = new FileDetailVO();
			fileDetailVONew.setFileSn(Integer.toString(fileSn));
			fileDetailVONew.setAtchFileId(fileReturnMstNo);
			FileDetailVO fileDetailVO = cmmFileDAO.selectFileDetail(fileDetailVONew);
			if (fileDetailVO != null) {
				this.deleteFile(fileDetailVO.getFileStreCours(), fileDetailVO.getStreFileNm());
				this.deleteFileDetail(fileDetailVO);
			}
		} else if (FileUtil.ORDER_FLAG_RETRIEVE.equals(ctrlStr)) {
		} else {
			FileDetailVO fileDetailVONew = null;
			fileDetailVONew = this.saveFile(multipartFile, fileManagementVO, fileReturnMstNo, path, null);
			if (fileDetailVONew != null) {
				fileDetailVONew.setFileSn(Integer.toString(fileSn));
				FileDetailVO fileDetailVO = cmmFileDAO.selectFileDetail(fileDetailVONew);
				if (fileDetailVO != null) {
					this.deleteFile(fileDetailVO.getFileStreCours(), fileDetailVO.getStreFileNm());
					fileDetailVONew.setFileSn(Integer.toString(fileSn));
					this.alternateFileDetail(fileDetailVONew);
				} else {
					fileReturnMstNo = fileDetailVONew.getAtchFileId();
					fileDetailVONew.setFileSn(Integer.toString(fileSn));
					this.createNewFileDetail(fileDetailVONew);
				}
			}
		}
		return fileReturnMstNo;
	}
	private boolean deleteFile(String filePath, String fileNm) {
		File file;
		try {
			file = new File(filePath, fileNm);
			if (file.exists()) {
				file.delete();
			}
		} catch(NullPointerException ex) {
			LOGGER.error("Error in deleteFile()" , ex);
			return false;
		} catch(SecurityException ex) {
			LOGGER.error("Error in deleteFile()" , ex);
			return false;
		}
		
		return true;
	}
	
	//파일을 실제 저장소에 저장하고 fileMaster를 디비에  저장한다.
	private FileDetailVO saveFile(
			MultipartFile multipartFile, FileManagementVO fileManagementVO,
			String fileMstNo, String path, String title
			) throws IOException, EgovBizException {
		
		FileMasterVO fileMasterVO = null;
		String saveFilename = "";
		@SuppressWarnings("unused")
		File file = null;
		String filename = multipartFile.getOriginalFilename();
		String filepath = "";
		
		if (StringUtils.isEmpty(filename)) {
			return null;
		}
		
		String fileExt = filename.substring(filename.lastIndexOf(".") + 1);	//파일의 확장자를 구한다.
		
	/*	 [ 실제 저장소에 저장 ] 	*/
		if (fileManagementVO.isSaveToDisk()) {	
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String today = formatter.format(new java.util.Date());
			// 저장될 파일 이름 => 오늘의 날짜와 랜덤으로 생성된 글자 하고 .확장자
			saveFilename = today + UUID.randomUUID().toString() +"."+ fileExt;
			filepath = path;	//저장장소
			LOGGER.debug("["+ fileManagementVO.getFdFile() +"]: "+ multipartFile.getName()
					+", "+ filename
					+", "+ saveFilename
				);
			// 저장
			multipartFile.transferTo(new File(path, saveFilename));
		}
		
		/*	 [ fileMaster를 디비에  저장 ] 	*/
		if (fileMstNo == null) {	//파일 아이디가 없다면 - 새로 파일을 올리는 경우 
			// createNewFileMaster에서 파일아이디 만들고 LETTNFILE에 등록 후 fileMasterVO를 반환
			fileMasterVO = this.createNewFileMaster(fileManagementVO);
			fileMstNo = fileMasterVO.getAtchFileId();
		} else {	//파일 아이디가 있다면 - 아마도 수정의 경우 이미 파일아이디가 존재
			// selectFileMasterCount - 파일 아이디가 동일하고 사용중인 파일이 몇개인지 세어준다.
			BigDecimal cnt = cmmFileDAO.selectFileMasterCount(fileMstNo);
			if (cnt.equals(BigDecimal.valueOf(0))) {	//BigDecimal.valueOf(0) 0을 BigDecimal형으로 변환 => 사용중인 파일이 없다면
				fileMasterVO = this.createNewFileMaster(fileManagementVO);
				fileMstNo = fileMasterVO.getAtchFileId();
			}
		}
		
		FileDetailVO fileDetailVO = new FileDetailVO();
		fileDetailVO.setAtchFileId(fileMstNo);    // 파일 아이디
		fileDetailVO.setOrignlFileNm(filename);	  // 원래 파일이름
		fileDetailVO.setFileStreCours(filepath);  // 저장 주소
		fileDetailVO.setStreFileNm(saveFilename); // 저장된 파일이름
		fileDetailVO.setFileExtsn(fileExt);		  // 확장자
		fileDetailVO.setFileSize(BigDecimal.valueOf(multipartFile.getSize()));	//파일 용량

		if (fileManagementVO.isSaveToDisk() == false) {	// 저장하지 않는다
			fileDetailVO.setFileData(multipartFile.getBytes());	//파일 데이터만 가져와서 db에 담음
		}
		
		return fileDetailVO;
	}
	
	public boolean deleteAllFileDetailAndMaster(String fileMstNo) throws IOException, EgovBizException {
		
		if (fileMstNo == null) {
			return false;
		}
		List<FileDetailVO> fileDetailVOList = cmmFileDAO.selectFileDetailList(fileMstNo);
		for (FileDetailVO fileDetailVO : fileDetailVOList) {
			this.deleteFile(fileDetailVO.getFileStreCours(), fileDetailVO.getStreFileNm());
			this.deleteFileDetail(fileDetailVO);
		}
		this.deleteFileMaster(fileMstNo);
		
		return true;
	}
	
	private void deleteFileMaster(String fileMstNo) throws IOException, EgovBizException {
		cmmFileDAO.deleteFileMaster(fileMstNo);
	}
	
	private void deleteFileDetail(FileDetailVO fileDetailVO) throws IOException, EgovBizException {
		cmmFileDAO.deleteFileDetail(fileDetailVO);
	}
	
	private void alternateFileDetail(FileDetailVO fileDetailVO) throws IOException, EgovBizException {
		cmmFileDAO.alternateFileDetail(fileDetailVO);
	}
	
	private void createNewFileDetail(FileDetailVO fileDetailVO) throws IOException, EgovBizException {
		cmmFileDAO.insertFileDetail(fileDetailVO);
	}

	private FileMasterVO createNewFileMaster(FileManagementVO fileManagementVO) throws IOException, EgovBizException {
		FileMasterVO fileMasterVO = new FileMasterVO();
		try {
			fileMasterVO.setAtchFileId(idgenService.getNextStringId());
		} catch (FdlException e) {
			LOGGER.error("fail in createNewFileMaster() getNextStringId()", e);
		}
		cmmFileDAO.insertFileMaster(fileMasterVO);
		return fileMasterVO;
	}
	
	public List<FileDetailVO> selectFileDetailList(String atchFileId) throws IOException, EgovBizException {
		if (atchFileId == null) {
			return null;
		}
		return cmmFileDAO.selectFileDetailList(atchFileId);
	}
	public FileDetailVO selectFileDetail(FileDetailVO fileDetailVOParam) throws IOException, EgovBizException {
		return cmmFileDAO.selectFileDetail(fileDetailVOParam);
	}
	public BigDecimal selectFileDetailCount(String fileMstNo) throws IOException, EgovBizException {
		return cmmFileDAO.selectFileDetailCount(fileMstNo);
	}
	public FileDetailVO selectFileDetailFirst(String atchFileId) throws IOException, EgovBizException {
		if (atchFileId == null) {
			return null;
		}
		return cmmFileDAO.selectFileDetailFirst(atchFileId);
	}
	public List<FileDetailVO> selectIconFileDownLoad(String atchFileId) throws IOException, EgovBizException {
		return cmmFileDAO.selectIconFileDownLoad(atchFileId);
	}
}
