package com.poscodx.mysite.web;

import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

public class EncodingFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		/* request 처리*/
		req.setCharacterEncoding("utf-8");
		
		chain.doFilter(req, res);
		
		/* response 처리 */
	}

	@Override
	public boolean accept(Object entry) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	
}