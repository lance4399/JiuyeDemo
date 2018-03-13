package cn.jiuye.dao;

import java.util.List;

import cn.jiuye.entity.Job;
import cn.jiuye.entity.JobVO;
import cn.jiuye.entity.PostedJobVO;
import cn.jiuye.entity.UserCompanyVO;
import cn.jiuye.entity.UserEmployeeVO;

public interface IEmployeeDAO {
	public List<JobVO> getJob();
	public List<JobVO> getJobByName(String jobName);
	public Job getJobItemById(int id);
	public void updatePersonInfo(UserEmployeeVO eVO);
	public UserEmployeeVO getEmployeeInfoById(int id);
	public void addApplyTable(int jobID, int userID);
	List<PostedJobVO> getJobListByUserID(int userId);
}
