package com.koreait.final1.service;

import java.util.List;

import com.koreait.final1.domain.Board;

public interface BoardService {

	public List<Board> getBoards();
	public Long getBoardCount();
	public Board getBoard(Long idx);
	public int addBoard(Board board);
	public int modifyBoard(Board board);
	public int removeBoard(Long idx);
	
}
