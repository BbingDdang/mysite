package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		Long num = Long.parseLong(no);
		
		String pageNo = request.getParameter("p");
		Long currentPage = Long.parseLong(pageNo);
		BoardDao boardDao = new BoardDao();
		
		String title = boardDao.findTitleByNo(num);
		String contents = boardDao.findContentsByNo(num);
		BoardVo boardVo = new BoardVo();
		Long userNo = boardDao.findUserNoByNo(num);
		boardVo.setUserNo(userNo);
		
		boolean visited = false;
		
		// read cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if(("visitNo"+no).equals(cookie.getName())) {
					 visited = true;
				}
			}
		}
		if (!visited) {
			boardDao.increaseHit(num);
			Cookie cookie = new Cookie("visitNo" + no, String.valueOf(""));
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(24 * 60 * 60); // 1day
			response.addCookie(cookie);
		}
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("boardVo", boardVo);
		request.setAttribute("title", title);
	    request.setAttribute("contents", contents);
	    request
	    	.getRequestDispatcher("/WEB-INF/views/board/view.jsp")
	    	.forward(request, response);
	}

}
