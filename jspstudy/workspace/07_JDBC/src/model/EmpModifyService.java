package model;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.EmpDAO;
import dto.EmpDTO;

public class EmpModifyService implements EmpService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {

		Long num = Long.parseLong(request.getParameter("num"));
		String name = request.getParameter("name");
		String hire = request.getParameter("hire");
		//https://www.baeldung.com/java-util-date-to-string
		
		EmpDTO empDTO = new EmpDTO();
		empDTO.setNum(num);
		empDTO.setName(name);
		empDTO.setHire(new java.sql.Date(new Date(hire.replaceAll("-", "//")).getTime()));	// 문자열인 hire 변수를 java.sql.Date로 parsing하기 위해서는 getTime(time-stamp)로 Date 값만 구해서 다시 parsing 해줌 : 그닥 중요하진 않음
//		empDTO.setHire(new Date(hire)); : 실무에서는 DB에서 Date를 String type으로 잡아 사실은 parsing이 필요 없다.
		
		EmpDAO empDAO = EmpDAO.getInstance();
		int result = empDAO.updateEmp(empDTO);
		
		ModelAndView modelAndView = null;
		if (result > 0 ) {
			modelAndView = new ModelAndView("/JDBC/selectDto.emp?num="+num, true);
		} else {
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.print("<script>");
				out.print("alert('사원 정보가 수정되지 않았습니다.');");
				out.print("history.back();");
				out.print("</script>");
				out.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return modelAndView;
	}

}
