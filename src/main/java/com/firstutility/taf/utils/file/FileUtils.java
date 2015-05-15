package com.firstutility.taf.utils.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

public class FileUtils {
	
	private static final Logger log = Logger.getLogger(FileUtils.class);

	public static boolean isFileExist(String filePath){
		File file = null;
		file = new File(filePath);
		if (!file.exists() || file.isDirectory()) { 
			return false;
		}
		return true;
	}
	
	public static File createFile(String filePath) {
		File file = null;
		try {
			file = new File(filePath);
			if (!file.exists() || file.isDirectory()) { 
				file.createNewFile();
				log.debug("[FileUtils] New file created: [" + filePath + "]");
			} else {
				log.warn("[FileUtils] File [" + filePath + "] is NOT created: file already exists!");
				//throw new FileExistsException("File already exists!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	  
	
	public static void deleteFile(String filePath) {
		File file = null;
		file = new File(filePath);
		if (file.exists()) { 
			file.delete();
			log.debug("[FileUtils] File deleted: [" + filePath + "]");
		} else {
			log.error("[FileUtils] File [" + filePath + "] is NOT deleted: file NOT found!");
			//throw new FileNotFoundException("[FileUtils] File is NOT found!");
		}
	}
	
	public static void writeToFile(String filePath, byte[] content) {
		FileOutputStream outputFile = null;
		try {
			outputFile = new FileOutputStream(filePath);
			outputFile.write(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			outputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}