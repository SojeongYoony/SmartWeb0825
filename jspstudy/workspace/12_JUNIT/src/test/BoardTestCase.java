package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.mybatis.BoardDAO;
import dto.Board;

class BoardTestCase {
	
	// 여기서 작업 한 것들도 DB로 들어가는 것 이다. ** 가상의 작업이 아닌 실제 작업을 테스트 하는 것이군.
	
	@BeforeEach
	void 선행작업() {

		Board board = new Board();
		board.setbNo("10003");
		board.setWriter("테스터");
		board.setContent("테스트내용");
		
		int result = BoardDAO.getInstance().insertBoard(board);
		assertEquals(1, result, "등록 오류");
	}
	
	@AfterEach
	void 후처리작업() {
				
		int result = BoardDAO.getInstance().deleteBoard("10003");
		assertEquals(1, result, "삭제 오류");
		
	}
	

	@Test	// 이 메소드는 JUnit 테스트 할 때 실행되는 메소드이다.		:: test 하는 거에만 @ 하면 된다. @안 하면 그 메소드는 실행하지 않음.
	void 게시글목록가져오기_테스트() { // 테스트 코드 메소드명은 "한글"로 해도 된다.
		
		System.out.println("게시글목록가져오기_테스트");
		
		// 게시글 목록 가져와서 현재 개수 맞는지 점검				:: sql, dto, dao(mapper) 작성 후(DB다녀올 수 있는 시점) test를 진행하고 controller, service 코드에 집중하면 된다.
		// assertEquals("기대한값", "실제값", ["에러메시지"]);
		assertEquals(3, BoardDAO.getInstance().selectBoardList().size());	// selectBoardList의 size() 사이즈 길이 	:: 딱 맞을 때만 동작함  :: true == 초록 / false == 빨강
		// 삽입 수정 일 때 사용하면 좋음
		
	}

	@Test
	void 게시글가져오기_테스트() {
		
		System.out.println("게시글가져오기_테스트");
		
		// 게시글번호 10000인 게시글을 가져와서 null 유무 점검하기
		assertNotNull(BoardDAO.getInstance().selectBoardBybNo("10003"));
		//assertNull(BoardDAO.getInstance().selectBoardBybNo("10005"));		== null 인가? true : false
		
		
	}
	
	
	
}
