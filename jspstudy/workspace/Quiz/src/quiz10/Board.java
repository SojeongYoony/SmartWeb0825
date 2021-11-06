package quiz10;

import java.sql.Date;

public class Board {

	private String title;
	private String wirter;
	private String content;
	private Date date;
	
	
	public Board() {
		
	}
	
	
	public Board(String title, String wirter, String content, Date date) {
		super();
		this.title = title;
		this.wirter = wirter;
		this.content = content;
		this.date = date;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWirter() {
		return wirter;
	}
	public void setWirter(String wirter) {
		this.wirter = wirter;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
		
}
