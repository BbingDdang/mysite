package com.poscodx.mysite.controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 계속 써야되는 코드 
		/////////////////////////////////////////////////////////////
		// Access Control
		if (session == null) {
			System.out.println(1);
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if (authUser == null) {
			System.out.println(2);
			response.sendRedirect(request.getContextPath());
			return;
		}
		/////////////////////////////////////////////////////////////
		
		UserVo userVo = new UserDao().findByNo(authUser.getNo());
		request.setAttribute("userVo", userVo);
		request
			.getRequestDispatcher("/WEB-INF/views/user/updateform.jsp")
			.forward(request, response);
	}

}
