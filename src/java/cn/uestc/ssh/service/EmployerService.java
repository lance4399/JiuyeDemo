package cn.jiuye.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jiuye.dao.EmployeeDAO;
import cn.jiuye.dao.EmployerDAO;
import cn.jiuye.dao.IEmployeeDAO;
import cn.jiuye.dao.IEmployerDAO;
import cn.jiuye.entity.ApplyTable;
import cn.jiuye.entity.Job;
import cn.jiuye.entity.JobVO;
import cn.jiuye.entity.PostedJobVO;
import cn.jiuye.entity.UserCompanyVO;
import cn.jiuye.entity.UserEmployeeVO;

@Service
public class EmployerService implements IEmployerService {
	
	private IEmployerDAO employerDAO;	
	
	@Resource
	public final void setUserDao(EmployerDAO employerDAO) {
		this.employerDAO = employerDAO;
	}

	public final IEmployerDAO getEmployerDAO () {
		return employerDAO;
	}

	@Override
	public UserCompanyVO getCompanyInfoById(int id) {
		
		return employerDAO.getCompanyInfoById(id);
	}

	@Override
	public void updateCompanyInfo(UserCompanyVO companyVO) {
		
		employerDAO.updateCompanyInfo(companyVO);
	}

	@Override
	public void addJobItem(Job job,int userId) {
		
		employerDAO.addJobItem(job,userId);
	}

	@Override
	public  List<Job> getJobItemByCompanyId(int userId) {
		
		return employerDAO.getJobItemByCompanyId(userId);
	}

	@Override
	public List<ApplyTable> getApplytable(int jobId) {
		
		return employerDAO.getApplytableItemByJobId(jobId);
	}

	@Override
	public UserEmployeeVO getResumeById(int resumeId) {

		return employerDAO.getResumeByID(resumeId);
	}

	@Override
	public void getAcceptedByResumeId(int resumeId) {
		employerDAO.getAcceptedByResumeId(resumeId);
		
	}
}
