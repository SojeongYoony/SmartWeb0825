package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.BoardDto;
import mybatis.config.MybatisSetting;

public class BoardDao {
	
	private SqlSessionFactory factory;
	
	private static BoardDao instance;
	private BoardDao() {
		factory = MybatisSetting.getInstance().getFactory();
	}
	
	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	
	private final String NAMESPACE = "mybatis.mapper.sqlmap";
	
	// 1. Board List 
	public List<BoardDto> selectAllBoardList() {
		SqlSession ss = factory.openSession();
		List<BoardDto> list = ss.selectList(NAMESPACE + ".selectAllBoardList");
		ss.close();
		return list;
	}
	
	// 2. select one
	public BoardDto selectOne(Long idx) {
		SqlSession ss = factory.openSession();
		BoardDto boardDto = ss.selectOne(NAMESPACE +".selectOne" , idx);
		ss.close();
		return boardDto;
	}
	
	// 3. Insert
	public int insertBoard(BoardDto boardDto) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert(NAMESPACE + ".insertBoard", boardDto);
		
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 4. update
	public int updateBoard(BoardDto boardDto) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update(NAMESPACE + ".updateBoard", boardDto);
		
		if (result > 0) {
			ss.commit();
		}
		ss.close();
		
		return result;
	}
	
	// 5. delete
	public int deleteOne(Long idx) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete(NAMESPACE + ".deleteOne", idx);	// sqlmapper 다녀오자.
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// 6. getTotalCount
	public int getTotalCount() {
		SqlSession ss = factory.openSession();
		int totalCount = ss.selectOne(NAMESPACE + ".getTotalCount");
		ss.close();
		return totalCount;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
