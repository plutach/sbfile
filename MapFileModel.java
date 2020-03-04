package com.cofile.model;

import java.io.File;
import java.sql.Timestamp;

public class MapFileModel{
	private int siteId;		
	private Timestamp updateDt;
	private String fileName;
    

	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public Timestamp getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(Timestamp updateDt) {
		this.updateDt = updateDt;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void transferTo(File destinationFile) {
		// TODO Auto-generated method stub
		
	}

	
	

}
