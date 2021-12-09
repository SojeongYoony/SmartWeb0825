package com.koreait.ex11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.koreait.ex11.service.NoticeService;
import com.koreait.ex11.service.NoticeServiceImpl;

@Configuration
public class NoticeConfig {
			// 등록을 해야 Auto-wired를 통해 사용할 수 있다.
	@Bean
	public NoticeService service() {		// Type은 interface로 잡아서 해당 service가 return되도록 구성.
		return new NoticeServiceImpl();		// Auto-wired는 반환타입으로 찾음.
	}
	
	
}
