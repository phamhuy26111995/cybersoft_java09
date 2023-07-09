package com.cybersoft.teacher.controller;

import com.cybersoft.common.AppUtils;
import com.cybersoft.consts.Consts;
import com.cybersoft.dto.CategoryDto;
import com.cybersoft.service.CategoryService;
import com.cybersoft.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Consts.PREFIX_TEACHER + "/categories")
public class TeacherCategoryController {
	@Autowired
	private CategoryService categoryService;

//Lấy toàn bộ category
	@GetMapping("/get-all")
	public Object get() {
		try {
			List<CategoryDto> dtos = categoryService.getAll();
			AppUtils.createOrderNumberWithoutPaging(dtos);
			return new ResponseEntity<Object>(dtos, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}
