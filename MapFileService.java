package com.cornerstonestech.service;


import org.springframework.beans.factory.annotation.Autowired;
import com.cornerstonestech.common.ReturnCode;
import com.cornerstonestech.dao.MapFileMapper;
import com.cornerstonestech.model.ReturnMsg;
import com.cornerstonestech.model.MapFileModel;


public class MapFileService {
	
	@Autowired
	MapFileMapper mapFileMapper;


	
	//insert files
	public ReturnMsg storeMapFile(MapFileModel model) {
		
		ReturnMsg returnMsg = new ReturnMsg();

        	try {
        		
        		int siteid = mapFileMapper.getMapFileId(model.getSiteId());
        		if (siteid != 0){
        					
        			mapFileMapper.updateMapFile(model);
        		}
                 	
        		else {
        			mapFileMapper.insertMapFile(model);
        		}   	    	
    		returnMsg.setReturnCode(ReturnCode.OK);
   	    }
    	catch (Exception ex) {
    		returnMsg.setReturnCode(ReturnCode.ERROR);
    	}
    	
        return returnMsg;
	}
	
	
	
	
	  public ReturnMsg MapFileurl(int siteId){
		  
		  ReturnMsg returnMsg = new ReturnMsg();
		  
		  try {
	          
	           Boolean exist = mapFileMapper.checkExist(siteId);
	          if(exist == true) {

	                   String fileName =  mapFileMapper.getfileName(siteId);
	                   String downloadUrl  = "http://127.0.0.1:8080/Map/download/"+fileName;
	                   
//	                   returnMsg = new ReturnMsg(ReturnCode.OK, downloadUrl);
	                   returnMsg.setData(downloadUrl);
	            }
	          else {
	             returnMsg.setReturnCode(ReturnCode.NO_DATA);
	          }
	           
	        }catch(Exception ex) {
	           returnMsg.setReturnCode(ReturnCode.ERROR);
	        }
	        return returnMsg;
	        
		  
	  }



}
