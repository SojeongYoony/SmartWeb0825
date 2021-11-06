package login;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class GetImage {
	
	public static void getImage(String key, HttpServletRequest request) {		// 매개변수 String type의 key : key를 받아온다.		//getImage가 request를 받아온다.
		
		String clientId = "_SGCqXrZPI5g0pMyEdOe";
		String clientSecret = "4AkripDV3f";

		String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
		
		// 요청 헤더(request header) : 아이디, 시크릿
		Map<String, String> requestHeaders = new HashMap<String, String>();		// 뒤의 String은 지워도 무관함 JDK 1.7이후로 패치
		requestHeaders.put("X-Naver-Client-Id", clientId);				// <-- entry
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);		// <-- entry : 빼오는 거 entry.getkey/entry.getValue , entrySet() : type:Entry(key & value)
		
		URL url = null;
		HttpURLConnection con = null;
		
		try {
				url = new URL(apiURL);
				con = (HttpURLConnection)url.openConnection();		// 이거 전용 catch가 하나 더 필요함. == IO Exception // con : 연결통로
				con.setRequestMethod("GET");			// form에 붙이던 method : get, post		-- GET방식으로 요청할 것이다 -- URL에 담고 있어서 PARAM을
				for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
					con.setRequestProperty(entry.getKey(), entry.getValue());
				}
				int responseCode = con.getResponseCode();		// 응답코드 : 404 501 200 등등 정상 비정상 유무 판단 하기 위해 
				if (responseCode == HttpURLConnection.HTTP_OK) { 				// 200이라고 써도 무방 : 성공  // else --> 실패의 경우
						BufferedInputStream bis = new BufferedInputStream(con.getInputStream());	// 읽는 통로고, 그림 읽어올 거임			// byte stream
						String realPath = request.getServletContext().getRealPath("storage");		// 경로 지정해줌. storage폴더 만들어
						File dir = new File(realPath);
						if (dir.exists() == false) {
							dir.mkdirs();		
						}
						String filename = Long.valueOf((new Date()).getTime()).toString() + ".jpg";	// 경로 지정해줘야 함.
						File file = new File(dir, filename);		// folder, file
						BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
						byte[] b = new byte[1024];
						int readCount = 0;
						while (true) {
								readCount = bis.read(b);
								if (readCount == -1 ) {
									break;
								}
								bos.write(b, 0, readCount);			// b 배열의 0번부터 readCount(읽은 것 만큼) bos에게 보내준다 
				}
				if (bos != null ) { bos.close();}
				if (bis != null ) { bis.close();}
				
				// LoginServlet의 request -> GetImage로 전달 == 둘이 같은 request
				// 그러면 LoginServlet과 GetImage의 request는 완전히 같다.
				request.setAttribute("filename", filename);  // request에 저장하는 방법
				
			} else {	
				InputStreamReader isr = new InputStreamReader(con.getErrorStream());		// 오류를 받는 곳 -- 에러가 있는 곳 : Error도 text로 넘어오기 때문에 똑같이 reader가 필요함.
				BufferedReader br = new BufferedReader(isr);
				StringBuilder sb = new StringBuilder();			// text 읽을 때 쓰는 친구
				while (true) {
					String line = br.readLine();		// 한 줄씩 읽어들여 
					if (line == null) {
						break;
					}
					sb.append(line);		// 한줄씩 읽어서 sb에 저장해 둔다.
				}		// 무한루프 끝나는지점
				System.out.println("네이버로부터 반환 받은 에러 : " + sb.toString());	
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiURL, e);		// 앞에건 메세지, 예외 객체를 담고 있는 e
		} catch (IOException e) {
			throw new RuntimeException("연결에 실패했거나, API 응답을 읽는데 실패했습니다. : " + apiURL, e);		// 메인에서는 그냥 JVM이 처리 함 다른 method였으면 받을 곳을 설정해 줬을텐데
		} finally {
			con.disconnect();
		}
		
	}
}
