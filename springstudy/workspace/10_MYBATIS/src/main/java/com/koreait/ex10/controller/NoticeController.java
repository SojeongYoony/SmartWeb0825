package com.koreait.ex10.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.ex10.domain.Notice;
import com.koreait.ex10.service.NoticeService;

@Controller
@RequestMapping("notice")
public class NoticeController {

	// field
	@Autowired
	private NoticeService service;
	
	@GetMapping("selectNoticeList")
	public String selectNoticeList(Model model) { // mapping == method's name
		model.addAttribute("list", service.selectNoticeList());
		return "notice/list";
	}
	
	@GetMapping("noticePage")
	public String noticePage() {
		return "notice/insert";
	}
	
	@PostMapping("insertNotice")
	public void insertNotice(Notice notice, HttpServletResponse response) { // 서비스 내에서 이동이 이뤄지므로 반환타입 없음 : void
		service.insertNotice(notice, response);
	}
	
	@GetMapping("selectNoticeByNo")
	public String selectNoticeByNo(@RequestParam("no")Long no, Model model) {
		model.addAttribute("notice", service.selectNoticeByNo(no));
		return "notice/detail";
	}
	
	@GetMapping("updateNotice")
	public void updateNotice(Notice notice, HttpServletResponse response) {
		service.updateNotice(notice, response);
	}
	
	@GetMapping("deleteNotice")
	public void deleteNotice(@RequestParam("no") Long no, HttpServletResponse response) {
		service.deleteNotice(no, response);
	}
	
	
}
