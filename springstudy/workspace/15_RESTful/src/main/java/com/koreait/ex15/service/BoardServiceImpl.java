package com.koreait.ex15.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.ex15.domain.Board;
import com.koreait.ex15.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardRepository repository;
	
	public BoardServiceImpl(SqlSessionTemplate sqlSession) {
		repository = sqlSession.getMapper(BoardRepository.class);
	}
	
	@Override
	public Map<String, Object> addBoard(MultipartHttpServletRequest multipartRequest) {
		
		String writer = multipartRequest.getParameter("writer");
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		Optional<String> opt = Optional.ofNullable(multipartRequest.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(multipartRequest.getRemoteAddr());
		
		Board board = Board.builder()
				.writer(writer)
				.title(title)
				.content(content)
				.ip(ip)
				.build();
		System.out.println("DB수행전 : " + board);
		int result = repository.insertBoard(board); // 호출 후에는 boardNo가 있다. 호출 전엔 boardNo가 없다
		System.out.println("DB수행후 : " + board);
//		Map<String, Object> map = new HashMap<>();
//		map.put("result", result);
		
		return null;
	}

}