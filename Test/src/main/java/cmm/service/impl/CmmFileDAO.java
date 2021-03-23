package cmm.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionException;

import cmm.service.FileDetailVO;
import cmm.service.FileMasterVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * CmmFileDAO
 */
@Repository("cmmFileDAO")
public class CmmFileDAO extends EgovComAbstractDAO
{
	public void insertFileMaster(FileMasterVO fileMasterVO) throws TransactionException
	{
        insert("cmmFileDAO.insertFileMasterXXX", fileMasterVO);
	}
	public int getMaxFileSN(FileDetailVO fileDetailVO) throws DataAccessException {
		return (Integer) select("cmmFileDAO.getMaxFileSN", fileDetailVO);
	}
	public void deleteFileDetail(FileDetailVO fileDetailVO) throws TransactionException
	{
        delete("cmmFileDAO.deleteFileDetailXXX", fileDetailVO);
	}
	public void insertFileDetail(FileDetailVO fileDetailVO) throws TransactionException
	{
        insert("cmmFileDAO.insertFileDetailXXX", fileDetailVO);
	}

	@SuppressWarnings("unchecked")
	public List<FileDetailVO> selectFileDetailList(String atchFileId) throws DataAccessException
	{
        return (List<FileDetailVO>)list("cmmFileDAO.selectFileDetailList", atchFileId);
	}

	@SuppressWarnings("deprecation")
	public BigDecimal selectFileMasterCount(String atchFileId) throws DataAccessException
	{
         return (BigDecimal)getSqlMapClientTemplate().queryForObject("cmmFileDAO.selectFileMasterCount", atchFileId);
	}

	@SuppressWarnings("deprecation")
	public BigDecimal selectFileDetailCount(String atchFileId) throws DataAccessException
	{
		return (BigDecimal)getSqlMapClientTemplate().queryForObject("cmmFileDAO.selectFileDetailCount", atchFileId);
	}

	public FileDetailVO selectFileDetail(FileDetailVO fileDetailVOParam) throws DataAccessException{
		return (FileDetailVO) select("cmmFileDAO.selectFileDetail", fileDetailVOParam);
	}

	public FileDetailVO selectFileDetailFirst(String atchFileId) throws DataAccessException{
		return (FileDetailVO)select("cmmFileDAO.selectFileDetailFirst", atchFileId);
	}

	public void alternateFileDetail(FileDetailVO fileDetailVO) throws TransactionException {
        update("cmmFileDAO.alternateFileDetail", fileDetailVO);
	}

	public void deleteFileMaster(String atchFileId) throws TransactionException  {
        delete("cmmFileDAO.deleteFileMasterXXX", atchFileId);
	}
	
	@SuppressWarnings("unchecked")
	public List<FileDetailVO> selectIconFileDownLoad(String atchFileId) throws DataAccessException {
		return (List<FileDetailVO>) list("cmmFileDAO.selectFileDetailList", atchFileId);
	}
}
