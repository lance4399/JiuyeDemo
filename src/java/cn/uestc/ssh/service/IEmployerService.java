package cn.jiuye.service;
import java.util.List;

import cn.jiuye.entity.ApplyTable;
import cn.jiuye.entity.Employee;
import cn.jiuye.entity.Job;
import cn.jiuye.entity.JobVO;
import cn.jiuye.entity.PostedJobVO;
import cn.jiuye.entity.User;
import cn.jiuye.entity.UserCompanyVO;
import cn.jiuye.entity.UserEmployeeVO;

public interface IEmployerService {
	public UserCompanyVO getCompanyInfoById(int id);

	public void updateCompanyInfo(UserCompanyVO user);

	public void addJobItem(Job job,int userId);
	public  List<Job> getJobItemByCompanyId(int userId);

	public List<ApplyTable> getApplytable(int jobId);

	public UserEmployeeVO getResumeById(int resumeId);

	public void getAcceptedByResumeId(int resumeId);


}
