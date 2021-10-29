<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	
	// 요청 Parameter저장하기
	String date = request.getParameter("date");
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	// Ip주소 알아내기
	Optional<String> optIp = Optional.ofNullable(request.getHeader("X-Forwarded-For"));
	String ip = optIp.orElse(request.getRemoteAddr());
	
	/* String ip = request.getHeader("X-Forwarded-For");
									X-Forwarded-For : forwarded for : 거쳐서 전해짐 : 거쳐서 온 곳이 있으면 
	 if (ip == null) {				// 거친 곳이 없으면
		ip = request.getRemoteAddr();	// 직접 알아내고
	 }
	*/
	
	/* IPv6 */
	// ip : 0:0:0:0:0:0:0:0:1
	// ip : 0_0_0_0_0_0_0_0_1
	String filename = ip.replaceAll(":", "_") + "_" + date + ".txt";
	
	/* IPv4 */
	// ip : 127.0.0.1
	// ip : 127_0_0_1
	// String filename = ip.replaceAll("\\.", "_");
	
	// 서버 내 저장 경로
	// application을 통해서 realPath 확인 가능
	String realPath = application.getRealPath("ex06_attribute");	// folder
	
	// 저장 경로 확실히 생성
	File dir = new File(realPath);
	if (dir.exists() == false) {
		dir.mkdirs();
	}
	
	// 파일 생성
	File file = new File(dir, filename);
	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	bw.write("작성일자 : " + date); bw.newLine();		// bw.newLine() --> 줄바꿈
	bw.write("작성자 : " + writer + "\n");
	bw.write("제목 : " + title + "\n");
	bw.write("내용 \n" + content + "\n");
	
	if (bw != null) {
		bw.close();
	}
	
	// 파일 생성 확인	
	if (file.exists()) {		// true 생략 --> == true
		application.setAttribute("success", true);
	} else {
		application.setAttribute("success", false);
	}
%>

<script>
	let exist = <%=file.exists()%>;
	if (exist) {
		alert('<%=filename%> 파일이 생성되었습니다.');
//		$('input[type=text]').val('');
	} else {
		alert('파일이 생성되지 않았습니다.')
	}
	history.back();
</script>