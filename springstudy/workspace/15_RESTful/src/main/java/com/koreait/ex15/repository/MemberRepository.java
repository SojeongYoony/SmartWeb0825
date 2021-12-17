package com.koreait.ex15.repository;

import java.util.List;
import java.util.Map;

import com.koreait.ex15.domain.Member;

public interface MemberRepository {
	
	public int selectMemberCount();
	public List<Member> selectMemberList(Map<String, Object> m);
	public Member slectMemberByNO(Long memberNo);
	public int insertMember(Member member);
	public int updateMember(Member member);
	public int deleteMember(Long memberNo);

}
