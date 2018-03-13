package cn.jiuye.service;
import java.util.List;

import cn.jiuye.entity.Employee;
import cn.jiuye.entity.Job;
import cn.jiuye.entity.JobVO;
import cn.jiuye.entity.PostedJobVO;
import cn.jiuye.entity.User;
import cn.jiuye.entity.UserEmployeeVO;

public interface IEmployeeService {
	public List<JobVO> getJob();

	public List<JobVO> getJobByName(String jobName);

	public Job queryJobItemById(int id);

	public void updatePersonInfo(UserEmployeeVO eVO);

	public UserEmployeeVO getEmployeeInfoById(int id);

	public void generateApplyTable(int jobID, int userID);

	List<PostedJobVO> getJobListByUserID(int userId);
}
