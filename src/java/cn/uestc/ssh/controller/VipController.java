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
import org.springframework.web.servlet.ModelAndView;

import cn.jiuye.entity.Employee;
import cn.jiuye.entity.User;
import cn.jiuye.service.IUserService;
import cn.jiuye.utils.VerifyCode;

@Controller
public class VipController {
 
	@Resource 
	private IUserService userService;    

	@RequestMapping(value="/toLoginVIP")
	public ModelAndView toLoginVIP(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("IfVIP");
		return modelAndView;
	}
	
	@RequestMapping(value="/toAdminLogin")
	public ModelAndView toAdminLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adminlogin");
		return modelAndView;
	}
	
	@RequestMapping(value="/toAdmin")
	public ModelAndView toAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("admin");
		return modelAndView;
	}
	
	@RequestMapping(value="/toEmployee")
	public ModelAndView toEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("employee");
		return modelAndView;
	}
	
	@RequestMapping(value="/toEmployer")
	public ModelAndView toEmployer(HttpServletRequest request, HttpServletResponse response) throws Exception{	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("employer");
		return modelAndView;
	}
	


	
}
