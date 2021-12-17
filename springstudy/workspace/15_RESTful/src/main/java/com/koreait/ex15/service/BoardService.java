package com.koreait.ex15.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {
	public Map<String, Object> addBoard(MultipartHttpServletRequest multipartRequest);
}
