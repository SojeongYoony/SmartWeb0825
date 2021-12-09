package com.koreait.ex12.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.ex12.service.GalleryService;

@Controller
@RequestMapping("gallery")
public class GalleryController {

	private GalleryService service; // GalleryConfig의 반환 Type과 일치해야 함. -- method는 통일하지 않아도 반환타입은 꼭 맞춰야함!

	public GalleryController(GalleryService service) {  // 생성자의 매개변수가 Auto-wired되기 때문에 만들어야 할 bean이 2개이상이면, 생성자를 사용한다. // setter or constructor를 이용할 수 있다.
		super(); 
		this.service = service;
	}

	// list 받아서 listpage로
	@GetMapping("selectGalleryList")
	public String selectGalleryList(Model model) {
		model.addAttribute("list", service.selectGalleryList());
		return "gallery/list";
	}
	
	// insertPage
	@GetMapping("insertPage")
	public String insertPage() {
		return "gallery/insert";
	}
	
	// post mapping insertGallery
	@PostMapping("insertGallery")
	public void insertGallery(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) { // 이미지로 받은건 일반 request로 받을 수 없고, MultipartHttpServletRequest로만 받을 수 있다.
		service.insertGallery(multipartRequest, response); // service에 multipartRequest 전달. ->gallery service Impl 작업
	}
	
	// selectGalleryByNo
	@GetMapping("selectGalleryByNo")
	public String selectGalleryByNo(@RequestParam("no") Long no, Model model) {
		model.addAttribute("gallery", service.selectGalleryByNo(no));
		return "gallery/detail";
	}
	
}
