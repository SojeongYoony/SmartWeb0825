package com.koreait.ex04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.ex04.domain.Product;

@Controller
@RequestMapping("product")
public class ProductController {
	
	// <form action="product/v1.do" method="get">
	// <input name="modelName">		<input name="price">
	
	@GetMapping("v1.do")
	public String v1(HttpServletRequest request, Model model) {
		String modelName = request.getParameter("modelName");
		int price = Integer.parseInt( request.getParameter("price") );
		
		Product product = new Product.Builder()
				.setModelName(modelName)
				.setPrice(price)
				.build();						// 최대한 친해져야할 친구.
		model.addAttribute("product", product);
		
		return "product/productDetail";
	}
	
	@PostMapping("v2.do")	// post로 올 땐, post Mapping임 주의
	public String v2(@RequestParam(value="modelName") String modelName,
					 @RequestParam(value="price")        int price,
					 								   Model model) {
		Product product = new Product.Builder()
				.setModelName(modelName)
				.setPrice(price)
				.build();
		model.addAttribute("product", product);
		
		return "product/productDetail";
	}
	
}
