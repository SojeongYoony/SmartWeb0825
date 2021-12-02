package com.koreait.ex04.domain;

public class Board {
	
	// field
	private Long no;			// 필수
	private String title;		// 필수
	private String content;		// 필수
	private Long hit;			// 선택
	
	// getters only 
	public Long getNo() {
		return no;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public Long getHit() {
		return hit;
	}
	
	// constructor
	private Board(BoardBuilder builder) {
		this.no = builder.no;
		this.title = builder.title;
		this.content = builder.content;
		this.hit = builder.hit;
	}
	
	// BoardBuilder 클래스를 내부 클래스로 등록
	public static class BoardBuilder {
		
		// field
		private Long no;			// 필수
		private String title;		// 필수
		private String content;		// 필수
		private Long hit;			// 선택

		// constructor : 필수 field만 작업
		public BoardBuilder(Long no, String title, String content) {	// hit는 선택이므로 제외함
			super();
			this.no = no;
			this.title = title;
			this.content = content;
		}
		
		// setter : BoardBuilder를 반환함, 선택 field만 작업
		public BoardBuilder setHit(Long hit) {
			this.hit = hit;
			return this;
		}
	
		// build() : Board를 반환함
		public Board build() {
			return new Board(this);	// BoardBuilder
		}
		
	} // end BoardBuilder  -- Lombok에 builder annotation을 사용하면 builder pattern화 된다.
	
}	// end Board
