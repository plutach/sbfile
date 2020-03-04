package com.cofile.dao;


import org.apache.ibatis.annotations.Param;
import com.cofile.model.MapFileModel;



public interface MapFileMapper {

	void insertMapFile(MapFileModel model);
	void updateMapFile(MapFileModel model);
	
	int getMapFileId(@Param("siteId") int siteId);
	Boolean checkExist(@Param("siteId") int siteId);
	String getfileName(@Param("siteId") int siteId);

}
