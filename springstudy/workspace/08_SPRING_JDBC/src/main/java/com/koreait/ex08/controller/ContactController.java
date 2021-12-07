package com.koreait.ex08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.ex08.domain.Contact;
import com.koreait.ex08.service.ContactService;

@Controller
@RequestMapping(value = "contact")
public class ContactController {
	
	// field
	@Autowired
	private ContactService service;
	// 사용자 - controller - service - repository - DB
	// method
	@GetMapping(value = "findAllContact")
	public String findAllContact(Model model) { // 목록을 보여주는 jsp로 갈 때, 목록을 보여주기 위해 Model을 사용
		model.addAttribute("list", service.findAllContact()); // list라는 이름으로, selectcontactList Service의 result 전달
		return "contact/list";
	}
	
	@GetMapping(value = "contactPage")
	public String contactPage() {
		return "contact/insert";
	}
	
	@PostMapping(value ="addContact")
	public String addContact(Contact contact) {	// contact bean으로 바로 받기 하려 하였으나 request로 넘겨줌.
		service.addContact(contact); // request 바로 넘겨주기.
		return "redirect:findAllContact"; // 어제 내용 다시 복습할 것... * 성공 실패처리는 하지 않았음.
	}
	
	// findContact
	@GetMapping(value = "findContact")
	public String findContact(Contact contact, Model model) {
		model.addAttribute("contact", service.findContact(contact));
		return "contact/detail";
	}
	
	// updateContact
	@GetMapping(value = "updateContact")
	public String updateContact(Contact contact) {
		service.updateContact(contact);
		return "redirect:findContact?no=" + contact.getNo();
	}
	
	// deleteContact
	@GetMapping(value = "deleteContact")
	public String deleteContact(Contact contact) {
		service.deleteContact(contact);
		return "redirect:findAllContact";
	}
	
	
}
