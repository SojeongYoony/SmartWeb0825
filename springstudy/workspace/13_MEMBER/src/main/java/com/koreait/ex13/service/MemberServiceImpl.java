package com.koreait.ex13.service;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.ex13.repository.MemberRepository;

public class MemberServiceImpl implements MemberService {

//	@Autowired
//	private MemberRepository repository;

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Map<String, Object> idCheck(String id) {
		MemberRepository repository = sqlSession.getMapper(MemberRepository.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", repository.selectMemberById(id));
		return map;
	}

}
