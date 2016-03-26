package com.amicom.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amicom.common.OftenData;
import com.amicom.dao.NormalBoard;
import com.amicom.dao.NormalFileMeta;
import com.amicom.repository.NormalBoardRepository;
import com.amicom.repository.NormalFileMetaRepository;

@Service
public class NormalFileMetaService {
	@Autowired
	NormalFileMetaRepository normalFileMetaRepository;

	@Autowired
	NormalBoardRepository normalBoardRepository;
	
	@Transactional
	public void insert(NormalBoard normalBoard, String boardName, List<MultipartFile> multipartFiles) {
		List<NormalFileMeta> listFileMeta = new ArrayList<NormalFileMeta>();
		Timestamp timestamp = OftenData.getCurrentTimestamp();
		normalBoard = normalBoardRepository.findOne(normalBoard.getBoardId());
		
		try {
			for (MultipartFile multipartFile : multipartFiles) {
				NormalFileMeta tempFileMeta = new NormalFileMeta();

				String fileName = multipartFile.getOriginalFilename();
				String fileType = multipartFile.getContentType();
				String fileSize = multipartFile.getSize() + "";

				tempFileMeta.setFileName(fileName);
				tempFileMeta.setFileType(fileType);
				tempFileMeta.setFileSize(fileSize);
				tempFileMeta.setTimeStamp(timestamp);
				tempFileMeta.setNormalBoard(normalBoard);

				// Test
				System.out.println("File Meta Test");
				System.out.println(fileName);
				System.out.println(fileType);
				System.out.println(fileSize);

				File file = new File(OftenData.getNormalFilePath() + File.separator + boardName + File.separator
						+ normalBoard.getBoardId());
				if (!file.exists()) {
					file.mkdirs();
				}
				
				multipartFile.transferTo(new File(file.getAbsolutePath(), fileName));
				listFileMeta.add(tempFileMeta);
			}
		} catch (Exception e) {
			System.out.println("FILE Fail");
		}

		normalFileMetaRepository.save(listFileMeta);
		System.out.println("FILE test save");
	}

	public void download(HttpServletRequest request, HttpServletResponse response, String boardName, int fileMetaID) {
		final int BUFFER_SIZE = 4096;

		NormalFileMeta fileMeta = normalFileMetaRepository.findOne(fileMetaID);
		String fileName = fileMeta.getFileName();
		int boardID = fileMeta.getNormalBoard().getBoardId();

		ServletContext context = request.getSession().getServletContext();
		String appPath = context.getRealPath("");
		System.out.println("appPath = " + appPath);

		File ffff = new File(OftenData.getNormalFilePath(), boardName + File.separator + boardID);
		if (!ffff.exists()) {
			ffff.mkdirs();
		}

		// construct the complete absolute path of the file
		String fullPath = new File(ffff, fileName).getAbsolutePath();

		System.out.println("fullPath : " + fullPath);

		File downloadFile = new File(fullPath);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// get MIME type of the file
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream = null;
		try {
			outStream = response.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		try {
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			inputStream.close();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
