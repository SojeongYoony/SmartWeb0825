package naver.captcha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class Ex01_ApiCaptchaKey {

	public static void main(String[] args) {
		
		String clientId = "_SGCqXrZPI5g0pMyEdOe";
		String clientSecret = "4AkripDV3f";
		
		String code = "0";	// 키 발급 "0", 이미지 비교 "1"
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;	// 이 주소로 접속 ***
		
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
				/*      위의 for문 코드와 같다 
			con.setRequestProperty("X-Naver-Client-Id", clientId);     // request header값을 쓰겠다.
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);		// 계속 넣어뒀다가 반복문으로 빼낸다.		  
				 */
			}
			int responseCode = con.getResponseCode();		// 응답코드 : 404 501 200 등등 정상 비정상 유무 판단 하기 위해 
			if (responseCode == HttpURLConnection.HTTP_OK) { 				// 200이라고 써도 무방 : 성공  // else --> 실패의 경우
				InputStreamReader isr = new InputStreamReader(con.getInputStream());		// inputstream reader는
				BufferedReader br = new BufferedReader(isr);
				StringBuilder sb = new StringBuilder();			// text 읽을 때 쓰는 친구
				while (true) {
					String line = br.readLine();		// 한 줄씩 읽어들여 
					if (line == null) {
						break;
					}
					sb.append(line);		// 한줄씩 읽어서 sb에 저장해 둔다.
				}		// 무한루프 끝나는지점
				System.out.println("네이버로부터 반환 받은 데이터 : " + sb.toString());		// String builder 는 반드시 toString으로 바꿔줘야 한다.
				JSONObject obj = new JSONObject(sb.toString());
				String captchaKey = (String) obj.get("key");	// Object 이기 때문에 String으로 Casting 해야 한다.
				System.out.println(captchaKey);
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
