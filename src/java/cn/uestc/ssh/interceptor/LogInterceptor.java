package cn.jiuye.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,Object obj) throws Exception {
		
		String url=req.getRequestURI();
		if(url.indexOf("login.do") >=0){
			return true;
		}
		//判断session
		HttpSession session  = req.getSession();
		//从session中取出用户身份信息
		String username = (String) session.getAttribute("username");		
		if(username != null){
			//身份存在，放行
			return true;
		}	
		//执行这里表示用户身份需要认证，跳转登陆页面
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp,Object arg2, ModelAndView mv)throws Exception {
		System.out.println("HandlerInterceptor1...postHandle");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest req,	HttpServletResponse resp, Object obj, Exception e)throws Exception {
		System.out.println("HandlerInterceptor1...afterCompletion");		
	}
}
