package com.koreait.ex13.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtils {

	// 크로스 사이트 스크립트(XXS)
	// 스크립트 코드 입력을 무력화 
	public String xxs(String str) {
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}
	
	// 자바 security 암호화
	// SHA-256 : 입력 -> 256비트(32바이트) 암호화 처리(복호는 불가)
	// 1바이트 : 2글자로 표현되므로 DB필드는 64로 설정
	public static String sha256(String str) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256"); // 이 알고리즘으로 md 객체를 만든다.
			md.update(str.getBytes()); // java io 공부 시 자주 나옴, 문자를 바이트 계열로 바꿔줌.
		} catch(NoSuchAlgorithmException e) { // NoSuchAlgorithmException : 이런 알고리즘 없는데요 라는 Exception
			e.printStackTrace();
		}
		// md.digest() : 입력된 문자열이 암호화(변환)된 32바이트 배열
		StringBuilder sb = new StringBuilder();
		for(byte b : md.digest()) { // 1바이트를 2글자 16진수로 변환
			sb.append(String.format("%02X", b)); // "%02X" 대문자로	형태를 바꿈
		}
		return sb.toString();
	}
	
	
	// 스프링 암호화 / 복호화
	// commons-codec 디펜던시
	
	// 암호 : 1111 -> dfjoiqjwi394820
	public static String encodeBase64(String str) {
		return new String(Base64.encodeBase64(str.getBytes())); // byte [] 
	}
	
	// 복호 : dfjoiqjwi394820 -> 1111
	public static String decodeBase64(String str) {
		return new String(Base64.decodeBase64(str.getBytes()));
	}
	
	// 인증코드 생성
	public static String authCode(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (Math.random() < 0.5) {
				sb.append(  (char)((int)(Math.random() * 26) + 'A')  );
			} else {
				sb.append(  (char)((int)(Math.random() * 26) + '0')  );
			}
		}
		System.out.println(sb);
		return sb.toString();
	}
			
}
