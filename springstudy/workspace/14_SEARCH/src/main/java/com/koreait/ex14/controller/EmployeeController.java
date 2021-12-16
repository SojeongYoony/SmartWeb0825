package com.koreait.ex14.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.ex14.service.EmployeeService;

@Controller
@RequestMapping("search/*")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping(value="searchPage")
	public String searchPage() {
		return "employee/search";
	}
	
	@GetMapping(value="findAll")
	public String findAll(HttpServletRequest request, Model model) { // request : page 정보를 넘길 때 필요함 
		model.addAttribute("request", request);
		service.findAllEmployee(model); // service가 model을 필요로 하므로 주자, jsp로 정보를 넘길 때 필요한것이 model
		return "employee/search";
	}
	
	@GetMapping(value="findEmployee")
	public String findEmployee(HttpServletRequest request, Model model) { // request로 받아 Model로 반환
		model.addAttribute("request", request);
		service.findEmployee(model);
		return "employee/search";
	}
	
	@PostMapping(value="autoComplete", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public void autoComplete(@RequestBody Map<String, Object> map, HttpServletResponse response) { // 본문에 포함 된 요청을 꺼낼 땐 @RequestBody
//		System.out.println(map.toString());  : data가 잘 넘어오는 것 까진 확인 완료
		service.autoComplete(map, response);
	}
	
}
