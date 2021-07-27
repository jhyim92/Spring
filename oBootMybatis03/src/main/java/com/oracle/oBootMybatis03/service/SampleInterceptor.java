package com.oracle.oBootMybatis03.service;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter {
	public SampleInterceptor() {

	}
//	3번
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws IOException {
		System.out.println("SampleInterceptor void postHandle start...3번");
		String ID = (String) modelAndView.getModel().get("id");
		int memCnt = (Integer) modelAndView.getModel().get("memCnt");
		System.out.println("SampleInterceptor void postHandle memCnt->" + memCnt);
		if (memCnt < 1) {
			System.out.println("memCnt Not exists");
			request.getSession().setAttribute("ID", ID);
//			User가 존재하지 않으면 User interCepter Page(회원등록) 이동
			response.sendRedirect("doMemberWrite");
		} else {
			System.out.println("memCnt exists");
			request.getSession().setAttribute("ID", ID);
//			User가 존재하면 User interCepter Page(회원List) 이동
			response.sendRedirect("doMemberList");
		}
	}

//	1번
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		System.out.println("SampleInterceptor boolean preHandle start...1번");
		HandlerMethod method = null;
		Method methodObj = null;
		try {
			method = (HandlerMethod) handler;
			methodObj = method.getMethod();
			System.out.println("Bean : " + method.getBean());
			System.out.println("Method : " + methodObj);
		} catch (Exception e) {
			System.out.println("SampleInterceptor boolean preHandle Exception-> " + e.getMessage());
		}
		
		return true;
	}

}
