package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.vo.BoardVo;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		Long num = Long.parseLong(no);
		BoardVo vo = new BoardVo();
		
		vo.setNo(num);
		request.setAttribute("no", no);
		request
			.getRequestDispatcher("/WEB-INF/views/board/reply.jsp")
			.forward(request, response);
	}

}
