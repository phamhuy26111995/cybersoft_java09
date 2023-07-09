package com.cybersoft.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.cybersoft.common.BaseService;
import com.cybersoft.service.FileService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybersoft.dto.CategoryDto;
import com.cybersoft.entity.Category;
import com.cybersoft.entity.User;
import com.cybersoft.repository.CategoryRepository;
import com.cybersoft.service.CategoryService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Service
public class CategoryServiceImp extends BaseService implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private FileService fileService;


	//Lấy toàn bộ danh sách category
	@Override
	public List<CategoryDto> getAll() {
		List<CategoryDto> dtos = new ArrayList<CategoryDto>();
		try {
			List<Category> entities = categoryRepository.findAll();
			for(Category entity : entities) {
				CategoryDto dto = new CategoryDto();
				dto.setId(entity.getId());
				dto.setIcon(entity.getIcon());
				dto.setTitle(entity.getTitle());
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}

	//Lấy category theo id
	@Override
	public CategoryDto getById(int id) {
		Category entity = categoryRepository.findById(id).get();
		return new CategoryDto(entity.getId(),entity.getTitle(), entity.getIcon());
	}

	//Thêm mới một category
	@Override
	public CategoryDto save(CategoryDto dto, MultipartFile file) {
		Category entity = createNew(dto,file);
		Category savedCategory = categoryRepository.save(entity);
		CategoryDto resultDto = new CategoryDto();

		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.map(savedCategory, resultDto);

		return resultDto;

	}

	private Category createNew(CategoryDto dto, MultipartFile file) {
		Category entity = new Category();
		entity.setTitle(dto.getTitle());
		if(file != null) {
			entity.setIcon(fileService.saveImageToCloudinary(file));
		}

		return entity;
	}

	//Sửa một category
	@Override
	public CategoryDto edit(CategoryDto dto, MultipartFile file) {
		Category entity = update(dto, file);
		Category savedEntity = categoryRepository.save(entity);
		CategoryDto resultDto = new CategoryDto();

		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.map(savedEntity, resultDto);

		return resultDto;
	}

	private Category update(CategoryDto dto, MultipartFile file) {
		Category entity = categoryRepository.findById(dto.getId()).get();

		if(dto.getTitle() != null && !dto.getTitle().equalsIgnoreCase("")) {
			entity.setTitle(dto.getTitle());
		}
		if(file != null) {
			String url = fileService.saveImageToCloudinary(file);
			entity.setIcon(url);;
		}

		return entity;
	}



	@Override
	public void delete(int id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response) {
		try (Workbook workbook = new SXSSFWorkbook()) {
			List<Category> entities = categoryRepository.findAll();
			List<CategoryDto> dtos = new ArrayList<CategoryDto>();
			for(Category entity : entities) {
				CategoryDto dto = new CategoryDto();
				dto.setId(entity.getId());
				dto.setIcon(entity.getIcon());
				dto.setTitle(entity.getTitle());
				dtos.add(dto);
			}
			Sheet sheet = workbook.createSheet("Category Data");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Title");
			headerRow.createCell(2).setCellValue("Icon");

			int rowNumber = 1;

			for(CategoryDto dto : dtos) {
				Row row = sheet.createRow(rowNumber++);
				row.createCell(0).setCellValue(dto.getId());
				row.createCell(1).setCellValue(dto.getTitle());
				row.createCell(2).setCellValue(dto.getIcon());
			}


			OutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
