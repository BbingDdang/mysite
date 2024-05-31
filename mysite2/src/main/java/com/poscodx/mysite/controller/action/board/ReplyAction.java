package com.poscodx.mysite.controller.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;

public class ReplyAction implements Action {

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
		
		BoardDao boardDao = new BoardDao();
		BoardVo vo = new BoardVo();
		
		List<BoardVo> list = boardDao.findGnoOnoDepthByNo(num);
		
		vo = list.get(0);
		Long gno = vo.getGroupNo();
		Long ono = vo.getOrderNo();
		Long depth = vo.getDepth();
		vo.setNo(num);
		vo.setGroupNo(gno);
		vo.setOrderNo(ono+1);
		vo.setDepth(depth+1);
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(authUser.getNo());
		boardDao.replyInsert(vo);
		response.sendRedirect(request.getContextPath() + "/board");
	}

}
