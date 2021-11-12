package model;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import common.ModelAndView;
import dao.ProductDao;
import dto.Product;

public class InsertService implements ProductService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// parameter 넘어옴 -> 받기
		String name = request.getParameter("name");
		String strPrice = request.getParameter("price");
		int price = 0;
		if (strPrice.isEmpty() == false) {	// 공백이면
			price = Integer.parseInt(strPrice);
		}
		
		// DTO
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		
		// DB에 삽입
		int result = ProductDao.getInstance().insert(product);		// getInstance까지가 다오를 부르고 다오를 불러야 DTO를 가질 수 있다 -- 순서 주의
		
		// JSONObject 생성 (JSON 데이터)
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		// JSONObject
		// {"result" : 1} 또는 {"result" : 0}
		
		// JSONObject 응답		-- 출발 지점으로 다시 돌아감
		response.setContentType("application/json; charset=utf-8");   // app type 주의 하기 (json type으로 바꿔주는 작업)
		PrintWriter out = response.getWriter();
		out.println(obj);		// obj가 $.ajax의 success function()으로 전달
		out.close();
		
		return null;		// page이동이 없으므로 return은 null이다.
	}

}
