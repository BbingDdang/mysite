package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// Access Control
		if(session == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		///////////////////////////////////////////////////////////
		String no = request.getParameter("no");
		Long num = Long.parseLong(no);
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		System.out.println(no);
		BoardVo vo = new BoardVo();
		
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(authUser.getNo());
		vo.setNo(num);
		BoardDao boardDao = new BoardDao();
		Long userNo = boardDao.findUserNoByNo(num);	
		Long authNo = authUser.getNo();
		
		if (userNo != authNo) {
			response.sendRedirect(request.getContextPath() + "/board");
		}
		else {
			boardDao.update(vo);
			response.sendRedirect(request.getContextPath() + "/board");
		}
		
		
		
	}

}
