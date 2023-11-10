package com.sunknowledge.dme.rcm.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import org.springframework.web.multipart.MultipartFile;

public class CommonUploadFile {
	static ResourceBundle rb = ResourceBundle.getBundle("application");
	private static final String UPLOAD_DIRECTORY = rb.getString("FILE.PATH");

	public static String getPathToStoreDocument(String module) {
		String rootpath = UPLOAD_DIRECTORY;
        System.out.println("rootpath=>"+rootpath);
		File rootDir = new File(rootpath);
		if (!rootDir.exists()) {
			rootDir.mkdir();
		}
		String uploadPath = rootpath + File.separator + module;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		return uploadPath;
	}

	public static String uploadDocument(MultipartFile multipartFile, String module) throws IOException {
		BufferedOutputStream stream = null;
		boolean bool = false;
		String uploadPath = getPathToStoreDocument(module);
        System.out.println("uploadPath=>"+uploadPath);
		String filePath = uploadPath + File.separator + multipartFile.getOriginalFilename();
		File storeFile = new File(filePath);
		bool = storeFile.exists();
		if (bool == true) {
			return filePath;
		}
		byte[] bytes = multipartFile.getBytes();
		stream = new BufferedOutputStream(new FileOutputStream(storeFile));
		stream.write(bytes);
		stream.close();
		return filePath;
	}
}
