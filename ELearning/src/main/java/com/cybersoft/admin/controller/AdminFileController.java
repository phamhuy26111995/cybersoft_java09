package com.cybersoft.admin.controller;

import com.cybersoft.consts.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cybersoft.service.FileService;

@RestController
@RequestMapping(Consts.PREFIX_ADMIN + "/file/")
public class AdminFileController {

	@Autowired
	private FileService fileService;

	@PostMapping("image/upload")
	public String uploadImage(@RequestBody MultipartFile file) {
		return fileService.saveImageToCloudinary(file);
	}
	
	
}
