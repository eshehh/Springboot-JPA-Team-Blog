package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	 @Autowired
	 private BoardService boardService;
	
	@GetMapping({"","/"})
	public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC)Pageable pageable) {// 스프링에서는 데이터를 가져갈때 Model을 사용해서
		model.addAttribute("boards", boardService.글목록(pageable));
		return "index"; //@Controller 로 묶여있기 때문에 viewResolver 작동!!
	}
	
	// User  권한이 필요
	@GetMapping({"/board/saveForm"})
	public String saveForm() { 
		return "board/saveForm";
	}
	
}
