package com.koreait.ex13.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	/* 
		보내는 사람의 이메일 등록
		1. 구글 메일만 등록함. (구글 계정만 등록가능) -- 전용계정 하나 만들기 왜냐하면 비밀번호/아이디 작성해야함.
		2. API용 아이디/비번 만들어서 사용.
		3. 보안 수준이 낮은 앱 허용 체크 필요.		  -- 우리거 보안 수준이 낮은 앱이라 여기서도 이용할 수 있도록 체크가 필요하다.(로그인 시)
		   https://support.google.com/accounts/answer/6010255 : 보안 수준이 낮은 앱 허용 -- 기간이 정해져 있으므로 주기적으로 해야 하는 작업
	 */
	
	@Bean
	public JavaMailSender javaMailSender() {
		
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.gmail.com"); 					// Gmail에서 보냅니다 : 정해진 값임
		sender.setPort(587); 		      					// 정해진 값임
		sender.setUsername("hyelim020501@gmail.com");		// 사용할 구글아이디
		sender.setPassword("zksxmdkwjTl1");					// 그 계정의 비밀번호
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);   // mail.smtp.auth 인증은 true 				-- Map이랑 사용법이 같음
		properties.put("mail.smtp.starttls.enable", true); // mail.smtp.starttls.enable 보완에관련된 true
		sender.setJavaMailProperties(properties);
		return sender;
	}
	
}
