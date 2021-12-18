package com.koreait.ex15.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.ex15.domain.Board;
import com.koreait.ex15.domain.BoardAttach;
import com.koreait.ex15.repository.BoardRepository;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardRepository boardRepository;
	
	public BoardServiceImpl(SqlSessionTemplate sqlSession) {
		boardRepository = sqlSession.getMapper(BoardRepository.class);
	}
	
	@Override
	public Map<String, Object> addBoard(MultipartHttpServletRequest multipartRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
		//  파라미터 처리
		String writer = multipartRequest.getParameter("writer"); 
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		Optional<String> opt = Optional.ofNullable(multipartRequest.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(multipartRequest.getRemoteAddr());
		if(writer.isEmpty()) throw new NullPointerException("작성자 입력필수");
		if(title.isEmpty()) throw new NullPointerException("제목 입력 필수");
		if(content.isEmpty()) throw new NullPointerException("내용 입력 필수");
		
		// DB에 writer, title, content, ip전달
		Board board = Board.builder()
				.writer(writer)
				.title(title)
				.content(content)
				.ip(ip)
				.build();
//		System.out.println("DB수행전 : " + board);
		int boardResult = boardRepository.insertBoard(board); // 호출 후에는 boardNo가 있다. 호출 전엔 boardNo가 없다
//		System.out.println("DB수행후 : " + board);
		
		// 첨부파일 저장 결과
		int boardAttachResult = 0;
		
		// 저장 경로 : resources/upload/2021/12/17
		String sep = Matcher.quoteReplacement(File.separator);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String path = "resources" + sep + "upload" + sep + sdf.format(new Date()).replace("-", sep);
		String realPath = multipartRequest.getServletContext().getRealPath(path);
		
		// 저장될 경로에 디렉터리 만들기 : 없으면 새로 만들어야 한다.
		File dir = new File(realPath);
		if (dir.exists() == false) {
				dir.mkdirs();
		}
		
		// 썸네일 목록 list
		List<String> thumbnails = new ArrayList<String>();
		
		// 서버에 파일 저장
		List<MultipartFile> files = multipartRequest.getFiles("files");		// 다중첨부 상황
		
		for (MultipartFile file : files) {
				
			if (file != null && !file.isEmpty()) {
				// 첨부파일의 본래 이름 origin
				String origin = file.getOriginalFilename();
				
				// 첨부파일의 확장자 [".jsp", ".jpeg", ".gif", ".png"]
				String extension = origin.substring(origin.lastIndexOf("."));
				
				// 하이픈 없는 UUID
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
				// 첨부파일 서버에 업로드 (예외 처리 필요)
				File uploadFile = new File(realPath, uuid + extension);
				file.transferTo(uploadFile);
				
				// 썸네일 이미지 생성 (예외 처리 필요)
				Thumbnails.of(uploadFile).size(100, 100).toFile(new File(realPath, "s_" + uuid + extension));
				
				// 썸네일 목록 저장
				thumbnails.add("s_" + uuid + extension);
				
				// DB에 uuid, path, origin, fileType, boardNo 전달
				BoardAttach boardAttach = new BoardAttach();
				boardAttach.setUuid(uuid);
				boardAttach.setPath(path);
				boardAttach.setOrigin(origin);
				boardAttach.setFileType("I");
				boardAttach.setBoardNo(board.getBoardNo());
				
				// DB에 boardAttach 저장
				boardAttachResult = boardRepository.insertBoardAttach(boardAttach);
				
			} // End if
		} // End for 
		
			// 응답할 데이터
			map.put("boardResult", boardResult);
			map.put("boardAttachResult", boardAttachResult);
			map.put("path", path);
			map.put("board", board);
			System.out.println("board에 담긴 정보 " + board);
			map.put("thumbnails", thumbnails);
		} catch (NullPointerException e) {
			map.put("errorMsg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
