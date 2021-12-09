package com.koreait.ex11.repository;

import java.util.List;

import com.koreait.ex11.domain.Notice;

public interface NoticeRepository { // notice.xml과 연결되어 type이 NoticeRepository이고 내부의 method들을 사용함. -- get mapper method 필요함
	
	public List<Notice> selectNoticeList();
	public Notice selectNoticeByNo(Long no);
	public int insertNotice(Notice notice);
	public int updateNotice(Notice notice);
	public int deleteNotice(Long no);
	
}
