package com.sample.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		// 判断url是不是公开地址（实际开发中，地址应该配置在配置文件中）
		if (url.indexOf("login.action") > 0) {
			return true;
		}

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if (username != null) {
			return true;
		}
		
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
		
		return false;// true放行，false拦截
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerInterceptorOne...postHandle");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("HandlerInterceptorOne...afterCompletion");

	}

}
