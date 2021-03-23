package cmm.service;

import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class FileManagementVO implements Serializable {
	private String fdMstNo = "atchFileNo";
	private String fdDtlNo = "atchDtlNo";
	private String fdFile = "atchFile";
	private String fdTitle;
	private String fdCtrl = "atchCtrl";
	private boolean isMulti = false;
	private boolean isSaveToDisk = true;
	private String fileClssCd;
	private String fileAuthCd = AUTH_CD_ALL;
	private int index = 0;
	
	public static final String AUTH_CD_OWNER = "O";
	public static final String AUTH_CD_GROUP = "G";
	public static final String AUTH_CD_ALL = "A";
	
	private ArrayList<String> fdFileArray = null;
	
	public FileManagementVO(){
	}
	
	public FileManagementVO (boolean isMulti){
		this.isMulti = isMulti;
	}
	public String getFdMstNo() {
		return fdMstNo;
	}
	public void setFdMstNo(String fdMstNo) {
		this.fdMstNo = fdMstNo;
	}
	public String getFdDtlNo() {
		return fdDtlNo;
	}
	public void setFdDtlNo(String fdDtlNo) {
		this.fdDtlNo = fdDtlNo;
	}
	public String getFdFile() {
		return fdFile;
	}
	public void setFdFile(String fdFile) {
		this.fdFile = fdFile;
	}
	public String getFdTitle() {
		return fdTitle;
	}
	public void setFdTitle(String fdTitle) {
		this.fdTitle = fdTitle;
	}
	public String getFdCtrl() {
		return fdCtrl;
	}
	public void setFdCtrl(String fdCtrl) {
		this.fdCtrl = fdCtrl;
	}
	public boolean isMulti() {
		return isMulti;
	}
	public void setMulti(boolean isMulti) {
		this.isMulti = isMulti;
	}
	public String getFileClssCd() {
		return fileClssCd;
	}
	public void setFileClssCd(String fileClssCd) {
		this.fileClssCd = fileClssCd;
	}
	public String getFileAuthCd() {
		return fileAuthCd;
	}
	public void setFileAuthCd(String fileAuthCd) {
		this.fileAuthCd = fileAuthCd;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isSaveToDisk() {
		return isSaveToDisk;
	}
	public void setSaveToDisk(boolean isSaveToDisk) {
		this.isSaveToDisk = isSaveToDisk;
	}
	
	public ArrayList<String> getFdFileArray() {
		ArrayList<String> ret = null;
		if(this.fdFileArray != null){
			ret = new ArrayList<String>(); 
			ret = this.fdFileArray;
		}
		return ret;
	}
	
	public void setFdFileArray(ArrayList<String> fdFileArray) { 
		this.fdFileArray = new ArrayList<String> (fdFileArray.size()); 
		for(int i=0; i<fdFileArray.size(); ++i) {
			this.fdFileArray.add(i, fdFileArray.get(i)); 
		}
	}

}
