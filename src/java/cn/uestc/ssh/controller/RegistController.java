package cn.jiuye.controller;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jiuye.entity.Employee;
import cn.jiuye.entity.User;
import cn.jiuye.exception.CustomException;
import cn.jiuye.service.IUserService;
import cn.jiuye.utils.VerifyCode;

@Controller
public class RegistController {
 
	@Resource 
	private IUserService userService;    

	@RequestMapping(value="/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping(value="/toRegist")
	public ModelAndView toRegist(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("regist");
		return modelAndView;
	}
	

	@RequestMapping(value="/regist",  method = RequestMethod.POST)
	public ModelAndView regist(User user,HttpSession session) throws Exception{	
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("name:"+user.getUsername()+",password:"+user.getPassword()+",role:"+user.getRole());
		User user2=userService.ajaxQueryByName(user.getUsername());
		if(user2==null){			
			userService.regist(user);
			User loginUser =userService.login(user);
			session.setAttribute("loginUser", loginUser);
			modelAndView.setViewName("success");
		}else{
			//session.setAttribute("errorMsg", "用户名存在，请重新注册!");
			modelAndView.setViewName("regist");	
		}
		System.out.println("regist.do invoked!");
		return modelAndView;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/ajax_regist")
	public Map<String,String > ajax_regist(HttpServletRequest request,HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();	
		
		String username =  request.getParameter("username");
		System.out.println("username:"+username);
		User existsUser=userService.ajaxQueryByName(username);
		if(existsUser != null){
			map.put("ret", 	"1");  //用户名存在
			map.put("msg", "请更换一个新的用户名再注册");
		}else{
			map.put("ret", 	"2"); //用户名可用
		}		
	
		System.out.println("ajax_regist2()方法被调用");
		return map;
		
		}
	
	
	
			
	@RequestMapping("/verifycode")
	public void  verifycode(Employee user,HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	
		VerifyCode vc=new VerifyCode();
		BufferedImage image =vc.getImage();
		request.getSession().setAttribute("session_verifycode", vc.getText());
		VerifyCode.output(image, response.getOutputStream());
		System.out.println("verifycode.do invoked!");
	}

	@InitBinder 
	public void initBinder(ServletRequestDataBinder binder){
		binder.registerCustomEditor(Date.class, 
				new CustomDateEditor(new SimpleDateFormat("YYYY-MM-dd"),true));
	}
	
	
}
