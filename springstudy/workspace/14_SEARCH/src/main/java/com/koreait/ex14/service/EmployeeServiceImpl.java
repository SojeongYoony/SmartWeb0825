package com.koreait.ex14.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.ex14.domain.Employee;
import com.koreait.ex14.repository.EmployeeRepository;
import com.koreait.ex14.util.PageUtils;

public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void findAllEmployee(Model model) { // 현재 model에는 request가 실려있음. --> controller에서 실어서 보냄 service에서 필요해서
		
		EmployeeRepository repository = sqlSession.getMapper(EmployeeRepository.class);
		// Model에 저장된 request 꺼내기
		Map<String, Object> m = model.asMap();
		HttpServletRequest request = (HttpServletRequest)m.get("request");
		
		// 전체 레코드 갯수
		int totalRecord = repository.selectTotalRecord();
//		System.out.println(totalRecord);
		
		// 전달된 페이지 번호 (전달 안 되면 page = 1 사용)
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// 페이징 처리 Page 객체 생성 및 계산
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, page);
		
		// beginRecord + endRecord => HashMap
		Map<String, Object> map = new HashMap<String, Object>();  // 후를 대비하여 Object로 setting해 둠
		map.put("beginRecord", pageUtils.getBeginRecord());
		map.put("endRecord", pageUtils.getEndRecord());
		
		// beginRecord ~ endRecord 목록 가져오기
		List<Employee> list = repository.selectEmployeeList(map);
		
		// View(employee/list.jsp)로 보낼 데이터
		model.addAttribute("list", list);
		model.addAttribute("startNum", totalRecord - ( page - 1 ) * pageUtils.getRecordPerPage());
		model.addAttribute("paging", pageUtils.getPageEntity("findAll")); // 목록을 출력하는 매핑값 전달 -- 이동 mapping 넘겨주기
	
	}

}
