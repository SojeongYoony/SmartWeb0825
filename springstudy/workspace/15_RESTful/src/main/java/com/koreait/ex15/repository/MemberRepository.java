package com.koreait.ex15.repository;

import java.util.List;

import com.koreait.ex15.domain.Member;

public interface MemberRepository {
	
	public List<Member> selectMemberList();
	public Member slectMemberByNO(Long memberNo);
	public int insertMember(Member member);
	public int updateMember(Member member);
	public int deleteMember(Long memberNo);

}
