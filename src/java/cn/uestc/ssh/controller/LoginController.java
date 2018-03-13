package cn.jiuye.controller;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.jiuye.entity.User;
import cn.jiuye.entity.UserCompanyVO;
import cn.jiuye.entity.UserEmployeeVO;
import cn.jiuye.service.IUserService;

@Controller
public class LoginController {
 
	@Resource 
	private IUserService userService;    
	
	@RequestMapping(value="/toLogin")
	public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	

	
	@RequestMapping(value="/toLogout")
	public ModelAndView toLogout(HttpSession session ,HttpServletRequest request, HttpServletResponse response) throws Exception{	
		session.invalidate();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
			
	@RequestMapping(value="/login",  method = RequestMethod.POST)
	public ModelAndView login(User user,HttpSession session) throws Exception{	
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("the one try to login whose name:"+user.getUsername()+",password:"+user.getPassword()+",role:"+user.getRole());
		User loginUser=userService.login(user);
		//判断用户身份
		if(loginUser != null ){
			System.out.println(" the loginUsername:"+loginUser.getUsername()+",password:"+loginUser.getPassword()+",role:"+loginUser.getRole());
		     
			session.setAttribute("loginUser", loginUser);
		     modelAndView.setViewName("index");	
		}else{
			modelAndView.setViewName("login");	
		}
		System.out.println("login.do invoked!");		
		return modelAndView;
	}
	
	
}
