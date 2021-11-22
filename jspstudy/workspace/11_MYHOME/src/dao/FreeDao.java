package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.Free;
import mybatis.config.DBService;

public class FreeDao {
	
	private SqlSessionFactory factory;
	
	/* singleton */
	private static FreeDao instance;
	private FreeDao() {
		factory = DBService.getInstance().getFactory();
	}
	
	public static FreeDao getInstance() {
		if (instance == null ) {
			instance = new FreeDao();
		}
		return instance;
	}
	
	/* select all free list */
	public List<Free> selectFreeList() {
		SqlSession ss = factory.openSession();
		List<Free> list = ss.selectList("dao.free.selectFreeList");
		ss.close();
		return list;
	}
	
	/* select total Count */
	public int selectTotalCount() {
		SqlSession ss = factory.openSession();
		int totalCount = ss.selectOne("dao.free.selectTotalCount");
		ss.close();
		return totalCount;
	}
	
	/* insert into free */
	public int insertFree(Free free) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.free.insertFree", free);
		if(result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	/* view one free */
	public Free selectFreeByfNo(Long fNo) {
		SqlSession ss = factory.openSession();
		Free free = ss.selectOne("dao.free.selectFreeByfNo", fNo);
		ss.close();
		return free;
	}
	
	/* update Hit */
	public int updateHit(Long fNo) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("dao.free.updateHit", fNo);
		if(result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	
	/* update Free */
	public int updateFree(Free free) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("dao.free.updateFree", free);
		if(result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	
	
}
