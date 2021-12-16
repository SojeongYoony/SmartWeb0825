package com.koreait.ex14.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
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
		int totalRecord = repository.selectTotalRecordCount();
		
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
	
	} // end findAllEmployee
	
	@Override
	public void findEmployee(Model model) { // 위의 메소드와 같은 jsp로 이동이므로 사용되는 이름이 같아야 함.
		EmployeeRepository repository = sqlSession.getMapper(EmployeeRepository.class);
		// Model에 저장된 request 꺼내기
		Map<String, Object> m = model.asMap();
		HttpServletRequest request = (HttpServletRequest)m.get("request");
		
		// column + query + begin + end => HashMap (검색할 칼럼 + 검색어) -- 검색할 column과 검색어(query)
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", column);
		map.put("query", query);
		map.put("begin", begin);
		map.put("end", end);
		
		// 검색된 레코드 갯수
		int totalRecord = repository.selectFindRecordCount(map);
		
		// 전달된 페이지 번호 (전달 안 되면 page = 1 사용)
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// 페이징 처리 Page 객체 생성 및 계산
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, page);
		
		// map + beginRecord + endRecord => HashMap          ==> 기존의 map에 추가 시켜 줌
		map.put("beginRecord", pageUtils.getBeginRecord());
		map.put("endRecord", pageUtils.getEndRecord());
		
		// 검색된 목록 중, beginRecord ~ endRecord 사이 목록 가져오기
		List<Employee> list = repository.selectFindList(map);
		
		// View(employee/list.jsp)로 보낼 데이터
		model.addAttribute("list", list);
		model.addAttribute("startNum", totalRecord - ( page - 1 ) * pageUtils.getRecordPerPage());
		
		// 검색 조건에 따라서 파라미터가 달라짐
		switch(request.getParameter("column")) {
		case "EMPLOYEE_ID" :
		case "FIRST_NAME" :
			model.addAttribute("paging", pageUtils.getPageEntity("findEmployee?column=" + column + "&query=" + query )); // 목록을 출력하는 매핑값 전달 -- 이동 mapping 넘겨주기
			break;
		case "HIRE_DATE" :
		case "SALARY" :
			model.addAttribute("paging", pageUtils.getPageEntity("findEmployee?column=" + column + "&begin=" + begin + "&end=" + end )); // 목록을 출력하는 매핑값 전달 -- 이동 mapping 넘겨주기
			break;
		}
	} // end findEmployee

	@Override
	public void autoComplete(Map<String, Object> map, HttpServletResponse response) {
		EmployeeRepository repository = sqlSession.getMapper(EmployeeRepository.class);
		List<Employee> list = repository.autoComplete(map); // column과 일치하는 것을 찾아오기 때문에 사실은 column하나의 값 밖에 들어있지 않음. == dto bean 한개
//		System.out.println("DB에서 가져온 List" + list.toString());
		
		// ajax 결과로 반환할 HashMap을 만들고, response로 응답해 줌.
		Map<String, Object> result = new HashMap<String, Object>();
		if (list.size() == 0) {
			result.put("status", 500);
			result.put("list", null);
		} else {
			result.put("status", 200);
			result.put("list", list);
//			System.out.println(list);
		}
		
		// response 응답
		response.setContentType("text/html; charset=UTF-8");  // jackson이 Map을 json으로 바꿔 준다
		try {
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject(result); // JSON Object 반환
			out.println(obj.toString());
//			System.out.println(obj);
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
