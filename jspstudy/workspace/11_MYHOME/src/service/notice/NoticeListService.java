package service.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.NoticeDao;
import dto.Notice;

public class NoticeListService implements NoticeService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();		// session 사용을 위해 httpsession 에서 가져오기.
		
		// 상세 보기할 때 session에 올려 둔 notice를 제거 -- 고려할 점 : login 정보는 남겨둬야 한다
		Notice notice = (Notice) session.getAttribute("notice");
		if (notice != null) {
			session.removeAttribute("notice");  		// notice만 제거하자. 다른 정보들은 필요하니까 냄겨두고
		}
		
		// 상세 보기할 때 session에 올려둔 open을 제거
		if (session.getAttribute("open") != null) {
			session.removeAttribute("open");
		}
		
		List<Notice> list = NoticeDao.getInstance().selectNoticeList();		// instance로 불러오기 method 가져오기
		request.setAttribute("list", list);
		return new ModelAndView("notice/list.jsp", false);		// request 가지고 가려면 forward :: false
	}

}
