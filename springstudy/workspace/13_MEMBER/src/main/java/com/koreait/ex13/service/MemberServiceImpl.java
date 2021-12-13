package com.koreait.ex13.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.koreait.ex13.domain.Member;
import com.koreait.ex13.repository.MemberRepository;
import com.koreait.ex13.util.SecurityUtils;

public class MemberServiceImpl implements MemberService {

	private SqlSessionTemplate sqlSession;
	private JavaMailSender javaMailSender;
	
	@Autowired
	public void setBean(SqlSessionTemplate sqlSession, JavaMailSender javaMailSender) { // 매개변수에 주입이되고 field값으로 전달 됨
		this.sqlSession = sqlSession;
		this.javaMailSender = javaMailSender;
	} // 생성자가 Auto-wired의 역할을 수행.

	@Override
	public Map<String, Object> idCheck(String id) {
		MemberRepository repository = sqlSession.getMapper(MemberRepository.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", repository.selectMemberById(id));
		return map;
	}

	@Override
	public Map<String, Object> findMemberByEmail(String email) {
		MemberRepository repository = sqlSession.getMapper(MemberRepository.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", repository.selectMemberByEmail(email));
		return map;
	}
	
	@Override   // for verify email
	public Map<String, Object> sendAuthCode(String email) {
		
		// 인증코드 발생
		String authCode = SecurityUtils.authCode(6);
		
		// 이메일 전송
		try {
			
			MimeMessage message = javaMailSender.createMimeMessage(); //보낼 메세지는 javaMailSender가 create해 줌 -- JavaMailSender interface와 그 기능을 사용하므로 auto wired가 필요함
			message.setHeader("Content-Type", "text/plain; charset=UTF-8");
			message.setFrom(new InternetAddress("hyelim020501@gmail.com", "인증코드관리자")); // 보내는 사람
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email)); // 받는 사람
			message.setSubject("인증 요청 메일입니다."); // 메일의 제목
			message.setText("인증번호는 " + authCode + " 입니다."); // 메일의 내용 == 인증번호
			javaMailSender.send(message); // 위의 set한 내용을 다 담아서 javaMailSender가 보내줌.
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<String, Object>(); // 많이 쓰임 잘 익혀두자.
		map.put("authCode", authCode);
		return map;
	}
	
	@Override
	public void join(Member member) {
		MemberRepository repository = sqlSession.getMapper(MemberRepository.class);
		member.setPw(SecurityUtils.sha256(member.getPw()));
		member.setName(SecurityUtils.xxs(member.getName()));
		repository.joinMember(member);
	}
	
	@Override
	public void login(HttpServletRequest request) { // response는 하지 않았으므로, 성공 실패 여부와 무관하게 페이지로 갈 것임.
		Member member = new Member();
		member.setId(request.getParameter("id"));
		member.setPw(SecurityUtils.sha256(request.getParameter("pw")));
		MemberRepository repository = sqlSession.getMapper(MemberRepository.class);
		Member loginUser = repository.login(member);
		if (loginUser != null) {
			request.getSession().setAttribute("loginUser", loginUser);
		}
	}
	
	@Override
	public void updatePw(Member member) {
		MemberRepository repository = sqlSession.getMapper(MemberRepository.class);
		member.setPw(SecurityUtils.sha256(member.getPw()));
		repository.updatePw(member);
	}
	
	@Override
	public void updateMember(Member member, HttpSession session) {
		member.setName(SecurityUtils.xxs(member.getName()));
		MemberRepository repository = sqlSession.getMapper(MemberRepository.class);
		repository.updateMember(member);
		Member loginUser = (Member)session.getAttribute("loginUser");
		loginUser.setName(member.getName());
		loginUser.setEmail(member.getEmail()); // 수정 후 정보로 다시 session에 올림 : 바뀐 정보 session에 다시 실어주기.
	}
	
	@Override
	public void leaveMember(Long no, HttpSession session) {
		MemberRepository repository = sqlSession.getMapper(MemberRepository.class);
		repository.leaveMember(no);
	}
	
	
	
	
	
	
	
}
