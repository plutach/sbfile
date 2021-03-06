package com.cofile.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.cofile.common.ReturnCode;
import com.cofile.model.MapFileModel;
import com.cofile.model.ReturnMsg;
import com.cofile.service.ChannelService;
import com.cofile.service.CodeService;
import com.cofile.service.ConfigServerService;
import com.cofile.service.ConfigService;
import com.cofile.service.EventLevelService;
import com.cofile.service.MapFileService;

@RestController
@RequestMapping("Map")
public class MapController {
    
    @Autowired
    ChannelService channelService;
 
    @Autowired
    CodeService codeService;
    
    @Autowired
    ConfigService configService;
    
    @Autowired
    ConfigServerService configServerService;
    
    @Autowired
    EventLevelService eventLevelService;
    
    
    @Autowired
    MapFileService mapFileService;
   
	
	/* file upload */

	@Value("${upload.dir}")
	private String udir;
	
	 @RequestMapping(value = "/upload/{siteId}", method = {RequestMethod.POST})
		 public ReturnMsg uploadMapFile(@PathVariable int siteId, @RequestParam("file") MultipartFile inputFile){

			ReturnMsg returnMsg = new ReturnMsg();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            java.util.Date date = new java.util.Date();			

                String fname = dateFormat.format(date)+".csmm";               
			    String fileName = siteId + "/" + fname;
			 
			   try {
			
			        File destinationFile = new File(udir + siteId + File.separator + fname);
			        inputFile.transferTo(destinationFile);
		                MapFileModel model = new MapFileModel();
			        model.setSiteId(siteId);
			        model.setFileName(fileName);

                                return mapFileService.storeMapFile(model);

				}
			   catch (Exception e){    
				returnMsg.setReturnCode(ReturnCode.ERROR);
			       }
			        return returnMsg;
				 } 


	 
		/*file download */
			 
          @RequestMapping(value="/download/{siteId}/{fileName:.+}", method= {RequestMethod.GET})
	      public ResponseEntity<InputStreamResource> download(@PathVariable int siteId, @PathVariable String fileName) throws FileNotFoundException { 


		  final File file = new File(udir + siteId + File.separator + fileName);
	          return ResponseEntity.ok()
	          .contentLength(file.length())
                  .contentType(MediaType.APPLICATION_OCTET_STREAM)
	          .contentType(MediaType.APPLICATION_PDF)
                  .body(new InputStreamResource(new FileInputStream(file)));
	  
		  }
		  
		  /* call url */
		  @RequestMapping(value="/{siteId}", method= {RequestMethod.GET})
		  public ReturnMsg reqMapFileurl(@PathVariable int siteId){
		  return mapFileService.MapFileurl(siteId);
	     }  
}
		  
