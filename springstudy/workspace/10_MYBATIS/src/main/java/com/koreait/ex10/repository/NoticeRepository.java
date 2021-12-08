package com.koreait.ex10.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.ex10.domain.Notice;

public class NoticeRepository {

	@Autowired
	private SqlSessionTemplate sqlSession;  // 이름은 아무래도 상관없지만 우리는 계속 method명과 통일하여 주고 있다.
	
	/* select all List */
	public List<Notice> selectNoticeList() {
		return sqlSession.selectList("mapper.notice.selectNoticeList");
	}
	
	/* select only one */
	public Notice selectNoticeByNo(Long no) {
		return sqlSession.selectOne("mapper.notice.selectNoticeByNo", no);
	}
	
	/* insert date into notice table */
	public int insertNotice(Notice notice) {
		return sqlSession.insert("mapper.notice.insertNotice", notice);
	}
	
	/* update data */
	public int updateNotice(Notice notice) {
		return sqlSession.update("mapper.notice.updateNotice", notice);
	}
	
	/* delete data */
	public int deleteNotice(Long no) {
		return sqlSession.delete("mapper.notice.deleteNotice", no);
	}
	
}
