package cn.jiuye.controller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.jiuye.entity.Employee;
import cn.jiuye.entity.Job;
import cn.jiuye.entity.JobVO;
import cn.jiuye.entity.PostedJobVO;
import cn.jiuye.entity.User;
import cn.jiuye.entity.UserCompanyVO;
import cn.jiuye.entity.UserEmployeeVO;
import cn.jiuye.service.IEmployeeService;

@Controller

public class EmployeeController {
 
	@Resource 
	private IEmployeeService employeeService;    
	
	//显示所有岗位
	@RequestMapping("/queryJob")
	public ModelAndView queryJob(HttpSession session ,User user) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		if(user ==null){
			modelAndView.setViewName("login");
		}else{
			List<JobVO> jobList = employeeService.getJob();
			
			modelAndView.addObject("jobList", jobList);
			modelAndView.setViewName("jobresult");
		}
		System.out.println("调用了queryJob()方法");
		return modelAndView;
	}
	
	//按关键字查询岗位
	@RequestMapping("/queryJobByName")
	public ModelAndView queryJobByName(HttpServletRequest request,HttpServletResponse response,Job job) throws Exception {
		System.out.println(job.getJobName());
		System.out.println(request.getParameter("jobName"));
		List<JobVO> jobList = employeeService.getJobByName(job.getJobName());		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("jobList", jobList);
		modelAndView.setViewName("jobresult");
		System.out.println("调用了queryJob()方法");
		return modelAndView;
	}
	
	//转向（子项）具体招聘信息
	@RequestMapping(value="/toJobTemplate")
	public ModelAndView toJobTemplate(User user,HttpSession session) throws Exception{	
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("jobTemplate");
		return modelAndView;
	}
	
	//转向（子项）具体招聘信息
	@RequestMapping(value = "/queryJobItemById")
	public ModelAndView queryJobItemById(HttpServletRequest request,HttpServletResponse response) {
		
		String id =  request.getParameter("id");
		Integer jobId=Integer.parseInt(id);
		
		System.out.println("jobId:"+jobId+",jobType:"+jobId.getClass());
		ModelAndView modelAndView = new ModelAndView();	
		Job job=employeeService.queryJobItemById(jobId);
		
		modelAndView.addObject("job", job);
	
		modelAndView.setViewName("jobTemplate");
		return modelAndView;
		}
	
	
	//编辑简历信息
	@RequestMapping(value = "/editPersonInfo")
	public ModelAndView editPersonInfo(HttpServletRequest request,UserEmployeeVO eVO,
					MultipartFile employee_pic,   HttpServletResponse response) throws Exception {
			//原始名称
		String originalFilename = employee_pic.getOriginalFilename();
			//上传图片
		/*if(employee_pic!=null && originalFilename!=null && originalFilename.length()>0){					
			//存储图片的物理路径
			String pic_path = "E:\\Jiuye-upload\\";
			//新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//新图片
			File newFile = new File(pic_path+newFileName);					
			//将内存中的数据写入磁盘
			employee_pic.transferTo(newFile);					
			//将新图片名称写到itemsCustom中
			eVO.setPic(newFileName);
		}*/
		ModelAndView mav=new ModelAndView();
		System.out.println("Controller收到的UserCompanyVO vo为:"+eVO.toString());
		try{
			employeeService.updatePersonInfo(eVO);
			mav.addObject("msg", "操作成功");
			//session.setAttribute("msg", "操作成功");
		}catch(Exception e) {
			mav.addObject("msg", "操作失败");
		}
		mav.setViewName("employee");
		System.out.println("editPersonInfo()............end");
		return mav;	
		}
	
	//查询简历信息
	@ResponseBody
	@RequestMapping("/queryPerson")
	public UserEmployeeVO queryPerson(HttpSession session ,User user,HttpServletRequest request) throws Exception {
			String id=request.getParameter("id");
			 Integer companyId=Integer.parseInt(id);
			 UserEmployeeVO employeeVO=employeeService.getEmployeeInfoById(companyId);
			 session.setAttribute("employee", employeeVO);
			 System.out.println("EmployeeController接收到的employeeVO为："+employeeVO.toString());
			 return employeeVO;
	}
	
	//形成申请表
	@ResponseBody
	@RequestMapping("/sendInfo")
	public Map<String,String> sendInfo(HttpSession session,HttpServletRequest request) throws Exception {	
			Map<String,String>map =new HashMap<String,String>();
			 String userId=request.getParameter("userId");
			 String jobId=request.getParameter("jobId");
			 String companyId=request.getParameter("companyId");
			 int companyID=Integer.parseInt(companyId);
			 int userID=Integer.parseInt(userId);
			 int jobID=Integer.parseInt(jobId);
			 //生成一个申请表  表中的内容有employeeID,jobID,投递时间、是否被查看，是否被接收
			 employeeService.generateApplyTable(jobID,userID);
			 map.put("msg","投递成功");
			 System.out.println("三个id分别为："+companyID+","+userID+","+jobID);
			 return map;
	}
	
	//查看所投简历
	@ResponseBody
	@RequestMapping("/getJobListByUserID")
	public ModelAndView getJobListByUserID(HttpSession session,HttpServletRequest request) throws Exception {
			ModelAndView mav=new ModelAndView();
			System.out.println("进行了用户查看所投岗位的操作");
			 String userId=request.getParameter("userId");
			 int userID=Integer.parseInt(userId);
			 List<PostedJobVO> pjVO=employeeService.getJobListByUserID(userID);
			 for (PostedJobVO postedjobVO:pjVO){
				 System.out.println("查询到的用户为:"+postedjobVO.toString());
			 }
			 
			 session.setAttribute("postedJobVO",pjVO );
			 System.out.println("userID为："+userID);
			 mav.setViewName("employee");
			 return mav;
	}
	
	@RequestMapping(value = "/getPostedJobDetails")
	public ModelAndView getPostedJobDetails(HttpServletRequest request,HttpServletResponse response) {
		
		String id =  request.getParameter("id");
		Integer jobId=Integer.parseInt(id);
		
		System.out.println("jobId:"+jobId+",jobType:"+jobId.getClass());
		ModelAndView modelAndView = new ModelAndView();	
		Job job=employeeService.queryJobItemById(jobId);
		
		modelAndView.addObject("job", job);
	
		modelAndView.setViewName("postedJobDetails");
		return modelAndView;
		}
	
}
