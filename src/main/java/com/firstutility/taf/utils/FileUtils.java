package com.firstutility.taf.utils;

import static com.firstutility.taf.utils.StringUtils.isNotNullOrEmpty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

public class FileUtils {
	
	private static final Logger log = Logger.getLogger(FileUtils.class);
	
	public static File getFile(String filePath) {
		if (isNotNullOrEmpty(filePath))
			return new File(filePath);
		throw new IllegalArgumentException("[FileUtils] Defined file path is NULL or empty.");
	}

	public static boolean isFileExist(String filePath) {
		File file = getFile(filePath);
		if (file.exists() && !file.isDirectory())
			return true;
		return false;
	}
	
	public static boolean isFileExist(File file) {
		if (file.exists() && !file.isDirectory())
			return true;
		return false;
	}

	public static File createFile(String filePath) {
		File file = getFile(filePath);
		if (!isFileExist(file)) {
			try {
				file.createNewFile();
				log.info("[FileUtils] New file created: [" + filePath + "].");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else 
			log.warn("[FileUtils] File [" + filePath + "] is NOT created: file already exists!");
		return file;
	}
	  
	public static void deleteFile(String filePath) {
		File file = getFile(filePath);
		if (isFileExist(file)) {
			file.delete();
			log.info("[FileUtils] File deleted: [" + filePath + "].");
		} else
			log.warn("[FileUtils] File [" + filePath + "] is NOT deleted: file NOT found!");
	}
	
	public static void writeToFile(String filePath, byte[] content) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(getFile(filePath));
			fos.write(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}