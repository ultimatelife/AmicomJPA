package com.amicom.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;

public class OftenData {

	public static String getNormalFilePath() {
		return "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "NormalFile";
	}

	public static String getImagePath(){
		return "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "img"; 
	}
	

	public static Timestamp getCurrentTimestamp() {
		java.util.Date date = new java.util.Date();
		return new Timestamp(date.getTime());
	}

	public static void deleteAllFile(File f) {
		try {
			if (f.isDirectory()) {
				for (File c : f.listFiles())
					deleteAllFile(c);
			}
			if (!f.delete())
				throw new FileNotFoundException("Failed to delete file: " + f);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
