package com.koreait.ex12.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.ex12.domain.Gallery;
import com.koreait.ex12.repository.GalleryRepository;

import net.coobird.thumbnailator.Thumbnails;

public class GalleryServiceImpl implements GalleryService {

	@Autowired
	private SqlSessionTemplate sqlSession;	// SqlSessionTemplate Type임 주의!!! sqlSession이라고 해서 sqlSession을 Type잡으면 안됨!
	
	@Override
	public List<Gallery> selectGalleryList() {
		GalleryRepository repository = sqlSession.getMapper(GalleryRepository.class); // DAO 객체 생성 method 실행 결과 담기
		return repository.selectGalleryList();
	}

	@Override
	public Gallery selectGalleryByNo(Long no) {
		GalleryRepository repository = sqlSession.getMapper(GalleryRepository.class);
		return repository.selectGalleryByNo(no);
	}

	@Override
	public void insertGallery(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		// 첨부파일 처리 : 서버에 파일 저장 + DB에 정보 저장
		
		// DB로 보낼 Gallery gallery
		Gallery gallery = new Gallery();
		gallery.setWriter(multipartRequest.getParameter("writer"));
		gallery.setTitle(multipartRequest.getParameter("title"));
		gallery.setContent(multipartRequest.getParameter("content"));
		Optional<String> opt = Optional.ofNullable(multipartRequest.getHeader("X-Forwarded-For")); // 거쳐온 주소가
		gallery.setIp(opt.orElse(multipartRequest.getRemoteAddr())); // 없으면 걍 주소로
		
		// 서버에 파일 저장
		try {
			MultipartFile file = multipartRequest.getFile("file"); // file첨부를 뺄 때는, getFile() 메소드 사용
			// MultipartFile type의 file변수를 선언하고 multipartRequest에서 getFile, origin parameter를 빼서 실어줌.
			
			if (file != null && !file.isEmpty()) { // 첨부가 있으면(둘 다 필요) -- 제한을 두 가지 다 걸어줘야 한다.
				
				// * 저장할 첨부파일명을 UUID로 변경
				
				// UUID : Universal Unique IDentifier - 범용 고유 식별자
				// UUID : 9f71108e-3e4e-437c-803e-a3d2ebd95b77 : 하이픈 제외하면 32자리
				// UUID를 파일명으로 사용하면,					 -- TIP : file 이름에 ip를 붙여서 사용하기도 함
				// 1. 파일명 인코딩 해결
				// 2. 파일명 중복 저장 해결
							
				// * 첨부파일의 본래 이름 origin
				String origin = file.getOriginalFilename();
				
				// * 첨부파일의 확장자[".jpg", ".jpeg", ".gif", ".png"]
				String extName = origin.substring(origin.lastIndexOf(".")); // extension name 확장자
				String uuid = UUID.randomUUID().toString().replaceAll("-", ""); // 하이픈 없는 UUID : uuid를 random으로 돌리고 string(문자열)로 변환해서 replace all : 하이픈을 빈문자열로 바꾸겠다.
	
				// * 저장될 파일명(saved) 결정
				String saved = uuid + extName; // 범용 고유 식별자(UUID) + 오리지널 파일네임(Original extName)
//				System.out.println("origin" + origin + "extName" + extName + "uuid" + uuid + "saved" + saved);
				
				// * 저장될 경로
				// resources/upload/2021/12/09
				String sep = Matcher.quoteReplacement(File.separator);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String path = "resources" + sep + "upload" + sep + sdf.format(new Date()).replaceAll("-", sep);
				String realPath = multipartRequest.getServletContext().getRealPath(path); // 실제로 저장 될 경로가 만들어진다.
				
				// * 저장될 경로에 디렉터리 만들기
				// 없으면 새로 만들어야 한다.
				File dir = new File(realPath);
				if (dir.exists() == false) {
					dir.mkdirs();	// directory가 여러개니까 mkdirs!
				}
				
				// * 첨부파일 서버에 업로드 (예외 처리 필요) 일리걸 / io exception
				File uploadFile = new File(realPath, saved); // new File(경로, 파일) realPath는 dir로 해도 됨
				file.transferTo(uploadFile); // 업로드 진행 코드
				
				// * 썸네일 이미지 생성 (예외 처리 필요)  -- 선택
				Thumbnails.of(uploadFile)
				.size(150, 150)
				.toFile(new File(realPath, "s_" + saved));
				
				// 첨부가 있으면 DB에 path, origin, saved 저장
				gallery.setPath(path);
				gallery.setOrigin(origin);
				gallery.setSaved(saved);
			} 
			// 첨부가 없으면					// list / detail : if 작업 필요함
			else {
				// 첨부가 없으면 path, origin, saved 빈 문자열 처리
				gallery.setPath("");
				gallery.setOrigin("");
				gallery.setSaved("");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		// DTO에 data 다 담고 난 뒤, dao(repository)를 불러서 DB에 넣어주기!! (정상 값만 넣고 싶지?)
		GalleryRepository repository = sqlSession.getMapper(GalleryRepository.class); // DAO 객체 생성 method 실행 결과 담기
		int result = repository.insertGallery(gallery);
		message(result, response, "새 갤러리가 등록되었습니다.", "등록 실패", "/ex12/gallery/selectGalleryList");
	}

	@Override
	public int updateGallery(Gallery gallery) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteGallery(Long no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
