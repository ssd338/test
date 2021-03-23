package cmm.service;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class FileMasterVO implements Serializable {

	private String atchFileId;

	private Date creatDt;

	private String useAt;

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public Date getCreatDt() {
		return creatDt;
	}

	public void setCreatDt(Date creatDt) {
		this.creatDt = creatDt;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	
}
