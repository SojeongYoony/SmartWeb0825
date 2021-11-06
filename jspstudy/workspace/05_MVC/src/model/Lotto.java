package model;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Lotto {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		Random random = new Random();
		
			String result = "random ";
		
		request.setAttribute("result", result);
		
	}
	
	
}
