package model;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.EmpDAO;
import dto.EmpDTO;

public class EmpAddService2 implements EmpService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		String name = request.getParameter("name");
		
		EmpDTO empDTO = new EmpDTO();
		empDTO.setName(name);
		
		EmpDAO empDAO = EmpDAO.getInstance();
		int result = empDAO.insertEmp(empDTO);				// DB 들어가는 것 까지 됨.

		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(result > 0) {
			out.println("<script>");
			out.println("alert('등록 성공')");
			out.println("location.href='/JDBC/selectList.emp';");
			// location.href는 redirect와 같음.
			out.println("</script>");
			out.close();			
		} else {
				out.println("<script>");
				out.println("alert('사원 등록이 실패했습니다.')");
				out.println("history.back();");
				out.println("</script>");
				out.close();			
			}
		} catch (Exception e) {
					e.printStackTrace();			
		}
		
		return null;		// modelandview를 null 값으로 넣어주면 controller 두번째 if문에서 걸려 동작 끝. 
	}

}
