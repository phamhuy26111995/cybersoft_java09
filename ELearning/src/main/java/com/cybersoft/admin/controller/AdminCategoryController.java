package com.cybersoft.admin.controller;

import java.util.List;

import javax.validation.Valid;

import com.cybersoft.common.AppUtils;
import com.cybersoft.consts.Consts;
import com.cybersoft.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cybersoft.dto.CategoryDto;
import com.cybersoft.dto.UserDto;
import com.cybersoft.service.CategoryService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(Consts.PREFIX_ADMIN + "/categories")
public class AdminCategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private FileService fileService;

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

	@GetMapping("/{id}")
	public Object getById(@PathVariable int id) {
		try {
			CategoryDto resultDto = categoryService.getById(id);
			return new ResponseEntity<Object>(resultDto, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	//Thêm mới một category
	@PostMapping(value = "/save" ,consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public Object save(@Valid @RequestPart CategoryDto dto,  MultipartFile file) {

		try {
			CategoryDto result = categoryService.save(dto, file);
			return new ResponseEntity<Object>(result,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	
	//Edit một category
	@PutMapping(value = "/edit" ,consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public Object put(@RequestPart CategoryDto dto, MultipartFile file) {
		try {
			if(categoryService.getById(dto.getId()) == null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}

			CategoryDto result = categoryService.edit(dto, file);
			return new ResponseEntity<Object>(result,HttpStatus.OK);


		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	//Xóa Category
	@DeleteMapping("/delete")
	public Object delete(@RequestBody CategoryDto dto) {
		try {
			categoryService.delete(dto.getId());;
			return new ResponseEntity<Object>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

}
