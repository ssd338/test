package cmm.service;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;


@SuppressWarnings("serial")
public class FileDetailVO implements Serializable {

	private String atchFileId;

	private String fileSn;

	private String fileStreCours;
	
	private String streFileNm;

	private String orignlFileNm;
	
	private String fileExtsn;

	private String fileCn;	
	
	private String fileTyp;

	private BigDecimal fileSize;
	


	private byte[] fileData;	
	
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }


	public String getAtchFileId() {
		return atchFileId;
	}


	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}


	public String getFileSn() {
		return fileSn;
	}


	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}


	public String getFileStreCours() {
		return fileStreCours;
	}


	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}


	public String getStreFileNm() {
		return streFileNm;
	}


	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}


	public String getOrignlFileNm() {
		return orignlFileNm;
	}


	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}


	public String getFileExtsn() {
		return fileExtsn;
	}


	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}


	public String getFileCn() {
		return fileCn;
	}


	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}


	public String getFileTyp() {
		return fileTyp;
	}


	public void setFileTyp(String fileTyp) {
		this.fileTyp = fileTyp;
	}


	public BigDecimal getFileSize() {
		return fileSize;
	}


	public void setFileSize(BigDecimal fileSize) {
		this.fileSize = fileSize;
	}

	public byte[] getFileData() {
		byte[] ret = null;
		if(this.fileData != null){
			ret = new byte[fileData.length]; 
			ret = this.fileData;
		}
		return ret;
	}

	public void setFileData(byte[] fileData) { 
		this.fileData = new byte[fileData.length]; 
		for(int i=0; i<fileData.length; ++i) {
			this.fileData[i] = fileData[i]; 
		}
	}

}
