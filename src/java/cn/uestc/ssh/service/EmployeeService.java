package cn.jiuye.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jiuye.dao.EmployeeDAO;
import cn.jiuye.dao.IEmployeeDAO;
import cn.jiuye.entity.Job;
import cn.jiuye.entity.JobVO;
import cn.jiuye.entity.PostedJobVO;
import cn.jiuye.entity.UserCompanyVO;
import cn.jiuye.entity.UserEmployeeVO;

@Service
public class EmployeeService implements IEmployeeService {
	
	private IEmployeeDAO employeeDAO;	
	
	@Resource
	public final void setUserDao(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public final IEmployeeDAO getEmployeeDAO () {
		return employeeDAO;
	}

	@Override
	public List<JobVO> getJob() {
		return employeeDAO.getJob();

	}

	@Override
	public List<JobVO> getJobByName(String jobName) {
		return employeeDAO.getJobByName(jobName);

	}

	@Override
	public Job queryJobItemById(int id) {
		System.out.println("执行了EmployeeService的queryJobItemById(id)方法");
		return employeeDAO.getJobItemById(id);
	}

	@Override
	public void updatePersonInfo(UserEmployeeVO eVO) {
		employeeDAO.updatePersonInfo(eVO);
		
	}

	@Override
	public UserEmployeeVO getEmployeeInfoById(int id) {
		
		return employeeDAO.getEmployeeInfoById(id);
	}

	@Override
	public void generateApplyTable(int jobID, int userID) {
		employeeDAO.addApplyTable(jobID,userID);
		
	}
	
	@Override
	public List<PostedJobVO> getJobListByUserID(int userId) {
		return employeeDAO.getJobListByUserID(userId);

	}
	
}
