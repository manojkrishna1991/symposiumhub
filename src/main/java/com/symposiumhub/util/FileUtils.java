package com.symposiumhub.util;

import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


public class FileUtils {

	private static final Log logger = LogFactory.getLog(FileUtils.class);
	private static final int maxWidth = 100;
	private static final int maxHeight = 100;
	public static final String THUMB="_thumb";
	public static final String LARGE="_large";


	public static boolean CompressImageFile(MultipartFile Source,
			String Destination) {

		try {
			Thumbnails.of(Source.getInputStream())
			.size(400, 400)
			.toFile(new File(Destination));
			
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		

	}
	
	public static String getFilePath(String originalName,int count,String type){
		
		String fileName=FilenameUtils.removeExtension(originalName);
		String fullFileName="";
		if(!StringUtils.isEmpty(type)){
		fullFileName=count+fileName+type+FilenameUtils.EXTENSION_SEPARATOR+FilenameUtils.getExtension(originalName);
		}
		else{
		
		 fullFileName=count+fileName+FilenameUtils.EXTENSION_SEPARATOR+FilenameUtils.getExtension(originalName);

		}
		System.out.println(fullFileName);
		return checkFileName(type, fullFileName);
	}
    
	
	
	public static String getFullPath(String ImagePath,String FilePath){
		return ImagePath.concat("/").concat(FilePath);
	}
	
    public static String checkFileName(String type,String fullName){
    	
    	if(!StringUtils.isEmpty(type)){
		if (FileUtils.THUMB.contains(type)) {

			return "thumb/" + fullName;
		}
		if (FileUtils.LARGE.contains(type)) {

			return "large/" + fullName;
		}
    	}
    	
		return fullName;
    	
    	
    }
    
    public static int getCount(String Imagedirectorypath,String type){
    	
    	String directoryPath="";
    	
    	if(!StringUtils.isEmpty(type)){
    	if (FileUtils.THUMB.contains(type)) {

    		directoryPath=Imagedirectorypath+"/thumb";
		}
    	if (FileUtils.LARGE.contains(type)) {

			directoryPath=Imagedirectorypath+"/large";
		}
    	}
    	else{
    		directoryPath=Imagedirectorypath;
    	}
    	
    	
    	Integer filesCount=new File(directoryPath).listFiles().length;
    	return filesCount+1;
    }
    public static void MakeDirectory(String ImagePath){
    	
    	String thumbPath=ImagePath+"/thumb";
    	String largePath=ImagePath+"/large";
    	
    	File thumbFile=new File(thumbPath);
    	File largeFile=new File(largePath);
    	
    	if(!thumbFile.exists()){
    		thumbFile.mkdir();
    	}
    	
    	if(!largeFile.exists()){
    		largeFile.mkdir();
    	}
    }
}
