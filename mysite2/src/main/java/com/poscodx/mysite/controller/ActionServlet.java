package com.poscodx.mysite.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ActionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected abstract Action getAction(String actionName);
	
//	public void m(Optional<String> p) {
//		// p값에 절대 null이 세팅될 수 없음.
//		
//	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 널 객체지향적 처리 
		String actionName = Optional.ofNullable(request.getParameter("a")).orElse("");
		
		Action action = getAction(actionName);
		if (action == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		action.execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	
	public static interface Action {
		void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	}
}
