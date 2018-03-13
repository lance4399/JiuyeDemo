package cn.jiuye.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jiuye.entity.ApplyTable;
import cn.jiuye.entity.Job;
import cn.jiuye.entity.User;
import cn.jiuye.entity.UserCompanyVO;
import cn.jiuye.entity.UserEmployeeVO;
import cn.jiuye.service.IEmployeeService;
import cn.jiuye.service.IEmployerService;
import cn.jiuye.service.IUserService;

@Controller
public class CompanyController {
 
	@Resource 
	private IEmployerService employerService; 
	@ResponseBody
	@RequestMapping("/queryCompany")
	public UserCompanyVO queryCompany(HttpSession session ,User user,HttpServletRequest request) throws Exception {
			String id=request.getParameter("id");
			 Integer companyId=Integer.parseInt(id);
			 UserCompanyVO companyVO=employerService.getCompanyInfoById(companyId);
			 session.setAttribute("company", companyVO);
			 System.out.println("CompanyController接收到的companyVO为："+companyVO.toString());
			 return companyVO;
	}
	


	@RequestMapping("/editCompanyInfo")
	public ModelAndView editCompanyInfo(HttpSession session ,UserCompanyVO cVO,HttpServletRequest request) throws Exception {
		ModelAndView mav=new ModelAndView();
			System.out.println("Controller收到的UserCompanyVO vo为:"+cVO.toString());
			try{
				employerService.updateCompanyInfo(cVO);
				mav.addObject("msg", "操作成功");
				//session.setAttribute("msg", "操作成功");
			}catch(Exception e) {
				mav.addObject("msg", "操作失败");
			}
			mav.setViewName("employer");
			System.out.println("editCompanyInfo()####################");
			return mav;			
	}
	
	
	@ResponseBody
	@RequestMapping("/postJobInfo")
	public ModelAndView postJobInfo(HttpSession session ,Job job,HttpServletRequest request) throws Exception {
		
		ModelAndView mav=new ModelAndView();
		int userId=Integer.parseInt(request.getParameter("userId"));
		System.out.println("job:"+request.getParameter("userId")+","+job.toString());
		try{
			employerService.addJobItem(job,userId);
			mav.addObject("msg", "操作成功");
			session.setAttribute("msg", "操作成功");
		}catch(Exception e) {
			mav.addObject("msg", "操作失败");
			throw new Exception(e.toString());
		}
		mav.setViewName("employer");
		System.out.println("&&&&&&&&&&&&&&&");
		return mav;			
	}
	
	
	@ResponseBody
	@RequestMapping("/getJobItemByCompanyId")
	public ModelAndView getJobItemByCompanyId(HttpSession session,HttpServletRequest request) throws Exception {
		ModelAndView mav=new ModelAndView();
		System.out.println("getJobItemByCompanyId..  start....");
		int userId=Integer.parseInt(request.getParameter("userId"));
		System.out.println("UserID:"+request.getParameter("userId"));
		List<Job> jobItemList=new ArrayList<Job>();
		try{
			jobItemList=employerService.getJobItemByCompanyId(userId);		
			session.setAttribute("jobItemList", jobItemList);
		}catch(Exception e) {
			throw new Exception(e.toString());
		}
		System.out.println("getJobItemByCompanyId......end");
		mav.setViewName("employer");
		return mav;			
	}
	
	
	//查看某一个工作的申请表信息
	@ResponseBody
	@RequestMapping("/toApplyTable")
	public ModelAndView toApplyTable(HttpSession session,HttpServletRequest request) throws Exception {
		ModelAndView mav=new ModelAndView();
		System.out.println("toApplyTable..  start....");
		int jobId=Integer.parseInt(request.getParameter("jobId"));
		System.out.println("jobId:"+request.getParameter("jobId"));
        List<ApplyTable> applytabletList=employerService.getApplytable(jobId);
        session.setAttribute("applytableList", applytabletList);
		System.out.println("toApplyTable......end");
		mav.setViewName("applytable");
		return mav;			
	}
	
	@RequestMapping("/backtoApplyTable")
	public ModelAndView backtoApplyTable(HttpSession session,HttpServletRequest request) throws Exception {
		ModelAndView mav=new ModelAndView();
		System.out.println("toApplyTable..  start....");
		System.out.println("toApplyTable......end");
		mav.setViewName("applytable");
		return mav;			
	}
	
	@ResponseBody
	@RequestMapping("/ApplyTable_getResume")
	public ModelAndView ApplyTable_getResume(HttpSession session,HttpServletRequest request) throws Exception {
		ModelAndView mav=new ModelAndView();
		System.out.println("toApplyTable_getResume..  start....(查询所发布岗位中用户投来的简历)");
		int resumeId=Integer.parseInt(request.getParameter("resumeId"));
		System.out.println("resumeId:"+request.getParameter("resumeId"));
		UserEmployeeVO EVO=employerService.getResumeById(resumeId);
			
		session.setAttribute("resumeVO", EVO);	
		System.out.println("toApplyTable_getResume......end");
		mav.setViewName("jianli");
		return mav;			
	}
	
	//录用
	@ResponseBody
	@RequestMapping("/getAccepted")
	public ModelAndView getAccepted(HttpSession session,HttpServletRequest request) throws Exception {
		ModelAndView mav=new ModelAndView();
		System.out.println("getAccepted..  start....(更改applytable中的accepted字段)");
		int resumeId=Integer.parseInt(request.getParameter("resumeId"));
		System.out.println("resumeId:"+request.getParameter("resumeId"));
		employerService.getAcceptedByResumeId(resumeId);
			
		session.setAttribute("msg", "操作成功");	
		System.out.println("getAccepted......end");
		mav.setViewName("applytable");
		return mav;			
	}
}
