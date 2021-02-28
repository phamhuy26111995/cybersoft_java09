package com.cybersoft.service.impl;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cybersoft.service.FileService;
@Service
public class FileServiceImpl implements FileService {
	
	@Value("${file.upload-dir-category}")
	private String uploadDir;
	
	@Value("${file.upload-dir-video}")
	private String uploadDirVideo;
	
	@Value("${file.upload-dir-profile}")
	private String uploadDirProfile;
	
	@Value("${file.upload-dir-course}")
	private String uploadDirCourse;

	@Override
	public String saveFileCategory(MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get(uploadDir + fileName);
			
			Files.write(filePath, file.getBytes());
			
			return fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String saveFileVideo(MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get(uploadDirVideo + fileName);
			
			Files.write(filePath, file.getBytes());
			
			return fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String saveFileProfile(MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get(uploadDirProfile + fileName);
			
			Files.write(filePath, file.getBytes());
			
			return fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String saveFileCourse(MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get(uploadDirCourse + fileName);
			
			Files.write(filePath, file.getBytes());
			
			return fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
}
