package com.poscodx.mysite.controller.action.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

public class BoardMainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("kwd");
		if (keyword == null || "".equals(keyword)) {
			BoardDao boardDao = new BoardDao();
			Long totalPage = boardDao.getTotalPageCount();
			Long currentPage = (request.getParameter("p") != null) ? Long.parseLong(request.getParameter("p")) : 1;
			List<BoardVo> list = boardDao.findAllByPageNum(currentPage);
			Long base = (currentPage - 1)/5;
			List<PageInfo> entry = new ArrayList<>();
			for (int i = 1; i <= 5;i++) {
				PageInfo pageInfo = new PageInfo();
				pageInfo.setPageNo(i + base*5);
				pageInfo.setActive(i + base*5 <= totalPage);
				entry.add(pageInfo);
			}
			
			request.setAttribute("entry", entry);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
			request.setAttribute("keyword", keyword);
			request
				.getRequestDispatcher("/WEB-INF/views/board/list.jsp")
				.forward(request, response);
		}
		else {
			
			BoardDao boardDao = new BoardDao();
			Long totalPage = boardDao.getTotalPageCountByKeyword(keyword);
			Long currentPage = (request.getParameter("p") != null) ? Long.parseLong(request.getParameter("p")) : 1;
			
			List<BoardVo> list = boardDao.findAllByPageNumAndKeyword(currentPage, keyword);
			Long base = (currentPage - 1)/5;
			
			List<PageInfo> entry = new ArrayList<>();
			for (int i = 1; i <= 5;i++) {
				PageInfo pageInfo = new PageInfo();
				pageInfo.setPageNo(i + base*5);
				pageInfo.setActive(i + base*5 <= totalPage);
				entry.add(pageInfo);
			}
			
			request.setAttribute("entry", entry);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
			request.setAttribute("keyword", keyword);
			request
				.getRequestDispatcher("/WEB-INF/views/board/list.jsp")
				.forward(request, response);
		}

	}
	
	
	public static class PageInfo {
		private boolean active;
		private Long pageNo;
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
		public Long getPageNo() {
			return pageNo;
		}
		public void setPageNo(Long pageNo) {
			this.pageNo = pageNo;
		}
	}
}

