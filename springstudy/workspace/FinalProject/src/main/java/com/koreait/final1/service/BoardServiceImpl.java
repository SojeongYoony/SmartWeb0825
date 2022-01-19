package com.koreait.final1.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.final1.domain.Board;
import com.koreait.final1.repository.BoardMapper;

public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	public BoardServiceImpl(SqlSessionTemplate sqlSession) {
		boardMapper = sqlSession.getMapper(BoardMapper.class);
	}
	
	@Override
	public List<Board> getBoards() {
		return boardMapper.selectList();
	}

	@Override
	public Long getBoardCount() {
		return boardMapper.selectBoardCount();
	}

	@Override
	public Board getBoard(Long idx) {
		return boardMapper.selectByIdx(idx);
	}

	@Override
	public int addBoard(Board board) {
		return boardMapper.insertBoard(board);
	}

	@Override
	public int modifyBoard(Board board) {
		return boardMapper.updateBoard(board);
	}

	@Override
	public int removeBoard(Long idx) {
		return boardMapper.deleteBoard(idx);
	}

}
