package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import model.InsertService;
import model.NameCheckService;
import model.ProductService;
import model.SelectListService;
import model.prevInsertNameService;

@WebServlet("*.do")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		ProductService service = null;
		ModelAndView mav = null;
		
		switch(command) {
		case "selectList.do" :
			service = new SelectListService();
			break;
		case "insertForm.do" :		// 이동하러 간다 :: view를 통해 redirect or forward dosent
			mav = new ModelAndView("views/insert.jsp", false);
			break;
		case "nameCheck.do" :
			service = new NameCheckService();
			break;
		case "insert.do" :
			service = new InsertService();
			break;
		case "prevInsertName.do" :
			service = new prevInsertNameService();
			break;
		}
		
		if (service != null) {
			try {
				mav = service.execute(request, response);				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
			
		if (mav == null) {					// 만약 null이면 이동이 없으니 return; 종료
			return;							// ajax를 사용하게 되면 1 or 0 으로 성공/실패여부가결정되고 page이동없이 결과만 반환한다.
		}
		
		if (mav.isRedirect()) {
			response.sendRedirect(mav.getView());
		} else {
			request.getRequestDispatcher(mav.getView()).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
