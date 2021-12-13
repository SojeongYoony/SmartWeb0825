package com.koreait.ex13.repository;

import org.springframework.stereotype.Repository;

import com.koreait.ex13.domain.Member;

@Repository
public interface MemberRepository {  // 이 친구는 member.xml과 한 몸이다. method 통일! parameterType 통일! resultType 통일!
	
	public Member selectMemberById(String id);
	public int joinMember(Member member);
	public Member login(Member member);
	public Member selectMemberByEmail(String email);
	public int updatePw(Member member);
	public int updateMember(Member member);
	public int leaveMember(Long no);
	
}
