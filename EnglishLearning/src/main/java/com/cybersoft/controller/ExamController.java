package com.cybersoft.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cybersoft.common.CurrentUser;
import com.cybersoft.dto.CategoryDto;
import com.cybersoft.dto.VocabularyDto;
import com.cybersoft.service.CategoryService;
import com.cybersoft.service.VocabularyService;

@Controller
@RequestMapping("/exam")
public class ExamController {

	private CategoryService categoryService;
	private VocabularyService vocabularyService;
	public ExamController(CategoryService categoryService,VocabularyService vocabularyService) {
		this.categoryService = categoryService;
		this.vocabularyService = vocabularyService;
	}
	
	//Trả về trang exam-list kèm theo một list Category
	@RequestMapping("")
	public String index(Model model) {
		List<CategoryDto> dtos = categoryService.getAllCategory();
		
		model.addAttribute("dtos", dtos);
		
		
		return "exam/exam-list";
	}
	
	//Trả về trang kiểm tra từ vựng Anh - Việt
	@GetMapping("/vocabulary/english-vietnam")
	public String EngToViet() {
		//String exam = request.getQueryString();
		
		return "exam/english";
	}
	//Trả về trang kiểm tra từ vựng Việt - Anh
	@GetMapping("/vocabulary/vietnam-english")
	public String VietToEng() {
		//String exam = request.getQueryString();
		
		return "exam/vietnamese";
	}
	
	
	//Trả về trang kiểm tra từ vựng Anh - Việt
	@GetMapping("/vocabulary/english-vietnam/all")
	public String EngToVietAll() {
		//String exam = request.getQueryString();
		
		return "exam/english-all";
	}
	//Trả về trang kiểm tra từ vựng Việt - Anh
	@GetMapping("/vocabulary/vietnam-english/all")
	public String VietToEngAll() { 
		//String exam = request.getQueryString();
		
		return "exam/vietnamese-all";
	}
	
	//Trả về trang lựa chọn phương thức kiểm tra
	@GetMapping("/vocabulary/option")
	public String option(HttpServletRequest request, Model model) {
		
		String param = request.getQueryString();
		if(param != "") {
			model.addAttribute("cate", param);
		}
		
		return "exam/exam-options";
	}
	
	
	@GetMapping("/vocabulary/option/all")
	public String optionAll() {
		
		
		
		return "exam/exam-options-all";
	}
	
	//API trả về đối tượng json cho lời gọi ajax
	@GetMapping("/api/vocabulary")
	@ResponseBody
	public Object getVocabularies(@RequestParam ("cate") int cateId) {
		List<VocabularyDto> dtos = vocabularyService.getAllVocabularyByUserAndCate(CurrentUser.getPrincipal().getId(), cateId);
		
		return dtos;
	}
	
	//API trả về đối tượng json cho lời gọi ajax
		@GetMapping("/api/vocabulary/all")
		@ResponseBody
		public Object getAllVocabulary() {
			List<VocabularyDto> dtos = vocabularyService.getAllVocabulary(CurrentUser.getPrincipal().getId());
			
			return dtos;
		}
		
	
}
