package com.cybersoft.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cybersoft.common.CurrentUser;
import com.cybersoft.dto.CategoryDto;
import com.cybersoft.dto.VocabularyDto;
import com.cybersoft.service.VocabularyService;

@Controller
@RequestMapping("/vocabulary")
public class VocabularyController {
	
	private VocabularyService vocabularyService;
	public VocabularyController(VocabularyService vocabularyService) {
		this.vocabularyService = vocabularyService;
	}
	
	//Phương thức trả về Parameter của url
	public static String getParamFromUrl(HttpServletRequest request) {
		if(request.getQueryString().indexOf("&") > 0) {
			String params[] = request.getQueryString().split("&");
			for(String param : params) {
				int urlIndex = param.indexOf("=") + 1;
				
			}
		}
		String baseUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
		  
		  int urlIndex = baseUrl.indexOf("=") + 1;
		  
		  String param = baseUrl.substring(urlIndex);
		  return param;
	}
	
	@GetMapping("")
	public String index(@RequestParam("cate") int cateId,Model model,HttpServletRequest request) {
		List<VocabularyDto> dtos = vocabularyService.getAllVocabularyByUserAndCate(CurrentUser.getPrincipal().getId(), cateId);
		  String paramUrl = getParamFromUrl(request);
		  model.addAttribute("dtos", dtos);
		  model.addAttribute("cate", paramUrl);
		return "vocabulary/vocabulary-list";
	}
	
	@GetMapping("/add")
	public String add(@RequestParam("cate") int cateId,Model model, HttpServletRequest request) {
		String paramUrl = getParamFromUrl(request);
		  model.addAttribute("cate", paramUrl);
		
		return "vocabulary/vocabulary-add";
	}
	
	@PostMapping("/add")
	public String add(@RequestParam("vietnamese") String vietnamese,
					@RequestParam("english") String english,
					@RequestParam("type") String type,
					@RequestParam("cate") int cateId) 
	{
		VocabularyDto dto = new VocabularyDto();
		dto.setVietnamese(vietnamese);
		dto.setEnglish(english);
		dto.setType(type);
		dto.setCate_id(cateId);
		dto.setUser_id(1);
		vocabularyService.add(dto);
		
		
		return "redirect:/vocabulary?cate="+cateId;
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam("cate") String cateId,@RequestParam("vol") String volId,Model model, HttpServletRequest request) {
		
		  model.addAttribute("cate", cateId);
		  model.addAttribute("vol", volId);
		
		return "vocabulary/vocabulary-edit";
	}
	
	@PostMapping("/edit")
	public String edit(@RequestParam("vietnamese") String vietnamese,
					@RequestParam("english") String english,
					@RequestParam("type") String type,
					@RequestParam("cate") int cateId,
					@RequestParam("vol") int volId) 
	{
		VocabularyDto dto = new VocabularyDto();
		dto.setVietnamese(vietnamese);
		dto.setEnglish(english);
		dto.setType(type);
		dto.setCate_id(cateId);
		dto.setUser_id(1);
		dto.setId(volId);
		vocabularyService.edit(dto);
		
		
		return "redirect:/vocabulary?cate="+cateId;
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("cate") int cateId,
						@RequestParam("vol") int id) {
		
		vocabularyService.delete(id, cateId);
		
		return "redirect:/vocabulary?cate="+cateId;
	}
	
	
	
	
}
