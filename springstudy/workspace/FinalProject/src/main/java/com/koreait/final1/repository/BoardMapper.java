package com.koreait.final1.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.final1.domain.Board;

@Mapper
public interface BoardMapper {

	public List<Board> selectList(); 
	public Long selectBoardCount();
	public Board selectByIdx(Long idx);
	public int insertBoard(Board board);
	public int updateBoard(Board board);
	public int deleteBoard(Long idx);
	
}