package com.poscodx.mysite.controller;

import javax.servlet.ServletException;

import com.poscodx.mysite.controller.action.main.MainAction;

public class MainServlet extends ActionServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected Action getAction(String actionName) {
		return new MainAction();
	}
}
