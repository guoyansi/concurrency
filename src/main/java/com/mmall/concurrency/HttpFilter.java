package com.mmall.concurrency;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.mmall.concurrency.example.threadLocal.RequestHolder;

public class HttpFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request1, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)request1;
		RequestHolder.add(Thread.currentThread().getId());
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
