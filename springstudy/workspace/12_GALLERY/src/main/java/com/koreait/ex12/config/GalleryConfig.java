package com.koreait.ex12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.koreait.ex12.service.GalleryService;
import com.koreait.ex12.service.GalleryServiceImpl;

@Configuration // 나는 configuration이다! 선언 부터
public class GalleryConfig {

	@Bean
	public GalleryService service() {
		return new GalleryServiceImpl(); // GalleryService는 interface이므로 객체생성이 불가능하고, GalleryServiceImpl을 생성하여 반환한다.
	}

	// 파일 첨부 시 등록해 둬야 할 Bean
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSizePerFile(1024 * 1024 * 10);
//	    commonsMultipartResolver.setMaxUploadSizePerFile(maxUploadSizePerFile); 여러개 업로드 할 수 있도록 하려면 사용하는 메소드 // MultipartFile 에서 배열처리 해야하고 배열을 사용하므로 for문을 사용해야 함
//      commonsMultipartResolver.setMaxUploadSize(1024 * 1024 * 10); // 파일 하나짜리	
		return multipartResolver; // 이름이 정해져 있음
	}
	
	
}
