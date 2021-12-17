package com.koreait.ex15.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.koreait.ex15.domain.Member;
import com.koreait.ex15.repository.MemberRepository;
import com.koreait.ex15.util.PageUtils;

public class MemberServiceImpl implements MemberService {
	
	private MemberRepository repository;
	
	
	
	public MemberServiceImpl(SqlSessionTemplate sqlSession) {
		repository = sqlSession.getMapper(MemberRepository.class);
	}

	@Override
	public Map<String, Object> findAllMember(Integer page) {
		int totalRecord = repository.selectMemberCount();
		PageUtils pageUtils = new PageUtils(); // paging을 위한 pageUtils 객체 생성
		pageUtils.setPageEntity(totalRecord, page);
		
		// page에 따른 목록 가져오기 위해 beginRecord / endRecord를 보냄.
		Map<String, Object> m = new HashMap<>();	// map에 실어서 보낼 경우 확장성에 좋다. (map에 여러개 실어서 보낼 수 있으므로)
		m.put("beginRecord", pageUtils.getBeginRecord());
		m.put("endRecord", pageUtils.getEndRecord());
		List<Member> list = repository.selectMemberList(m); // 검색당 paging이 필요 없는 경우에는 pageUtils 객체를 바로 전달하면 되지만, 검색당 paging이 필요하므로, 검색어 전달도 필요하다 따라서, Map 객체에 실어서 보내는 방법으로 연습한 것.
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageUtils", pageUtils); // pageUtils를 하나의 Bean으로 사용하여 그 field/getter/setter를 사용하고, 원래 작업했던 paging 코드는 삭제 후, JS에서 작업. --> 페이지 이동이 없는 ajax는 a링크를 사용할 수 없다 
//		map.put("totalRecord", totalRecord); // list의 size는 페이지당 개수이므로 최대10개(이건 우리가 설정 함) 따라서, 전체 레코드가 필요할 경우 totalRecord에 저장된 값을 사용
		map.put("list", list);
		return map;
	}

	@Override
	public Map<String, Object> findMember(Long memberNo) {
		Member member = repository.slectMemberByNO(memberNo); // Member Bean에 DB의 결과를 바로 저장.
		Map<String, Object> map = new HashMap<>();
		map.put("member", member);
		return map;
	}

	@Override
	public Map<String, Object> addMember(Member member) { // 받아 온 member에는 memberNo 없음.
		int result = repository.insertMember(member); // DB로 member보내면 selectKey 태그로 member에 memberNo가 저장됨 // 삽입이 되면, 삽입된 회원의 번호를 받아옴.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result); // 성공 유무 판단용 0 또는 1
		map.put("memberNo", member.getMemberNo()); // DB를 다녀온 뒤에는 member에 memberNo가 있음.
		return map;
	}

	@Override
	public Map<String, Object> modifyMember(Member member) {
		int result = repository.updateMember(member);
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		return map;
	}

	@Override
	public Map<String, Object> removeMember(Long memberNo) {
		int result = repository.deleteMember(memberNo);
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		return map; 
	}

}
