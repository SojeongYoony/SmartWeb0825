package com.koreait.ex14.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.ex14.service.EmployeeService;

@Controller
@RequestMapping("search/*")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;

	@GetMapping(value="findAll")
	public String findAll(HttpServletRequest request, Model model) { // request : page 정보를 넘길 때 필요함 
		model.addAttribute("request", request);
		service.findAllEmployee(model); // service가 model을 필요로 하므로 주자, jsp로 정보를 넘길 때 필요한것이 model
		return "employee/list";
	}
	
}
