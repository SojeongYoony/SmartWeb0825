package service.mybatis;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {
	// 받자마자 response ==> 반환 타입 :: void													// InputStream, OutputStream, Serialized Form
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}


// ModelAndView 가 없음 == response가 처리  == printwriter ==> input/output exception ==> IOException	== 사실 안 나는게 맞지만 printWriter를 쓰기위해서 