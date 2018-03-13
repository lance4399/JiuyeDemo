package cn.jiuye.dao;

import java.util.List;

import cn.jiuye.entity.ApplyTable;
import cn.jiuye.entity.Job;
import cn.jiuye.entity.JobVO;
import cn.jiuye.entity.PostedJobVO;
import cn.jiuye.entity.UserCompanyVO;
import cn.jiuye.entity.UserEmployeeVO;

public interface IEmployerDAO {

	public void addJobItem(Job job, int userId);

	public void updateCompanyInfo(UserCompanyVO companyVO);

	public UserCompanyVO getCompanyInfoById(int id);

	public List<Job> getJobItemByCompanyId(int userId);

	public List<ApplyTable> getApplytableItemByJobId(int jobId);

	public UserEmployeeVO getResumeByID(int resumeId);

	public void getAcceptedByResumeId(int resumeId);

	

	
}
