package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.EmpDAO;

public class EmpFindEmpListService implements EmpService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {

		EmpDAO empDAO = EmpDAO.getInstance();		// singleton
		
		request.setAttribute("empList", empDAO.selectEmpList());						// empDAO.selectEmpList(); == 전체 사원의 목록이다.
				
		return new ModelAndView("views/selectEmpList.jsp", false);						// redirect가 아니라 forward할거니까 false 
	}

}
