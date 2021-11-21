package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.NoticeDao;
import dto.Notice;

class MyhomeTest {

	// test에서 작업한 것들도 DB로 들어간다  :: 가상의 작업, data가 아님.
	
	@BeforeEach
	void 선행작업_게시글삽입() {
		Notice notice = new Notice();
		notice.setWriter("tester");
		notice.setTitle("test title");
		notice.setContent("test content");
		notice.setIp("testip");
		
		int result = NoticeDao.getInstance().insertNotice(notice);
		assertEquals(1,result, "등록오류");
		
	}
	
	@AfterEach
	void 후처리작업_게시글삭제() {
		int result = NoticeDao.getInstance().deleteNotice(2L);
		int result2 = NoticeDao.getInstance().deleteNotice(3L);
		int result3 = NoticeDao.getInstance().deleteNotice(4L);
		assertEquals(1, result, "삭제오류");
		assertEquals(1, result2, "두번째 글 삭제오류");
		assertEquals(1, result3, "세번째 글 삭제오류");
	}
	
	
	
	@Test
	void 게시글목록가져오기() {
		
		System.out.println("게시글목록가져오기 테스트");
		assertEquals(5, NoticeDao.getInstance().selectNoticeList().size(), "목록갯수일치하지않음");
	}

	@Test
	void 게시글가져오기_nullcheck() {
		System.out.println("게시글 가져오기");
		assertNotNull(NoticeDao.getInstance().selectNoticeView(7L), "널값임");
	// 7번 부터 null 임을 확인했다. == 7번부터 삽입 가능 	
	
	}
	
	// 2021-11-21 comment : 각 글의 sequence를 내가 다 알아야만 확인이 가능한데.. seq를 몰라도 check하는 방법
	// ex) last seq check, current seq check 하는 법을 알고 싶다..
	
}
