package cn.jiuye.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jiuye.entity.Gonggao;
import cn.jiuye.entity.User;
import cn.jiuye.exception.CustomException;
import cn.jiuye.service.IUserService;

@Controller
public class AdminController {
 
	@Resource 
	private IUserService userService;    
	
	/*
	@ResponseBody
	@RequestMapping(value = "/adminShow")
	public Map<String,String > adminShow(HttpServletRequest request,HttpServletResponse response) {
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
		
		}*/
	
	// 角色查询
		@RequestMapping("/queryUser")
		public ModelAndView queryItems(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception {

			//List<User> userList = userService.getUser(user.getRole());
			List<User> userList = userService.getAllUser();
			if (userList != null) {
				for (User u : userList) {
					System.out
							.println(u.getUsername() + u.getEmail() + u.getRole());
				}
			} else {
				System.out.println("userList ==null");
			}
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("userList", userList);

			modelAndView.setViewName("admin");

			return modelAndView;
		}
		
				
		// 角色查询
		@RequestMapping("/addOfficialInfo")
		public ModelAndView postOfficialInfo(HttpSession session,HttpServletRequest request,Gonggao gg) throws Exception {
			String userId=request.getParameter("userId");
			System.out.println("userID:"+userId+","+gg.toString());
			int id=Integer.parseInt(userId);
			 userService.addGonggao(gg,id);
			
			ModelAndView modelAndView = new ModelAndView();
			session.setAttribute("msg", "发布成功");
			modelAndView.setViewName("admin");

			return modelAndView;
		}
		
		
		
		// 角色查询
		@RequestMapping("/queryGonggao")
		public ModelAndView queryGonggao(HttpSession session,HttpServletRequest request) throws Exception {
			String userId=request.getParameter("userId");
			System.out.println("userID:"+userId);
			int id=Integer.parseInt(userId);
			List<Gonggao> gonggaoList = userService.getGonggaoByUserId(id);		
			ModelAndView modelAndView = new ModelAndView();
			session.setAttribute("gonggaoList", gonggaoList);
			modelAndView.setViewName("admin");

			return modelAndView;
		}
		
		
}
