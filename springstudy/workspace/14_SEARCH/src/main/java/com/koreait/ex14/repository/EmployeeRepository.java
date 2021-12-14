package com.koreait.ex14.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.koreait.ex14.domain.Employee;

@Repository
public interface EmployeeRepository {
	
	public int selectTotalRecordCount();
	public List<Employee> selectEmployeeList(Map<String, Object> map);
	public int selectFindRecordCount(Map<String, Object> map); // 검색이 필요한 정보를 Map에 실어서 보낸다.
	public List<Employee> selectFindList(Map<String, Object> map);

}
