package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dto.BoardDTO;

public class BoardDAO {
	
	private static BoardDAO boardDAO;
	private BoardDAO() {
		
	}
	public static BoardDAO getInstance() {
		if (boardDAO == null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}
	
	/* context.xml 내용으로 DataSource 객체 생성 */
	private static DataSource dataSource;
	static {
		try {
			Context context = new InitialContext();	
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle11g");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	/* field */
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	/* method */
	
	// 1. 접속 해제
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (con != null) {con.close();}
			if (ps != null) {ps.close();}
			if (rs != null) {rs.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 2. Board 삽입
	public int insertBoard(BoardDTO boardDTO) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO board VALUES (board_seq.nextval, ?, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, boardDTO.getTitle() );
			ps.setString(2, boardDTO.getWriter());
			ps.setString(3, boardDTO.getContent());
			result = ps.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	
	// 3. 삭제
	public int deleteBoard(Long idx) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM board WHERE idx = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, idx);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	// 4. 수정
	public int updateBoard(BoardDTO boardDTO) {
		
		System.out.println(boardDTO);
		
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "UPDATE board SET title = ?, content = ? WHERE idx = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, boardDTO.getTitle());
			ps.setString(2, boardDTO.getContent());
			ps.setLong(3, boardDTO.getIdx());
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	
	// 5. 게시글 상세보기 --> 게시글 가져오기
	public BoardDTO selectBoardDTO(Long idx) {
		BoardDTO boardDTO = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT idx, title, writer, content FROM board WHERE idx = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, idx);
			rs = ps.executeQuery();
			if(rs.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setIdx(idx);
				boardDTO.setTitle(rs.getString("title"));
				boardDTO.setWriter(rs.getString("writer"));
				boardDTO.setContent(rs.getString("content"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return boardDTO;
	}
	
	

	// 6. 테이블 목록 반환
	public List<BoardDTO> selectBoardList() {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT idx, title, writer, content, register FROM board ORDER BY idx DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setIdx( rs.getLong("idx") );
				boardDTO.setTitle( rs.getString("title") );
				boardDTO.setWriter( rs.getString("writer") );
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setContent( rs.getString("register") );
				boardList.add(boardDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return boardList;
		
		
		// 7. 총 게시글 갯수 조회
	}
	


}
