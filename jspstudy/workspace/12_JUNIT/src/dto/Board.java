package dto;

import java.sql.Date;

public class Board {

	private String bNo;
	private String writer;
	private String content;
	private Date bDate;
	
	
	public String getbNo() {
		return bNo;
	}
	public void setbNo(String bNo) {
		this.bNo = bNo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	
	// 나중에 정보가 잘 넘어갔나 확인을 위해서 toString을 사용하면 된다.			:: 오타나 빠진 요소를 확인할 수 있음.
	// toString을 사용하지 않으면 주소값이 표시되므로 내용을 보고 싶으면 toString을 사용하여 바꿔준다.
	@Override
	public String toString() {
		return "Board [bNo=" + bNo + ", writer=" + writer + ", content=" + content + ", bDate=" + bDate + "]";
	}
	
	
	
	
}
